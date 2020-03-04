package xyz.morlotti.escapegame.mode;

import org.apache.logging.log4j.Logger;

import xyz.morlotti.escapegame.Log;
import xyz.morlotti.escapegame.Config;
import xyz.morlotti.escapegame.user.AbstractUser;
import xyz.morlotti.escapegame.user.BumbAI;
import xyz.morlotti.escapegame.user.Human;

import java.util.Arrays;

public class ModeDual extends AbstractMode
{
    protected final Logger m_logger;

    public ModeDual(Config config)
    {
        super(config);

        m_logger = Log.getLogger("ModeDual", config.isDeveloperMode());
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

        m_logger.info("Combinaison à " + m_config.getCombinationLength() + " chiffres à faire deviner à l'utilisateur : " + Arrays.toString(combinationAI));
        m_logger.info("Combinaison à " + m_config.getCombinationLength() + " chiffres à faire deviner à l'IA : " + Arrays.toString(combinationHuman));

        for (int i = 0; i < m_config.getCombinationNumberOfTry(); i++)
        {
            System.out.println("Tentative numéro " + (i + 1));

            System.out.println("Joueur:");
            if(playATurn(i, human, combinationAI, comparisonHuman))
            {
                return AbstractMode.HUMAN_WON;
            }

            System.out.println("IA:");
            if(playATurn(i, ai, combinationHuman, comparisonAI))
            {
                return AbstractMode.AI_WON;
            }
        }

        return AbstractMode.NOBODY_WON;
    }
}
