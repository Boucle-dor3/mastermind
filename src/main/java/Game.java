import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Game {

    private final static Scanner scanner = new Scanner(System.in);

    private ArrayList<ArrayList<Integer>> alreadySeenComputerProposition = new ArrayList<ArrayList<Integer>>();

    protected abstract String hasDifferences(ArrayList<Integer> proposition, ArrayList<Integer> secret);

    /**
     * Start the game depending on the game mode.
     * @param gameMode the mode in which the game is launched
     * @return false if you want to stop the program after the game finish
     */
    public Boolean execute (GameMode gameMode, GameType gameType) {
        System.out.println("Vous avez lancé le jeu " + gameType + " en mode " + gameMode);
        System.out.println("----------------------------------------");
        this.resetAlreadySeenComputerProposition();

        if (gameMode.equals(GameMode.CHALLENGER)) {
            this.executeGameChallenger();
        }
        else if (gameMode.equals(GameMode.DEFENDER)) {
            this.executeGameDefender();
        }
        else if (gameMode.equals(GameMode.DUEL)) {
            return this.executeGameDuel();
        }
        return false;
    }

    /**
     * Generate a random combination that computer never play before
     * @return a list containing the five digits
     */
    private ArrayList<Integer> getCombiComputer() {
        ArrayList<Integer> combi;
        Boolean isNew;
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
        ArrayList<Integer> combi = new ArrayList<Integer>();
        for(int i = 0; i < 5; i++) {
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
        this.alreadySeenComputerProposition = new ArrayList<ArrayList<Integer>>();
    }

    /**
     * Ask to player a five digit combination and retry until it succeeded
     * @return a list containing the five digits
     */
    private ArrayList<Integer> getCombiPlayer(String description) {
        while(true) {
            ArrayList<Integer> combi = new ArrayList<Integer>();
            String answer = "";
            Boolean hasError = false;
            System.out.println("Entrez votre "+ description +" à 5 chiffres :");
            try {
                answer = scanner.next();
            } catch (InputMismatchException e) {
                System.out.println("Vous devez entrer votre "+ description +" à 5 chiffres :");
                scanner.next();
            }
            for (char c : answer.toCharArray()) {
                int currentDigit = Character.getNumericValue(c);
                if (currentDigit == -1) {
                    hasError = true;
                }
                combi.add(0 , Character.getNumericValue(c));
            }
            if (combi.size() == 5 && !hasError) {
                return combi;
            }
        }
    }

    /**
     * @return a list containing five random digits
     */
    private ArrayList<Integer> generateComputerSecret() {
        return this.generateRandomCombi();
    }

    /**
     * @return a list containing five digits chosen by player
     */
    private ArrayList<Integer> getPlayerSecret() {
        return this.getCombiPlayer("secret");
    }

    /**
     * player must guess the combination of computer
     * @return false if you want to stop the program after the game finish .
     */
    private Boolean executeGameChallenger() {
        ArrayList<Integer> combiComputerSecret = this.generateComputerSecret();

        //System.out.println("combi secrète" + combiComputerSecret.toString());
        for (int i = 0; i < Mastermind.NB_TRIALS; i++) {
            System.out.println("**********************");
            System.out.println("Tour du joueur");
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

        //System.out.println("combi secrète" + combiComputerSecret.toString());
        for (int i = 0; i < Mastermind.NB_TRIALS; i++) {
            System.out.println("**********************");
            System.out.println("Tour de l'ordinateur");
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

        //System.out.println("combi secrète" + combiComputerSecret.toString());
        for (int i = 0; i < Mastermind.NB_TRIALS; i++) {
            System.out.println("**********************");
            System.out.println("Tour de l'ordinateur");
            if (playGameTour(combiPlayerSecret, true)) { return false; }
            System.out.println("**********************");
            System.out.println("Tour du joueur");
            if (playGameTour(combiComputerSecret, false)) { return false; }
        }
        return false;
    }

    /**
     * Compare tour with the secret combinaison
     * @param secret the secret to find
     * @param amIComputer true if computer play false if player play
     * @return true for win and false for loose
     */
    private boolean playGameTour(ArrayList<Integer> secret, Boolean amIComputer) {
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


