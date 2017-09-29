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
```java
```
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
