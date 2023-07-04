package examples.mvc.controller.player.impl;

import examples.mvc.controller.command.CommandType;
import examples.mvc.controller.command.impl.MillCommand;
import examples.mvc.controller.command.impl.MoveCommand;
import examples.mvc.controller.move.factory.impl.FlyingStrategyFactory;
import examples.mvc.controller.move.factory.impl.MillStrategyFactory;
import examples.mvc.controller.move.factory.impl.MovingStrategyFactory;
import examples.mvc.controller.move.factory.impl.PlacingStrategyFactory;
import examples.mvc.model.player.PlayerStateEnum;
import examples.utils.CommandTypeConverter;
import src.exception.InvalidMenuChoose;
import src.mvc.controller.command.Command;
import src.mvc.controller.move.MoveStrategy;
import src.mvc.controller.move.factory.MoveStrategyFactory;
import src.mvc.controller.player.Player;
import src.mvc.controller.turn.Pollable;
import src.mvc.model.coordinate.Coordinate;
import src.mvc.model.player.AIPlayerModel;
import src.mvc.model.player.PlayerModel;
import src.mvc.model.token.Token;
import src.mvc.view.View;
import src.utils.Display;

import java.util.*;

/**
 * @author: fengyuxiang
 * @ClassName: AIPlayer
 * @version: 1.0
 * @description:
 * @create: 25/5/2023
 */
public class AIPlayerImpl implements Player, Pollable {

    // Declare a PlayerModel instance
    private AIPlayerModel playerModel;

    // private variable to help the requestIntegersInput function to check the return value
    private InitDescribeType initDescribeType;
    private Queue<Coordinate> queue = new LinkedList<>();

    /**
     * constructor
     * @param playerModel
     */
    public AIPlayerImpl(AIPlayerModel playerModel) {
        // Assign the provided playerModel to the class variable
        this.playerModel = playerModel;
    }

    /**
     * play the game
     * just add this for the further use
     *
     * @return
     */
    @Override
    public Command play() {
        return null;
    }

    /**
     * set the state manager
     * Injection interface for setting PlayerModel
     *
     * @param playerModel
     */
    @Override
    public void setStateManager(PlayerModel playerModel) {
        // an injection interface for player model
        this.playerModel = (AIPlayerModel) playerModel;
    }

    /**
     * get the uid of the player
     *
     * @return
     */
    @Override
    public Integer getUid() {
        return playerModel.getUid();
    }

    /**
     * get one token
     *
     * @return
     */
    @Override
    public Token getOneToken() {
        return playerModel.getOneToken();
    }

    /**
     * get the player name
     *
     * @return
     */
    @Override
    public String getPlayerName() {
        return playerModel.getPlayerName();
    }

    /**
     * get player state
     *
     * @return
     */
    @Override
    public PlayerStateEnum getPlayerState() {
        return playerModel.getState();
    }

