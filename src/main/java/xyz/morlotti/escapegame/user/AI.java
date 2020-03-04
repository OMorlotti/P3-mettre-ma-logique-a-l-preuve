package xyz.morlotti.escapegame.user;

import java.util.Random;

import xyz.morlotti.escapegame.Config;

public class AI extends AbstractUser
{
	// Dichotomy
	private final int START = 0;
	private final int STOP = 10;

	private final int CENTER = (STOP - START) / 2;

	// Définition de tableaux contenant les valeurs limites
	private final int[] m_startValues;
	private final int[] m_stopValues;
	protected final int[] m_lastValues;

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

	public int[] generateCombination()
	{
		int low = 0;
		int high = 10;

		// Générateur de nombres aléatoires
		Random numberChosenByRandom = new Random();

		// Création d'un tableau qui contient autant d'entrées que ce que retourne la méthode getCombinationLength()
		int[] combination = new int[m_config.getCombinationLength()];

		// Génération d'autant de chiffres que ce que retourne la méthode getCombinationLength()
		for (int i = 0; i < m_config.getCombinationLength(); i++)
		{
			int randomNum = numberChosenByRandom.nextInt(high - low) + low;

			// Stockage du nombre aléatoire en cours dans le tableau combination[], "i" vaudra successivement 0, 1, 2, 3
			combination[i] = randomNum;
		}

		return combination;
	}

	public void reset()
	{
		for(int i = 0; i < m_config.getCombinationLength(); i++)
		{
			m_startValues[i] = START;
			m_stopValues[i] = STOP;
			m_lastValues[i] = CENTER;
		}

		m_firstIter = true;
	}

	public int[] guessCombination(int[] comparison)
	{
		int[] combination = new int[m_config.getCombinationLength()];

		for(int i = 0; i < m_config.getCombinationLength(); i++)
		{
			if(m_firstIter)
			{
				m_firstIter = false;

				combination[i] = m_lastValues[i];
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

				combination[i] = m_lastValues[i];
			}
		}

		return combination;
	}
}
