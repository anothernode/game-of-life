package com.anothernode.gameoflife.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class LocationTests {

    @Test
    public void locationExists() {

        Square location = Square.create(-5, 14);

        assertThat(location.getX()).isEqualTo(-5);
        assertThat(location.getY()).isEqualTo(14);
    }
}
