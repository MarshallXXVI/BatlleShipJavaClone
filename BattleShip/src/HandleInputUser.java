import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HandleInputUser {

    Position targetTile;

    HandleInputUser() {

    }

    void toggleZ() {
        Game.playerBoard.DeleteShipShadow(targetTile);
        Board.ifVertical = !Board.ifVertical;
    }
}
