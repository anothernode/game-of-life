package com.anothernode.gameoflife;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * CoordinatesTests
 */
public class CoordinatesTests {

    @Test
    public void coordinatesExist() {

        Coordinates coordinates = new Coordinates(-5, 14);

        assertThat(coordinates.getX()).isEqualTo(-5);
        assertThat(coordinates.getY()).isEqualTo(14);
    }
}
