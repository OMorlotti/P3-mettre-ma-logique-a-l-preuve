package xyz.morlotti.escapegame.user;

import xyz.morlotti.escapegame.Config;

import java.util.Random;

public class BumbIA extends AI {
    private final int smartPercent;

    public BumbIA(Config config, int smartPercent) {
        super(config);
        this.smartPercent = smartPercent;
    }

    @Override
    public int[] guessCombination(int[] comparison) {
        int rand = new Random().nextInt(100);
        if (rand < smartPercent) {
            return super.guessCombination(comparison);
        } else {
            int[] dumb = super.randomCombination();
            for (int i = 0; i < m_lastValues.length; i++) {
                m_lastValues[i] = dumb[i];
            }
            return dumb;
        }
    }
}
