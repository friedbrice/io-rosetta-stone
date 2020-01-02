.PHONY: haskell java javascript python scala

all: haskell java javascript python scala

haskell:
	(cd haskell && runghc App.hs)

java:
	(cd java && javac *.java && java App && rm *.class)

javascript:
	(cd javascript && (xdg-open App.html || open App.html || start App.html))

python:
	(cd python && python App.py)

scala:
	(cd scala && scalac *.scala && scala App && rm *.class)
