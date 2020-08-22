package com.uma.battleship.core;

public enum ShipType {
    P_TYPE(1), Q_TYPE(2);
    int lives;

    ShipType(int lives) {
        this.lives = lives;
    }
}
