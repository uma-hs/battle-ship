package com.uma.battleship.core;

import lombok.Data;

@Data
public class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }
}
