import akka.actor.typed.{ActorRef, ActorSystem, Behavior, Terminated}
import akka.actor.typed.scaladsl.{ActorContext, Behaviors}

object Server:
  private val rand = new scala.util.Random

  final case class ClientGuess(replyTo: ActorRef[Client.ServerInfo], guessedValue: Int)

  def apply(upper: Int): Behavior[ClientGuess] =
    Behaviors.setup { context =>
      println(s"${context.self.path.name}. started")
      println(s"${context.self.path.name}. Guess my number from the interval [0..$upper]")
      val currentNumb = rand.nextInt(upper + 1)

      Behaviors.receiveMessage { message =>
        if message.guessedValue == currentNumb
        then message.replyTo ! Client.ServerInfo(Client.Equality.Equal)
        else if message.guessedValue < currentNumb
        then message.replyTo ! Client.ServerInfo(Client.Equality.TooSmall)
        else message.replyTo ! Client.ServerInfo(Client.Equality.TooBig)
        Behaviors.same
      }
    }
end Server

object Client:
  private val rand = new scala.util.Random

  enum Equality:
    case Equal
    case TooSmall
    case TooBig

  final case class ServerInfo(equality: Equality)

  def apply(upper: Int, server: ActorRef[Server.ClientGuess]): Behavior[ServerInfo] =
    Behaviors.setup { context =>
      println(s"${context.self.path.name}. started")
      val firstTry = rand.nextInt(upper)
      println(s"${context.self.path.name}. First random try = $firstTry")

      def guessNumber(lower: Int, upper: Int, guessedNumber: Int): Behavior[ServerInfo] = {
        server ! Server.ClientGuess(context.self, guessedNumber)
        Behaviors.receiveMessage { message =>
          message.equality match
            case Equality.Equal =>
              println(s"${context.self.path.name}. I guessed it! ${guessedNumber}")
              Behaviors.stopped
            case Equality.TooBig =>
              val nextTry = rand.nextInt(guessedNumber - lower + 1) + lower
              println(s"${context.self.path.name}. Response: too big. I'm trying: $nextTry")
              guessNumber(lower, guessedNumber - 1, nextTry)
            case Equality.TooSmall =>
              val nextTry = rand.nextInt(upper - guessedNumber + 1) + guessedNumber
              println(s"${context.self.path.name}. Response: to small. I'm trying: $nextTry")
              guessNumber(guessedNumber + 1, upper, nextTry)
        }
      }
      guessNumber(0, upper, firstTry)
    }

end Client

object GreeterMain:
  final case class StartGuessing(numbOfClients: Int, maxNumb: Int)

  def apply(): Behavior[StartGuessing] =
    Behaviors.receive[StartGuessing] { (context, message) =>
      println(s"${context.self.path.name}. started guessing with ${message.numbOfClients} clients - max number: ${message.maxNumb}")
      val server: ActorRef[Server.ClientGuess] = context.spawn(Server(message.maxNumb), "Server")

      for i <- 1 to message.numbOfClients
        do
          context.watch(context.spawn(Client(message.maxNumb, server), s"Client$i"))
      watching(message.numbOfClients)
    }

  private def watching(actors: Int): Behavior[StartGuessing] =
    Behaviors.receiveSignal {
      case (context, Terminated(ref)) =>
        context.log.info(s"Client stopped: ${ref.path.name}")
        if actors > 1 then watching(actors - 1)
        else
          context.log.info(s"The guardian '${context.self.path.name}' is stopping")
        Behaviors.stopped
    }

  def main(args: Array[String]): Unit =
    val greeterMain: ActorSystem[GreeterMain.StartGuessing] = ActorSystem(GreeterMain(), "Guardian")
    greeterMain ! GreeterMain.StartGuessing(3, 10)

end GreeterMain
