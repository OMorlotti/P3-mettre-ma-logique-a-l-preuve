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
        System.out.println("------------------------------------------------------------------------------");

        System.out.println("Veuillez choisir un mode : 1- Défenseur, 2- Challenger, 3- Duel, 4- Quitter");

        Scanner sc = new Scanner(System.in);

        try
        {
            int choiceOfGameMode = sc.nextInt();

            AbstractMode mode;

            int result;

            switch(choiceOfGameMode)
            {
                case 1:
                    m_logger.info("Nouvelle partie en mode \"défenseur\"");
                    System.out.println("Vous avez choisi le mode \"défenseur\"\nBon jeux !\n");
                    mode = new ModeDefender(m_config);
                    result = mode.start();
                    break;

                case 2:
                    m_logger.info("Nouvelle partie en mode \"challenger\"");
                    System.out.println("Vous avez choisi le mode \"challenger\"\nBon jeux !\n");
                    mode = new ModeChallenger(m_config);
                    result = mode.start();
                    break;

                case 3:
                    m_logger.info("Nouvelle partie en mode \"duel\"");
                    System.out.println("Vous avez choisi le mode \"duel\" !\nBon jeux !\n");
                    mode = new ModeDual(m_config);
                    result = mode.start();
                    break;

                case 4:
                    System.out.println("Bye !");
                    return false;

                default:
                    System.out.println("Vous devez choisir un mode entre les 4 proposés :-)\nMerci de ressaisir votre choix !");
                    result = -99999;
            }

            switch(result)
            {
                case AbstractMode.AI_WON:
                    m_logger.info("L'IA a gagnée");
                    System.out.println("L'IA a gagnée ;-)\n");
                    break;
                case AbstractMode.HUMAN_WON:
                    m_logger.info("Le joueur a gagné");
                    System.out.println("Le joueur a gagné :-)\n");
                    break;
                case AbstractMode.NOBODY_WON:
                    m_logger.info("Tout le monde a perdu");
                    System.out.println("Oups... Tout le monde a perdu :-(\n");
            }
        }
        catch(InputMismatchException e)
        {
            System.out.println("Vous devez choisir un mode entre les 4 proposés :-)\nMerci de ressaisir votre choix !");
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
