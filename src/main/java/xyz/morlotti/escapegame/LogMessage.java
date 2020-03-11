package xyz.morlotti.escapegame;

public class LogMessage
{
	public static final String SEPARATOR = "------------------------------------------------------------------------------";

	public static final String MENU_PROMPT = "Veuillez choisir un mode : 1- Défenseur, 2- Challenger, 3- Duel, 4- Quitter";

	public static final String NEW_DEFENDER = "Nouvelle partie en mode \"défenseur\"";
	public static final String NEW_DEFENDER_2 = "Vous avez choisi le mode \"défenseur\"\nBon jeux !\n";
	public static final String START_DEFENDER = "Démarrage du mode Défenseur";

	public static final String NEW_CHALLENGER = "Nouvelle partie en mode \"challenger\"";
	public static final String NEW_CHALLENGER_2 = "Vous avez choisi le mode \"challenger\"\nBon jeux !\n";
	public static final String START_CHALLENGER = "Démarrage du mode Challenger";

	public static final String NEW_DUAL = "Nouvelle partie en mode \"duel\"";
	public static final String NEW_DUAL_2 = "Vous avez choisi le mode \"duel\" !\nBon jeux !\n";
	public static final String START_DUAL = "Démarrage du mode Duel";

	public static final String BYE = "Bye !";

	public static final String BAD_CHOICE = "Vous devez choisir un mode entre les 4 proposés :-)\nMerci de ressaisir votre choix !";

	public static final String HUMAN = "Joueur:";
	public static final String AI = "IA:";

																// %d pour remplacer par un "int" avec "String.format()"
																// %s pour remplacer par un "String" avec "String.format()"
	public static final String COMBINATION_HUMAN = "Combinaison à %d chiffres à faire deviner à l'utilisateur : %s";
	public static final String COMBINATION_AI = "Combinaison à %d chiffres à faire deviner à l'IA : %s";

	public static final String AI_WON = "L'IA a gagnée";
	public static final String AI_WON_2 = "L'IA a gagnée ;-)\n";

	public static final String HUMAN_WON = "Le joueur a gagné";
	public static final String HUMAN_WON_2 = "Le joueur a gagné :-)\n";

	public static final String NOBODY_WON = "Tout le monde a perdu";
	public static final String NOBODY_WON_2 = "Oups... Tout le monde a perdu :-(\n";

	public static final String ATTEMPT = "Tentative n°%d";

	public static final String COMBINATION_WAS = "La combinaison à trouver était %d";
}
