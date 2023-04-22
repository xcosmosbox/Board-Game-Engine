package group.gan.mvc.model.token.impl;

import group.gan.mvc.controller.player.Player;
import group.gan.mvc.model.token.Token;

/**
 * @author: fengyuxiang
 * @ClassName: TokenImpl
 * @version: 1.0
 * @description: An implement class for Token
 * @create: 22/4/2023
 */
public class TokenImpl implements Token {

    /**
     * store player reference
     */
    private Player owner;
    /**
     * store itself render symbol
     */
    private Character symbol;

    /**
     * constructor with player and symbol
     * @param owner
     * @param symbol
     */
    public TokenImpl(Player owner, Character symbol) {
        this.owner = owner;
        this.symbol = symbol;
    }

    /**
     * no-parameter constructor
     */
    public TokenImpl() {

    }

    /**
     * One Token have One Owner
     *
     * @return A player reference for owner
     */
    @Override
    public Player getOwner() {
        return owner;
    }

    /**
     * Setter for owner
     *
     * @param player a player object
     */
    @Override
    public void setOwner(Player player) {
        this.owner = player;
    }

    /**
     * Getter for drawing symbol
     *
     * @return Character data for symbol
     */
    @Override
    public Character getSymbol() {
        return symbol;
    }

    /**
     * Setter for Symbol
     *
     * @param character character symbol
     */
    @Override
    public void setSymbol(Character character) {
        this.symbol = character;
    }

    /**
     * remove the owner of token
     */
    @Override
    public void goDie() {
        this.owner = null;
    }
}