    /**
     * The player returns a number according to the prompt
     * For AI player, it plays the game anyway, it doesn't exit the game
     *
     * @return
     */
    @Override
    public Integer requestOneIntegerInput() {
        try {
            Thread.sleep(800);
            System.out.print(1);
            Thread.sleep(400);
            System.out.println();
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return 1;
    }

    /**
     * The player returns a list numbers according to the prompt
     *
     * @return
     */
    @Override
    public Integer[] requestIntegersInput() {
        Integer[] option = new Integer[2];

        if (queue.isEmpty()) {
            if (initDescribeType == InitDescribeType.PLACING) {
                List<Coordinate> placableOptions = this.playerModel.getPlaceableOptions();
                queue.add(placableOptions.get(new Random().nextInt(placableOptions.size())));
            } else if (initDescribeType == InitDescribeType.MOVING) {
                List<Coordinate[]> movableOptions = this.playerModel.getMovableOptions();
                Coordinate[] movePair = movableOptions.get(new Random().nextInt(movableOptions.size()));
                queue.add(movePair[0]);
                queue.add(movePair[1]);
            } else if (initDescribeType == InitDescribeType.FLYING) {
                List<Coordinate[]> flyableOptions = this.playerModel.getFlyableOptions();
                Coordinate[] flyPair = flyableOptions.get(new Random().nextInt(flyableOptions.size()));
                queue.add(flyPair[0]);
                queue.add(flyPair[1]);
            } else if (initDescribeType == InitDescribeType.MILLING) {
                List<Coordinate> millableOptions = this.playerModel.getRemovableOptions();
                queue.add(millableOptions.get(new Random().nextInt(millableOptions.size())));
            }

            Coordinate input = queue.poll();
            option[0] = input.getX();
            option[1] = input.getY();
            try {
                Thread.sleep(800);
                System.out.print(input.getX());
                Thread.sleep(300);
                System.out.print(",");
                Thread.sleep(300);
                System.out.print(input.getY());
                Thread.sleep(600);
                System.out.println();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        } else {
            Coordinate input = queue.poll();
            option[0] = input.getX();
            option[1] = input.getY();
            try {
                Thread.sleep(800);
                System.out.print(input.getX());
                Thread.sleep(300);
                System.out.print(",");
                Thread.sleep(300);
                System.out.print(input.getY());
                Thread.sleep(600);
                System.out.println();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return option;
    }

    /**
     * get the command type from the view
     *
     * @param view
     * @return commandType
     */
    @Override
    public CommandType poll(View view) {
        // get the text message that will be displayed
        Display display = new Display();
        view.draw(display);
        CommandType commandType = null;

        while (commandType == null) {
            // get the user input
            // for AI player, it plays the game anyway, it doesn't exit the game
            int input = requestOneIntegerInput();

            try {
                commandType = CommandTypeConverter.IntegerToCommandType(input, view);
            } catch (InvalidMenuChoose e) {
                System.out.println(e);
            }
        }


        return commandType;
    }

    /**
     * fill the command with the command type
     *
     * @param command
     * @return command
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
            if (this.playerModel.getState() == PlayerStateEnum.PLACING) {
                initDescribeType = InitDescribeType.PLACING;
                moveStrategyFactory = new PlacingStrategyFactory();
//                moveCommand.initMoveStrategy(moveStrategyFactory.createStrategy());

            } else if (this.playerModel.getState() == PlayerStateEnum.MOVING) {
                initDescribeType = InitDescribeType.MOVING;
                moveStrategyFactory = new MovingStrategyFactory();
//                moveCommand.initMoveStrategy(moveStrategyFactory.createStrategy());
            } else if (this.playerModel.getState() == PlayerStateEnum.FLYING) {
                initDescribeType = InitDescribeType.FLYING;
                moveStrategyFactory = new FlyingStrategyFactory();
            }

            if (moveStrategyFactory != null) {
                MoveStrategy moveStrategy = moveStrategyFactory.createStrategy();
                moveStrategy.initDescription(this);
                ((MoveCommand) command).initMoveStrategy(moveStrategy);
            }

        } else if (command.getCommandType() == CommandType.MILL) {
            initDescribeType = InitDescribeType.MILLING;
            // Cast the command to MillCommand and initialize it with the player
            ((MillCommand) command).init(this);
            MoveStrategy moveStrategy = new MillStrategyFactory().createStrategy();
            moveStrategy.initDescription(this);
            command.initMoveStrategy(moveStrategy);
        }

        return command;
    }

    /**
     * Override the equals method for comparing PlayerImpl objects
     *
     * @param o
     * @return true if the two objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AIPlayerImpl aiPlayerImpl = (AIPlayerImpl) o;
        return Objects.equals(playerModel, aiPlayerImpl.playerModel);
    }

    /**
     * Override the hashCode method for PlayerImpl objects
     *
     * @return the hash code of the player
     */
    @Override
    public int hashCode() {
        return Objects.hash(playerModel);
    }

    /**
     * Override the toString method for the PlayerImpl class
     *
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
