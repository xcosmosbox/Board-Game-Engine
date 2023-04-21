package group.gan.mvc.model.game.rule;

public interface GameRule {
    Integer eachPlayerTokens();
    Integer totalPlayers();
    Integer failedCondition();
    Boolean differTokenOverlap();

}
