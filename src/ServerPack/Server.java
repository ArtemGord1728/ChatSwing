package ServerPack;


public class Server
{
    private Thread serverRun;
    private int client;
    private boolean isConnect = false;

    public Server()
    {
        System.out.println("Server start!");
    }

    public boolean actionServer(int client){
        serverRun = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        return true;
    }
}