package xyz.morlotti.escapegame;

import java.util.Properties;

public class Config {
    private boolean developerMode = false;
    private int combinationLength = 4;
    private int combinationNumberOfTry = 10;

    public void load() {
        try {
            Properties p = new Properties();
            p.load(getClass().getResourceAsStream("/escapeGame.properties"));

            developerMode = Boolean.parseBoolean(p.getProperty("developerMode"));
            combinationLength = Integer.parseInt(p.getProperty("combinationLength"));
            combinationNumberOfTry = Integer.parseInt(p.getProperty("combinationNumberOfTry"));
        } catch (Exception e) {
            System.out.println("Lecture impossible du fichier 'escapeGame.properties'");
        }
    }

    public boolean isDeveloperMode() {
        return developerMode;
    }

    public int getCombinationLength() {
        return combinationLength;
    }

    public int getCombinationNumberOfTry() {
        return combinationNumberOfTry;
    }
}
