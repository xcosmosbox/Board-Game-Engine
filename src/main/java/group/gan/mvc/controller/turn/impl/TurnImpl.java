package group.gan.mvc.controller.turn.impl;

import group.gan.mvc.controller.command.Command;
import group.gan.mvc.controller.command.CommandType;
import group.gan.mvc.controller.command.factory.CommandFactory;
import group.gan.mvc.controller.command.factory.impl.MoveCommandFactory;
import group.gan.mvc.controller.turn.Pollable;
import group.gan.mvc.controller.turn.Turn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TurnImpl implements Turn {

    private Queue<Pollable> pollableObjects;
    private Pollable currentPollable;
    MoveCommandFactory move = new MoveCommandFactory();


    public TurnImpl() {
        pollableObjects = new LinkedList<>();

    }

    @Override
    public Command runTurn() {
        currentPollable = getPollableInstance();
        if (currentPollable != null) {
            CommandType commandType = currentPollable.poll(null);
            Command command = move.createCommand(commandType);
            if (command != null) {
                Command filledCommand = currentPollable.fillCommand(command);
                switchPollableObject();
                return filledCommand;
            }
        }
        return null;
    }

    @Override
    public Command continueRun() {
        return runTurn();
    }

    @Override
    public Command refillCommand() {
        Pollable currentPollable = getPollableInstance();
        if (currentPollable != null) {
            return currentPollable.fillCommand(move.createCommand(CommandType.MOVE));
        }
        return null;
    }

    @Override
    public Pollable getPollableInstance() {
        return pollableObjects.poll();

    }

    @Override
    public void switchPollableObject() {
        if (!pollableObjects.isEmpty()) {
            pollableObjects.offer(currentPollable);
            currentPollable = pollableObjects.poll();
        }
    }

    @Override
    public void registerPollableObject(Pollable pollable) {
        pollableObjects.offer(pollable);
    }
}
