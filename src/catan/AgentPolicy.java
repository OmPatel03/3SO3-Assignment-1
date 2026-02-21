package catan;

import java.util.List;
import java.util.Map;

public interface AgentPolicy {
    Move chooseInitialSettlement(GameState state);

    Move chooseInitialRoad(GameState state);

    Move chooseMove(GameState state);

    Map<ResourceType, Integer> chooseDiscard(GameState state, int discardCount);

    ResourceType chooseResource(GameState state);

    int chooseRobberTarget(GameState state, List<Integer> possibleTargets);

    DevelopmentCard chooseDevelopmentCard(GameState state);
}
