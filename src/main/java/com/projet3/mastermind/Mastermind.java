package com.projet3.mastermind;

import com.projet3.mastermind.config.GameConfig;
import com.projet3.mastermind.games.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Mastermind {

    public final static GameConfig gameConfig = new GameConfig();

    private final static Scanner scanner = new Scanner(System.in);
    private final static Logger logger = LogManager.getLogger(Mastermind.class.getName());

    public static void main (String[] args){

        if (args.length > 0 && args[0].equals("dev")) {
            gameConfig.forceModeDev();
        }

        if (gameConfig.getModeDev()) {
            System.out.println("*****************");
            System.out.println("Mode dev activé !");
            System.out.println("*****************");
        }

        if (!gameConfig.getModeDev()) {
            Mastermind.gameConfig.forceErrorLevel();
        }

        Boolean continueGame;

        do {
            GameType gameType = Mastermind.gameTypeChoice();
            GameMode gameMode = Mastermind.gameModeChoice();
            Game game = null;
            if (gameType.equals(GameType.SEARCH)) {
                game = new GameSearch();
            }
            else if (gameType.equals(GameType.MASTERMIND)) {
                game = new GameMastermind();
            }
            continueGame = game.execute(gameMode,gameType);
        } while(continueGame);
    }

    /**
     * Display the menu to choose the game type and get answer
     * @return the chosen game type
     */
    private static GameType gameTypeChoice () {
        Mastermind.logger.info("gameTypeChoice");
        while(true) {
            int answer = 0;
            System.out.println();
            System.out.println("Quel type choisissez-vous?");
            System.out.println("1 - Recherche ");
            System.out.println("2 - Mastermind ");
            try {
                answer = scanner.nextInt();
            } catch (InputMismatchException e) {
                Mastermind.logger.warn("User does not enter a digit.");
                System.out.println("Vous devez entrer un chiffre.");
                scanner.next();
            }
            if (answer > 0 && answer <= 2) {
                GameType answerGameType = GameType.values()[answer - 1];
                System.out.println("Vous avez choisi " + answerGameType);
                return answerGameType;
            }
        }
    }

    /**
     * Display the menu to choose the game mode and get answer
     * @return the chosen game mode
     */
    private static GameMode gameModeChoice () {
        Mastermind.logger.info("gameModeChoice");
        while(true) {
            int answer = 0;
            System.out.println();
            System.out.println("Quel mode choisissez-vous?");
            System.out.println("1 - Challenger ");
            System.out.println("2 - Défenseur ");
            System.out.println("3 - Duel ");
            try {
                answer = scanner.nextInt();
            } catch (InputMismatchException e) {
                Mastermind.logger.warn("User does not enter a digit.");
                System.out.println("Vous devez entrer un chiffre.");
                scanner.next();
            }
            if (answer > 0 && answer <= 3) {
                Mastermind.logger.warn("User enter a not existing choice.");
                GameMode answerGameMode = GameMode.values()[answer - 1];
                System.out.println("Vous avez choisi " + answerGameMode);
                return answerGameMode;
            }
        }
    }

}
