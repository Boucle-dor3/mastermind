# Mastermind

It’s a game where you must to guess the combination of the other. There are two games.  
  
The first, it’s a game of search and the second it’s a Mastermind. The only difference between this two games is on the indices.  
  
In the game search, you indicate the figure is larger or smaller.  
  
Then, in the game mastermind, you say if the figure is in the right or wrong place. 

## Prerequisites
You need to java 9. You also need a JDK  if you want to compile the project.


## Installing

In the command line, go to the root of project.

### Compile

You need to install maven.

```
 mvn install  
 mvn compile assembly:single
```

### Launch
 
```
 java -jar target/mastermind-jar-with-dependencies.jar
```

## Architecture

### Libraries used

* __Log4j2__ : it's the reference library for log management. We use the logs to follow the application state in the time. It offer differents levels of logs and can to send it to differents places (console, file, network etc.).
* __Config4j__ : it open and understand the language of our config files.

## Demo

```
09:46:09.442 [main] INFO  config.GameConfig - Load app config from config.properties.
09:46:09.716 [main] INFO  Mastermind - gameTypeChoice
Quel type choisissez-vous?
1 - Recherche 
2 - Mastermind 
1
Vous avez choisi SEARCH
09:46:41.779 [main] INFO  Mastermind - gameModeChoice
Quel mode choisissez-vous?
1 - Challenger 
2 - Défenseur 
3 - Duel 
2
09:46:48.246 [main] WARN  Mastermind - User enter a not existing choice.
Vous avez choisi DEFENDER
Vous avez lancé le jeu SEARCH en mode DEFENDER
----------------------------------------
09:46:48.268 [main] INFO  Game - resetAlreadySeenComputerProposition
09:46:48.271 [main] INFO  Game - execute
09:46:48.271 [main] INFO  Game - getPlayerSecret
09:46:48.271 [main] INFO  Game - getCombiPlayer
Entrez votre secret à 5chiffres :
12345
09:47:07.284 [main] INFO  Game - executeGameDefender with combination secret of player
**********************
Tour de l'ordinateur
09:47:07.285 [main] TRACE Game - playGameTour
09:47:07.285 [main] INFO  Game - compare combinaisons
09:47:07.285 [main] INFO  Game - Generate a random list.
09:47:07.286 [main] TRACE Game - GameSearch.hasDifferences
 + + + - - Retentez votre chance!
**********************
Tour de l'ordinateur
09:47:07.288 [main] TRACE Game - playGameTour
09:47:07.288 [main] INFO  Game - compare combinaisons
09:47:07.288 [main] INFO  Game - Generate a random list.
09:47:07.288 [main] TRACE Game - GameSearch.hasDifferences
 + - + - - Retentez votre chance!
**********************
Tour de l'ordinateur
09:47:07.289 [main] TRACE Game - playGameTour
09:47:07.289 [main] INFO  Game - compare combinaisons
09:47:07.289 [main] INFO  Game - Generate a random list.
09:47:07.289 [main] TRACE Game - GameSearch.hasDifferences
 - + - = - Retentez votre chance!
**********************
Tour de l'ordinateur
09:47:07.290 [main] TRACE Game - playGameTour
09:47:07.290 [main] INFO  Game - compare combinaisons
09:47:07.290 [main] INFO  Game - Generate a random list.
09:47:07.290 [main] TRACE Game - GameSearch.hasDifferences
 - + - - + Retentez votre chance!
**********************
Tour de l'ordinateur
09:47:07.291 [main] TRACE Game - playGameTour
09:47:07.291 [main] INFO  Game - compare combinaisons
09:47:07.291 [main] INFO  Game - Generate a random list.
09:47:07.291 [main] TRACE Game - GameSearch.hasDifferences
 + + + - - Retentez votre chance!
```