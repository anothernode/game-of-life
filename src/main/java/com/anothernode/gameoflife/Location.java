package com.anothernode.gameoflife;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Location {

    public static Location create(int x, int y) {
        return new AutoValue_Location(x, y);
    }

    abstract public int getX();
    abstract public int getY();
}
