package group.gan.mvc.model.position.impl;

import group.gan.mvc.model.position.Position;
import group.gan.mvc.model.token.Token;
import group.gan.mvc.model.token.impl.TokenImpl;

import java.util.Stack;

/**
 * @author: fengyuxiang
 * @ClassName: PositionImpl
 * @version: 1.0
 * @description:
 * @create: 22/4/2023
 */
public class PositionImpl implements Position {

    /**
     * private method
     * using stack data structure to store token
     * maximum capacity = 1
     */
    private Stack<Token> token = new Stack<>();

    /**
     * Get the Token stored on the Position, but not remove it from the Position
     *
     * @return Token
     */
    @Override
    public Token peekToken() {
        Token unmodifiableToken = new TokenImpl(token.peek().getOwner(),token.peek().getSymbol());
        return unmodifiableToken;
    }

    /**
     * Pass a token to store on the position
     *
     * @param token a token object
     */
    @Override
    public void addToken(Token token) {
        this.token.push(token);
    }

    /**
     * Get the Token stored on the Position, and remove it from the Position
     *
     * @return Token
     */
    @Override
    public Token removeToken() {
        return token.pop();
    }

    /**
     * Check if there is a Token on the Position
     *
     * @return Boolean
     */
    @Override
    public Boolean isEmpty() {
        return token.isEmpty();
    }
}
