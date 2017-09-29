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
`java
vNextRoom = this.aCurrentRoom.eastExit;
`
il faut :
`java
vNextRoom = this.aCurrentRoom.getExit("east");
`
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
`java
Room vNextRoom = this.aCurrentRoom.getExit(vDirection);
`
## Exercice 7.7 (getExitString)
```java
```
## Exercice 7.8 (HashMap, setExit)
```java
```
## Exercice 7.9 (keySet)
```java
```
## Exercice 7.10 (getExitString CCM?)
```java
```
# Mode d'emploi
