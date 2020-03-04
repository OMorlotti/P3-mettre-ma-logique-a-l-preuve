package xyz.morlotti.escapegame;

import java.util.Properties;

public class Config
{
    private boolean m_developerMode = false;
    private int m_combinationLength = 4;
    private int m_combinationNumberOfTry = 10;
    private int m_smartPercent = 50;

    public void load()
    {
        try
        {
            Properties p = new Properties();
            p.load(getClass().getResourceAsStream("/escapeGame.properties"));

            m_developerMode = Boolean.valueOf(p.getProperty("developerMode"));
            m_combinationLength = Integer.valueOf(p.getProperty("combinationLength"));
            m_combinationNumberOfTry = Integer.valueOf(p.getProperty("combinationNumberOfTry"));
            m_smartPercent = Integer.valueOf(p.getProperty("smartPercent"));
        }
        catch(Exception e)
        {
           System.out.println("Lecture impossible du fichier 'escapeGame.properties'");
        }
    }

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
