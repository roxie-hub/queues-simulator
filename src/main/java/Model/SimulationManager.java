package Model;

import View.Gui;
import View.ResGui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static View.Gui.*;
import static java.lang.Math.abs;
import static java.util.Collections.sort;

public class SimulationManager implements Runnable{
    public int simulationTime = x6;
    public int maxServiceTime = x5;
    public int minServiceTime = x4;
    public int noOfServers = x1;
    public int noOfClients = x;
    public int minArrivalTime = x2;
    public int maxArrivalTime = x3;
    private Scheduler scheduler;
    private List<Task> tasks;
    private FileWriter fw;
    private ResGui frame;

    public SimulationManager(ResGui view){
        frame = view;
        scheduler = new Scheduler(noOfServers);
        generateNRandomTasks();
        Gui.threadSimulationManager = new Thread(this);
        try {
            fw = new FileWriter("testThread.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void generateNRandomTasks(){
        tasks = new ArrayList<Task>();
        int servingT = 0;
        int arrivalT = 0;
        for(int j = 1; j <= noOfClients; j++){
            Random random = new Random();
            int n = maxServiceTime - minServiceTime +1;
            int i = abs(random.nextInt())%n;
            servingT = minServiceTime + i;

            random = new Random();
            n = maxArrivalTime - minArrivalTime + 1;
            i = abs(random.nextInt())%n;
            arrivalT = minArrivalTime + i;

           tasks.add(new Task(j,arrivalT, servingT));
        }
        sort(tasks);
    }
    @Override
    public void run() {
        int currentTime = 0;

        while(currentTime <= simulationTime){
            frame.setRez(makeString(currentTime));
            print(currentTime);//print si upt

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentTime ++;
            addTasks(currentTime);
        }
        Server.stop = false;
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void print(int currentTime) {
        System.out.println("Time: " + currentTime);
        System.out.print("Clients: ");
        for(Task t : tasks){
            System.out.print(t.toString()+"; ");
        }
        int cnt = 1;
        System.out.println();
        if(scheduler.getServers()!=null)
        for(Server s : scheduler.getServers()){
            System.out.println("Queue" + cnt + " :" + s.toString());
            cnt++;
        }
        writeInFile(makeString(currentTime));
        updateServ();
    }

    public void addTasks(int currentTime){
        for(Task t : tasks){
            if(currentTime == t.getArrivalTime()) {
                scheduler.dispatchTask(t);
            }
            else break;
        }
        int k=0;
        while(k < tasks.size()){
            if(currentTime == tasks.get(k).getArrivalTime()){
                tasks.remove(k);
            }
            else k++;
        }
    }

    private String makeString(int currentTime) {
        String s = "";
        s+="Time: " + currentTime + "\n";
        s+="Clients: ";
        for(Task t : tasks){
           s+=t.toString()+"; ";
        }
        int cnt = 1;
        s+="\n";
        if(scheduler.getServers()!=null)
            for(Server sv : scheduler.getServers()){
               s+="Queue" + cnt + " :" + sv.toString() +"\n";
                cnt++;
            }
        return s;
    }

    public void writeInFile(String s){
        try {
            fw.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateServ(){
        Task t;
        for(Server s : scheduler.getServers()){
            t = s.getTasks().peek();
            if(t!=null) {
                t.decST();
                s.decWP();
                if (t.getServiceTime() == 0)
                    s.getTasks().remove(t);
            }
        }
    }
}