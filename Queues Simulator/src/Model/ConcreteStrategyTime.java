package Model;

import java.util.List;

public class ConcreteStrategyTime implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task t) {
        Server min = servers.get(0);
        int time, tmin;
        tmin = min.getWaitingPeriod().intValue();
        for(Server s: servers){
            time = s.getWaitingPeriod().intValue();
            if(time < tmin)
            {
                tmin = time;
                min = s;
            }
        }

        min.addTask(t);
    }
}
