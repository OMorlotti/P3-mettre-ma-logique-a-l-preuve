package xyz.morlotti.escapegame.mode;

import java.util.Arrays;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;

import xyz.morlotti.escapegame.Log;
import xyz.morlotti.escapegame.Config;
import xyz.morlotti.escapegame.LogMessage;
import xyz.morlotti.escapegame.user.AbstractUser;

abstract public class AbstractMode
{
    public static final int NOBODY_WON = 0; // Personne n'a gagné
    public static final int HUMAN_WON = 1; // L'humain a gagné
    public static final int AI_WON = 2; // L'IA a gagné

    protected final Config m_config;
    protected final Logger m_logger;

    public AbstractMode(Config config)
    {
        m_config = config;

        m_logger = Log.getLogger("AbstractMode", config.isDeveloperMode());
    }

    // Méthode abstraite qui démarre une partie et retourne qui a gagné
    public abstract int start();

    // Genère un tableau de "CombinationLength" entrées initialisées à zéro.
    // Le résultat est utilisé par la méthode "playATurn" pour stocker la proximité entre la combinaison du joueur et
    // la combinaison à deviner. -> utilisée par la dichotomie
    protected int[] generateComparison()
    {
        int[] combinationComparison = new int[m_config.getCombinationLength()];

        for (int i = 0; i < combinationComparison.length; i++)
        {
            combinationComparison[i] = 0; // simple rappel que le tableau par défaut contient des 0 et que cette boucle pourrait être supprimée
        }

        return combinationComparison;
    }

    // Cette méthode effectue un tour de jeux. Elle évalue la proximité entre la combinaison du joueur et la combinaison à deviner.
    // Elle retourne "true" si la combinaison est trouvée, "false", sinon.
    protected boolean playATurnAI(int attempt, AbstractUser user, int[] combination, int[] comparison)
    {
        int[] newCombination = user.guessCombination(comparison);

        if (newCombination.length == m_config.getCombinationLength())
        {
            // La varibale "countNumberOfEquals" est déclarée à 0 puis incrémentée dès la condition atteinte
            int countNumberOfEquals = 0;

            m_logger.info("Tentative n°" + (attempt + 1) + ": " + Arrays.toString(newCombination));

            for (int j = 0; j < m_config.getCombinationLength(); j++)
            {
                comparison[j] = combination[j] - newCombination[j];

                /**/ if (comparison[j] < 0)
                {
                    System.out.print("-");
                }
                else if(comparison[j] > 0)
                {
                    System.out.print("+");
                }
                else
                {
                    System.out.print("=");
                    countNumberOfEquals++;
                }
            }

            System.out.println();

            // Quand "countNumberOfEquals" égale la valeur de "CombinationLength()", l'application retourne true :
            if (countNumberOfEquals == m_config.getCombinationLength())
            {
                return true;
            }
        }

        return false;
    }

    // Cette méthode effectue un tour de jeux. Elle évalue la proximité entre la combinaison du joueur et la combinaison à deviner.
    // Elle retourne "true" si la combinaison est trouvée, "false", sinon.
    protected boolean playATurnHuman(int attempt, AbstractUser user, int[] combination, int[] comparison)
    {
        int[] newCombination = user.guessCombination(comparison);

        if (newCombination.length == m_config.getCombinationLength())
        {
            int countNumberOfEquals = 0;

            // Détection de la tricherie (voir infra)
            boolean isCheating = false;

            m_logger.info("Tentative n°" + (attempt + 1) + ": " + Arrays.toString(newCombination));
            System.out.println("Tentative n°" + (attempt + 1) + ": " + Arrays.toString(newCombination));

            String comparisonString;

            do  {
                System.out.println(String.format(LogMessage.ENTER_COMBINATION_COMPARITION, m_config.getCombinationLength()));

                comparisonString = new Scanner(System.in).nextLine();

            } while(!comparisonString.matches("[-+=]{" + m_config.getCombinationLength() + "}"));

            System.out.println(comparisonString);

            for (int j = 0; j < m_config.getCombinationLength(); j++)
            {
                /**/ if (comparisonString.charAt(j) == '-')
                {
                    comparison[j] = -1;
                }
                else if(comparisonString.charAt(j) == '+')
                {
                    comparison[j] = +1;
                }
                else
                {
                    comparison[j] = 0;
                    countNumberOfEquals++;
                }

                // Calcul de la vraie différence entre la bonne combinaison et celle proposée par l'IA
                int diff = combination[j] - newCombination[j];

                // Calcul du signe (positif ou négatif)  de la différence calculée ci-dessus;
                int sign;

                /**/ if(diff < 0) {
                    sign = -1;
                }
                else if(diff > 0) {
                    sign = +1;
                }
                else {
                    sign = 0;
                }

                // Détecte la tricherie : si la vraie différence n'a pas le même signe que celui indiqué à l'utilisateur, il y a triche
                if(comparison[j] != sign)
                {
                    isCheating = true;
                }
            }

            if(isCheating)
            {
                System.out.println(LogMessage.CHEATING);
            }

            if (countNumberOfEquals == m_config.getCombinationLength())
            {
                return true;
            }
        }

        return false;
    }
}
