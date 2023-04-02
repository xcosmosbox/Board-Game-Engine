### Design Rationale

#### Introduction

This program is a Nine Men's Morris game running locally. As a board game, we need to display its board and pieces, and provide interactive interfaces for players. Due to the high-level feature our group chose was the AI player. Therefore, we will increase the battle mode between human players and AI players.

Next, we will introduce our design in two parts.

The first part is to explain the domain model above. Explain that the entities in the picture have those relationships.

The second part is Design Principle. In this section, we will elaborate on which principles we follow? How do we follow it? Why do we follow these principles?

The third part is Design Pattern. In this part, we will elaborate on which design patterns we plan to implement? How did we achieve them? Also illustrate the design with some pictures with more classes. And why we choose such a design patter.



#### Entity Relationship Description

#####  Game

Game entity is the entrance of the entire game. It is responsible for controlling the overall flow of the game, such as building the game, starting the game, and ending the game.

Game needs to rely on Display for output.

The relationship between Game and Board is Composition, because without a game, the board is meaningless.

Game contains Player and Turn. But Game needs to operate Player through Turn. The specific reasons will be explained in the subsequent sections.



##### Display

Display is the output interface of the entire game. The content that needs to be output in the entire game needs to be output through this unified interface to maintain the formatting of the overall GUI.



##### Board

Board and Position are in the relationship of Composition. Because without the Board, the Position loses its meaning. A Board will load 24 Positions. The Board is observed by the Player.



##### Position

Position will hold Token. But the number of Tokens is limited. Therefore, Position may or may not have a Token.



##### Token

Tokens will be placed on the board. At the beginning of the game, each player holds 9 Tokens. Players will place the Tokens in their hands on the board in turn. During the game, the player's piece may be "milling" by another player, at which point the player will lose control of the Token.



##### Player

Player is controlled by Turn. When the Turn informs the Player that the player needs to play the game, the Player can perform the current round of game operations. Player needs to observe the Board and feed back the observed Board situation to PlayerState. Player can create Command.



##### PlayerState

PlayerState is an entity that controls player state. It will share the Board situation fed back by the player, and decide whether the Player's state needs to be changed according to the rules of the game. PlayerState must be held by Player, and a Player corresponds to a PlayerState.



##### Command

Command is created by the player, and the Game is responsible for executing the Command. There are two generic types of Command, one is QuitCommand and the other is MoveCommand. (There is actually an UndoCommand, but the Undo feature is not part of the advanced effects our group chose, so decided not to show it, just for illustration)
MoveCommand will contain the concrete generic type of Move.



##### Move

Move has four generic types, Placing, Moving, Flying and Milling. Move wraps the algorithm for the specific operation that the Token will perform. So a Move will correspond to a Token.



##### Turn

Turn is used to control the round in the game. Turn stores the player. Whenever the Game runs a Turn, the Turn will notify the player to play with the game. After a player has played a turn, the Turn changes who will be playing the next time.