package com.uma.battleship;

import com.uma.battleship.core.GameStatus;
import com.uma.battleship.core.Player;

public interface Game {
    void start();

    GameStatus getStatus();

    Player getWinner();
}
