package catan;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class PolicyBackedCatanAgent implements CatanAgent {
    private final AgentPolicy policy;
    private int playerId;

    public PolicyBackedCatanAgent(AgentPolicy policy) {
        this.policy = Objects.requireNonNull(policy);
        this.playerId = -1;
    }

    @Override
    public void init(int playerId) {
        this.playerId = playerId;
    }

    public int playerId() {
        return playerId;
    }

    @Override
    public Move chooseInitialSettlement(GameState state) {
        return policy.chooseInitialSettlement(state);
    }

    @Override
    public Move chooseInitialRoad(GameState state) {
        return policy.chooseInitialRoad(state);
    }

    @Override
    public Move chooseMove(GameState state) {
        return policy.chooseMove(state);
    }

    @Override
    public Map<ResourceType, Integer> chooseDiscard(GameState state, int discardCount) {
        return policy.chooseDiscard(state, discardCount);
    }

    @Override
    public ResourceType chooseResource(GameState state) {
        return policy.chooseResource(state);
    }

    @Override
    public int chooseRobberTarget(GameState state, List<Integer> possibleTargets) {
        return policy.chooseRobberTarget(state, possibleTargets);
    }

    @Override
    public DevelopmentCard chooseDevelopmentCard(GameState state) {
        return policy.chooseDevelopmentCard(state);
    }
}
