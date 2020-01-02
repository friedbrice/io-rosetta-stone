object App {

  def appLogic(x: String, y: String): String =
    "Result: " + (x.toInt + y.toInt).toString

  def printMaybe(x: Option[String]): IO[Unit] =
    x.fold(IO._return(()))(IO.putStrLn)

  def prompt( greet: Option[String],
              confirm: String => Option[String]
            ): IO[String] = for {
    _ <- printMaybe(greet)
    l <- IO.getLine
    _ <- printMaybe(confirm(l))
  } yield l

  val app: IO[Unit] = for {
    x <- prompt( Some("Please input two numbers."),
                 l => Some("Got first input: " + l) )
    y <- prompt( None,
                 l => Some("Got second input: " + l) )
    _ <- IO.putStrLn(appLogic(x, y))
  } yield ()

  def main(args: Array[String]) = app.unsafeRunIO
}
