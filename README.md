#Battle Ship Game

##Instructions
* Install JDK 8+
* Import as mvn project
* TestBattle has test cases that checks various unit tests.

##API
#### ShipBlock class
```$xslt
Represents each cell of the ship.
Lives will be initialised based on ship type, lives = 2 for Q type and 1 for P type.
A block is considered destroyed when lives<1
```

#### Ship class
```$xslt
Ship consists of a grid of blocks.
A ship is considered sunk when health<1.
Health is initialised to number of blocks.  
```
#### BattleArea class
``` 
Each player gets his own battle area.
It holds set of actions in a queue.
BattleArea is considered as lost when all ships are sunk.
```
#### BattleShipGame class
```$xslt
Initialisation of game from file input/console.
This handles the logic of taking next action and firing.
```
