package Model;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t) {
        Server min = servers.get(0);
        int l, lmin;
        lmin = min.getTasks().length;
        for(Server s: servers){
            l = s.getTasks().length;
            if(l < lmin)
            {
                lmin = l;
                min = s;
            }
        }

        min.addTask(t);
    }
}
