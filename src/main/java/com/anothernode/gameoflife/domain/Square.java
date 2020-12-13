package com.anothernode.gameoflife.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Square {

    @JsonCreator
    public static Square create(@JsonProperty("x") int x, @JsonProperty("y") int y) {
        return new AutoValue_Square(x, y);
    }

    public abstract int getX();

    public abstract int getY();
}
