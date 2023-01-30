import java.util.*;

public class JobSequncing {

    public static class Job{                        //Using a new class "Job"
        String job_id;int deadline;int profit;
        Job(String id, int pro, int dl){
            deadline=dl;job_id=id;profit=pro;
        }
    }

    public static void JobSequencingAlgo(ArrayList<Job> arr) {    
        Collections.sort(arr, (a,b) -> {                    //Sorting array on basis of deadlines
            return a.deadline-b.deadline;
        });
        //Printarray(arr);
        ArrayList<Job> result = new ArrayList<>();          // initialise the result array and maxHeap
        PriorityQueue<Job> MaxHeap = new PriorityQueue<>((a, b) -> { return b.profit - a.profit; });
        for(int i=arr.size()-1;i>=0;i--){                   //starting iteration from end
            int slot_ava;
            if(i==0){                                       //slots between deadlines
                slot_ava=arr.get(i).deadline;
            }else{
                slot_ava=arr.get(i).deadline - arr.get(i-1).deadline;
            }
            MaxHeap.add(arr.get(i));                        // include the profit of job(as priority), deadline and job_id in maxHeap
            while(slot_ava>0 && MaxHeap.size()>0){
                Job job = MaxHeap.remove();                 // get the job with max_profit
                slot_ava--;                                 // reduce the slots
                result.add(job);                            // include the job in the result array
            }
        }
        Collections.sort(result, (a, b) -> {                //once again sort the result array by their deadlines
            return a.deadline - b.deadline;
        });
        int totalprofit=0;
        System.out.print("Hence, work will be done in order <");
        for (Job job : result) {
            totalprofit=totalprofit+job.profit;
            System.out.print(job.job_id + " ");
        }
        System.out.println("> and thus the total profit gain is "+totalprofit);
        System.out.println();
    }

    public static void Printtable(ArrayList<Job> arr) {
        System.out.println("\nHence, we have -> ");
        System.out.println("\nJob Index         Profit         Deadline\n");
        for(int j=0;j<arr.size();j++){
            System.out.println("   "+arr.get(j).job_id+" ".repeat(16-(arr.get(j).job_id).length())+arr.get(j).profit+" ".repeat(17-String.valueOf(arr.get(j).profit).length())+arr.get(j).deadline);
        }
        System.out.println();
    }

    /*public static void Printarray(ArrayList<Job> arr) {                                         //Function to print sorted array
        System.out.println();
        for(int j=0;j<arr.size();j++){
            System.out.println(arr.get(j).job_id+" "+arr.get(j).profit+" "+arr.get(j).deadline);
        }
    }*/

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter number of jobs -> ");int jobs = scan.nextInt();
        ArrayList<Job> jobarr = new ArrayList<Job>();       //Not using Vector bcause it is single threaded and slow in performance
        for(int i=0;i<jobs;i++){
            System.out.println("\nFor Job "+(i+1)+" ->");
            System.out.print("\nEnter the profit and deadline (space separated) ->");
            jobarr.add(new Job("J"+(i+1), scan.nextInt(), scan.nextInt()));
        }scan.close();
        Printtable(jobarr);
        JobSequencingAlgo(jobarr);
    }
}
/*Sample Input ->
8
2, 60
6, 90
3, 30
4, 90
1, 30
4, 60
1, 70
6, 100*/
