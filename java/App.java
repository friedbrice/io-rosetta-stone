import java.util.function.Function;
import java.util.Optional;

public final class App {

  private static String appLogic(String x, String y) {
    return "Result: " + Integer.toString(
      Integer.parseInt(x) + Integer.parseInt(y));
  }

  private static IO<Void> printMaybe(Optional<String> x) {
    return x.map(IO::putStrLn).orElse(IO._return(null));
  }

  private static IO<String> prompt( Optional<String> greet,
                                    Function<String, Optional<String>> confirm ) {
    return printMaybe(greet)
      .and(IO.getLine).bind( l ->
        printMaybe(confirm.apply(l))
          .and(IO._return(l)));
  }

  public static IO<Void> app =
    prompt( Optional.of("Please input two numbers."),
            l -> Optional.of("Got first input: " + l) ).bind( x ->
    prompt( Optional.empty(),
            l -> Optional.of("Got second input: " + l) ).bind( y ->
    IO.putStrLn(appLogic(x, y)) ));

  public static void main(String[] args) {
    app.unsafeRunIO();
  }
}
