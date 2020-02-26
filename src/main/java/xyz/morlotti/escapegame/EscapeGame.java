package xyz.morlotti.escapegame;

import xyz.morlotti.escapegame.mode.AbstractMode;
import xyz.morlotti.escapegame.mode.ModeChallenger;
import xyz.morlotti.escapegame.mode.ModeDefender;
import xyz.morlotti.escapegame.mode.ModeDual;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EscapeGame {
    public final Config config = new Config();

    public static void main(String[] args) {
        new EscapeGame().start();

//      EscapeGame escapeGame = new EscapeGame();
//      escapeGame.start();

        System.exit(0);
    }

    public boolean showMenu() {
        System.out.println("Veuillez choisir un mode : 1- Défenseur, 2- Challenger, 3- Duel, 4- Quitter");

        Scanner sc = new Scanner(System.in);

        try {
            int choiceOfGameMode = sc.nextInt();

            AbstractMode mode;

            int result;

            switch (choiceOfGameMode) {
                case 1:
                    System.out.println("Vous avez choisi le mode Défenseur\nBon jeux !\n");
                    mode = new ModeDefender(config);
                    result = mode.start();
                    break;

                case 2:
                    System.out.println("Vous avez choisi le mode Challenger\nBon jeux !\n");
                    mode = new ModeChallenger(config);
                    result = mode.start();
                    break;

                case 3:
                    System.out.println("Vous avez choisi le mode Duel !\nBon jeux !\n");
                    mode = new ModeDual(config);
                    result = mode.start();
                    break;

                case 4:
                    System.out.println("Bye !");
                    return false;

                default:
                    System.out.println("Vous devez choisir un mode entre les 4 proposés :-)\nMerci de ressaisir votre choix !");
                    result = -99999;
            }

            switch (result) {
                case AbstractMode.AI_WON:
                    System.out.println("L'IA a gagnée ;-)\n");
                    break;
                case AbstractMode.HUMAN_WON:
                    System.out.println("Le joueur a gagné :-)\n");
                    break;
                case AbstractMode.NOBODY_WON:
                    System.out.println("Oups... perdu :-(\n");
            }
        } catch (InputMismatchException e) {
            System.out.println("Vous devez choisir un mode entre les 4 proposés :-)\nMerci de ressaisir votre choix !");
        }

        return true;
    }

    public void start() {
        config.load();

        while (true) {
            if (showMenu() == false) {
                break;
            }
        }
    }
}
