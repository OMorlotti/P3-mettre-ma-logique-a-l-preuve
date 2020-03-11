package xyz.morlotti.escapegame.mode;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;

import xyz.morlotti.escapegame.Log;
import xyz.morlotti.escapegame.Config;
import xyz.morlotti.escapegame.LogMessage;
import xyz.morlotti.escapegame.user.AbstractUser;
import xyz.morlotti.escapegame.user.DumbAI;
import xyz.morlotti.escapegame.user.Human;

public class ModeDual extends AbstractMode
{
    protected final Logger m_logger;

    public ModeDual(Config config)
    {
        super(config);

        m_logger = Log.getLogger("ModeDual", config.isDeveloperMode());
    }

    // Démarrage d'une partie en mode duel.
    public int start()
    {
        System.out.println(LogMessage.START_DUAL);

        AbstractUser ai = new DumbAI(m_config);
        AbstractUser human = new Human(m_config);

        int[] combinationHuman = human.generateCombination();
        int[] combinationAI = ai.generateCombination();

        int[] comparisonHuman = generateComparison();
        int[] comparisonAI = generateComparison();

        m_logger.info(String.format(LogMessage.COMBINATION_HUMAN, m_config.getCombinationLength(), Arrays.toString(combinationAI)));
        m_logger.info(String.format(LogMessage.COMBINATION_AI, m_config.getCombinationLength(), Arrays.toString(combinationHuman)));

        // Effectue "CombinationNumberOfTry" tentatives de divination
        for (int i = 0; i < m_config.getCombinationNumberOfTry(); i++)
        {
            System.out.println(String.format(LogMessage.ATTEMPT, i + 1));

            // Essaye pour l'humain
            System.out.println(LogMessage.HUMAN);
            if(playATurn(i, human, combinationAI, comparisonHuman))
            {
                return AbstractMode.HUMAN_WON;
            }

            // Essaye pour l'IA
            System.out.println(LogMessage.AI);
            if(playATurn(i, ai, combinationHuman, comparisonAI))
            {
                System.out.println(String.format(LogMessage.COMBINATION_WAS, Arrays.toString(combinationAI)));

                return AbstractMode.AI_WON;
            }
        }

        System.out.println(String.format(LogMessage.COMBINATION_WAS, Arrays.toString(combinationAI)));

        return AbstractMode.NOBODY_WON;
    }
}
