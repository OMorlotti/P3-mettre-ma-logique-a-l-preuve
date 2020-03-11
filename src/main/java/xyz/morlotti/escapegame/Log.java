package xyz.morlotti.escapegame;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;

public class Log
{
	// Récupère le logger dont le nom est "name"
	public static Logger getLogger(String name, boolean isDeveloperMode)
	{
		Logger logger = LogManager.getLogger(name);

		// Si "isDeveloperMode" est à "true", tout les messages sont affichés, sinon seulement les erreurs et les warnings le sont.
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
