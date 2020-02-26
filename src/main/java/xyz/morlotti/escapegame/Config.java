package xyz.morlotti.escapegame;

import java.util.Properties;

public class Config
{
    private boolean developperMode = false;
    private int combinationLength = 4;
    private int combinationNumberOfTry = 10;

    public void load()
    {
        try
        {
            Properties p = new Properties();
            p.load(getClass().getResourceAsStream("/escapeGame.properties"));

            developperMode = Boolean.valueOf(p.getProperty("developperMode"));
            combinationLength = Integer.valueOf(p.getProperty("combinationLength"));
            combinationNumberOfTry = Integer.valueOf(p.getProperty("combinationNumberOfTry"));
        }
        catch(Exception e)
        {
           System.out.println("Lecture impossible du fichier 'escapeGame.properties'");
        }
    }

    public boolean isDevelopperMode() { return developperMode; }

    public int getCombinationLength()
    {
        return combinationLength;
    }

    public int getCombinationNumberOfTry()
    {
        return combinationNumberOfTry;
    }
}
