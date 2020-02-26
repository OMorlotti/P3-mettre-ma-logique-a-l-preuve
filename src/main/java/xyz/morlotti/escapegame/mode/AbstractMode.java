package xyz.morlotti.escapegame.mode;

import java.util.Random;
import java.util.Scanner;

import xyz.morlotti.escapegame.Config;
import xyz.morlotti.escapegame.user.AbstractUser;

abstract public class AbstractMode
{
    public static final int NOBODY_WON = 0;
    public static final int HUMAN_WON = 1;
    public static final int AI_WON = 2;

    protected final Config m_config;

    public AbstractMode(Config config)
    {
        m_config = config;
    }

    protected int[] readCombination()
    {
        String combinationAsString;

        do
        {
            System.out.println("Veuillez saisir la combinaison à " + m_config.getCombinationLength() + " chiffres à faire deviner à l'IA:");

            combinationAsString = new Scanner(System.in).nextLine();

        } while(combinationAsString.length() != m_config.getCombinationLength());

        // Création d'un tableau qui contient autant d'entrées que ce que retourne la méthode getCombinationLength()
        int[] combination = new int [m_config.getCombinationLength()];

        // Génération d'autant de chiffres que ce que retourne la méthode getCombinationLength()
        for (int i = 0; i < m_config.getCombinationLength(); i++ )
        {
            int keyboardNum = (int) combinationAsString.charAt(i) - 48; // voir table de correspondance ASCII

            // Stockage du nombre du clavier en cours dans le tableau combination[], "i" vaudra successivement 0, 1, 2, 3
            combination[i] = keyboardNum;
        }

        return combination;
    }

    protected int[] generateCombination()
    {
        System.out.println("Génération de la combinaison à " + m_config.getCombinationLength() + " chiffres à faire deviner au joueur:");

        int low = 0;
        int high = 10;

        // Générateur de nombres aléatoires
        Random numberChosenByRandom = new Random();

        // Création d'un tableau qui contient autant d'entrées que ce que retourne la méthode getCombinationLength()
        int[] combination = new int [m_config.getCombinationLength()];

        // Génération d'autant de chiffres que ce que retourne la méthode getCombinationLength()
        for (int i = 0; i < m_config.getCombinationLength(); i++ )
        {
            int randomNum = numberChosenByRandom.nextInt(high - low) + low;

            // Stockage du nombre aléatoire en cours dans le tableau combination[], "i" vaudra successivement 0, 1, 2, 3
            combination[i] = randomNum;
            System.out.print(randomNum);
        }

        System.out.println();

        return combination;
    }

    protected int[] generateComparison()
    {
        int[] combinationComparison = new int[m_config.getCombinationLength()];

        for (int i = 0; i < combinationComparison.length; i++)
        {
            combinationComparison[i] = 0;
        }

        return combinationComparison;
    }

    protected boolean playATurn(AbstractUser user, int[] combination, int[] comparison)
    {
        String newCombinationAsString = user.getCombinationAsString(comparison);

        if (newCombinationAsString.length() == m_config.getCombinationLength())
        {
            int count = 0;

            for (int j = 0; j < m_config.getCombinationLength(); j++)
            {
                int numberChosenByUser = (int) newCombinationAsString.charAt(j) - 48; // voir table de correspondance ASCII

                /**/ if(numberChosenByUser > combination[j])
                {
                    comparison[j] = -1;
                    System.out.print("-");
                }
                else if(numberChosenByUser < combination[j])
                {
                    comparison[j] = +1;
                    System.out.print("+");
                }
                else
                {
                    comparison[j] = 0;
                    System.out.print("=");
                    count++;
                }
            }

            System.out.println();

            if (count == m_config.getCombinationLength())
            {
                return true;
            }
        }
        else
        {
            System.out.println("Mauvaise longueur de combinaison ! Vous avez perdu un tour ! ;-)");
        }

        return false;
    }

    public abstract int start();
}
