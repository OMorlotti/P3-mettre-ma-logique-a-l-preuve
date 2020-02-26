package xyz.morlotti.escapegame.user;

import xyz.morlotti.escapegame.Config;

public abstract class AbstractUser {
    protected final Config config;

    public AbstractUser(Config _config) {
        config = _config;
    }

    public abstract int[] generateCombination();

    public abstract int[] guessCombination(int[] comparison);
}
