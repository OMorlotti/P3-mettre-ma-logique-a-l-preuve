package xyz.morlotti.escapegame;

import java.util.Properties;

import org.apache.logging.log4j.Logger;

public class Config
{
    private boolean m_developerMode = false;
    private int m_combinationLength = 4;
    private int m_combinationNumberOfTry = 10;
    private int m_smartPercent = 50;

    // Lecture de la configuration du jeux dans le fichier "escapeGame.properties"
    public void load()
    {
        try
        {
            // chargement du fichier escapeGame.properties
            Properties p = new Properties();
            p.load(getClass().getResourceAsStream("/escapeGame.properties"));

            // lecture des paramètres
            m_developerMode = Boolean.parseBoolean(p.getProperty("developerMode"));
            m_combinationLength = Integer.parseInt(p.getProperty("combinationLength"));
            m_combinationNumberOfTry = Integer.parseInt(p.getProperty("combinationNumberOfTry"));
            m_smartPercent = Integer.parseInt(p.getProperty("smartPercent"));
        }
        catch(Exception e) // Si le fichier ne peut pas être lu
        {
           System.out.println(LogMessage.CANNOT_READ_CONF_FILE);
        }

        // Ecriture des paramètres courants dans le fichier de log

        Logger logger = Log.getLogger("Config", m_developerMode);

        logger.info("combinationLength: " + m_combinationLength);
        logger.info("combinationNumberOfTry: " + m_combinationNumberOfTry);
        logger.info("smartPercent: " + m_smartPercent);
    }

    // Définition des getters

    public boolean isDeveloperMode()
    {
        return m_developerMode;
    }

    public int getCombinationLength()
    {
        return m_combinationLength;
    }

    public int getCombinationNumberOfTry()
    {
        return m_combinationNumberOfTry;
    }

    public int getSmartPercent()
    {
        return m_smartPercent;
    }
}
