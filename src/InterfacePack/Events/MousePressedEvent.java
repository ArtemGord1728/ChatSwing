package InterfacePack.Events;

public class MousePressedEvent extends MouseButtonEvents {
    public MousePressedEvent(Type t, int keyCode, int x, int y) {
        super(Type.MOUSE_PRESSED, keyCode, x, y);
    }
}
