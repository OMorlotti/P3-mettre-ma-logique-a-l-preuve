package xyz.morlotti.escapegame.mode;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;

import xyz.morlotti.escapegame.Log;
import xyz.morlotti.escapegame.Config;
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
    protected boolean playATurn(int attempt, AbstractUser user, int[] combination, int[] comparison)
    {
        int[] newCombination = user.guessCombination(comparison);

        if (newCombination.length == m_config.getCombinationLength())
        {
            int count = 0;

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
                    count++;
                }
            }

            System.out.println();

            if (count == m_config.getCombinationLength())
            {
                return true;
            }
        }

        return false;
    }
}
