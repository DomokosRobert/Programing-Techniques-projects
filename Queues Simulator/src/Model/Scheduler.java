package Model;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;

    private Strategy strategy;

    /**
     * in this constructor we create for each server from the list a thread
     **/
    public Scheduler(int maxNoServers){
        servers = new ArrayList<>();
        for(int i=0;i<maxNoServers;i++){
            Server s = new Server();
            servers.add(s);
            new Thread(s).start();
        }
    }

    /**
     * maintains the instance of the strategies**/
    public void changeStrategy(SelectionPolicy policy){

        if(policy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ConcreteStrategyQueue();
        }
        if(policy == SelectionPolicy.SHORTEST_TIME){
            strategy = new ConcreteStrategyTime();
        }
    }
    public void dispatchTask(Task t){
        strategy.addTask(servers,t);
    }

    public List<Server> getServers(){
        return servers;
    }

}
