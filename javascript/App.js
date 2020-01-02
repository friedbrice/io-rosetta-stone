const App = (function() {
  'use strict';

  const appLogic = (x, y) => 'Result: ' + (parseInt(x) + parseInt(y));

  const printMaybe = (strM) => strM == null ? IO.return(null) : IO.putStrLn(strM)

  const prompt = (greet, confirm) =>
    printMaybe(greet)
      .and(IO.getLine).bind( l =>
        printMaybe(confirm(l))
          .and(IO.return(l)));

  const app =
    prompt( 'Please input two numbers.',
            l => 'Got first input: ' + l ).bind( x =>
    prompt( null,
            l => 'Got second input: ' + l ).bind( y =>
    IO.putStrLn(appLogic(x, y)) ));

  return {
    main: app
  };
}());
