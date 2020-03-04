package xyz.morlotti.escapegame;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;

public class Log
{
	static
	{
		try(LoggerContext context = (LoggerContext) LogManager.getContext(false))
		{
			context.setConfigLocation(Log.class.getResource("/log4j.xml").toURI());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public static Logger getLogger(String name, boolean isDevelopperMode)
	{
		Logger logger = LogManager.getLogger(name);

		if(isDevelopperMode)
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
