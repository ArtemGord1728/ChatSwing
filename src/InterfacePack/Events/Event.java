package InterfacePack.Events;

public abstract class Event
{
    private Type type;
    public boolean handled;

    public enum Type {
        MOUSE_PRESSED,
        MOUSE_RELEASED,
        MOUSE_MOVED
    }

    public Event(Type t)
    {
        type = t;
    }

    public Type getType() {
        return type;
    }
}
