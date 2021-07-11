package fr.lernejo.navy_battle;

import org.jetbrains.annotations.NotNull;

public class Fire {
    @NotNull
    private final String consequence;
    @NotNull
    private final boolean shipLeft;

    public Fire(boolean shipLeft, String consequence) {
        this.consequence = consequence;
        this.shipLeft = shipLeft;
    }
    public String getConsequence() {
        return consequence;
    }

    public boolean getShipLeft() { return shipLeft; }

}
