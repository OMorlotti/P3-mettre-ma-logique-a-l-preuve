package xyz.morlotti.escapegame;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;

public class Log
{
	public static Logger getLogger(String name, boolean isDeveloperMode) // récupère le logger dont le nom est "name"
	{
		Logger logger = LogManager.getLogger(name);

		if(isDeveloperMode)
		{
			Configurator.setLevel(name, Level.INFO);
		}
		else
		{
			Configurator.setLevel(name, Level.WARN);
		}

		return logger;
	}
}
