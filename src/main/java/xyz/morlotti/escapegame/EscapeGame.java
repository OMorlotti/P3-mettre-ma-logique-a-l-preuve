package xyz.morlotti.escapegame;

import org.apache.logging.log4j.Logger;

import xyz.morlotti.escapegame.mode.AbstractMode;
import xyz.morlotti.escapegame.mode.ModeChallenger;
import xyz.morlotti.escapegame.mode.ModeDefender;
import xyz.morlotti.escapegame.mode.ModeDual;

import java.util.Scanner;
import java.util.InputMismatchException;

public class EscapeGame
{
    private Logger m_logger;

    public final Config m_config = new Config();

    public boolean showMenu()
    {
        System.out.println(LogMessage.SEPARATOR);
        System.out.println(LogMessage.MENU_PROMPT);

        Scanner sc = new Scanner(System.in);

        try
        {
            int choiceOfGameMode = sc.nextInt();

            AbstractMode mode;

            int result;

            switch(choiceOfGameMode)
            {
                case 1:
                    m_logger.info(LogMessage.NEW_DEFENDER);
                    System.out.println(LogMessage.NEW_DEFENDER_2);
                    mode = new ModeDefender(m_config);
                    result = mode.start();
                    break;

                case 2:
                    m_logger.info(LogMessage.NEW_CHALLENGER);
                    System.out.println(LogMessage.NEW_CHALLENGER_2);
                    mode = new ModeChallenger(m_config);
                    result = mode.start();
                    break;

                case 3:
                    m_logger.info(LogMessage.NEW_DUAL);
                    System.out.println(LogMessage.NEW_DUAL_2);
                    mode = new ModeDual(m_config);
                    result = mode.start();
                    break;

                case 4:
                    System.out.println(LogMessage.BYE);
                    return false;

                default:
                    System.out.println(LogMessage.BAD_CHOICE);
                    result = -99999;
            }

            switch(result)
            {
                case AbstractMode.AI_WON:
                    m_logger.info(LogMessage.AI_WON);
                    System.out.println(LogMessage.AI_WON_2);
                    break;

                case AbstractMode.HUMAN_WON:
                    m_logger.info(LogMessage.HUMAN_WON);
                    System.out.println(LogMessage.HUMAN_WON_2);
                    break;

                case AbstractMode.NOBODY_WON:
                    m_logger.info(LogMessage.NOBODY_WON);
                    System.out.println(LogMessage.NOBODY_WON_2);
            }
        }
        catch(InputMismatchException e)
        {
            System.out.println(LogMessage.BAD_CHOICE);
        }

        return true;
    }

    public void start()
    {
        m_config.load();

        m_logger = Log.getLogger("EscapeGame", m_config.isDeveloperMode());

        while(true)
        {
            if(!showMenu())
            {
                break;
            }
        }
    }

    public static void main(String[] args)
    {
        new EscapeGame().start();

//      EscapeGame escapeGame = new EscapeGame();
//      escapeGame.start();

        System.exit(0);
    }
}
