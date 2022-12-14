package pl.lssystems.adventofcode.v2022.day09;

public class Head implements RopeElement {
    private int x = 0;
    private int y = 0;
    private final Tail tail;

    public Head(Tail tail) {
        this.tail = tail;
        this.tail.setParent(this);
        Move.addMove(Direction.UP, 0, 0); // Start position
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public char getId() {
        return 'H';
    }

    @Override
    public RopeElement getChild() {
        return tail;
    }

    public void move(Direction direction, int steps) {
        for (int i = 0; i < steps; i++) {
            switch (direction) {
                case UP: y++; break;
                case DOWN: y--; break;
                case LEFT: x--; break;
                case RIGHT: x++; break;
            }
            tail.updatePosition(direction);
            Board.get().update(this);
        }
    }

}
