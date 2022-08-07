package Model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    public static boolean stop = true;
    private AtomicInteger waitingPeriod;
    private BlockingQueue<Task> tasks;

    public Server(){
        waitingPeriod = new AtomicInteger(0);
        tasks = new LinkedBlockingDeque<Task>();
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public int getWaitingPeriod() {
        return waitingPeriod.get();
    }

    @Override
    public void run() {
        while(stop){
            Task task = tasks.peek();
            try { if(task!=null)
              Thread.sleep(task.getServiceTime()*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(stop == false) {
                Thread.interrupted();
            }
        }
    }

    @Override
    public String toString(){
        String s = "";
        if(tasks.size() == 0)
            return "Closed";

        for(Task t : tasks) {
            s+=t.toString() + ";";
        }
        return s;
    }

    public void setAddWP(int x){
        waitingPeriod.set(waitingPeriod.get()+x);
    }

    public void decWP() {
        waitingPeriod.decrementAndGet();
    }
}
