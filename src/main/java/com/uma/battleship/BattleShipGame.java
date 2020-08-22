package com.uma.battleship;

import com.uma.battleship.core.*;
import com.uma.battleship.helper.Logger;
import lombok.Data;

import java.io.FileNotFoundException;
import java.util.*;

@Data
public class BattleShipGame implements Game {
    private BattleArea player1;
    private BattleArea player2;
    private GameStatus status;

    public BattleShipGame(Scanner scanner) throws FileNotFoundException {
        status = GameStatus.IN_PROGRESS;
        this.init(scanner);
    }

    public void start() {
        while ((!player1.isLost() || !player2.isLost()) && (!player1.hasNoMissiles() || !player2.hasNoMissiles())) {
            attackOpponent(player1, player2);
            attackOpponent(player2, player1);
        }

        if (!player2.isLost() && !player1.isLost()) {
            Logger.log("Game drawn");
            this.status = GameStatus.DRAWN;
            return;
        }
        this.status = GameStatus.COMPLETED;


        Player winner = player2.isLost() ? player1.getPlayer() : player2.getPlayer();
        Logger.log(winner.getName() + " won the battle");
    }

    @Override
    public GameStatus getStatus() {
        return this.status;
    }

    @Override
    public Player getWinner() {
        if (this.status != GameStatus.COMPLETED)
            return null;
        return player2.isLost() ? player1.getPlayer() : player2.getPlayer();
    }

    private void attackOpponent(BattleArea attacker, BattleArea enemy) {
        Attack attackerAction = attacker.getNextAction();
        while (attackerAction != null && attackerAction.execute(attacker, enemy)) {
            attackerAction = attacker.getNextAction();
        }
        if (attackerAction == null) {
            Logger.log(attacker.getPlayer().getName() + " has no more missiles left to launch");
        }
    }


    private void init(Scanner sc) throws FileNotFoundException {
        int battleAreaWidth = sc.nextInt();
        int battleAreaHeight = sc.next().charAt(0) - 'A';
        player1 = new BattleArea(new Player("Player-1"), battleAreaWidth, battleAreaHeight);
        player2 = new BattleArea(new Player("Player-2"), battleAreaWidth, battleAreaHeight);
        int noOfShips = sc.nextInt();
        while (--noOfShips >= 0) {
            ShipType shipType = ("Q").equals(sc.next()) ? ShipType.Q_TYPE : ShipType.P_TYPE;
            int shipWidth = sc.nextInt();
            int shipHeight = sc.nextInt();
            Coordinate ship1Location = getCoordinateFromString(sc.next());
            Coordinate ship2Location = getCoordinateFromString(sc.next());
            player1.placeShips(Arrays.asList(new Ship(ship1Location, shipType, shipWidth, shipHeight)));
            player2.placeShips(Arrays.asList(new Ship(ship2Location, shipType, shipWidth, shipHeight)));
        }
        sc.nextLine();
        List<Attack> player1Attacks = getAttacksFromString(sc.nextLine());
        List<Attack> player2Attacks = getAttacksFromString(sc.nextLine());
        player1.addAttacks(player1Attacks);
        player2.addAttacks(player2Attacks);
    }

    private Coordinate getCoordinateFromString(String input) {
        return Coordinate.of(input.charAt(0) - 'A', input.charAt(1) - '0' - 1);
    }

    private List<Attack> getAttacksFromString(String input) {
        StringTokenizer tokenizer = new StringTokenizer(input);
        List<Attack> attackList = new ArrayList<>();
        while (tokenizer.hasMoreElements()) {
            attackList.add(new MissileAttack(this.getCoordinateFromString(tokenizer.nextToken())));
        }
        return attackList;
    }
}
