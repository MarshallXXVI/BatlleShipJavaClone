import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Game implements KeyListener {
    public static Board playerBoard;
    public static Board enemyBoard;
    MainPanel mainPanel;
    public static HandleInputUser userInput;

    //For AI.
    public static ArrayList<Position> PositionsToFire = new ArrayList<Position>();

    public static int GameState = 0;
    Game() {
        System.out.println("---------------Preparing State---------------");
        System.out.println("            Press 'Z' to Rotate              ");
        System.out.println("               Red means hit                 ");
        playerBoard = new Board(0);
        enemyBoard = new Board(1);
        mainPanel = new MainPanel();
        userInput = new HandleInputUser();
        JFrame f = new JFrame("Battle Ship");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 500);
        f.setMinimumSize(new Dimension(1000, 500));
        f.add(mainPanel);
        f.addKeyListener(this);
        f.setResizable(false);
        f.setVisible(true);
        SetUp();
    }

    private void SetUp() {
        boolean ifVisible = false;
        Ship first = new Ship(new Position(1, 2), 5, true, ifVisible);
        Ship second = new Ship(new Position(2, 4), 4, false, ifVisible);
        Ship third = new Ship(new Position(5, 4), 3, true, ifVisible);
        Ship forth = new Ship(new Position(5, 6), 3, true, ifVisible);
        Ship fifth = new Ship(new Position(8, 0), 2, false, ifVisible);
        enemyBoard.placeShip(first);
        enemyBoard.placeShip(second);
        enemyBoard.placeShip(third);
        enemyBoard.placeShip(forth);
        enemyBoard.placeShip(fifth);
    }

    public static void BattleState() {
        System.out.println("---------------Battle State---------------");
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                PositionsToFire.add(new Position(x, y));
            }
        }
        System.out.println(PositionsToFire.size());
    }

    public static void AIFireBack() {
        Collections.shuffle(PositionsToFire);
        Position tempPos = PositionsToFire.remove(0);
        System.out.println("AI fire player at Position: " + tempPos.x + " : " + tempPos.y);
        playerBoard.AIShootPlayer(tempPos);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Z) {
            userInput.toggleZ();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    class MainPanel extends JPanel {
        MainPanel() {
            setLayout(new BorderLayout(500,500));
            add(playerBoard.thisBoard, BorderLayout.LINE_START);
            add(enemyBoard.thisBoard, BorderLayout.LINE_END);
        }
    }
}
