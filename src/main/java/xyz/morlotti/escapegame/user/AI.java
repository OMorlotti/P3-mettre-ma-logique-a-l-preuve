package xyz.morlotti.escapegame.user;

import xyz.morlotti.escapegame.Config;

import java.util.Random;

public class AI extends AbstractUser {
    protected final int[] m_lastValues;
    private final int START = 0;
    private final int STOP = 10;
    private final int CENTER = (STOP - START) / 2;
    private final int[] m_startValues;
    private final int[] m_stopValues;

    public AI(Config config) {
        super(config);

        int length = config.getCombinationLength();

        m_startValues = new int[length];
        m_stopValues = new int[length];
        m_lastValues = new int[length];

        reset();
    }

    public void reset() {
        for (int i = 0; i < config.getCombinationLength(); i++) {
            m_startValues[i] = START;
            m_stopValues[i] = STOP;
            m_lastValues[i] = CENTER;
        }
    }

    @Override
    public int[] generateCombination() {
        System.out.println("Génération de la combinaison à " + config.getCombinationLength() + " chiffres à faire deviner au joueur:");
        int[] combination = randomCombination();
        System.out.println();
        return combination;
    }

    protected int[] randomCombination() {
        int low = 0;
        int high = 10;

        // Générateur de nombres aléatoires
        Random numberChosenByRandom = new Random();

        // Création d'un tableau qui contient autant d'entrées que ce que retourne la méthode getCombinationLength()
        int[] combination = new int[config.getCombinationLength()];

        // Génération d'autant de chiffres que ce que retourne la méthode getCombinationLength()
        for (int i = 0; i < config.getCombinationLength(); i++) {
            int randomNum = numberChosenByRandom.nextInt(high - low) + low;

            // Stockage du nombre aléatoire en cours dans le tableau combination[], "i" vaudra successivement 0, 1, 2, 3
            combination[i] = randomNum;
            System.out.print(randomNum);
        }
        return combination;
    }

    public int[] guessCombination(int[] comparison) {
        int[] newCombination = new int[config.getCombinationLength()];

        for (int i = 0; i < config.getCombinationLength(); i++) {
            /**/
            if (comparison[i] < 0) {
                m_stopValues[i] = m_lastValues[i];
                m_lastValues[i] = (m_stopValues[i] + m_startValues[i]) / 2;
            } else if (comparison[i] > 0) {
                m_startValues[i] = m_lastValues[i];
                m_lastValues[i] = (m_stopValues[i] + m_startValues[i]) / 2;
            }
            newCombination[i] = m_lastValues[i];
        }

        return newCombination;
    }
}
