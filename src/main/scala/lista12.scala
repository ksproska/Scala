// Kamila Sproska

import akka.actor.typed.{ActorRef, ActorSystem, Behavior, Terminated}
import akka.actor.typed.scaladsl.{ActorContext, Behaviors}

// Zadanie 1
object GuesserMain:
  // a
  final case class StartGuessing(numbOfClients: Int, maxNumb: Int)

  def apply(): Behavior[StartGuessing] =
    Behaviors.receive[StartGuessing] { (context, message) =>
      context.log.info(s"${context.self.path.name}. started guessing with ${message.numbOfClients} clients - max number: ${message.maxNumb}")
      val server: ActorRef[Server.ClientGuess] = context.spawn(Server(message.maxNumb), "Server")

      for i <- 1 to message.numbOfClients do
          context.watch(context.spawn(Client(message.maxNumb, server), s"Client$i"))
      watching(message.numbOfClients)
    }

  // b
  private def watching(actors: Int): Behavior[StartGuessing] =
    Behaviors.receiveSignal {
      case (context, Terminated(ref)) =>
        context.log.info(s"Client stopped: ${ref.path.name}")
        if actors > 1
        then
          watching(actors - 1)
        else
          context.log.info(s"The guardian '${context.self.path.name}' is stopping")
          Behaviors.stopped
    }

  def main(args: Array[String]): Unit =
    val greeterMain: ActorSystem[GuesserMain.StartGuessing] = ActorSystem(GuesserMain(), "Guardian")
    val numbOfClients = 3
    val maxNumb = 100
    greeterMain ! GuesserMain.StartGuessing(numbOfClients, maxNumb)

end GuesserMain

// Zadanie 2
object Server:
  private val rand = new scala.util.Random

  final case class ClientGuess(replyTo: ActorRef[Client.ServerInfo], guessedValue: Int)

  def apply(upper: Int): Behavior[ClientGuess] =
    Behaviors.setup { context =>
      context.log.info(s"${context.self.path.name}. started")
      context.log.info(s"${context.self.path.name}. Guess my number from the interval [0..$upper]")
      val currentNumb = rand.nextInt(upper + 1)

      Behaviors.receiveMessage { message =>
        if message.guessedValue == currentNumb
        then message.replyTo ! Client.ServerInfo.Equal
        else if message.guessedValue < currentNumb
        then message.replyTo ! Client.ServerInfo.TooSmall
        else message.replyTo ! Client.ServerInfo.TooBig
        Behaviors.same
      }
    }
end Server

// Zadanie 3
object Client:
  private val rand = new scala.util.Random

  enum ServerInfo:
    case Equal, TooSmall, TooBig

  def apply(upper: Int, server: ActorRef[Server.ClientGuess]): Behavior[ServerInfo] =
    Behaviors.setup { context =>
      context.log.info(s"${context.self.path.name}. started")
      val firstTry = rand.nextInt(upper)
      context.log.info(s"${context.self.path.name}. First random try = $firstTry")

      def guessNumber(lower: Int, upper: Int, guessedNumber: Int): Behavior[ServerInfo] = {
        server ! Server.ClientGuess(context.self, guessedNumber)
        Behaviors.receiveMessage { message =>
          message match
            case ServerInfo.Equal =>
              context.log.info(s"${context.self.path.name}. I guessed it! ${guessedNumber}")
              Behaviors.stopped
            case ServerInfo.TooBig =>
              val nextTry = (guessedNumber - lower)/2 + lower
              context.log.info(s"${context.self.path.name}. Response: too big. I'm trying: $nextTry")
              guessNumber(lower, guessedNumber - 1, nextTry)
            case ServerInfo.TooSmall =>
              val nextTry = (upper - guessedNumber + 1)/2 + guessedNumber
              context.log.info(s"${context.self.path.name}. Response: to small. I'm trying: $nextTry")
              guessNumber(guessedNumber + 1, upper, nextTry)
        }
      }
      guessNumber(0, upper, firstTry)
    }

end Client
