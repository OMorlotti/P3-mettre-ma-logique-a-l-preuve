package xyz.morlotti.escapegame.mode;

import xyz.morlotti.escapegame.Config;
import xyz.morlotti.escapegame.user.AbstractUser;
import xyz.morlotti.escapegame.user.BumbAI;
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

        AbstractUser ai = new BumbAI(m_config);
        AbstractUser human = new Human(m_config);

        int[] combinationHuman = human.generateCombination();
        int[] combinationAI = ai.generateCombination();

        int[] comparisonHuman = generateComparison();
        int[] comparisonAI = generateComparison();

        for (int i = 0; i < m_config.getCombinationNumberOfTry(); i++)
        {
            System.out.println("Tentative numéro " + (i + 1));

            System.out.println("Joueur:");
            if(playATurn(human, combinationAI, comparisonHuman) == true)
            {
                return AbstractMode.HUMAN_WON;
            }

            System.out.println("IA:");
            if(playATurn(ai, combinationHuman, comparisonAI) == true)
            {
                return AbstractMode.AI_WON;
            }
        }

        return AbstractMode.NOBODY_WON;
    }
}
