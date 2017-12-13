import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Game {

    private final static Scanner scanner = new Scanner(System.in);

    protected abstract void executeGameChallenger();
    protected abstract void executeGameDefender();
    protected abstract Boolean executeGameDuel();

    /**
     * Start the game depending on the game mode.
     * @param gameMode the mode in which the game is launched
     * @return false if you want to stop the program after the game finish
     */
    public Boolean execute (GameMode gameMode, GameType gameType) {
        System.out.println("Vous avez lancé le jeu " + gameType + " en mode " + gameMode);
        System.out.println("----------------------------------------");
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
     * computer generates five random digits combination
     * @return a list containing the five digits
     */
    protected ArrayList<Integer> getCombiComputer () {
        ArrayList<Integer> combi = new ArrayList<Integer>();
        for(int i = 0; i < 5; i++) {
            Double randomDouble = Math.random()* 10;
            Integer randomInt = randomDouble.intValue();
            combi.add(randomInt);
        }
        return combi;
    }

    /**
     * Ask to player a five digit combination and retry until it succeeded
     * @return a list containing the five digits
     */
    protected ArrayList<Integer> getCombiPlayer(String description) {
        ArrayList<Integer> combi = new ArrayList<Integer>();
        while(true) {
            int answer = 0;
            System.out.println("Entrez votre "+ description +" à 5 chiffres :");
            try {
                answer = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Vous devez entrer votre "+ description +" à 5 chiffres :");
                scanner.next();
            }
            if (answer > 10000 && answer <= 99999) {
                while (answer > 0) {
                    combi.add(0 , answer % 10);
                    answer = answer / 10;
                }
                return combi;
            }
        }
    }

    /**
     * @return a list containing five random digits
     */
    protected ArrayList<Integer> generateComputerSecret() {
        return this.getCombiComputer();
    }

    /**
     * @return a list containing five digits chosen by player
     */
    protected ArrayList<Integer> getPlayerSecret() {
        return this.getCombiPlayer("secret");
    }

}


