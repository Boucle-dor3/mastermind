package com.projet3.mastermind.games;

import com.projet3.mastermind.Mastermind;

import java.util.ArrayList;

public class GameSearch extends Game {

    /**
     * calculate the differences between proposition and secret
     * @param proposition
     * @param secret
     * @return null if there is no differences or the differences (eg. - + - = +)
     */
    protected String hasDifferences(ArrayList<Integer> proposition, ArrayList<Integer> secret) {
        Game.logger.trace("GameSearch.hasDifferences", proposition, secret);
        if (proposition.equals(secret)) {
           return null;
        }
        StringBuilder  differences = new StringBuilder();
        for(int i = 0; i<proposition.size(); i++) {
            int propositionDigit = proposition.get(i);
            int secretDigit = secret.get(i);
            if(secretDigit-propositionDigit>0) {
                differences.append(" + ");
            } else if (secretDigit-propositionDigit<0) {
                differences.append(" - ");
            } else {
                differences.append(" = ");
            }
        }
        return differences.toString();
    }

    protected Integer getNbItems() {
        return Mastermind.gameConfig.getNbItemsSearch();
    }

    protected Integer getNbTrials() {
        return Mastermind.gameConfig.getNbTrialsSearch();
    }

}


