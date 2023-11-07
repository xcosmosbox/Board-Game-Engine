# Board Game Engine

Board Game Engine realizes the development of full interface and construction of MVC architecture mode.

Using this engine, you could easily develop board games that comply with the MVC architecture. 

Due to the scalability of the interface design, game rendering can be easily expanded from the terminal to the desktop or web page.

## Core Features

- [x] MVC Framework
- [x] Good Practice in SOLID Principle
- [x] Dependency Injection
- [x] Implement Design Patterns to a high standard
- [x] Unit Test 

## Interface Design

<img src="https://raw.githubusercontent.com/xavierfrankland/PicRepo/master/uPic/cYlbCOmermaid-diagram-2023-11-07-121340.png" alt="mermaid-diagram-2023-11-07-121340" style="zoom: 33%;" />

## Demo

Implementing this engine implements a classic tabletop game: Nine Men's Morris
![nnm](https://github.com/xcosmosbox/BoardGameFramework/assets/56502269/2c06736c-0c3a-4e87-9140-73e608c072c8)







## Getting Start

Clean Example Class File:

```bash
> mvn clean
```

After using the engine to write the board game you want to implement, re-compile:

```
> mvn install
```

Open Terminal to run .jar file

```
> java -jar [YOUR COMPILED JAR FILE PATH]
```

## API

### 1. Board

| Board  | API                                                           | Description                                                                                      |
| ------ | ------------------------------------------------------------- | ------------------------------------------------------------------------------------------------ |
| Method | `void placeToken(Token token, Coordinate coordinate)`         | Players can place token on the board                                                             |
| Method | `void moveToken(Coordinate from, Coordinate to)`              | Players can move token on the board                                                              |
| Method | `void removeToken(Player player, Coordinate coordinate)`      | Players can remove one token from the board                                                      |
| Method | `void flyToken(Coordinate from, Coordinate to)`               | Players can fly one token on the board                                                           |
| Method | `Boolean checkPositionValid(Coordinate coordinate)`           | Check whether the Coordinate is within the allowed range of the chessboard                       |
| Method | `Boolean checkMoveValid(Coordinate from, Coordinate to)`      | Check whether the Move operation is in compliance with the rules                                 |
| Method | `Boolean checkRemoveValid(Player player, Coordinate coordinate)` | Check whether the removed token belong to different players                                      |
| Method | `Boolean checkPositionIsEmpty(Coordinate coordinate)`         | Check position is empty or not                                                                   |
| Method | `Integer parsePosition(Coordinate coordinate)`                | Map the Integer corresponding to Coordinate according to the properties file                    |
| Method | `Position getOnePositionFromBoard(Coordinate coordinate)`     | Get one position from the board                                                                  |
| Method | `Position[] getAllPositionsFromBoard()`                       | Get all positions                                                                                |

### 2. Command

| Command    | API                                               | Description                                          |
| ---------- | ------------------------------------------------- | ---------------------------------------------------- |
| Method     | `Boolean execute(Player player, Board board)`     | A set of functions with execution                    |
| Method     | `Boolean execute()`                               | A set of functions with execution                    |
| Method     | `void init(Game game)`                            | A set of functions with initialization               |
| Method     | `void init(Player player)`                        | A set of functions with initialization               |
| Method     | `void init(Board board)`                          | A set of functions with initialization               |
| Method     | `void init(Game game, Player player)`             | A set of functions with initialization               |
| Method     | `void init(Board board, Player player)`           | A set of functions with initialization               |
| Method     | `void init(Game game, Board board)`               | A set of functions with initialization               |
| Method     | `void init(Game game, Player player, Board board)`| A set of functions with initialization               |
| Method     | `CommandType getCommandType()`                    | What type of Command is used to return at this time  |
| Method     | `void initMoveStrategy(MoveStrategy moveStrategy)`| initialize move strategy                             |


### 3. Game

| Game     | API                            | Description                                              |
| -------- | ------------------------------ | -------------------------------------------------------- |
| Method   | `void run()`                   | Game Start                                               |
| Method   | `void quit()`                  | Quit                                                     |
| Method   | `void quitForWin(Player winner)` | Quit for winning, with the winner's information         |
| Method   | `void build(GameModel gameModel)` | Build the Game model, initializing it with a GameModel  |


### 4. MoveStrategy

| MoveStrategy | API                                         | Description                              |
| ------------ | ------------------------------------------- | ---------------------------------------- |
| Method       | `Boolean executeStrategy(Player player, Board board)` | Run a specific strategy and return success criteria |
| Method       | `void initDescription(Player player)`       | Initialize strategy description with a player reference |


### 5. Player

| Player          | API                             | Description                                                     |
| --------------- | ------------------------------- | --------------------------------------------------------------- |
| Method          | `Command play()`                | Play the game and return a Command object                       |
| Method          | `void setStateManager(PlayerModel playerModel)` | Set the state manager for the player with a PlayerModel        |
| Method          | `Integer getUid()`              | Get the unique identifier (UID) of the player                   |
| Method          | `Token getOneToken()`           | Get one token from the player                                   |
| Method          | `String getPlayerName()`        | Get the name of the player                                      |
| Method          | `PlayerStateEnum getPlayerState()` | Get the current state of the player                            |
| Method          | `Integer requestOneIntegerInput()` | The player returns a number according to the prompt            |
| Method          | `Integer[] requestIntegersInput()` | The player returns a list of numbers according to the prompt   |


### 6. Pollable


| Pollable     | API                                   | Description                                             |
| ------------ | ------------------------------------- | ------------------------------------------------------- |
| Method       | `CommandType poll(View view)`         | Get the command type from the view                      |
| Method       | `Command fillCommand(Command command)`| Fill the given command with the command type            |

### 7. Turn


| Turn          | API                                   | Description                                                               |
| ------------- | ------------------------------------- | ------------------------------------------------------------------------- |
| Method        | `Command runTurn()`                   | Run the turn and return a Command that represents the turn's action       |
| Method        | `Command continueRun()`               | Continue running the turn after the initial run and return a Command      |
| Method        | `Command refillCommand(Command command)` | When an invalid command is received, refill and return a command of the same type |
| Method        | `Pollable getPollableInstance()`      | Get the instance of Pollable that is currently in use                     |
| Method        | `void switchPollableObject()`         | Switch the current Pollable object with another                           |
| Method        | `void registerPollableObject(Pollable pollable)` | Register a new Pollable object to be used                                  |

### 8. Trigger

| Trigger        | API                                      | Description                                          |
| -------------- | ---------------------------------------- | ---------------------------------------------------- |
| Method         | `void initializeTrigger()`               | Initialize triggers and rules in various ways.       |
| Method         | `Boolean getTriggerNodeState(Integer index)` | Return the state of the trigger node at a given index.|


### 9. BoardModel

| BoardModel     | API                                                      | Description                                                         |
| -------------- | -------------------------------------------------------- | ------------------------------------------------------------------- |
| Method         | `Position selectOnePosition(int index)`                  | Pass an index to retrieve a `Position` object reference.            |
| Method         | `Position[] selectAllPositions()`                        | Get all the `Position` information as an array.                     |
| Method         | `Token selectOneTokenByPosition(int index)`              | Pass an index to retrieve a `Token` object reference from position. |
| Method         | `void addOneTokenIntoPosition(Token token, int index)`   | Add a `Token` to the corresponding `Position` using an index.       |
| Method         | `Token removeOneTokenByPosition(int index)`              | Remove and return a `Token` from the corresponding `Position`.      |


### 10. Coordinate


| Coordinate | API                        | Description                         |
|------------|----------------------------|-------------------------------------|
| Method     | `Integer getX()`           | Get the x-coordinate value.         |
| Method     | `Integer getY()`           | Get the y-coordinate value.         |
| Method     | `void setX(Integer x)`     | Set the x-coordinate to a new value.|
| Method     | `void setY(Integer y)`     | Set the y-coordinate to a new value.|

### 11. GameModel


| GameModel       | API                                  | Description                                                      |
|-----------------|--------------------------------------|------------------------------------------------------------------|
| Method          | `void setTurn(Turn turn)`            | Setter for the Turn manager.                                     |
| Method          | `Turn getTurn()`                     | Getter for the Turn manager.                                     |
| Method          | `void setPlayers(Player... players)` | Setter for multiple players.                                     |
| Method          | `void setPlayer(Player player)`      | Setter for a single player.                                      |
| Method          | `Player getPlayer()`                 | Getter for a single player.                                      |
| Method          | `Player[] getPlayers()`              | Getter for multiple players.                                     |
| Method          | `void setBoard(Board board)`         | Setter for the board.                                            |
| Method          | `Board getBoard()`                   | Getter for the board.                                            |
| Method          | `Player getWinner()`                 | Returns the winner of the game.                                  |
| Method          | `Boolean isGameOver()`               | Checks if the game is over.                                      |
| Method          | `void saveGame()`                    | Save game data (interface only, no implementation).              |
| Method          | `void loadGame()`                    | Load game data (interface only, no implementation).              |

### 12. AIPlayerModel

| AIPlayerModel   | API                                              | Description                                                            |
|-----------------|--------------------------------------------------|------------------------------------------------------------------------|
| Method          | `Position[] getPositionsCache()`                 | Getter for the cached positions.                                       |
| Method          | `List<Coordinate> getPlaceableOptions()`         | Retrieves a list of valid coordinates for placing tokens.              |
| Method          | `List<Coordinate[]> getMovableOptions()`         | Retrieves a list of coordinate arrays for valid moves.                 |
| Method          | `List<Coordinate[]> getFlyableOptions()`         | Retrieves a list of coordinate arrays for valid flying moves.          |
| Method          | `List<Coordinate> getRemovableOptions()`         | Retrieves a list of coordinates for valid token removals after a Mill. |


### 13. PlayerModel

| PlayerModel     | API                         | Description                                                                |
|-----------------|-----------------------------|----------------------------------------------------------------------------|
| Method          | `String getPlayerName()`    | Retrieves the player's name.                                               |
| Method          | `void setPlayerName(String name)` | Sets the player's name.                                               |
| Method          | `PlayerStateEnum getState()`| Retrieves the current state of the player.                                 |
| Method          | `void setState(PlayerStateEnum state)` | Sets the state of the player.                                  |
| Method          | `void updateState(PlayerStateEnum state)` | Updates the state of the player.                              |
| Method          | `String getUserInput()`     | Gets user input.                                                           |
| Method          | `Command getCommandBuilder()`| Retrieves the command builder for generating commands.                     |
| Method          | `Integer getUid()`          | Retrieves the unique identifier of the player.                             |
| Method          | `Token getOneToken()`       | Retrieves one token owned by the player.                                   |
| Method          | `void setTokens(Token[] tokens)` | Sets the tokens associated with the player.                           |
| Method          | `Token[] getTokens()`       | Retrieves all tokens owned by the player.                                  |
| Method          | `Integer getNumberOfTokens()` | Retrieves the number of tokens the player has.                        |
| Method          | `Token removeOneToken()`    | Removes one token from the player's collection and returns it.             |


### 14. Position

| Position        | API                          | Description                                                                 |
|-----------------|------------------------------|-----------------------------------------------------------------------------|
| Method          | `Token peekToken()`          | Retrieves the token located at this position without removing it.           |
| Method          | `void addToken(Token token)` | Places a given token onto this position.                                     |
| Method          | `Token removeToken()`        | Removes and returns the token from this position.                            |
| Method          | `Boolean isEmpty()`          | Checks if the position is empty (i.e., if there is no token present).       |


### 15. Token

| Token         | API                           | Description                                                             |
|---------------|-------------------------------|-------------------------------------------------------------------------|
| Method        | `Player getOwner()`           | Retrieves the player who owns the token.                                |
| Method        | `void setOwner(Player player)`| Sets the owner of the token to the specified player.                    |
| Method        | `Character getSymbol()`       | Gets the character symbol that represents the token for drawing.        |
| Method        | `void setSymbol(Character character)`| Sets the character symbol to represent the token.               |
| Method        | `Integer getTokenID()`        | Retrieves the unique identifier for the token.                          |
| Method        | `void setTokenID(Integer tokenID)`| Sets the unique identifier for the token.                          |
| Method        | `void goDie()`                | Marks the token as removed or no longer in play.                        |


### 16. View


| View          | API                     | Description                                                       |
|---------------|-------------------------|-------------------------------------------------------------------|
| Method        | `void draw(Display display)` | Defines the method for drawing view elements, to be implemented by subclasses. The `Display` object is used for rendering the view. |
