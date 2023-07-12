import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile extends JLabel {
        Ship shipAtThisTile = null;
        Position TilePos;

        int type;

        private boolean thisTileIsMarked = false;
        Tile(int x, int y, int typ) {
            this.type = typ;
            setPreferredSize(new Dimension(40, 40));
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            setOpaque(true);
            TilePos = new Position(x, y);
            addMouseListener(new TileMouseListener(x, y, typ));
            setOpaque(true);
            setBackground(Color.CYAN);
        }


        public void PlaceShip(Ship ship) {
            setOpaque(true);
            if (ship.ifVisible) {
                setBackground(Color.GRAY);
            } else {
                setBackground(Color.CYAN);
            }
            this.shipAtThisTile = ship;
        }

        public boolean isShip() {
            return this.shipAtThisTile != null;
        }
        public void HoverValid() {
            setOpaque(true);
            setBackground(Color.GREEN);
        }

        public void HoverTarget() {
            if (Game.GameState == 1) {
                setOpaque(true);
                setBackground(Color.BLUE);
            }
        }

        public void HoverDefault() {
            setOpaque(true);
            setBackground(Color.CYAN);
        }

        public void ThisTileIsMark() {
            thisTileIsMarked = true;
            setOpaque(true);
            if (shipAtThisTile != null) {
                shipAtThisTile.isDead = true;
                setBackground(Color.RED);
            } else {
                setBackground(Color.BLUE);
            }
        }
        class TileMouseListener implements MouseListener {
            public int x;
            public int y;

            public int type;

            TileMouseListener(int x, int y, int typ) {
                this.x = x;
                this.y = y;
                this.type = typ;
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Game.GameState == 0 && this.type == 1) {
                    return;
                }
                if (this.type == 0) {
                    Game.playerBoard.printPos(new Position(this.x, this.y));

                } else {
                    Game.enemyBoard.printPos(new Position(this.x, this.y));
                    Game.AIFireBack();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Game.userInput.targetTile = new Position(this.x, this.y);
                if (thisTileIsMarked ||
                        (Game.GameState == 0 && this.type == 1) ||
                        (Game.GameState == 1 && this.type == 0)) {
                    return;
                }
                switch (this.type) {
                    case 0:
                        if (shipAtThisTile == null && Game.GameState == 0) {
                            Game.playerBoard.RenderHoveringShip(new Position(this.x, this.y));
                        }
                        break;
                    case 1:
                        if (shipAtThisTile == null && Game.GameState == 1) {
                            Game.enemyBoard.RenderHoverTargetToFire(new Position(this.x, this.y));
                        }
                        if (shipAtThisTile != null && !shipAtThisTile.ifVisible) {
                            Game.enemyBoard.RenderHoverTargetToFire(new Position(this.x, this.y));
                        }
                        break;

                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (thisTileIsMarked) {
                    return;
                }
                if (Game.GameState == 1 && this.type == 0) {
                    return;
                }
                switch (this.type) {
                    case 0:
                        if (shipAtThisTile == null && Game.GameState == 0 && Game.playerBoard.type == 0) {
                            Game.playerBoard.DeleteShipShadow(new Position(this.x, this.y));
                        }
                        break;
                    case 1:
                        if (shipAtThisTile == null && Game.GameState == 1) {
                            Game.enemyBoard.DeleteTargetToFire(new Position(this.x, this.y));
                        }
                        if (shipAtThisTile != null && !shipAtThisTile.ifVisible) {
                            Game.enemyBoard.DeleteTargetToFire(new Position(this.x, this.y));
                        }
                        break;

                }
            }
        }
}
