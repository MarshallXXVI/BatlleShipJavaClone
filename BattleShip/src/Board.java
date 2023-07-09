import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.List;


/// Board class will hold all of game Logic.
public class Board {
    int type;
    static boolean ifVertical = true;
    private Tile[][] field = new Tile[10][10];
    BoardPanel thisBoard;
    public boolean ifBoardBattleState = false;

    Board(int typ) {
        this.type = typ;
        thisBoard = new BoardPanel(typ);
    }

    public static final int[] BOAT_SIZES = {5,4,3,3,2};
    private int indexOfBoat = 0;

    class BoardPanel extends JPanel {
        BoardPanel(int typ) {
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            GridLayout layout = new GridLayout(field.length,
                    field.length);
            setLayout(layout);

            for (int i = 0; i < field.length ; i++)   {

                for (int j = 0; j < field.length; j++)  {
                    Tile tempTile = new Tile(i, j, typ);
                    add(tempTile);
                    field[i][j] = tempTile;
                }
            }
        }
    }

    public void RenderHoverTargetToFire(Position pos) {
        this.field[pos.x][pos.y].HoverTarget();
    }

    public void DeleteTargetToFire(Position pos) {
        this.field[pos.x][pos.y].HoverDefault();
    }

    /**
     * player function.
     */
    public void printPos(Position pos) {
        System.out.println(pos.x + " : " + pos.y);
        switch (this.type) {
            case 0:
                if (indexOfBoat < BOAT_SIZES.length) {
                    placeShip(new Ship(pos, BOAT_SIZES[indexOfBoat], ifVertical), pos);
                }
                if (indexOfBoat == 5) {
                    Game.GameState = Game.GameState + 1;
                    this.ifBoardBattleState = true;
                    System.out.println("BattleState");
                }
                break;
            case 1:
                this.field[pos.x][pos.y].ThisTileIsMark();
                break;
        }
    }


    private boolean isValidToPlaceShip(Position pos) {
        if(pos.x < 0 || pos.y < 0) return false;

        if(ifVertical) { // handle the case when vertical
            if(pos.y > 10 || pos.x + BOAT_SIZES[indexOfBoat] > 10) return false;
            for(int x = 0; x < BOAT_SIZES[indexOfBoat]; x++) {
                if(this.field[pos.x+x][pos.y].isShip()) return false;
            }
        } else { // handle the case when horizontal
            if(pos.y + BOAT_SIZES[indexOfBoat] > 10 || pos.x > 10) return false;
            for(int y = 0; y < BOAT_SIZES[indexOfBoat]; y++) {
                if(this.field[pos.x][pos.y+y].isShip()) return false;
            }
        }
        return true;
    }
    public void RenderHoveringShip(Position pos) {
        if (isValidToPlaceShip(pos)) {
            RenderHoverPlacing(pos);
        }
    }
    public void placeShip(Ship ship, Position pos) {
        if (!isValidToPlaceShip(pos)) {
            return;
        }
        if (ship.isSideways) {
            for (int x = 0; x < ship.segments; x++) { //case just now ship is Vertical.
                this.field[pos.x + x][pos.y].PlaceShip(ship);
            }
        } else {
            for (int y = 0; y < ship.segments; y++) { //case just now ship is Horizontal.
                this.field[pos.x][pos.y + y].PlaceShip(ship);
            }
        }
        indexOfBoat = indexOfBoat + 1;
    }

    private void RenderHoverPlacing(Position pos) {
        if (ifVertical) {
            for (int x = 0; x < BOAT_SIZES[indexOfBoat]; x++) { //case just now ship is Vertical.
                if (this.field[pos.x + x][pos.y].shipAtThisTile != null) {
                    return;
                }
                this.field[pos.x + x][pos.y].HoverValid();
            }
        } else {
            for (int y = 0; y < BOAT_SIZES[indexOfBoat]; y++) { //case just now ship is Horizontal.
                if (this.field[pos.x][pos.y+ y].shipAtThisTile != null) {
                    return;
                }
                this.field[pos.x][pos.y + y].HoverValid();
            }
        }
    }

    public void DeleteShipShadow(Position pos) {
        if (!isValidToPlaceShip(pos)) {
            return;
        }
        if (ifVertical) {
            for (int x = 0; x < BOAT_SIZES[indexOfBoat]; x++) { //case just now ship is Vertical.
                if (this.field[pos.x + x][pos.y].shipAtThisTile != null) {
                    return;
                }
                this.field[pos.x + x][pos.y].HoverDefault();
            }
        } else {
            for (int y = 0; y < BOAT_SIZES[indexOfBoat]; y++) { //case just now ship is Vertical.
                if (this.field[pos.x][pos.y+ y].shipAtThisTile != null) {
                    return;
                }
                this.field[pos.x][pos.y + y].HoverDefault();
            }
        }
    }

}
