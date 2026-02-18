package catan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class CatanCoreEngine {
    private final List<CatanAgent> agents;
    private final Map<Integer, Integer> victoryPoints;
    private int turn;

    public CatanCoreEngine(List<CatanAgent> agents) {
        if (agents.size() != 4) {
            throw new IllegalArgumentException("Expected 4 agents");
        }
        this.agents = new ArrayList<>(agents);
        this.victoryPoints = Map.of(0, 0, 1, 0, 2, 0, 3, 0);
        this.turn = 0;

        for (int i = 0; i < this.agents.size(); i++) {
            this.agents.get(i).init(i);
        }
    }

    public Move runSingleTurn() {
        int currentPlayer = turn % agents.size();
        Set<MoveType> legalThisTurn = Set.of(MoveType.PASS, MoveType.BUILD_ROAD, MoveType.OFFER_TRADE);
        GameState state = new GameState(turn, currentPlayer, legalThisTurn, victoryPoints);

        CatanAgent agent = agents.get(currentPlayer);
        Move chosen = agent.chooseMove(state);

        Move applied = state.isLegal(chosen) ? chosen : Move.of(MoveType.PASS, "illegal->pass");
        turn++;
        return applied;
    }
}
