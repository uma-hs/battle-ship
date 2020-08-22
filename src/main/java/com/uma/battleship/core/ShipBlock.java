package com.uma.battleship.core;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShipBlock {
    //internal coordinate inside ship grid.
    private Coordinate location;
    //set at the time of initialisation, based on ship type.
    private int lives;
    private boolean destroyed;


    public boolean damage() {
        this.lives--;
        if (lives < 1)
            this.destroyed = true;
        return destroyed;
    }
}
