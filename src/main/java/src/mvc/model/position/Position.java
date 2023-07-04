package src.mvc.model.position;

import src.mvc.model.token.Token;

public interface Position {
    /**
     * Get the Token stored on the Position, but not remove it from the Position
     * @return Token
     */
    Token peekToken();

    /**
     * Pass a token to store on the position
     * @param token a token object
     */
    void addToken(Token token);

    /**
     * Get the Token stored on the Position, and remove it from the Position
     * @return Token
     */
    Token removeToken();

    /**
     * Check if there is a Token on the Position
     * @return Boolean
     */
    Boolean isEmpty();




}
