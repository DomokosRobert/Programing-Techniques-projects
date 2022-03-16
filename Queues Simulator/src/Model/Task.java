package Model;

public class Task {
    private int arrivalTime;
    private int processingTime;
    private int ID;

    public Task(int arrivalTime, int processingTime, int ID) {
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
        this.ID=ID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public int getProcessingTime() {
        return processingTime;
    }
    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "("+ ID + "," + getArrivalTime() + "," + getProcessingTime() + ");";
    }
}
