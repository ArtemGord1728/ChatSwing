package ServerClientsFunctionality;

import java.util.Date;

public interface ServerFunctionality
{
    void receive();
    void gettingMessage();
    void structMessage(Date date, String message);
}
