public class Position {
    public static final Position DOWN = new Position(0,1);
    public static final Position UP = new Position(0,-1);
    public static final Position LEFT = new Position(-1,0);
    public static final Position RIGHT = new Position(1,0);
    public static final Position ZERO = new Position(0,0);
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
