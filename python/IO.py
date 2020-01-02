class IO:

  def __init__(self, run):
    self.unsafeRunIO = run

  def map(self, f):
    return IO(lambda: f(self.unsafeRunIO()))

  def bind(self, f):
    return IO(lambda: f(self.unsafeRunIO()).unsafeRunIO())

  def _and(self, other):
    def go():
      self.unsafeRunIO()
      return other.unsafeRunIO()
    return IO(go)

def _return(x):
  return IO(lambda: x)

def putStrLn(str):
  def go():
    print(str)
    return None
  return IO(go)

getLine = IO(input)
