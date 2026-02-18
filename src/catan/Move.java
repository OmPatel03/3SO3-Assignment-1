package catan;

import java.util.Objects;

public final class Move {
    private final MoveType type;
    private final String payload;

    private Move(MoveType type, String payload) {
        this.type = Objects.requireNonNull(type);
        this.payload = payload == null ? "" : payload;
    }

    public static Move of(MoveType type) {
        return new Move(type, "");
    }

    public static Move of(MoveType type, String payload) {
        return new Move(type, payload);
    }

    public MoveType type() {
        return type;
    }

    public String payload() {
        return payload;
    }

    @Override
    public String toString() {
        return type + (payload.isEmpty() ? "" : "(" + payload + ")");
    }
}
