package com.anothernode.gameoflife;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CellTests {

    @Test
    public void cellExists() {

        Coordinates coordinates = new Coordinates(-12, -37);
        Cell cell = new Cell(coordinates);

        assertThat(cell.getCoordinates()).isEqualTo(coordinates);
    }
}
