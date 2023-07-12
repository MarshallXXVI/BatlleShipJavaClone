public class Ship {
    public Position gridPosition;
    public int segments;
    public boolean isVertical;
    public boolean ifVisible;
    public boolean isDead = false;
    public Ship(Position gridPosition, int segments, boolean isVertical, boolean ifVisible) {
        this.gridPosition = gridPosition;
        this.segments = segments;
        this.isVertical = isVertical;
        this.ifVisible = ifVisible;
    }
}
