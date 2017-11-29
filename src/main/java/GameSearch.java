import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameSearch {

    private final static Scanner scanner = new Scanner(System.in);

    public Boolean execute (GameMode gameMode) {
        System.out.println("Vous avez lancé le jeu SEARCH en mode "+ gameMode);
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

    private void executeGameChallenger() {
    }

    private void executeGameDefender() {
    }

    private Boolean executeGameDuel(){
        ArrayList<Integer> combiComputerSecret = this.generateComputerSecret();
        ArrayList<Integer> combiPlayerSecret = this.getPlayerSecret();
        System.out.println("combi secrète" + combiComputerSecret.toString());
        for (int i = 0; i < Mastermind.NB_TRIALS; i++) {
            ArrayList<Integer> propositionComputer =  this.getCombiComputer();
            if (this.testGameDuel(propositionComputer, combiPlayerSecret)) {
                System.out.println("Vous avez trouvé la bonne combinaison!");
                System.out.println("L'ordinateur a gagné!");
                return false;
            }
            System.out.println("Retentez votre chance!");
            ArrayList<Integer> propositionPlayer = this.getCombiPlayer();
            if (this.testGameDuel(propositionPlayer, combiComputerSecret)) {
                System.out.println("Vous avez trouvé la bonne combinaison!");
                System.out.println("Le joueur a gagné!");
                return false;
            }
        }
        return false;
    }

    private ArrayList<Integer> getCombiComputer () {
        ArrayList<Integer> combi = new ArrayList<Integer>();
        for(int i = 0; i < 5; i++) {
            Double randomDouble = Math.random()* 10;
            Integer randomInt = randomDouble.intValue();
            combi.add(randomInt);
        }
        return combi;
    }

    private ArrayList<Integer> getCombiPlayer() {
        ArrayList<Integer> combi = new ArrayList<Integer>();
        while(true) {
            int answer = 0;
            System.out.println("Entrez votre combinaison à 5 chiffres :");
            try {
                answer = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Vous devez entrer un nombre à 5 chiffres :");
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

    private ArrayList<Integer> generateComputerSecret() {
       return this.getCombiComputer();
    }

    private ArrayList<Integer> getPlayerSecret() {
        System.out.println("Entrez votre combinaison secrète.");
        return this.getCombiPlayer();
    }

    private  Boolean testGameDuel(ArrayList<Integer> proposition, ArrayList<Integer> secret) {
        return proposition.equals(secret);
    }
}


