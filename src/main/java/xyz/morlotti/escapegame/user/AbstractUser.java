package xyz.morlotti.escapegame.user;

import xyz.morlotti.escapegame.Config;

public abstract class AbstractUser
{
	protected final Config m_config;

	public AbstractUser(Config config)
	{
		m_config = config;
	}

	// Génère une combinaison à "combinationLength" chiffres.
	public abstract int[] generateCombination();

	// Propose une combinaison depuis le clavier ou par l'IA selon l'implémentation
	abstract public int[] guessCombination(int[] comparison);
}
