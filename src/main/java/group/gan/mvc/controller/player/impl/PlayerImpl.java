package group.gan.mvc.controller.player.impl;

import group.gan.mvc.controller.command.Command;
import group.gan.mvc.controller.command.CommandType;
import group.gan.mvc.controller.command.impl.MoveCommand;
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

import java.util.Scanner;

public class PlayerImpl implements Player, Pollable {

    private PlayerModel playerModel;

    public PlayerImpl(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

    @Override
    public Command play() {
        return null;
    }

    @Override
    public void setStateManager(PlayerModel playerModel) {
        // a injection interface for player model
    }

    @Override
    public Integer getUid() {
        return playerModel.getUid();
    }

    @Override
    public Token getOneToken() {
        return playerModel.getOneToken();
    }

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

    @Override
    public Integer requestOneIntegerInput() {
        Scanner singleInput = new Scanner(System.in);
        int input = singleInput.nextInt();
        return input;
    }

    @Override
    public Integer[] requestIntegersInput() {
        Scanner multipleInput = new Scanner(System.in);
        String input = multipleInput.nextLine();

        String[] inputArray = input.split(",");

        Integer[] intArray = new Integer[inputArray.length];

        try {
            intArray[0] = Integer.parseInt(inputArray[0]);
            intArray[1] = Integer.parseInt(inputArray[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a string with two integers separated by a comma.");
        }

        return intArray;

    }

    @Override
    public CommandType poll(View view) {
        Display display = new Display();
        view.draw(display);
        int input = requestOneIntegerInput();
        return CommandTypeConverter.IntegerToCommandType(input, view);

    }

    @Override
    public Command fillCommand(Command command) {
        if (command.getCommandType() == CommandType.MOVE) {
            MoveCommand moveCommand = (MoveCommand) command;
            moveCommand.init(this);
            MoveStrategyFactory moveStrategyFactory;

            if (this.playerModel.getState() == PlayerStateEnum.PLACING){
                moveStrategyFactory = new PlacingStrategyFactory();
                moveCommand.initMoveStrategy(moveStrategyFactory.createStrategy());

            } else if (this.playerModel.getState() == PlayerStateEnum.MOVING) {
                moveStrategyFactory = new MovingStrategyFactory();
                moveCommand.initMoveStrategy(moveStrategyFactory.createStrategy());
            } // ...Subsequent needs to implement flying


        }

        return command;
    }
}
