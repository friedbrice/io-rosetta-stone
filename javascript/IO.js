const IO = (function() {
  'use strict';

  const IO = (run) => {
    return {
      unsafeRunIO: run,

      map: (f) => IO(() => f(run())),

      bind: (f) => IO(() => f(run()).unsafeRunIO()),

      and: (x) => IO(() => {
        run();
        return x.unsafeRunIO();
      })
    };
  };

  return {
    getLine: IO(() => window.prompt("")),

    putStrLn: (str) => IO(() => {
      window.alert(str);
      return null;
    }),

    return: (x) => IO(() => x)
  };
}());
