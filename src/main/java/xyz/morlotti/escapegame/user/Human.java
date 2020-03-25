package xyz.morlotti.escapegame.user;

import java.util.Scanner;

import xyz.morlotti.escapegame.Config;
import xyz.morlotti.escapegame.LogMessage;

public class Human extends AbstractUser
{
	public Human(Config config)
	{
		super(config);
	}

	// Génère une combinaison à "combinationLength" chiffres lue au clavier
	public int[] generateCombination() // combinaison générée par le joueur
	{
		return readCombination(
			String.format(LogMessage.ENTER_CONBINATION_FOR_IA, m_config.getCombinationLength())
		);
	}

	// Propose une combinaison lue au clavier
	public int[] guessCombination(int[] comparison) // tentative du joueur
	{
		return readCombination(
			String.format(LogMessage.ENTER_YOUR_CONBINATION, m_config.getCombinationLength())
		);
	}

	private int[] readCombination(String message)
	{
		String combinationAsString;

		do {
			System.out.println(message);

			combinationAsString = new Scanner(System.in).nextLine();

			// boucle tant que "combinationAsString" n'est pas une chaine de "combinationLength"; chiffres testés avec une expression régulière (regex).
		} while (!combinationAsString.matches("[0-9]{" + m_config.getCombinationLength() + "}+"));

		// Création d'un tableau qui contient autant d'entrées que ce que retourne la méthode getCombinationLength()
		int[] combination = new int[m_config.getCombinationLength()];

		// Génération d'autant de chiffres que ce que retourne la méthode getCombinationLength()
		for (int i = 0; i < m_config.getCombinationLength(); i++)
		{
			int keyboardNum = (int) combinationAsString.charAt(i) - '0'; // voir table de correspondance ASCII

			// Stockage du nombre du clavier en cours dans le tableau combination[], "i" vaudra successivement 0, 1, 2, 3
			combination[i] = keyboardNum;
		}

		return combination;
	}
}
