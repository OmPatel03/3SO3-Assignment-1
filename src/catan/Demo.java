package catan;

import java.util.List;

public final class Demo {
    private Demo() {
    }

    public static void main(String[] args) {
        CatanAgent a0 = new PolicyBackedCatanAgent(new ScriptedPolicy(List.of(
                Move.of(MoveType.BUILD_ROAD, "edge-3")
        )));
        CatanAgent a1 = new PolicyBackedCatanAgent(new ScriptedPolicy(List.of(
                Move.of(MoveType.BUILD_CITY, "v-11")
        )));
        CatanAgent a2 = new PolicyBackedCatanAgent(new ScriptedPolicy(List.of(
                Move.of(MoveType.OFFER_TRADE, "2 wool for 1 ore")
        )));
        CatanAgent a3 = new PolicyBackedCatanAgent(new ScriptedPolicy(List.of()));

        CatanCoreEngine engine = new CatanCoreEngine(List.of(a0, a1, a2, a3));

        for (int i = 0; i < 6; i++) {
            Move move = engine.runSingleTurn();
            System.out.println("Turn " + i + ": " + move);
        }
    }
}
