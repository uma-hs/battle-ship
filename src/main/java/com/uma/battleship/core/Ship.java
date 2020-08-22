package com.uma.battleship.core;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Ship {
    private Coordinate location;
    private ShipType shipType;
    private int width;
    private int height;
    private int health;
    private List<List<ShipBlock>> shipBlocks;

    public Ship(Coordinate coordinate, ShipType shipType, int width, int height) {
        this.location = coordinate;
        this.width = width;
        this.health = width * height;
        this.height = height;
        this.shipType = shipType;
        initShipBlocks();
    }

    public boolean isAt(Coordinate coordinate) {
        return coordinate.getY() < location.getX() + width && coordinate.getX() < location.getY() + health && coordinate.getY() >= location.getX() && coordinate.getX() >= location.getY();
    }

    public boolean isSunk() {
        return health < 1;
    }

    public boolean attackBlock(Coordinate coordinate) {
        ShipBlock shipBlock = shipBlocks.get(coordinate.getX()).get(coordinate.getY());
        if (shipBlock.isDestroyed())
            return false;
        boolean destroyed = shipBlock.damage();
        if (destroyed)
            this.health--;

        return true;
    }

    private void initShipBlocks() {
        shipBlocks = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            List<ShipBlock> row = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                row.add(new ShipBlock(Coordinate.of(i, j), this.shipType.lives, false));
            }
            shipBlocks.add(row);
        }
    }
}
