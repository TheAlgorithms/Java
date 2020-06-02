package Others;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * RR scheduling implememtation with the process queue as a list
 *
 * @author Badal
 * @data 2 june 2020
 */



public class RR_Scheduling {

	/**
	 * Calculate the TurnAroundTime i.e burst_time + waiting time
	 * 
	 * @param n , number of processes
	 * @param wTime , Waiting time of process
	 * @param bTime , Burst time of processes
	 * @param arrivalTime
	 * @param tat, TurnAround Time of processes , to be calculated.
	 */
	
	 private static void findTurnAroundTime(int n, int[] wTime, int[] bTime, int[] arrivalTime, int[] tat){
		// for all processes
		for( int i = 0; i < n; i++ ){	
			tat[i] = bTime[i] + wTime[i];	// it is equalent to completiontime - arrivaltime
		}
	}

	/**
	 * It find the wainting of the processes envolve in the execution
	 * 
	 * @param n
	 * @param bTime
	 * @param arrivalTime
	 * @param quantum
	 * @param wTime
	 */
	private static void findWaitingTime(int n, int[] bTime, int[] arrivalTime ,int quantum, int[] wTime){
		// array to store remaing time of respective process
		int[] rem_bt = new int[n];

		int cpu_time = 0;

		// initially it will be equal to the burst time of all processes respectively
		for( int i = 0; i < n; i++ ) rem_bt[i] = bTime[i];

	

		// get the processes queue
		List<Integer> pQueue = new ArrayList<Integer>();

		// get the processes by thier arrival time
		while( pQueue.isEmpty() ){
			for( int i = 0; i < n; i++){
				if( arrivalTime[i] == cpu_time){
					pQueue.add(i);
				}
			}

			if( pQueue.isEmpty()){
				cpu_time++;
			}
		}



		// executed process
		int	executedProcess = 0;
		while(executedProcess != n){

			// process all pQueue processes
			for( int p = 0; p < pQueue.size(); p++){

				// if not done 
				if( rem_bt[ pQueue.get(p) ] > 0 ){

					// partail execution
					if( rem_bt[pQueue.get(p)] > quantum){
						
						// collect all the processes that arrive under the execution 
						for( int i = 0; i < n; i++){
							if(  arrivalTime[i] > cpu_time && arrivalTime[i] < cpu_time + quantum ){
								// add to pQueue
								pQueue.add(i);
							}
						}

						// update cpu_time
						cpu_time += quantum;

						// update rem_bt time
						rem_bt[pQueue.get(p)] -= quantum;
					}
					else{



						// collect all the processes that arrive under the execution 
						for( int i = 0; i < n; i++){
							if(  arrivalTime[i] > cpu_time && arrivalTime[i] < cpu_time + rem_bt[pQueue.get(p)]){
								// add to pQueue
								pQueue.add(i);
							}
						}

						// udpate cpu_time
						cpu_time += rem_bt[pQueue.get(p)];

						// completely executed
						rem_bt[pQueue.get(p)] = 0;

						// fetch completion time and waiting time
						int TurnAroundTime = cpu_time - arrivalTime[pQueue.get(p)];
						wTime[pQueue.get(p)] = TurnAroundTime - bTime[pQueue.get(p)];

						//update execute process
						executedProcess++;
						
					}
				}
			

			
				// get the processes by thier arrival time
				for( int i = 0; i < n; i++ ){
					if( arrivalTime[i] == cpu_time){
						pQueue.add(i);
					}
				}
			}
			
		}
	}


	// take number of processes via command line
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ));

		int processes = Integer.parseInt(br.readLine());
		int[] arrivalTime = new int[processes];
		int[] burstTime = new int[processes];
		for( int i = 0; i < processes; i++ ){
			arrivalTime[i] = Integer.parseInt(br.readLine());
			burstTime[i] = Integer.parseInt(br.readLine());
		}

		int[] waitingTime = new int[processes];
		int quantum = Integer.parseInt(br.readLine());

		// find and print the waiting time of process after the RR scheduling 
		findWaitingTime(processes, burstTime, arrivalTime, quantum, waitingTime);
		for( int i = 0; i < processes; i++){
			System.out.printf("Waiting time : P(%d) : %d\n" , i, waitingTime[i]);
		}

		// print th turnAroundTime of the process,
		int[] tat = new int[processes];
		findTurnAroundTime(processes, waitingTime, burstTime, arrivalTime, tat);
		for( int i = 0; i < processes; i++ ){
			System.out.printf("Turn Around Time : P(%d) : %d\n", i, tat[i]);
		}
	}

}