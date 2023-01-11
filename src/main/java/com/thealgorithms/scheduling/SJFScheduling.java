package com.thealgorithms.scheduling;

import com.thealgorithms.devutils.entities.ProcessDetails;

import java.util.ArrayList;


public class SJFScheduling {
    protected ArrayList<ProcessDetails> processes;
    protected ArrayList<String>schedule ;

    SJFScheduling(final ArrayList<ProcessDetails> processes) {
        this.processes = processes;
        schedule=new ArrayList<String>();
        sortByArrivalTime();
    }
protected void sortByArrivalTime() {
        int size=processes.size(),i,j;
        ProcessDetails temp;
        for(i=0;i<size;i++)
        {
            for(j=i+1;j<size-1;j++)
            {
                if(processes.get(j).getArrivalTime()>processes.get(j+1).getArrivalTime())
                {
                    temp=processes.get(j);
                    processes.set(j,processes.get(j+1));
                    processes.set(j+1,temp);
                }
            }
        }

}
    public void scheduleProcesses() {
        createSchedule();
    }

    protected void createSchedule() {
        ArrayList<ProcessDetails> ready=new ArrayList<>();

        int size = processes.size(),runtime,time=0;
        int i=0,j,k=0;
        ProcessDetails running;

        if (size == 0) {
            return;
        }


        while(i<size)
        {
            while(k<size && processes.get(k).getArrivalTime()<=time)
            {
                ready.add(processes.get(k));
                k++;
            }

          running=findShortestJob(ready);
          if(running==null)
          {
              time++;
          }
          else {
              runtime = running.getBurstTime();
              for (j = 0; j < runtime; j++) {
                  time++;
              }
              schedule.add(running.getProcessId());
              ready.remove(running);
              i++;
          }
        }


    }


    private ProcessDetails findShortestJob(ArrayList<ProcessDetails> ReadyProcesses) {
if (ReadyProcesses.isEmpty()){
    return null;
}
        int size = ReadyProcesses.size();
        int i, minBurstTime = ReadyProcesses.get(0).getBurstTime(), temp, positionOfShortestJob = 0;
        for (i = 1; i < size; i++) {
            temp = ReadyProcesses.get(i).getBurstTime();
            if (minBurstTime > temp  ) {
                minBurstTime = temp;
                positionOfShortestJob = i;
            }
        }

        return ReadyProcesses.get(positionOfShortestJob);
    }





    }

