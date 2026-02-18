package catan;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class GameState {
    private final int turn;
    private final int currentPlayerId;
    private final Set<MoveType> legalMoveTypes;
    private final Map<Integer, Integer> victoryPoints;

    public GameState(int turn, int currentPlayerId, Set<MoveType> legalMoveTypes, Map<Integer, Integer> victoryPoints) {
        this.turn = turn;
        this.currentPlayerId = currentPlayerId;
        this.legalMoveTypes = Set.copyOf(legalMoveTypes);
        this.victoryPoints = Map.copyOf(victoryPoints);
    }

    public int turn() {
        return turn;
    }

    public int currentPlayerId() {
        return currentPlayerId;
    }

    public Set<MoveType> legalMoveTypes() {
        return Collections.unmodifiableSet(legalMoveTypes);
    }

    public Map<Integer, Integer> victoryPoints() {
        return Collections.unmodifiableMap(victoryPoints);
    }

    public boolean isLegal(Move move) {
        return legalMoveTypes.contains(move.type());
    }

    public List<Integer> possibleRobberTargets() {
        return victoryPoints.keySet().stream()
                .filter(id -> id != currentPlayerId)
                .toList();
    }
}
