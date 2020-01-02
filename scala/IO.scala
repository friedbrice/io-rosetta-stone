sealed trait IO[A] {

  def unsafeRunIO: A

  final def map[B](f: A => B): IO[B] = IO(f(unsafeRunIO))

  final def flatMap[B](f: A => IO[B]): IO[B] = IO(f(unsafeRunIO).unsafeRunIO)

  final def and[B](x: IO[B]): IO[B] = IO {
    unsafeRunIO
    x.unsafeRunIO
  }
}

object IO {

  private def apply[A](x: => A): IO[A] = new IO[A] {
    def unsafeRunIO: A = x
  }

  val getLine: IO[String] = IO(io.StdIn.readLine)

  def putStrLn(str: String): IO[Unit] = IO(println(str))

  def _return[A](x: A): IO[A] = IO(x)
}
