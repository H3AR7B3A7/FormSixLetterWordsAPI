# Six Letter Words API
There's a file in the root of the repository, input.txt, that contains words of varying lengths (1 to 6 characters).

Your objective is to show all combinations of those words that together form a word of 6 characters. That combination must also be present in input.txt  
E.g.:
``` 
foobar  
fo  
obar
```
should result in the output: 
```
fo+obar=foobar
```
You can start by only supporting combinations of two words and improve the algorithm at the end of the exercise to support any combinations.
Treat this exercise as if you were writing production code; think unit tests, SOLID, clean code and avoid primitive obsession. Be mindful of changing requirements like a different maximum combination length, or a different source of the input data.  
The solution must be stored in a git repo. After the repo is cloned, the application should be able to run with one command / script.
The best way to do so, would be a solution (docker container e.g.) that contains an API which could be called like this:

>curl --request POST 'http://localhost:8080/api/file' --data '@input.txt' --header "Content-Type: text/plain" >> output.txt

### Docker HUB Repository
https://hub.docker.com/repository/docker/h3ar7b3a7/six-letter-words