
package catan;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public final class ScriptedPolicy implements AgentPolicy {
    private final Queue<Move> scriptedMoves;

    public ScriptedPolicy(List<Move> scriptedMoves) {
        this.scriptedMoves = new ArrayDeque<>(scriptedMoves);
    }

    @Override
    public Move chooseInitialSettlement(GameState state) {
        return nextOrPass();
    }

    @Override
    public Move chooseInitialRoad(GameState state) {
        return nextOrPass();
    }

    @Override
    public Move chooseMove(GameState state) {
        return nextOrPass();
    }

    @Override
    public Map<ResourceType, Integer> chooseDiscard(GameState state, int discardCount) {
        return Collections.unmodifiableMap(new EnumMap<>(ResourceType.class));
    }

    @Override
    public ResourceType chooseResource(GameState state) {
        return ResourceType.BRICK;
    }

    @Override
    public int chooseRobberTarget(GameState state, List<Integer> possibleTargets) {
        return possibleTargets.isEmpty() ? -1 : possibleTargets.get(0);
    }

    @Override
    public DevelopmentCard chooseDevelopmentCard(GameState state) {
        return DevelopmentCard.KNIGHT;
    }

    private Move nextOrPass() {
        return scriptedMoves.isEmpty() ? Move.of(MoveType.PASS) : scriptedMoves.remove();
    }
}
