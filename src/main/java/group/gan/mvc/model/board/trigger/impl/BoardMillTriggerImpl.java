package group.gan.mvc.model.board.trigger.impl;

import group.gan.events.Event;
import group.gan.events.EventListener;
import group.gan.events.EventType;
import group.gan.events.ListenerType;
import group.gan.events.impl.MillingEvent;
import group.gan.mvc.model.board.trigger.Trigger;
import group.gan.mvc.model.position.Position;

import java.util.*;

/**
 * @author: fengyuxiang
 * @ClassName: BoardMillTrigger
 * @version: 1.0
 * @description:
 * @create: 7/5/2023
 */
public class BoardMillTriggerImpl implements Trigger {

    /**
     * private variable to store all event listener
     */
    private List<EventListener> eventListenerList = new ArrayList<>();

    /**
     * private variable to store all listening mill node
     */
    private MillNode[] millNodes;

    /**
     * private variable to store the listener type
     */
    private ListenerType listenerType = ListenerType.TRIGGER;


    /**
     * Non-parameter constructor
     */
    public BoardMillTriggerImpl() {
        this.initializeTrigger();
    }

    /**
     * Event listener interface
     *
     * @param event
     */
    @Override
    public void onEvent(Event event) {
        if (event.getEventType() == EventType.BOARD_CHANGE){
            if (event.geEventContext().getClass().isArray()
                    && event.geEventContext().getClass().getComponentType().equals(Position.class)) {
                // init the counter and casting event context to Position[] type
                int counter = 0;
                Position[] positions = (Position[]) event.geEventContext();

                // verify whether is form a new mill
                for (MillNode millNode : millNodes) {
                    counter += millNode.verify(positions) ? 1 : 0;
                }

                // according to the counter to call the listener
                if (counter != 0){
                    Event<BoardMillTriggerImpl> millEvent = new MillingEvent();
                    millEvent.setEventSource(this);
                    notifyListeners(millEvent);
                }

            }
        }
    }

    /**
     * Get the type of listener
     *
     * @return
     */
    @Override
    public ListenerType getListenerType() {
        return listenerType;
    }

    /**
     * Add one listener
     *
     * @param listener
     */
    @Override
    public void addListener(EventListener listener) {
        eventListenerList.add(listener);
    }

    /**
     * remove one listener
     *
     * @param listener
     */
    @Override
    public void removeListener(EventListener listener) {
        eventListenerList.remove(listener);
    }

    /**
     * Notify all listeners
     */
    @Override
    public void notifyListeners() {
        // nothing to do
    }

    /**
     * Notify all listener and pass one Event
     *
     * @param event
     */
    @Override
    public void notifyListeners(Event event) {
        for (EventListener listener : eventListenerList) {
            listener.onEvent(event);
        }
    }

    /**
     * Remove all listeners
     */
    @Override
    public void clearListeners() {
        eventListenerList = new ArrayList<>();
    }

    /**
     * Initialize triggers and rules.
     * Multiple ways are allowed.
     */
    @Override
    public void initializeTrigger() {
        // load resource file
        ResourceBundle MillTriggerConfigBundle = ResourceBundle.getBundle("MillTriggerConfig");

        // read the number of lines
        int lines = Integer.parseInt(MillTriggerConfigBundle.getString("lines"));

        // init millNodes done
        this.millNodes = new MillNode[lines];

        // assign value
        for (int line = 0; line < lines; line++) {
            // create search key
            String key = String.valueOf(line);
            if (MillTriggerConfigBundle.containsKey(key)){
                // according to the key to get the mill position
                String millStr = MillTriggerConfigBundle.getString(key);

                // using split function to get the three position index
                String[] millArray = millStr.split(",");

                // init the mill position
                List<Integer> index = new ArrayList<>();
                for (String millIndex : millArray) {
                    index.add(Integer.parseInt(millIndex.trim()));
                }

                // create mill node
                MillNode millNode = new MillNode(index);

                // add millNode to millNodes
                this.millNodes[line] = millNode;

            }
        }

    }

    /**
     * According to the index to return the trigger node state
     *
     * @param index
     * @return
     */
    @Override
    public Boolean getTriggerNodeState(Integer index) {
        return millNodes[index].getState();
    }


}
