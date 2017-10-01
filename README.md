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
Aprés avoir ecrire les methodes printWelcome et goRoom,on remarque que toute deux execute la meme suite de fontion.Ceci est une duplation de code.
Pour eviter cette duplication de code on peut créé une methode printLocationInfo qui effectura cette meme suite de fonction.
Ensuite nous appellerons cette procedure dans printWelcome et goRoom.
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
Nous souhaitons ajoutée deux nouveaux type de direction pour sortir d'une piece tel que "haut" et "bas".Malheuresement lors de la premiere creation de la class Room les attribut de direction on etaient rendue publique au autre class.Ce qui à permit a la class Game d'utiliser un accées a ceci tres simple.Mais maintenant que nous voulons modifié la class Room cela va perturbée le bon fonctionement de Game car ces deux class on un couplage fort.
Pour remedier a ce probleme nous alons renforce la separtion de ces deux class en rendant les attribu prive.
Ce qui oblige de créé un getteur a la class room.
```java
public Room getExit(String pDirection)
{
    if(vDirection.equals("nord")) return this.aNorthExit;
    if(vDirection.equals("south")) return this.aSouthExit;
    if(vDirection.equals("east")) return this.aEastExit;
    if(vDirection.equals("west")) return this.aWestExit;
}
```
Maintenant il faut egalement modifie la class Game.Qui a maintenant besoin des getter pour accèdais au champ de Room.
Au lien d'ecrire :
```java
vNextRoom = this.aCurrentRoom.eastExit;
```
il faut :
```java
vNextRoom = this.aCurrentRoom.getExit("east");
```
Au premiere abord rendre privé les attribut peut semblée generer une difficultée en plus, mais sur le long terme, cela facilite la modification du code.
Par exemple le code suivant :
```java
        Room vNextRoom = null;
        String vDirection =pCommand.getSecondWord();

        if( vDirection.equals("north") ) vNextRoom = this.aCurrentRoom.aNorthExit;
        if( vDirection.equals("south") ) vNextRoom = this.aCurrentRoom.aSouthExit;
        if( vDirection.equals("east") ) vNextRoom = this.aCurrentRoom.aEastExit;
        if( vDirection.equals("west") ) vNextRoom = this.aCurrentRoom.aWestExit;
        
```
devient beaucoup plus court, et permet d'ajouté une nouvelle direction de sortie avec aucune ligne a modifiée dans la class Game:
```java
Room vNextRoom = this.aCurrentRoom.getExit(vDirection);
```
## Exercice 7.7 (getExitString)
Dans la meme optique que dans la question precedante, nous allons créée une methode getExitString dans la class Room.
Elle aura pour but de simplifier la methode printLocationInfo, cette derniere n'aura plus qu'a afficher la String de retour de getExitString.
Ainsi si de nouvelle direction de sortie sont ajoutée cela n'aura pas d'impacte sur printLocationInfo.
A noté que le il faudra tout de meme modifier getExitSting si une nouvelle direction est ajoutée pour l'intant.
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
Dans le but d'avoir de créé des sorti diferente pour chaque objet Room. Nous allons remplacé les 4 attributs de direction par une table de hachage.Ainsi on ne sear plus limitée à cree des sorite correspondant au quatre point cardinaux.
Et comme la class Room à desormée une encapsulation forte grace au travaux precedant, les modification qui vont lui etre apporte n'aurai aucune repercutation sur les autre class (correction : il faut tout de meme revoir les instanciation des objets Room dans les autres class).
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
Une table de hachage simplement est un tableau ou les indices ne sont pas des entiers de 0 à N-1 mais des objets que l'on nomera "key".
Dans notre cas les "key" sont les noms de sortie de la piece.
Pour utiliser ce paquetage il faut ajouter :
`java
 import java.util.HashMap;
`
Ainsi on peut instancier des objets de type Hashmap et utiliser les methodes qui sont deja créé dans le paquetage.
Le construteur naturel subit lui aussi des modifications pour correspondre aux atribut de la class.
## Exercice 7.9 (keySet)
Il faut mettre la methode getExitString à jour.
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
Le but de la methode getExitString est de retourner sous forme d'un String toute les sorties possible pour la commande go.
Toute ces direction de sortie sont les les "key" de la table de hachage aExits.
L'interface Set est une collection d'objet dans laquel on ne peut pas avoir de doublons.
La methode keySet() permet de retourner un objet de type set<> représentant la liste des clés contenues dans la collection.
Donc en faisant `java Set<String> vKeys = this.aExits.keySet();` On stock toute les "key" (direction de sortie de la piece this.) dans la collection de type Set<String> vKeys.
La boucle for each :
```java
for(type variable : collection)
{
 /*instruction*/
}
```
Va effectuer les instruction sur les objets de la collection un par un dans l'ordre.
## Exercice 7.11 (getLongDescription)
Pour encore reduire n'encapsulation de la class Room et en prevision de modification futur tel que l'jaout de personnage et objet dans les piece.Il faut ajouter une nouvelle methode qui pourra fournir une description de la piece et de tout de qui s'y trouve.
```java
public String getLongDescription()
{   
	return " You are in " + this.aDescription + ".\n" + getExitString();
}
```
## Exercice 7.12 optionnel (diagramme objet) /*todo*/
## Exercice 7.13 optionnel (go => changement) /*todo*/
## Exercice 7.14 (look)7
Depuis le debut du projet nous nous jamais soucier des probleme de couplage implicite.
Un couplage implicite est une situation ou une class depend des information d'une autre, mais a la difference d'un couplage normal, celui-ci ne produira pas d'erreur de compilation.
Ce probleme s'ilustre dans cette exercice par l'ajout d'une nouvelle commande (look) dans le jeu.
```java
private void look()
{ 
    System.out.println(this.aCurrentRoom.getLongDescription());
}
```
Si nous ajoutons seulement la methode look dans la class game il n'y aura pas d'erreur de compilation.En rechanche l'utisateur ne pourra jamais utiliser cette commande car elle n'est pas connu de la class CommanWords .
```java
private static final String[] sValidCommands = {
    "go", "quit", "help", "look"
};
```
et de la methode qui interprete les commande dans la class Game.
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
Pour cette cette commande il faut effectuée les meme mofication que pour l' exercice precedent.
Dans la class Game
```java
private void eat()
{
    System.out.println("You have eaten now and you are not hungry any more.");
}
```
Ajout de l'interpertation de la commande par le methode processCommand()
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
Dans les deux exercice precedent il a aite oublie d'ajouter a la methode help() d'ajouter les commande look et eat.
C'est un probleme de couplage implicite.
Pour que ce probleme n'arrive plus on va ajouter une methode showAll() qui affichera la liste de toute les commande repertorier dans sValidCommands de la class CommandWords. 
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
Il faut maintenant pouvoir appeler cette methode dans printHelp() mais comme nous ne souhaitons pas augmenter le degré de couplage dans l'application, il ne faut pas faire de lien directe en la class game et commandWords.
Il faut donc faire communiquer CoomandWords avec Parser puis Parser avec Game
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
