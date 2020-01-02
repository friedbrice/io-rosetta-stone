import java.util.function.Function;
import java.util.function.Supplier;

public final class IO<A> {
  private final Supplier<A> run;

  private IO(Supplier<A> run) {
    this.run = run;
  }

  /** Constructors */

  public static IO<String> getLine = new IO<String>( () ->
    System.console().readLine() );

  public static IO<Void> putStrLn(String str) {
    return new IO<Void>( () -> {
      System.out.println(str);
      return null;
    });
  }

  public static <A> IO<A> _return(A x) {
    return new IO<A>( () -> x );
  }

  /** Combinators */

  public <B> IO<B> map(Function<A, B> f) {
    return new IO<B>( () ->
      f.apply(this.unsafeRunIO()) );
  }

  public <B> IO<B> bind(Function<A, IO<B>> f) {
    return new IO<B>( () ->
      f.apply(this.unsafeRunIO()).unsafeRunIO() );
  }

  public <B> IO<B> and(IO<B> other) {
    return new IO<B>( () -> {
      this.unsafeRunIO();
      return other.unsafeRunIO();
    });
  }

  /** Eliminators */

  public A unsafeRunIO() {
    return run.get();
  }
}
