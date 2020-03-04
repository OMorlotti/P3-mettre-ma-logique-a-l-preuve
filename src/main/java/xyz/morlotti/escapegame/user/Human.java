package xyz.morlotti.escapegame.user;

import java.util.Arrays;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;

import xyz.morlotti.escapegame.Config;
import xyz.morlotti.escapegame.Log;

public class Human extends AbstractUser
{
	protected final Logger m_logger;

	public Human(Config config)
	{
		super(config);

		m_logger = Log.getLogger("AI", config.isDeveloperMode());
	}

	public int[] guessCombination(int[] comparison)
	{
		return generateCombination(
			"Veuillez saisir votre tentative de combinaison à " + m_config.getCombinationLength() + " chiffres :",
			"Tentative de combinaison à " + m_config.getCombinationLength() + " chiffres :"
		);
	}

	public int[] generateCombination()
	{
		return generateCombination(
			"Veuillez saisir la combinaison à " + m_config.getCombinationLength() + " chiffres à faire deviner à l'IA :",
			"Combinaison à " + m_config.getCombinationLength() + " chiffres à faire deviner à l'IA :"
		);
	}

	private int[] generateCombination(String screenMessage, String logMessage)
	{
		String combinationAsString;

		do {
			System.out.println(screenMessage);

			combinationAsString = new Scanner(System.in).nextLine();

		} while (combinationAsString.matches("[0-9]{" + m_config.getCombinationLength() + "}+") == false);

		// Création d'un tableau qui contient autant d'entrées que ce que retourne la méthode getCombinationLength()
		int[] combination = new int[m_config.getCombinationLength()];

		// Génération d'autant de chiffres que ce que retourne la méthode getCombinationLength()
		for (int i = 0; i < m_config.getCombinationLength(); i++)
		{
			int keyboardNum = (int) combinationAsString.charAt(i) - '0'; // voir table de correspondance ASCII

			// Stockage du nombre du clavier en cours dans le tableau combination[], "i" vaudra successivement 0, 1, 2, 3
			combination[i] = keyboardNum;
		}

		m_logger.info(logMessage + Arrays.toString(combination));

		return combination;
	}
}
