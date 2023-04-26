package group.gan.mvc.controller.player.impl;

import group.gan.mvc.controller.command.Command;
import group.gan.mvc.controller.command.CommandType;
import group.gan.mvc.controller.command.impl.MoveCommand;
import group.gan.mvc.controller.move.MoveStrategy;
import group.gan.mvc.controller.move.factory.MoveStrategyFactory;
import group.gan.mvc.controller.move.factory.impl.MovingStrategyFactory;
import group.gan.mvc.controller.move.factory.impl.PlacingStrategyFactory;
import group.gan.mvc.controller.player.Player;
import group.gan.mvc.controller.turn.Pollable;
import group.gan.mvc.model.player.PlayerModel;
import group.gan.mvc.model.player.PlayerStateEnum;
import group.gan.mvc.model.token.Token;
import group.gan.mvc.view.View;
import group.gan.utils.CommandTypeConverter;
import group.gan.utils.Display;

import java.util.Objects;
import java.util.Scanner;

public class PlayerImpl implements Player, Pollable {

    // Declare a PlayerModel instance
    private PlayerModel playerModel;

    // Constructor for the PlayerImpl class, taking a PlayerModel as an argument
    public PlayerImpl(PlayerModel playerModel) {
        // Assign the provided playerModel to the class variable
        this.playerModel = playerModel;
    }

    // just add this for the further use
    @Override
    public Command play() {
        return null;
    }

    // Injection interface for setting PlayerModel
    @Override
    public void setStateManager(PlayerModel playerModel) {
        // an injection interface for player model
        this.playerModel = playerModel;
    }

    // Getter for the player's unique ID
    @Override
    public Integer getUid() {
        return playerModel.getUid();
    }

    // Getter for one of the player's tokens
    @Override
    public Token getOneToken() {
        return playerModel.getOneToken();
    }

    // Getter for the player's name
    @Override
    public String getPlayerName() {
        return playerModel.getPlayerName();
    }

    /**
     * get player state
     *
     * @return the player's current state
     */
    @Override
    public PlayerStateEnum getPlayerState() {
        return playerModel.getState();
    }

    // Request a single integer input from the user
    @Override
    public Integer requestOneIntegerInput() {
        Scanner singleInput = new Scanner(System.in);
        int input = singleInput.nextInt();
        return input;
    }

    // Request multiple integer inputs from the user
    @Override
    public Integer[] requestIntegersInput() {
        Scanner multipleInput = new Scanner(System.in);
        String input = multipleInput.nextLine();

        // Split the input string into an array
        String[] inputArray = input.split(",");

        // Convert the string array into an Integer array
        Integer[] intArray = new Integer[inputArray.length];

        try {
            intArray[0] = Integer.parseInt(inputArray[0]);
            intArray[1] = Integer.parseInt(inputArray[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a string with two integers separated by a comma.");
        }

        return intArray;

    }

    // Poll for user input with a provided View
    @Override
    public CommandType poll(View view) {
        // get the text message that will be displayed
        Display display = new Display();
        view.draw(display);
        //get the user input
        int input = requestOneIntegerInput();
        return CommandTypeConverter.IntegerToCommandType(input, view);

    }

    // Fill in the necessary information for a command
    @Override
    public Command fillCommand(Command command) {
        // If the command is of type MOVE
        if (command.getCommandType() == CommandType.MOVE) {

//            MoveCommand moveCommand = (MoveCommand) command;
//            moveCommand.init(this);

            // Cast the command to MoveCommand and initialize it with the player
            ((MoveCommand) command).init(this);
            MoveStrategyFactory moveStrategyFactory = null;

            // Create a move strategy depending on the player's current state
            if (this.playerModel.getState() == PlayerStateEnum.PLACING){
                moveStrategyFactory = new PlacingStrategyFactory();
//                moveCommand.initMoveStrategy(moveStrategyFactory.createStrategy());

            } else if (this.playerModel.getState() == PlayerStateEnum.MOVING) {
                moveStrategyFactory = new MovingStrategyFactory();
//                moveCommand.initMoveStrategy(moveStrategyFactory.createStrategy());
            } // ...Subsequent needs to implement flying

            if (moveStrategyFactory != null){
                MoveStrategy moveStrategy = moveStrategyFactory.createStrategy();
                moveStrategy.initDescription(this);
                ((MoveCommand) command).initMoveStrategy(moveStrategy);
            }

        }

        return command;
    }

    // Override the equals method for comparing PlayerImpl objects
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerImpl player = (PlayerImpl) o;
        return Objects.equals(playerModel, player.playerModel);
    }

    // Override the hashCode method for PlayerImpl objects
    @Override
    public int hashCode() {
        return Objects.hash(playerModel);
    }

    // Override the toString method for the PlayerImpl class
    @Override
    public String toString() {
        // Get all tokens of the player
        Token[] allTokens = playerModel.getTokens();
        // Get the symbol of the first token
        String symbol = String.valueOf(allTokens[0].getSymbol());
        // Return a string representation of the player with their name and token color
        return getPlayerName() + " / Token Color: " + symbol;
    }
}
