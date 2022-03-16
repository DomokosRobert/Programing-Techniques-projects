package Controller;

import Model.Scheduler;
import Model.SelectionPolicy;
import Model.Server;
import Model.Task;
import View.GUI;
import View.SimulationFrame;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class SimulationManager implements Runnable {
    public int timeLimit;
    public int maxProcessingTime;
    public int minProcessingTime;
    public int numberOfServers;
    public int numberOfClients;
    public int minArivalTime;
    public int maxArivalTime;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    private Scheduler scheduler;
    private SimulationFrame frame;
    private List<Task> generatedTasks;
    private Writer log;
    private double avrgProcessingTime;
    private int peakTime;
    private int peakClients = 0;

    public SimulationManager(int timeLimit, int maxProcessingTime, int minProcessingTime, int numberOfServers, int numberOfClients, int minArivalTime, int maxArivalTime) {
        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.minArivalTime = minArivalTime;
        this.maxArivalTime = maxArivalTime;
        scheduler = new Scheduler(numberOfServers);
        scheduler.changeStrategy(selectionPolicy);
        generateNRandomTasks();
        averageProcess();
        try {
            log = new FileWriter("Log.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * this method computes the averageProcessTime**/
    public void averageProcess(){
        for(Task t: generatedTasks)
        {
            avrgProcessingTime = avrgProcessingTime + t.getProcessingTime();
        }
        avrgProcessingTime = avrgProcessingTime/numberOfClients;
    }
    public void setFrame(SimulationFrame frame) {
        this.frame = frame;
    }
    /**
     * this method generates the radnom tasks**/
    private void generateNRandomTasks(){
        generatedTasks= new ArrayList<>();
        Random r = new Random();
        for(int i=0;i<numberOfClients;i++){
            Task t = new Task(minArivalTime+r.nextInt(maxArivalTime-minArivalTime+1),minProcessingTime+ r.nextInt(maxProcessingTime-minProcessingTime+1),i);
            generatedTasks.add(t);
        }
        Collections.sort(generatedTasks, Comparator.comparingInt(Task::getArrivalTime));

    }
    /**
     * function used for printing the required results in the wanted way
     * **/
    public String currentText( int currentTime){
        String s = "";
        s = s + "Time " + currentTime + "\n";
        s = s + "Waiting clients: ";
        for(Task t: generatedTasks)
            s = s + t;
        s = s + "\n";
        int i=1;
        for(Server ser: scheduler.getServers()) {
            s = s + "Queue "+ (i++) + ": " + ser + "\n";
        }
        return s;
    }
    /**
     * prints on the frame and on the Log.txt file the required output
     * **/
    public void writeText(String s){
        frame.getSimulation().setText(frame.getSimulation().getText() + s);
        try {
            log.write(s);
            log.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * prints the average waiting and process time and also the peak hour
     * while computing the average waiting time**/
    public void averageWaitText(){
        double avrgWait = 0;
        for(Server s: scheduler.getServers())
        {
            avrgWait=avrgWait+s.getAvrgWaiting();
        }
        avrgWait = avrgWait/numberOfClients;
        writeText("Average waiting time: " + avrgWait + "\n");
        writeText("Average processing time: " + avrgProcessingTime + "\n");
        writeText("Peak hour: " + peakTime + "\n");
    }
    /**
     * this method computes the peak hour**/
    public void peakText(int currentTime){
        int currentClients = 0;

        for(Server s: scheduler.getServers()) {
            currentClients = currentClients + s.getTasks().length;
            if (s.getTasks().length != 0) {
                Task t = s.getTasks()[0];
                t.setProcessingTime(t.getProcessingTime() - 1);
            }
        }
        if(currentClients > peakClients)
        {
            peakClients = currentClients;
            peakTime= currentTime;
        }
    }
    /**
     * here we are puting the clients to the queues while the currentTime is lower than the timeLimit
     * and when the arrival time is equal with the current time
     * after that the client is deleted from the list of generated task
     * we print the required output by calling the methods already mentioned
     * **/
    @Override
    public void run() {
        int currentTime = 0;
        while( currentTime < timeLimit){
            List<Task> delClient= new ArrayList<>();
            for(int i=0;i<generatedTasks.size();i++){
                Task t = generatedTasks.get(i);
                if (currentTime == t.getArrivalTime()){
                    scheduler.dispatchTask(t);
                    delClient.add(t);
                }
            }
            generatedTasks.removeAll(delClient);
            writeText(currentText(currentTime));
            peakText(currentTime);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentTime++;
        }
        averageWaitText();
        try {
            log.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Queues Simulator");
        frame.setContentPane(new GUI().getFirstPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        GUI.frame=frame;
    }
}
