package Model;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    private List<Server> servers;
    private Strategy strategy;

    public Scheduler(int noServers){

        servers = new ArrayList<Server>();
        for(int q = 0; q < noServers; q++){
        Server serv = new Server();
        servers.add(serv);
        Thread t = new Thread(serv);
        t.start();}
        strategy = new TimeStrategy();
        }
    public List<Server> getServers() {
        return servers;
    }
    public void dispatchTask(Task t){
        strategy.addTask(servers, t);
    }
}
