package xyz.morlotti.escapegame.user;

import java.util.Scanner;

import xyz.morlotti.escapegame.Config;

public class Human extends AbstractUser
{
	public Human(Config config)
	{
		super(config);
	}

	public String getCombinationAsString(int[] comparison)
	{
		String newCombinationAsString = new Scanner(System.in).next();

		return newCombinationAsString;
	}
}
