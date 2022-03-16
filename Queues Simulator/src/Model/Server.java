package Model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private double avrgWaiting = 0;

    public Server(){
        waitingPeriod = new AtomicInteger(0);
        tasks = new LinkedBlockingQueue<>();
    }

    public void addTask(Task newTask){
       tasks.add(newTask);
       waitingPeriod.getAndAdd(newTask.getProcessingTime());
       avrgWaiting = avrgWaiting + waitingPeriod.get();
    }
    public double getAvrgWaiting() {
        return avrgWaiting;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    /**
     *  In this overridden method we wait for each task to be accessible
     *  then wait until it's waitingPeriod is over and take it out of the list
     *
     * **/
    @Override
    public void run() {
        while(true){
            if(tasks.isEmpty())
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            Task t = null;
            try {
                t = tasks.peek();
                Thread.sleep(1000*t.getProcessingTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            waitingPeriod.getAndAdd(-t.getProcessingTime());
            try {
                tasks.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * toString for printing each queue and the clients that wait**/
    @Override
    public String toString() {
        if(tasks.isEmpty())
            return "closed";
        else
        {
            String s = "";
            for(Task t: getTasks()){
                s = s + t + " ";
            }
            return s;
        }
    }

    /**
     * return the tasks from the list
      **/
    public Task[] getTasks(){
       Task[] t = new Task[tasks.size()];
       int i=0;
       for(Task tsk: tasks)
           t[i++]=tsk;
       return t;
    }
}
