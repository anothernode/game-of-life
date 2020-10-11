package com.anothernode.gameoflife;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Location {

    @JsonCreator
    public static Location create(@JsonProperty("x") int x, @JsonProperty("y") int y) {
        return new AutoValue_Location(x, y);
    }

    public abstract int getX();

    public abstract int getY();
}
