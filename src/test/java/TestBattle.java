import com.uma.battleship.BattleShipGame;
import com.uma.battleship.Game;
import com.uma.battleship.core.Coordinate;
import com.uma.battleship.core.GameStatus;
import com.uma.battleship.core.MissileAttack;
import com.uma.battleship.core.Player;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class TestBattle {

    @Test
    public void testShipPlacements() throws FileNotFoundException {
        Scanner sc = new Scanner(getFileFromResources("sample-input-test1.txt"));
        BattleShipGame game = new BattleShipGame(sc);
        Assert.assertEquals(game.getPlayer1().getShips().size(), 2);
        Assert.assertEquals(game.getPlayer2().getShips().size(), 2);
    }


    @Test
    public void testAttacks() throws FileNotFoundException {
        Scanner sc = new Scanner(getFileFromResources("sample-input-test1.txt"));
        BattleShipGame game = new BattleShipGame(sc);
        Assert.assertEquals(game.getPlayer1().getAttacks().size(), 4);
        Assert.assertEquals(game.getPlayer2().getAttacks().size(), 10);
        Assert.assertEquals(game.getPlayer1().getAttacks().peek().getClass(), MissileAttack.class);
        Assert.assertEquals(((MissileAttack) game.getPlayer1().getAttacks().peek()).getCoordinate(), Coordinate.of(0,
                0));
    }

    @Test
    public void testPlayer2WonTheGame() throws FileNotFoundException {
        Scanner sc = new Scanner(getFileFromResources("sample-input-test1.txt"));
        Game game = new BattleShipGame(sc);
        game.start();
        Player winner = game.getWinner();
        Assert.assertEquals(winner.getName(), "Player-2");
    }


    @Test
    public void testGameIsDrawn() throws FileNotFoundException {
        Scanner sc = new Scanner(getFileFromResources("test-draw-game.txt"));
        Game game = new BattleShipGame(sc);
        game.start();
        Assert.assertEquals(game.getStatus(), GameStatus.DRAWN);
    }

    private File getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }
}
