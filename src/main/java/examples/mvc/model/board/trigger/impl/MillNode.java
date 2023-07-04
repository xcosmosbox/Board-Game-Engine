package examples.mvc.model.board.trigger.impl;

import src.mvc.model.position.Position;

import java.util.List;

/**
 * @author: fengyuxiang
 * @ClassName: MillNode
 * @version: 1.0
 * @description:
 * @create: 7/5/2023
 */
public class MillNode {
    /**
     * A boolean variable to store the mill state
     */
    private boolean state = false;
    /**
     * Using array to store the node for one mill node
     */
    private int[] node;

    /**
     * init
     * @param config
     */
    public MillNode(List<Integer> config){
        this.node = new int[config.size()];
        for (int i = 0; i < config.size(); i++) {
            this.node[i] = config.get(i);
        }
    }

    /**
     * Verify whether the mill is destroyed or a new mill is formed
     * @param positions
     * @return
     */
    public boolean verify(Position[] positions){
        for (int i : node) {
            if (positions[i].isEmpty()){
                state = false;
                return false;
            }
        }
        // get the first symbol
        Character symbol = positions[node[0]].peekToken().getSymbol();
        // verify others positions whether have the same symbol
        for (int i = 1; i < node.length; i++) {
            if (!symbol.equals(positions[node[i]].peekToken().getSymbol())){
                // different symbol means no mill or destroyed mill
                state = false;
                return false;
            }
        }
        // if state == false, means that is a new mill
        if (state == false){
            state = true;
            return true;
        }else {
            // state == true but all symbols are same, means that is a old mill
            // needed return the false when that is a old mill
            return false;
        }
    }

    /**
     * Getter for state
     * @return
     */
    public boolean getState() {
        return state;
    }
}
