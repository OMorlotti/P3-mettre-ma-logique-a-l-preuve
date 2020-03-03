## Escape Game

Escape Game est une application de jeux du type *Mastermind* : le joueur et l'ordinateur jouent face à face selon le mode choisi par le joueur.

### 3 modes :

  - **Challenger** : le joueur doit deviner la combinaison choisie par l'ordinateur.
  - **Défenseur** : l'ordinateur doit deviner la combinaison choisie par le joueur.
  - **Duel** : le joueur et l'ordinateur choisissent chacun une combination que l'autre doit deviner. Le premier qui trouve la combinaison de l'autre, dans le temps imparti, gagne.

Le code-source est composé de deux packages :
  - `mode` : ìl contient la classe abstraite `AbstractMode`, dont hérite les 3 classes-filles `ModeChallenger`, `ModeDefender` et `ModeDual`.
             Ces 3 dernières gèrent chacune les fonctionnalités des modes présentés ci-dessus.
  - `user` : il contient, la classe abstraite `AbstractUser`, dont hérite les 2 classes-filles `AI` et `Human`.
             Ces 2 dernières gèrent les fonctionnalités liées à chaque type de joueur : l'humain et l'intelligence artificielle.

et trois classes :

  - `EscapeGame` : elle correspond au jeu lui-même avec la méthode point d'entrée `main`.
  - `Config` : elle gére la lecture du fichier de propriétés `escapeGame.properties`. Ce-dernier contient les paramètres de configuration de base du jeux (taille de la combinaison à trouver, nombre d'essai(s) et activation ou non du mode *Développeur*).
  - `Log` : elle assure l'affichage des messages avec le logger *Log4J*.


### Pré-requis

Ce qu'il est requis pour commencer avec votre projet...

  * Assurez-vous que  [Java 8](http://www.oracle.com/technetwork/java/javase/) et [Maven 3](http://maven.apache.org/) sont installés:
```bash
java -version
mvn -version
```

 * Compilation

```bash
mvn package
```

### Utilisation

```bash
java -Dfile.encoding=UTF-8 -classpath $HOME/.m2/repository/org/apache/logging/log4j/log4j-core/2.13.0/log4j-core-2.13.0.jar:$HOME/.m2/repository/org/apache/logging/log4j/log4j-api/2.13.0/log4j-api-2.13.0.jar -jar target/escapegame-1.0.0.jar

```

## Fabriqué avec

IntelliJ IDEA CE

## Auteur

Olivier Morlotti
