package xyz.morlotti.escapegame;

import java.util.Properties;

public class Config
{
    private boolean developerMode = false;
    private int combinationLength = 4;
    private int combinationNumberOfTry = 10;
    private int smartPercent = 50;

    public void load()
    {
        try
        {
            Properties p = new Properties();
            p.load(getClass().getResourceAsStream("/escapeGame.properties"));

            developerMode = Boolean.valueOf(p.getProperty("developerMode"));
            combinationLength = Integer.valueOf(p.getProperty("combinationLength"));
            combinationNumberOfTry = Integer.valueOf(p.getProperty("combinationNumberOfTry"));
            smartPercent = Integer.valueOf(p.getProperty("smartPercent"));
        }
        catch(Exception e)
        {
           System.out.println("Lecture impossible du fichier 'escapeGame.properties'");
        }
    }

    public boolean isDeveloperMode()
    {
        return developerMode;
    }

    public int getCombinationLength()
    {
        return combinationLength;
    }

    public int getCombinationNumberOfTry()
    {
        return combinationNumberOfTry;
    }

    public int getSmartPercent()
    {
        return smartPercent;
    }
}
