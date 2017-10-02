# Présentation du jeu
## Auteur :
Baptiste Espinasse
## Thème  :
Une équipe d'aventurier doivent retrouver un antidote volé par un savant fou à l’intérieur d'un donjon
## Résumé du scénario complet : 
## Plan complet
## Scénario détaillé
## Détail des lieux, items, personnages
## Situations gagnantes et perdantes
## Eventuellement énigmes, mini-jeux, combats, etc.
## Commentaires (ce qui manque, reste à faire, ...)
# Réponses aux exercices
## Exercice 7.1 (ex 'zuul-bad')
## Exercice 7.1.1 : Choisir un thème ...
## Exercice 7.2.1 : La classe Scanner ... /*todo: explication*/
## Faire l'exercice 7.3 (scénario libre)
## Exercice 7.3.1 : Écrire dans le Rapport ...
## Exercice 7.3.2 : Dessiner un plan du jeu ...
##  Exercice 7.4 (zuul-v1, rooms, exits)
## Exercice 7.5 (printLocationInfo)
Après avoir écrit les méthodes printWelcome() et goRoom(), on remarque que tous deux exécutent la même suite de fonctions. Ceci est une duplication de code.
Pour éviter cette duplication de code, on peut créer une méthode printLocationInfo() qui effectuera cette même suite de fonctions.
Ensuite, nous appellerons cette procédure dans printWelcome et goRoom.
```java
private void printLocationInfo()
{
    System.out.println("You are in the "+ this.aCurrentRoom.getDescription());
    System.out.print("Exit(s):");
    if(this.aCurrentRoom.getExit("north") != null) System.out.print("north ");
    if(this.aCurrentRoom.getExit("south") != null) System.out.print("south ");
    if(this.aCurrentRoom.getExit("east") != null) System.out.print("east ");
    if(this.aCurrentRoom.getExit("west") != null) System.out.print("west");
}
```
## Exercice 7.6 (getExit)
Nous souhaitons ajouter deux nouveaux types de direction pour sortir d'une pièce tels que "haut" et "bas". Malheureusement, lors de la première création de la class Room les attributs de directions étaient publique aux autres class. Ce qui a permis à la class Game d'utiliser un accès à ceci très simple. Mais maintenant que nous voulons modifier la class Room cela va perturber le bon fonctionnement de Game, car ces deux class ont un couplage fort.
Pour remédier à ce problème nous allons renforcer la séparation de ces deux class en rendant les attributs prive.
Ce qui oblige de créer un getteur a la class room.

```java
public Room getExit(String pDirection)
{
    if(vDirection.equals("nord")) return this.aNorthExit;
    if(vDirection.equals("south")) return this.aSouthExit;
    if(vDirection.equals("east")) return this.aEastExit;
    if(vDirection.equals("west")) return this.aWestExit;
}
```
Maintenant, il faut également modifie la class Game. Qui a maintenant besoin des getters pour accédait au champ de Room.
Au lieu d'écrire :

```java
vNextRoom = this.aCurrentRoom.eastExit;
```
il faut :
```java
vNextRoom = this.aCurrentRoom.getExit("east");
```
Au premier abord rendre privé les attributs peut sembler générer une difficulté en plus, mais sur le long terme, cela facilite la modification du code.
Par exemple le code suivant :

```java
        Room vNextRoom = null;
        String vDirection =pCommand.getSecondWord();

        if( vDirection.equals("north") ) vNextRoom = this.aCurrentRoom.aNorthExit;
        if( vDirection.equals("south") ) vNextRoom = this.aCurrentRoom.aSouthExit;
        if( vDirection.equals("east") ) vNextRoom = this.aCurrentRoom.aEastExit;
        if( vDirection.equals("west") ) vNextRoom = this.aCurrentRoom.aWestExit;
        
```
Devient beaucoup plus court, et permet d'ajouté une nouvelle direction de sortie avec aucune ligne a modifiée dans la class Game:
```java
Room vNextRoom = this.aCurrentRoom.getExit(vDirection);
```
## Exercice 7.7 (getExitString)
Dans la même optique que dans la question précédente, nous allons créer une méthode getExitString() dans la class Room.
Elle aura pour but de simplifier la méthode printLocationInfo, cette dernière n'aura plus qu'à afficher la String de retour de getExitString().
Ainsi si de nouvelles directions de sortie sont ajoutées cela n'aura pas d'impact sur printLocationInfo().
À noter qu’il faudra tout de même modifier getExitSting() si une nouvelle direction est ajoutée pour l'instant.

