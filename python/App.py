import IO

def appLogic(x, y):
  return "Result: " + str(int(x) + int(y))

def printMaybe(strM):
  return IO.putStrLn(strM) if strM is not None else IO._return(None)

def prompt(greet, confirm):
  salute = printMaybe(greet)._and(IO.getLine)
  certify = lambda l: printMaybe(confirm(l))._and(IO._return(l))
  return salute.bind(certify)

app = prompt( "Please input two numbers.",
              lambda l: "Got first input: " + str(l) ).bind(lambda x:
      prompt( None,
              lambda l: "Got second input: " + str(l) ).bind(lambda y:
      IO.putStrLn(appLogic(x, y)) ))

if __name__ == "__main__":
  app.unsafeRunIO()
