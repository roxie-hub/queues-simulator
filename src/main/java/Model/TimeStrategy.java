package Model;

import java.util.List;

public class TimeStrategy implements Strategy{
    @Override
    public void addTask(List<Server> servers, Task t) {
        Server queue = null;
        int minimWPeriod = -10;
        if(servers != null){
            for(Server q: servers){
                if(minimWPeriod<0){
                    minimWPeriod = q.getWaitingPeriod();
                    queue = q;
                } else if( minimWPeriod > q.getWaitingPeriod()) {
                    minimWPeriod = q.getWaitingPeriod();
                    queue = q;
                }
            }
            if(queue != null) {
                queue.getTasks().add(t);
                queue.setAddWP(t.getServiceTime());
            }
        }
    }
}
