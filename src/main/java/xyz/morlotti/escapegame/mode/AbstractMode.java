package xyz.morlotti.escapegame.mode;

import xyz.morlotti.escapegame.Config;
import xyz.morlotti.escapegame.user.AbstractUser;

import java.util.Arrays;

abstract public class AbstractMode {
    public static final int NOBODY_WON = 0;
    public static final int HUMAN_WON = 1;
    public static final int AI_WON = 2;

    protected final Config m_config;

    public AbstractMode(Config config) {
        m_config = config;
    }

    protected int[] generateComparison() {
        return new int[m_config.getCombinationLength()];
    }

    protected boolean playATurn(AbstractUser user, int[] combination, int[] comparison) {
        int[] newCombination = user.guessCombination(comparison);
        System.out.println(Arrays.toString(newCombination));

        if (newCombination.length == m_config.getCombinationLength()) {
            int count = 0;

            for (int j = 0; j < m_config.getCombinationLength(); j++) {
                comparison[j] = combination[j] - newCombination[j];

                /**/
                if (comparison[j] < 0) {
                    System.out.print("-");
                } else if (comparison[j] > 0) {
                    comparison[j] = +1;
                    System.out.print("+");
                } else {
                    System.out.print("=");
                    count++;
                }
            }

            System.out.println();

            return count == m_config.getCombinationLength();
        } else {
            System.out.println("Mauvaise longueur de combinaison ! Vous avez perdu un tour ! ;-)");
        }

        return false;
    }

    public abstract int start();
}
