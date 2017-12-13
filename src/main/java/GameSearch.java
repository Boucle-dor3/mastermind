import java.util.ArrayList;

public class GameSearch extends Game {


    protected void executeGameChallenger() {
    }

    protected void executeGameDefender() {
    }

    /**
     * player and computer play together until one of us wins.
     * @return false if you want to stop the program after the game finish .
     */
    protected Boolean executeGameDuel(){
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

    /**
     * calculate the differences between proposition and secret
     * @param proposition
     * @param secret
     * @return null if there is no differences or the differences (eg. - + - = +)
     */
    private String hasDifferences(ArrayList<Integer> proposition, ArrayList<Integer> secret) {
        if (proposition.equals(secret)) {
           return null;
        }
        StringBuilder  difference = new StringBuilder();
        for(int i = 0; i<proposition.size(); i++) {
            int propositionDigit = proposition.get(i);
            int secretDigit = secret.get(i);
            if(secretDigit-propositionDigit>0) {
                difference.append(" +");
            } else if (secretDigit-propositionDigit<0) {
                difference.append(" -");
            } else {
                difference.append(" =");
            }
        }
        return difference.toString();
    }


}


