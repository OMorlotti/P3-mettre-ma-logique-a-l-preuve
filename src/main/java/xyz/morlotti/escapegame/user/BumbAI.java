package xyz.morlotti.escapegame.user;

import java.util.Random;

import xyz.morlotti.escapegame.Config;

public class BumbAI extends AI
{
	private final int m_smartPercent; // Permet d'ajuster le niveau de difficulté de jeux en fonction du pourcentage de difficulté

	public BumbAI(Config config)
	{
		super(config);

		this.m_smartPercent = config.getSmartPercent();
	}

	@Override
	public int[] guessCombination(int[] comparison)
	{
		int rand = new Random().nextInt(100);

		if (rand < m_smartPercent)
		{
			return super.guessCombination(comparison);
		}
		else
		{
			int[] combination = super.generateCombination();

			for (int i = 0; i < m_lastValues.length; i++)
			{
				m_lastValues[i] = combination[i];
			}

			return combination;
		}
	}
}