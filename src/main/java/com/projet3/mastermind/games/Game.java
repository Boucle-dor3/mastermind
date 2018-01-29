package com.projet3.mastermind.games;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.projet3.mastermind.Mastermind;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Game {

    private final static Scanner scanner = new Scanner(System.in);

    private ArrayList<ArrayList<Integer>> alreadySeenComputerProposition = new ArrayList<ArrayList<Integer>>();

    protected abstract String hasDifferences(ArrayList<Integer> proposition, ArrayList<Integer> secret);

    protected final static Logger logger = LogManager.getLogger(Game.class.getName());

    /**
     * Start the game depending on the game mode.
     * @param gameMode the mode in which the game is launched
     * @return false if you want to stop the program after the game finish
     */
    public Boolean execute (GameMode gameMode, GameType gameType) {
        System.out.println();
        System.out.println("Vous avez lancé le jeu " + gameType + " en mode " + gameMode);
        System.out.println("----------------------------------------");
        this.resetAlreadySeenComputerProposition();

        Game.logger.info("execute", gameType, gameMode);
        if (gameMode.equals(GameMode.CHALLENGER)) {
            this.executeGameChallenger();
        }
        else if (gameMode.equals(GameMode.DEFENDER)) {
            this.executeGameDefender();
        }
        else if (gameMode.equals(GameMode.DUEL)) {
            this.executeGameDuel();
        }
        return this.wantToContinue();
    }

    /**
     * Ask to the player if he wants to continue to play
     * @return if player want to continue the game
     */
    private Boolean wantToContinue () {
        System.out.println();
        System.out.println("--------------------");
        System.out.println("Voulez-vous continuer à jouer? O pour oui et N pour non.");
        String answer = scanner.next();
        if (answer.equals("N")) {
            System.out.println("A bientôt !");
            return false;
        } else if (answer.equals("O")) {
            return true;
        }
        return this.wantToContinue();
    }

    /**
     * Generate a random combination that computer never play before
     * @return a list containing the five digits
     */
    private ArrayList<Integer> getCombiComputer() {
        ArrayList<Integer> combi;
        Boolean isNew;

        Game.logger.info("compare combinaisons");
        do {
            isNew = true;
            combi = this.generateRandomCombi();
            for (ArrayList<Integer> proposition : this.alreadySeenComputerProposition) {
                if (proposition.equals(combi)) {
                    isNew = false;
                }
            }
        } while (isNew.equals(false));
        return combi;
    }

    /**
     * computer generates five random digits combination
     * @return a list containing the five digits
     */
    private ArrayList<Integer> generateRandomCombi() {

        Game.logger.info("Generate a random list.");
        ArrayList<Integer> combi = new ArrayList<Integer>();
        for(int i = 0; i < Mastermind.gameConfig.getNbItems(); i++) {
            Double randomDouble = Math.random()* 10;
            Integer randomInt = randomDouble.intValue();
            combi.add(randomInt);
        }
        return combi;
    }

    /**
     * Reset the list of proposition already choose by the computer. Use, for example, when a new game start.
     */
    private void resetAlreadySeenComputerProposition() {
        Game.logger.info("resetAlreadySeenComputerProposition");
        this.alreadySeenComputerProposition = new ArrayList<ArrayList<Integer>>();
    }

    /**
     * Ask to player a five digit combination and retry until it succeeded
     * @param description "secret" or "combination"
     * @return a list containing the five digits
     */
    private ArrayList<Integer> getCombiPlayer(String description) {

        Game.logger.info("getCombiPlayer", description);
        while(true) {
            ArrayList<Integer> combi = new ArrayList<Integer>();
            String answer = "";
            Boolean hasError = false;
            System.out.println("Entrez votre " + description + " à " + Mastermind.gameConfig.getNbItems() + " chiffres :");
            answer = scanner.next();
            for (char c : answer.toCharArray()) {
                int currentDigit = Character.getNumericValue(c);
                if (currentDigit == -1) {
                    hasError = true;
                }
                combi.add(currentDigit);
            }
            if (combi.size() == Mastermind.gameConfig.getNbItems() && !hasError) {
                return combi;
            }
        }
    }

    /**
     * @return a list containing five random digits
     */
    private ArrayList<Integer> generateComputerSecret() {
        Game.logger.info("generateComputerSecret");
        return this.generateRandomCombi();
    }

    /**
     * @return a list containing five digits chosen by player
     */
    private ArrayList<Integer> getPlayerSecret() {
        Game.logger.info("getPlayerSecret");
        return this.getCombiPlayer("secret");
    }

    /**
     * player must guess the combination of computer
     * @return false if you want to stop the program after the game finish .
     */
    private Boolean executeGameChallenger() {
        ArrayList<Integer> combiComputerSecret = this.generateComputerSecret();


        Game.logger.info("executeGameChallenger with combination secret of computer");
        //System.out.println("combi secrète" + combiComputerSecret.toString());
        for (int i = 0; i < Mastermind.gameConfig.getNbTrials(); i++) {
            Integer tryCount = Mastermind.gameConfig.getNbTrials() - i;
            System.out.println();
            System.out.println("**********************");
            System.out.println("Tour du joueur (" + tryCount + " essais restants)");
            if (playGameTour(combiComputerSecret, false)) { return false; }
        }
        System.out.println("La combi secrète était " + combiComputerSecret.toString());

        return false;
    }

    /**
     * computer must guess the combination of player
     * @return false if you want to stop the program after the game finish .
     */
    private Boolean executeGameDefender() {
        ArrayList<Integer> combiPlayerSecret = this.getPlayerSecret();

        Game.logger.info( "executeGameDefender with combination secret of player" );
        //System.out.println("combi secrète" + combiComputerSecret.toString());
        for (int i = 0; i < Mastermind.gameConfig.getNbTrials(); i++) {
            Integer tryCount = Mastermind.gameConfig.getNbTrials() - i;
            System.out.println();
            System.out.println("**********************");
            System.out.println("Tour de l'ordinateur (" + tryCount + " essais restants)");
            if (playGameTour(combiPlayerSecret, true)) { return false; }
        }

        return false;
    }

    /**
     * player and computer play together until one of us wins.
     * @return false if you want to stop the program after the game finish .
     */
    private Boolean executeGameDuel(){
        ArrayList<Integer> combiComputerSecret = this.generateComputerSecret();
        ArrayList<Integer> combiPlayerSecret = this.getPlayerSecret();

        Game.logger.info("executeGameDuel with each tour of each player");

        //System.out.println("combi secrète" + combiComputerSecret.toString());
        for (int i = 0; i < Mastermind.gameConfig.getNbTrials(); i++) {
            Integer tryCount = Mastermind.gameConfig.getNbTrials() - i;
            System.out.println();
            System.out.println("**********************");
            System.out.println("Tour de l'ordinateur(" + tryCount + " essais restants)");
            if (playGameTour(combiPlayerSecret, true)) {
                System.out.println("La combi secrète était " + combiComputerSecret.toString());
                return false;
            }
            System.out.println();
            System.out.println("**********************");
            System.out.println("Tour du joueur(" + tryCount + " essais restants)");
            if (playGameTour(combiComputerSecret, false)) { return false; }
        }
        System.out.println("La combi secrète était " + combiComputerSecret.toString());
        return false;
    }

    /**
     * Compare tour with the secret combinaison
     * @param secret the secret to find
     * @param amIComputer true if computer play false if player play
     * @return true for win and false for loose
     */
    private boolean playGameTour(ArrayList<Integer> secret, Boolean amIComputer) {

        Game.logger.trace("playGameTour", secret, amIComputer);
        ArrayList<Integer> proposition =  amIComputer ? this.getCombiComputer() : this.getCombiPlayer("combinaison");
        String difference = this.hasDifferences(proposition, secret);
        if (difference == null) {
            System.out.println("Vous avez trouvé la bonne combinaison!");
            if (amIComputer) {
                System.out.println("L'ordinateur a gagné!");
            } else {
                System.out.println("Le joueur a gagné!");
            }
            return true;
        }
        System.out.println(difference + " Retentez votre chance!");
        return false;
    }

}