```java    
public Room getExitString()
{
    String vExitString="Exits: ";
    if(this.aNorthExit != null) vExitString += "north";
    if(this.aSouthExit != null) vExitString += "south";
    if(this.aEastExit != null) vExitString += "east";
    if(this.aWestExit != null) vExitString += "west";
    return vExitString;
}
```

```java
private void printLocationInfo()
{
    System.out.println("You are in the "+ this.aCurrentRoom.getDescription());
    System.out.println(this.aCurrentRoom.getExitString());
}
```
## Exercice 7.8 (HashMap, setExit)
Dans le but d'avoir de créé des sorti différente pour chaque objet Room. Nous allons remplacer les 4 attributs de direction par une table de hachage. Ainsi on ne sera plus limitée à créer des sorties correspondant aux quatre points cardinaux.
Et comme la class Room a déformé une encapsulation forte grâce au travail précédent, les modifications qui vont lui être apporté n'auraient aucune répercussion sur les autres class (correction : il faut tout de même revoir les instanciations des objets Room dans les autres class).

```java
import java.util.HashMap;

public class Room
{
    private String aDescription;
    private HashMap<String, Room> aExits;

    public Room(final String pDescription)
    {
        this.aDescription = pDescription;
        aExits = new HashMap<>();
    }

    public String getDescription(){return this.aDescription;}

    public Room getExit(String pDirection)
    {
        return this.aExits.get(pDirection);
    }

    public String getExitString()
    {
        String vExitString="Exits: ";
        if(this.aNorthExit != null) vExitString += "north";
        if(this.aSouthExit != null) vExitString += "south";
        if(this.aEastExit != null) vExitString += "east";
        if(this.aWestExit != null) vExitString += "west";
        return vExitString;
    }
    
    public void setExit(final String pDirection, final Room pNeighbor)
    {
        this.aExits.put(pDirection, pNeighbor);
    }
}
```
Une table de hachage simplement est un tableau ou les indices ne sont pas des entiers de 0 à N-1, mais des objets que l'on nommera "key".
Dans notre cas les "key" sont les noms de sortie de la pièce.
Pour utiliser ce paquetage, il faut ajouter :

`java
 import java.util.HashMap;
`
Ainsi on peut instancier des objets de type Hashmap et utiliser les méthodes qui sont déjà créées dans le paquetage.
Le constructeur naturel subit lui aussi des modifications pour correspondre aux attributs de la class.

## Exercice 7.9 (keySet)
Il faut mettre la méthode getExitString à jour.
```java
 public String getExitString()
 {
    String vReturnString = "Exits:";
    Set<String> vKeys = this.aExits.keySet();
    for(String vExit : vKeys)
    {
        vReturnString += " "+vExit;
    }
    return vReturnString;
}
```
## Exercice 7.10 (getExitString CCM?)
Le but de la méthode getExitString est de retourner sous forme d'un String toutes les sorties possibles pour la commande go.
Toutes ces directions de sortie sont les "key" de la table de hachage aExits.
L'interface Set est une collection d'objets dans lequel on ne peut pas avoir de doublons.
La méthode keySet() permet de retourner un objet de type set<> représentant la liste des clés contenues dans la collection.

