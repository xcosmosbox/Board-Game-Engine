package group.gan.mvc.controller.turn.impl;

import group.gan.mvc.controller.command.Command;
import group.gan.mvc.controller.command.CommandType;
import group.gan.mvc.controller.command.factory.impl.MoveCommandFactory;
import group.gan.mvc.controller.command.factory.impl.QuitCommandFactory;
import group.gan.mvc.controller.turn.Pollable;
import group.gan.mvc.controller.turn.Turn;
import group.gan.mvc.view.factory.FacViewFactory;

import java.util.LinkedList;
import java.util.Queue;

public class TurnImpl implements Turn {

    private Queue<Pollable> pollableQueue;
    private Pollable currentPollable;
    private MoveCommandFactory moveCommandFactory = new MoveCommandFactory();
    private QuitCommandFactory quitCommandFactory = new QuitCommandFactory();


    public TurnImpl() {
        pollableQueue = new LinkedList<>();

    }

    @Override
    public Command runTurn() {
        currentPollable = getCurrentPollableObject();
        if (currentPollable != null) {
            CommandType commandType = currentPollable.poll(FacViewFactory.createFactory(currentPollable).createView());
            Command command = moveCommandFactory.createCommand(commandType);
            if (command.getCommandType() == CommandType.MOVE) {
                Command filledCommand = currentPollable.fillCommand(command);
                return filledCommand;
            } else {
                return quitCommandFactory.createCommand(CommandType.QUIT);
            }
        }
        return null;
    }

    @Override
    public Command continueRun() {
        // sprint2 does not test this
        return null;
    }

    /**
     * when we get a invalid command, we will send a command with the same type command.
     *
     * @param command one command
     * @return refill command
     */
    @Override
    public Command refillCommand(Command command) {

        if (command.getCommandType() == CommandType.MOVE && currentPollable != null){
            Command newCommand = moveCommandFactory.createCommand(CommandType.MOVE);
            Command filledCommand = currentPollable.fillCommand(newCommand);
            return filledCommand;
        }

        return null;
    }


    @Override
    public Pollable getPollableInstance() {
        return getCurrentPollableObject();

    }

    @Override
    public void switchPollableObject() {
        pollableQueue.offer(currentPollable);
        currentPollable = pollableQueue.poll();
    }

    @Override
    public void registerPollableObject(Pollable pollable) {
        pollableQueue.offer(pollable);
    }

    private Pollable getCurrentPollableObject(){
        if (currentPollable == null){
            currentPollable = pollableQueue.poll();
        }

        return currentPollable;
    }
}
