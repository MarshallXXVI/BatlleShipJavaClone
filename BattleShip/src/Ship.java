public class Ship {
    private Position gridPosition;
    public int segments;
    public boolean isSideways;
    public Ship(Position gridPosition, int segments, boolean isSideways) {
        this.gridPosition = gridPosition;
        this.segments = segments;
        this.isSideways = isSideways;
    }
}
