package xyz.morlotti.escapegame.mode;

import xyz.morlotti.escapegame.Config;
import xyz.morlotti.escapegame.user.BumbIA;
import xyz.morlotti.escapegame.user.Human;

public class ModeDefender extends AbstractMode {
    public ModeDefender(Config config) {
        super(config);
    }

    public int start() {
        System.out.println("Démarrage du mode Défenseur");

        BumbIA ia = new BumbIA(m_config, 50);
        Human human = new Human(m_config);

        int[] combination = human.generateCombination();

        int[] comparison = generateComparison();

        for (int i = 0; i < m_config.getCombinationNumberOfTry(); i++) {
            System.out.println("Tentative numéro " + (i + 1));

            if (playATurn(ia, combination, comparison) == true) {
                return AbstractMode.AI_WON;
            }
        }

        return AbstractMode.NOBODY_WON;
    }
}
