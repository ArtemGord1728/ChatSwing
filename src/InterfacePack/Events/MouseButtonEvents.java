package InterfacePack.Events;

public class MouseButtonEvents extends Event {
    private int keyCode, x, y;

    public MouseButtonEvents(Type t, int keyCode, int x, int y) {
        super(t);
        this.keyCode = keyCode;
        this.x = x;
        this.y = y;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
