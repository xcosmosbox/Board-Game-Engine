package src.mvc.model.game.rule;

public interface GameRule {
    /**
     * return each player's token
     * @return return each player's token
     */
    Integer eachPlayerTokens();

    /**
     * return total players number
     * @return return total players number
     */
    Integer totalPlayers();

    /**
     * return a number, this number represents how many tokens will make the player lose
     * @return how many tokens will make the player lose
     */
    Integer failedCondition();

    /**
     * whether to allow tokens to overlap
     * @return whether to allow tokens to overlap
     */
    Boolean differTokenOverlap();

}
