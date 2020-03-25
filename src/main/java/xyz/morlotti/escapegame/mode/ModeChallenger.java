package xyz.morlotti.escapegame.mode;

import org.apache.logging.log4j.Logger;

import xyz.morlotti.escapegame.Log;
import xyz.morlotti.escapegame.Config;
import xyz.morlotti.escapegame.LogMessage;
import xyz.morlotti.escapegame.user.AbstractUser;
import xyz.morlotti.escapegame.user.DumbAI;
import xyz.morlotti.escapegame.user.Human;

import java.util.Arrays;

// Le joueur doit deviner la combinaison proposée par l'IA
public class ModeChallenger extends AbstractMode
{
	protected final Logger m_logger;

	public ModeChallenger(Config config)
	{
		super(config);

		m_logger = Log.getLogger("ModeChallenger", config.isDeveloperMode());
	}

	// Démarrage d'une partie en mode challenger.
	public int start()
	{
		System.out.println(LogMessage.START_CHALLENGER);

		AbstractUser ai = new DumbAI(m_config); // instancie une intelligence artificielle
		AbstractUser human = new Human(m_config); // instancie un joueur humain

		int[] combination = ai.generateCombination(); // combinaison générée aléatoirement par l'IA

		int[] comparison = generateComparison(); // pour stocker la proximité entre la combinaison proposée par l'IA et
												// la combinaison à deviner.

		m_logger.info(String.format(LogMessage.COMBINATION_HUMAN, m_config.getCombinationLength(), Arrays.toString(combination)));

		// Effectue "CombinationNumberOfTry" tentatives de divination
		for (int i = 0; i < m_config.getCombinationNumberOfTry(); i++)
		{
			System.out.println(String.format(LogMessage.ATTEMPT, i + 1));

			if(playATurnAI(i, human, combination, comparison))
			{
				System.out.println(String.format(LogMessage.COMBINATION_WAS, Arrays.toString(combination)));

				return AbstractMode.HUMAN_WON;
			}
		}

		System.out.println(String.format(LogMessage.COMBINATION_WAS, Arrays.toString(combination)));

		return AbstractMode.AI_WON;
	}
}
