package xyz.morlotti.escapegame.mode;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;

import xyz.morlotti.escapegame.Log;
import xyz.morlotti.escapegame.Config;
import xyz.morlotti.escapegame.LogMessage;
import xyz.morlotti.escapegame.user.AbstractUser;
import xyz.morlotti.escapegame.user.DumbAI;
import xyz.morlotti.escapegame.user.Human;

// L'IA doit deviner la combinaison proposée par le joueur
public class ModeDefender extends AbstractMode
{
    protected final Logger m_logger;

    public ModeDefender(Config config)
    {
        super(config);

        m_logger = Log.getLogger("ModeDefender", config.isDeveloperMode());
    }

    // Démarrage d'une partie en mode défenseur.
    public int start()
    {
        System.out.println(LogMessage.START_DEFENDER);

        AbstractUser ia = new DumbAI(m_config); // instancie une intelligence artificielle
        AbstractUser human = new Human(m_config); // instancie un joueur humain

        int[] combination = human.generateCombination(); // saisie au clavier par l'utilisateur

        int[] comparison = generateComparison(); // pour stocker la proximité entre la combinaison proposée par le joueur et
                                                 // la combinaison à deviner.

        m_logger.info(String.format(LogMessage.COMBINATION_AI, m_config.getCombinationLength(), Arrays.toString(combination)));

        // Effectue "CombinationNumberOfTry" tentatives de divination
        for (int i = 0; i < m_config.getCombinationNumberOfTry(); i++)
        {
            System.out.println(String.format(LogMessage.ATTEMPT, i + 1));

            if(playATurn(i, ia, combination, comparison))
            {
                return AbstractMode.AI_WON;
            }
        }

        return AbstractMode.HUMAN_WON;
    }
}
