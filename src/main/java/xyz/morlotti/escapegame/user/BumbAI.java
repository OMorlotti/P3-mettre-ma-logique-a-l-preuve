package xyz.morlotti.escapegame.user;

import java.util.Random;

import xyz.morlotti.escapegame.Config;

public class BumbAI extends AI
{
	private final int smartPercent;

	public BumbAI(Config config)
	{
		super(config);

		this.smartPercent = config.getSmartPercent();
	}

	@Override
	public int[] guessCombination(int[] comparison)
	{
		int rand = new Random().nextInt(100);

		if (rand < smartPercent)
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