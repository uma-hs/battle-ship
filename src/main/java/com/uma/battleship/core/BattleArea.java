package com.uma.battleship.core;

import com.uma.battleship.helper.Logger;
import lombok.Data;

import java.util.*;

@Data
public class BattleArea {
    private Player player;
    private int width;
    private int height;
    private List<Ship> ships;
    private Queue<Attack> attacks;

    public BattleArea(Player player, int width, int height) {
        this.width = width;
        this.height = height;
        this.player = player;
        this.ships = new ArrayList<>();
        this.attacks = new LinkedList<>();
    }

    public void placeShips(List<Ship> ships) {
        this.ships.addAll(ships);
    }

    public void addAttacks(List<Attack> attacks) {
        this.attacks.addAll(attacks);
    }

    public Attack getNextAction() {
        return this.attacks.poll();
    }

    public boolean attackedByMissile(BattleArea attacker, Coordinate coordinate) {
        Optional<Ship> shipAt = this.findShipAt(coordinate);
        boolean hit = false;
        if (shipAt.isPresent()) {
            hit = this.attackShip(shipAt.get(), coordinate);
        }
        String result = hit ? "hit" : "miss";
        Logger.log(attacker.getPlayer().getName() + " fires a missile with target " + getStringFrom(coordinate) + " " +
                "which got " + result);
        return hit;
    }

    public boolean isLost() {
        return !ships.stream().anyMatch(s -> !s.isSunk());
    }

    public boolean hasNoMissiles() {
        return this.attacks.isEmpty();
    }

    private boolean attackShip(Ship target, Coordinate coordinate) {
        Coordinate blockCoordinate = Coordinate.of(coordinate.getX() - target.getLocation().getX(),
                coordinate.getY() - target.getLocation().getY());
        return target.attackBlock(blockCoordinate);
    }

    protected Optional<Ship> findShipAt(Coordinate coordinate) {
        return ships.stream().filter(ship -> ship.isAt(coordinate)).findFirst();
    }


    private String getStringFrom(Coordinate coordinate) {
        return String.valueOf((char) (coordinate.getX() + 'A')) + (coordinate.getY() + 1);
    }


}
