package xyz.morlotti.escapegame.user;

import java.util.Scanner;

import xyz.morlotti.escapegame.Config;

public class Human extends AbstractUser
{
	public Human(Config config)
	{
		super(config);
	}

	public int[] guessCombination(int[] comparison)
	{
		return generateCombination("Veuillez saisir votre tentative de combinaison à " + config.getCombinationLength() + " chiffres :");
	}

	public int[] generateCombination()
	{
		return generateCombination("Veuillez saisir la combinaison à " + config.getCombinationLength() + " chiffres à faire deviner à l'IA :");
	}

	private int[] generateCombination(String message)
	{
		String combinationAsString;

		do {
			System.out.println(message);

			combinationAsString = new Scanner(System.in).nextLine();

		} while (combinationAsString.matches("[0-9]{" + config.getCombinationLength() + "}+") == false);

		// Création d'un tableau qui contient autant d'entrées que ce que retourne la méthode getCombinationLength()
		int[] combination = new int[config.getCombinationLength()];

		// Génération d'autant de chiffres que ce que retourne la méthode getCombinationLength()
		for (int i = 0; i < config.getCombinationLength(); i++)
		{
			int keyboardNum = (int) combinationAsString.charAt(i) - '0'; // voir table de correspondance ASCII

			// Stockage du nombre du clavier en cours dans le tableau combination[], "i" vaudra successivement 0, 1, 2, 3
			combination[i] = keyboardNum;
		}

		return combination;
	}
}
