appLogic :: String -> String -> String
appLogic x y = "Result: " <> show (read x + read y)

printMaybe :: Maybe String -> IO ()
printMaybe = maybe (return ()) putStrLn

prompt :: Maybe String -> (String -> Maybe String) -> IO String
prompt greet confirm = do
  printMaybe greet
  l <- getLine
  printMaybe (confirm l)
  return l

app :: IO ()
app = do
  x <- prompt
    (Just "Please input two numbers.")
    (Just . ("Got first input: " <>))
  y <- prompt
    Nothing
    (Just . ("Got second input: " <>))
  putStrLn (appLogic x y)

main :: IO ()
main = app
