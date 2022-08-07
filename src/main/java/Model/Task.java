package Model;

public class Task implements Comparable<Task>{
    private int id;
    private int arrivalTime;
    private int serviceTime;

    public Task(int id, int arrivalTime, int serviceTime){
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.id = id;
    }

    public int getServiceTime() {
        return serviceTime;
    }
    public int getArrivalTime() {return arrivalTime;}
    @Override
    public String toString(){
        return "(" + this.id + "," + this.arrivalTime + "," + this.serviceTime + ")";
    }

    @Override
    public int compareTo(Task o) {
        if(o.arrivalTime > this.arrivalTime) return -1;
        else if(o.arrivalTime < this.arrivalTime) return 1;
        return 0;
    }

    public void decST() {
        serviceTime--;
    }
}
