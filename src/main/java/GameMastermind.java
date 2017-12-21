import java.util.ArrayList;

public class GameMastermind extends Game {

    /**
     * calculate the differences between proposition and secret
     * @param proposition
     * @param secret
     * @return null if there is no differences or the differences (eg. 0 présent, 1 bien placé)
     */
    protected String hasDifferences(ArrayList<Integer> proposition, ArrayList<Integer> secret) {
        if (proposition.equals(secret)) {
            return null;
        }
        int presentCount = 0;
        int goodPlaceCount = 0;
        for (int i = 0; i < proposition.size(); i++) {
            int propositionDigit = proposition.get(i);
            int secretDigit = secret.get(i);
            if (secretDigit == propositionDigit) {
                goodPlaceCount++;
            } else {
                for (Integer aSecret : secret) {
                    if (aSecret == propositionDigit) {
                        presentCount++;
                        break;
                    }
                }
            }
        }
        return presentCount + " présents. " + goodPlaceCount + " biens placés.";
    }

}
