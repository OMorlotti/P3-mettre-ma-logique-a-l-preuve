package xyz.morlotti.escapegame.mode;

import xyz.morlotti.escapegame.Config;
import xyz.morlotti.escapegame.user.AI;
import xyz.morlotti.escapegame.user.Human;

public class ModeDual extends AbstractMode
{
    public ModeDual(Config config)
    {
        super(config);
    }

    public int start()
    {
        System.out.println("Démarrage du mode Duel");

        Human human = new Human(m_config);
        AI ai = new AI(m_config);

        int[] combinationHuman = readCombination();
        int[] combinationAI = generateCombination();

        int[] comparisonHuman = generateComparison();
        int[] comparisonAI = generateComparison();

        for (int i = 0; i < m_config.getCombinationNumberOfTry(); i++)
        {
            System.out.println("Tentative numéro " + (i + 1));

            System.out.println("Joueur:");
            if(playATurn(human, combinationHuman, comparisonHuman) == true)
            {
                return AbstractMode.HUMAN_WON;
            }

            System.out.println("IA:");
            if(playATurn(ai, combinationAI, comparisonAI) == true)
            {
                return AbstractMode.AI_WON;
            }
        }

        return AbstractMode.NOBODY_WON;
    }
}
