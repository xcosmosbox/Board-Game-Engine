package group.gan.mvc.model.token;

import group.gan.mvc.controller.player.Player;

/**
 * A class that encapsulates token information
 */
public interface Token {

    /**
     * One Token have One Owner
     * @return A player reference for owner
     */
    Player getOwner();

    /**
     * Setter for owner
     * @param player a player object
     */
    void setOwner(Player player);

    /**
     * Getter for drawing symbol
     * @return Character data for symbol
     */
    Character getSymbol();

    /**
     * Setter for Symbol
     * @param character character symbol
     */
    void setSymbol(Character character);

    /**
     * getter for token ID
     * @return Integer token id
     */
    Integer getTokenID();

    /**
     * setter for token id
     * @param tokenID token id
     */
    void setTokenID(Integer tokenID);

    /**
     * Let token to die
     */
    void goDie();



}
