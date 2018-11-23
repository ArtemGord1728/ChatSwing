package ServerPack;


public class Server
{
    private Thread serverRun;
    private int client;
    private boolean isConnect = false;


    public boolean actionServer(int client){
        serverRun = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        return true;
    }
}