package xyz.morlotti.escapegame.user;

import xyz.morlotti.escapegame.Config;

public class AI extends AbstractUser
{
	private final int START = 0;
	private final int STOP = 10;

	private final int CENTER = (STOP - START) / 2;

	private final int[] m_startValues;
	private final int[] m_stopValues;
	private final int[] m_lastValues;

	private boolean m_firstIter;

	public AI(Config config)
	{
		super(config);

		int length = config.getCombinationLength();

		m_startValues = new int[length];
		m_stopValues = new int[length];
		m_lastValues = new int[length];

		reset();
	}

	public void reset()
	{
		for(int i = 0; i < config.getCombinationLength(); i++)
		{
			m_startValues[i] = START;
			m_stopValues[i] = STOP;
			m_lastValues[i] = CENTER;
		}

		m_firstIter = true;
	}

	public String getCombinationAsString(int[] comparison)
	{
		String newCombinationAsString = "";

		for(int i = 0; i < config.getCombinationLength(); i++)
		{
			if(m_firstIter)
			{
				m_firstIter = false;

				newCombinationAsString += String.valueOf(m_lastValues[i]);
			}
			else
			{
				/**/ if(comparison[i] < 0)
				{
					m_stopValues[i] = m_lastValues[i];
					m_lastValues[i] = (m_stopValues[i] + m_startValues[i]) / 2;
				}
				else if(comparison[i] > 0)
				{
					m_startValues[i] = m_lastValues[i];
					m_lastValues[i] = (m_stopValues[i] + m_startValues[i]) / 2;
				}

				newCombinationAsString += String.valueOf(m_lastValues[i]);
			}
		}

		return newCombinationAsString;
	}
}
