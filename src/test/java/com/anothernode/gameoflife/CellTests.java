package com.anothernode.gameoflife;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CellTests {

    @Test
    public void cellExists() {

        int x = -12;
        int y = -37;

        Coordinates coordinates = new Coordinates(x, y);
        Cell cell = new Cell(coordinates);

        assertThat(cell.getCoordinates().getX()).isEqualTo(x);
        assertThat(cell.getCoordinates().getY()).isEqualTo(y);
    }
}
