package group.gan.mvc.controller.player.impl;

import group.gan.mvc.controller.command.Command;
import group.gan.mvc.controller.command.CommandType;
import group.gan.mvc.controller.command.impl.MillCommand;
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

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * PlayerImpl class implements the Player interface and Pollable interface.
 */
public class PlayerImpl implements Player, Pollable {

    // Declare a PlayerModel instance
    private PlayerModel playerModel;

    /** Constructor for the PlayerImpl class, taking a PlayerModel as an argument
     *
     * @param playerModel
     */
    public PlayerImpl(PlayerModel playerModel) {
        // Assign the provided playerModel to the class variable
        this.playerModel = playerModel;
    }

    /** just add this for the further use
     *
     * @return
     */
    @Override
    public Command play() {
        return null;
    }

    /** Injection interface for setting PlayerModel
     *
     * @param playerModel
     */
    @Override
    public void setStateManager(PlayerModel playerModel) {
        // an injection interface for player model
        this.playerModel = playerModel;
    }

    /** Getter for the player's unique ID
     *
     * @return the player's unique ID
     */
    @Override
    public Integer getUid() {
        return playerModel.getUid();
    }

    /** Getter for one of the player's tokens
     *
     * @return a token
     */
    @Override
    public Token getOneToken() {
        return playerModel.getOneToken();
    }

    /** Getter for the player's name
     *
     * @return the player's name
     */
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

    /** Request a single integer input from the user
     * @return the integer input
     */
    @Override
    public Integer requestOneIntegerInput() {
        Scanner singleInput = new Scanner(System.in);
        Integer input = null;

        // Continually prompt the user for input until a valid integer is entered
        while(input == null) {
            try {
                input = singleInput.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                singleInput.nextLine(); // Clear the current line to prepare for the next input
            }
        }

        return input;
    }

    /** Request multiple integer inputs from the user
     *
     * @return an array of integers
     */
    @Override
    public Integer[] requestIntegersInput() {
        Scanner multipleInput = new Scanner(System.in);
        Integer[] intArray = null;


        // Continually prompt the user for input until two integers separated by a comma are entered
        while(intArray == null) {
            String input = multipleInput.nextLine();

            // Split the input string into an array
            String[] inputArray = input.split(",");

            // Check if the array has exactly two elements
            if(inputArray.length != 2) {
                System.out.println("Invalid input. Please enter two integers separated by a comma.");
                continue;
            }

            // Convert the string array into an Integer array
            intArray = new Integer[2];

            try {
                intArray[0] = Integer.parseInt(inputArray[0].trim());
                intArray[1] = Integer.parseInt(inputArray[1].trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter two integers separated by a comma.");
                intArray = null; // Reset the intArray to null to continue the loop
            }
        }


        return intArray;

    }

    /** Poll for user input with a provided View
     * @param view
     * @return the command type
     */
    @Override
    public CommandType poll(View view) {
        // get the text message that will be displayed
        Display display = new Display();
        view.draw(display);
        //get the user input
        int input = requestOneIntegerInput();
        return CommandTypeConverter.IntegerToCommandType(input, view);

    }

    /** Fill in the necessary information for a command
     * @param command
     * @return the command with the necessary information filled in
     */
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

        }else if (command.getCommandType() == CommandType.MILL) {
            // Cast the command to MillCommand and initialize it with the player
            ((MillCommand) command).init(this);
        }

        return command;
    }

    /** Override the equals method for comparing PlayerImpl objects
     * @param o
     * @return true if the two objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerImpl player = (PlayerImpl) o;
        return Objects.equals(playerModel, player.playerModel);
    }

    /** Override the hashCode method for PlayerImpl objects
     * @return the hash code of the player
     */
    @Override
    public int hashCode() {
        return Objects.hash(playerModel);
    }

    /** Override the toString method for the PlayerImpl class
     * @return a string representation of the player
     */
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
