package group.gan.mvc.controller.turn.impl;

import group.gan.mvc.controller.command.Command;
import group.gan.mvc.controller.command.CommandType;
import group.gan.mvc.controller.command.factory.CommandFactory;
import group.gan.mvc.controller.command.factory.impl.MoveCommandFactory;
import group.gan.mvc.controller.command.factory.impl.QuitCommandFactory;
import group.gan.mvc.controller.turn.Pollable;
import group.gan.mvc.controller.turn.Turn;
import group.gan.mvc.view.factory.FacViewFactory;

import java.util.LinkedList;
import java.util.Queue;

public class TurnImpl implements Turn {

    // Queue of Pollable objects to manage game turns
    private Queue<Pollable> pollableQueue;
    // The current Pollable object in the turn
    private Pollable currentPollable;


    // Factory for creating move commands
    //modified
    private CommandFactory moveCommandFactory = new MoveCommandFactory();
    private CommandFactory quitCommandFactory = new QuitCommandFactory();

    /**
     * Constructor for TurnImpl class
     */
    public TurnImpl() {
        // Initialize the pollableQueue as a LinkedList
        pollableQueue = new LinkedList<>();

    }

    /**
     * Method to run the turn and return the appropriate command
     */
    @Override
    public Command runTurn() {
        // Get the current Pollable object
        currentPollable = getCurrentPollableObject();
        // If the current Pollable object exists
        if (currentPollable != null) {
            // Poll the current Pollable object for the command type
            CommandType commandType = currentPollable.poll(FacViewFactory.createFactory(currentPollable).createView());
            // Create a new move command
            Command command = moveCommandFactory.createCommand(commandType);
            // If the command type is MOVE
            if (command.getCommandType() == CommandType.MOVE) {
                // Fill the move command with the necessary data
                Command filledCommand = currentPollable.fillCommand(command);
                // Return the filled move command
                return filledCommand;
            } else {
                // If the command type is not MOVE, create and return a quit command
                return quitCommandFactory.createCommand(CommandType.QUIT);
            }
        }
        // If the current Pollable object is null, return null
        return null;
    }

    /**
     * Method to continue running the turn (not tested in sprint 2)
     */
    @Override
    public Command continueRun() {
        // sprint2 does not test this
        return null;
    }

    /**
     * Method to refill the command with the same type command if it's invalid
     *
     * @param command one command
     * @return refill command
     */
    @Override
    public Command refillCommand(Command command) {
        // If the command type is MOVE and the current Pollable object exists
        if (command.getCommandType() == CommandType.MOVE && currentPollable != null){
            // Create a new move command
            Command newCommand = moveCommandFactory.createCommand(CommandType.MOVE);
            // Fill the new move command with the necessary data
            Command filledCommand = currentPollable.fillCommand(newCommand);
            // Return the filled move command
            return filledCommand;
            // If the command type is Mill and the current Pollable object exists
        } else if (command.getCommandType() == CommandType.MILL && currentPollable != null) {
            // Create a new mill command
            Command newCommand = moveCommandFactory.createCommand(CommandType.MILL);
            // Fill the new mill command with the necessary data
            Command filledCommand = currentPollable.fillCommand(newCommand);
            // Return the filled move command
            return filledCommand;

        }

        // If conditions are not met, return null
        return null;
    }


    /**
     * Method to get the current Pollable object
     */
    @Override
    public Pollable getPollableInstance() {
        return getCurrentPollableObject();

    }

    /**
     * Method to switch the current Pollable object
     */
    @Override
    public void switchPollableObject() {
        // Add the current Pollable object to the end of the pollableQueue
        pollableQueue.offer(currentPollable);
        // Remove and set the current Pollable object to the next item in the pollableQueue
        currentPollable = pollableQueue.poll();
    }

    /**
     * Method to register a Pollable object
     */
    @Override
    public void registerPollableObject(Pollable pollable) {
        // Add the Pollable object to the pollableQueue
        pollableQueue.offer(pollable);
    }

    /**
     * Method to get the current Pollable object or the next one if it's null
     */
    private Pollable getCurrentPollableObject(){
        // Check if the current Pollable object is null
        if (currentPollable == null){
            // If it's null, remove and set the current Pollable object
            // to the next item in the pollableQueue
            currentPollable = pollableQueue.poll();
        }

        // Return the current Pollable object, which is either the original
        // non-null object or the next object in the queue
        return currentPollable;
    }
}
