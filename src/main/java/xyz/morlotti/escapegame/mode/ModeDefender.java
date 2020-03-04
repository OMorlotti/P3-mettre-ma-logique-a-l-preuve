package xyz.morlotti.escapegame.mode;

import org.apache.logging.log4j.Logger;

import xyz.morlotti.escapegame.Config;
import xyz.morlotti.escapegame.Log;
import xyz.morlotti.escapegame.user.AbstractUser;
import xyz.morlotti.escapegame.user.BumbAI;
import xyz.morlotti.escapegame.user.Human;

import java.util.Arrays;

public class ModeDefender extends AbstractMode
{
    protected final Logger m_logger;

    public ModeDefender(Config config)
    {
        super(config);

        m_logger = Log.getLogger("ModeDefender", config.isDeveloperMode());
    }

    public int start()
    {
        System.out.println("Démarrage du mode Défenseur");

        AbstractUser ia = new BumbAI(m_config);
        AbstractUser human = new Human(m_config);

        int[] combination = human.generateCombination();

        int[] comparison = generateComparison();

        m_logger.info("Combinaison à " + m_config.getCombinationLength() + " chiffres à faire deviner à l'IA : " + Arrays.toString(combination));

        for (int i = 0; i < m_config.getCombinationNumberOfTry(); i++)
        {
            System.out.println("Tentative numéro " + (i + 1));

            if(playATurn(i, ia, combination, comparison))
            {
                return AbstractMode.AI_WON;
            }
        }

        return AbstractMode.NOBODY_WON;
    }
}
