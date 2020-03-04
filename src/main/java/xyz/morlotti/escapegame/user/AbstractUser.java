package xyz.morlotti.escapegame.user;

import xyz.morlotti.escapegame.Config;

public abstract class AbstractUser
{
	protected final Config m_config;

	public AbstractUser(Config config)
	{
		m_config = config;
	}

	public abstract int[] generateCombination();

	abstract public int[] guessCombination(int[] comparison);
}