Donc en faisant `java Set<String> vKeys = this.aExits.keySet();`On stock toute les "key" (direction de sortie de la pièce this.) dans la collection de types Set<String> vKeys.
La boucle for each :
:
```java
for(type variable : collection)
{
 /*instruction*/
}
```
Va effectuer les instructions sur les objets de la collection un par un dans l'ordre.
## Exercice 7.11 (getLongDescription)
Pour encore réduire n'encapsulation de la class Room et en prévision de modification future telle que l'ajout de personnage et objet dans les pièces. Il faut ajouter une nouvelle méthode qui pourra fournir une description de la pièce et de tout ce qui s'y trouve.
```java
public String getLongDescription()
{   
	return " You are in " + this.aDescription + ".\n" + getExitString();
}
```
## Exercice 7.12 optionnel (diagramme objet) /*todo*/
## Exercice 7.13 optionnel (go => changement) /*todo*/
## Exercice 7.14 (look)
Depuis le début du projet nous nous jamais soucier des problèmes de couplage implicite.
Un couplage implicite est une situation ou une class dépend des informations d'une autre, mais à la différence d'un couplage normal, celui-ci ne produira pas d'erreur de compilation.
Ce problème s'illustre dans cet exercice par l'ajout d'une nouvelle commande (look) dans le jeu.

```java
private void look()
{ 
    System.out.println(this.aCurrentRoom.getLongDescription());
}
```
Si nous ajoutons seulement la méthode look dans la class Game il n'y aura pas d'erreur de compilation. En revanche l'utilisateur ne pourra jamais utiliser cette commande, car elle n'est pas connue de la class CommanWords.
```java
private static final String[] sValidCommands = {
    "go", "quit", "help", "look"
};
```
Et de la méthode qui interprète les commandes dans la class Game.
```java
public boolean processCommand(final Command pCommand)
{
    if(pCommand.isUnknown()) System.out.println("I don't know what you mean...");
    if (pCommand.getCommandWord().equals("help"))  printHelp();
    if (pCommand.getCommandWord().equals("go"))    goRoom(pCommand);
    if (pCommand.getCommandWord().equals("look"))  look();
    if (pCommand.getCommandWord().equals("quit"))
    { 
        Command vCommand = new Command(pCommand.getSecondWord(), null);
        return quit(vCommand);
    }
    return false;
}
```
## Exercice 7.14.1 optionnel (look item) /*todo*/
## Exercice 7.15 (eat)
Pour cette commande il faut effectuée les mêmes modifications que pour l’exercice précèdent.
Dans la class Game

```java
private void eat()
{
    System.out.println("You have eaten now and you are not hungry any more.");
}
```
Ajout de l'interprétation de la commande par la méthode processCommand()
```java
if (pCommand.getCommandWord().equals("eat"))   eat();
```
et dans la class CommandWords
```java
private static final String[] sValidCommands = {
    "go", "quit", "help", "look","eat"
};
```
## Exercice 7.16 (showAll, showCommands)
Dans les deux exercices précèdent il a été oublie d'ajouter à la méthode help() d'ajouter les commandes look et eat.
C'est un problème de couplage implicite.
Pour que ce problème n'arrive plus on va ajouter une méthode showAll() qui affichera la liste de toutes les commandes répertorier dans sValidCommands de la class CommandWords.
 
```java
public void showAll()
{
    for(String vCommand : sValidCommands)
    {
        System.out.print(vCommand + "    ");
    }
    System.out.println();
}
```
Il faut maintenant pouvoir appeler cette méthode dans printHelp(), mais comme nous ne souhaitons pas augmenter le degré de couplage dans l'application, il ne faut pas faire de lien direct en la class Game et CommandWords.
Il faut donc faire communiquer ComandWords avec Parser puis Parser avec Game

```java
public void showCommands()
{
    aValidCommands.showAll();
}
```
```java
private void printHelp()
{
    System.out.println("You are lost. You are alone.");
    System.out.println("You wander around at the university.");
    System.out.println();
    System.out.println("Your command words are:");
    aParser.showCommands();
}
```
## Exercice 7.17 (optionnel) (changer Game ?) /*todo*/
# Mode d'emploi
