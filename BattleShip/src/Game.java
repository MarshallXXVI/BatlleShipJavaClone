import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Game implements KeyListener {
    public static Board playerBoard;
    public static Board enemyBoard;
    MainPanel mainPanel;
    public static HandleInputUser userInput;

    public static int GameState = 0;
    Game() {
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
        Ship first = new Ship(new Position(1, 2), 5, true);
        enemyBoard.placeShip(first, new Position(1, 2));
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
