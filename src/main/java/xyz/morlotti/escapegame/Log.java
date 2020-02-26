package xyz.morlotti.escapegame;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class Log
{
	public static Logger getLogger(String name, boolean isDevelopperMode)
	{
		Logger logger = LogManager.getLogger(name);

		if(isDevelopperMode)
		{
			Configurator.setLevel(name, Level.DEBUG);
		}
		else
		{
			Configurator.setLevel(name, Level.WARN);
		}

		return logger;
	}
}
