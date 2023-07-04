package examples.mvc.model.token.impl;

import src.mvc.controller.player.Player;
import src.mvc.model.token.Token;

import java.util.Objects;

/**
 * @author: fengyuxiang
 * @ClassName: TokenImpl
 * @version: 1.0
 * @description: An implement class for Token
 * @create: 22/4/2023
 */
public class TokenImpl implements Token {

    /**
     * a static global class variable for TokenImpl
     */
    public static int uid = 0;

    /**
     * store token id for each token
     */
    private int tokenID;

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
        this.tokenID = TokenImpl.uid;
        TokenImpl.uid += 1;
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
     * getter for token ID
     *
     * @return Integer token id
     */
    @Override
    public Integer getTokenID() {
        return tokenID;
    }

    /**
     * setter for token id
     *
     * @param tokenID token id
     */
    @Override
    public void setTokenID(Integer tokenID) {
        this.tokenID = tokenID;
    }

    /**
     * remove the owner of token
     */
    @Override
    public void goDie() {
        this.owner = null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenImpl token = (TokenImpl) o;
        return tokenID == token.tokenID && Objects.equals(owner, token.owner) && Objects.equals(symbol, token.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenID, owner, symbol);
    }
}

