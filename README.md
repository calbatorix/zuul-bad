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
# Mode d'emploi
