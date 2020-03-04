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

    protected int[] generateComparison()
    {
        int[] combinationComparison = new int[m_config.getCombinationLength()];

        for (int i = 0; i < combinationComparison.length; i++)
        {
            combinationComparison[i] = 0; // simple rappel que le tableau par défaut contient des 0 et que cette boucle pourrait être supprimée
        }

        return combinationComparison;
    }

    protected boolean playATurn(AbstractUser user, int[] combination, int[] comparison)
    {
        int[] newCombination = user.guessCombination(comparison);

        if (newCombination.length == m_config.getCombinationLength())
        {
            int count = 0;

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

    public abstract int start();
}
