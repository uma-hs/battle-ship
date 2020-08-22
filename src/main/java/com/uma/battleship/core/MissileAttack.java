package com.uma.battleship.core;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MissileAttack implements Attack {
    private Coordinate coordinate;

    public boolean execute(BattleArea attacker, BattleArea target) {
        return target.attackedByMissile(attacker, coordinate);
    }
}
