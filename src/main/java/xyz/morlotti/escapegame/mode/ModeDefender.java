package xyz.morlotti.escapegame.mode;

import xyz.morlotti.escapegame.Config;
import xyz.morlotti.escapegame.user.AI;

public class ModeDefender extends AbstractMode
{
    public ModeDefender(Config config)
    {
        super(config);
    }

    public int start()
    {
        System.out.println("Démarrage du mode Défenseur");

        AI user = new AI(m_config);

        int[] combination = readCombination();

        int[] comparison = generateComparison();

        for (int i = 0; i < m_config.getCombinationNumberOfTry(); i++)
        {
            System.out.println("Tentative numéro " + (i + 1));

            if(playATurn(user, combination, comparison) == true)
            {
                return AbstractMode.AI_WON;
            }
        }

        return AbstractMode.NOBODY_WON;
    }
}
