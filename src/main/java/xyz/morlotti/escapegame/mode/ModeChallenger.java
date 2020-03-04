package xyz.morlotti.escapegame.mode;

import xyz.morlotti.escapegame.Config;
import xyz.morlotti.escapegame.user.AbstractUser;
import xyz.morlotti.escapegame.user.BumbAI;
import xyz.morlotti.escapegame.user.Human;

public class ModeChallenger extends AbstractMode
{
	public ModeChallenger(Config config)
	{
		super(config);
	}

	public int start()
	{
		System.out.println("Démarrage du mode Challenger");

		AbstractUser ai = new BumbAI(m_config);
		AbstractUser human = new Human(m_config);

		int[] combination = ai.generateCombination();

		int[] comparison = generateComparison();

		for (int i = 0; i < m_config.getCombinationNumberOfTry(); i++)
		{
			System.out.println("Tentative numéro " + (i + 1));

			if(playATurn(human, combination, comparison) == true)
			{
				return AbstractMode.HUMAN_WON;
			}
		}

		return AbstractMode.NOBODY_WON;
	}
}
