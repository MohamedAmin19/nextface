package com.nextface.entity.enums;

public enum AirportName {
    HURGHADA_AIRPORT("Hurghada Airport"),
    CAIRO_AIRPORT("Cairo Airport");

    private final String displayName;

    AirportName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
