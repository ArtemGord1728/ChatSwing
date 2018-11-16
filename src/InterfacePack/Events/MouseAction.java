package InterfacePack.Events;

public class MouseAction extends Event {

    private int x, y;
    private boolean isDragged;

    public MouseAction(int x, int y, boolean isDragged) {
        super(Type.MOUSE_MOVED);
        this.x = x;
        this.y = y;
        this.isDragged = isDragged;
    }

    public boolean isDragged() {
        return isDragged;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
