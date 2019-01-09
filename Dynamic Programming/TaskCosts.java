package projectb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author dimig
 */
public class TaskCosts {
    
    private Scanner scanner;
    private File file;
    
    private int tasks;
    private int vms;
    
    private int[][] vmsPer;
    private int[][] vmsCom;
    private int[][] costs;
    
    
    private TaskCosts(String nameOfInputFile) throws FileNotFoundException{
        File file = new File(nameOfInputFile);
        scanner = new Scanner(file);
        tasks = scanner.nextInt();
        vms = scanner.nextInt();
        costs = new int[tasks][vms];
        vmsPer = new int[tasks][vms];
        vmsCom = new int[vms][vms];
        for(int i=0;i<tasks;i++){
            for(int j=0;j<vms;j++){
                vmsPer[i][j]=scanner.nextInt();
            }
        }
        for(int i=0;i<vms;i++){
            for(int j=0;j<vms;j++){
                vmsCom[i][j] = scanner.nextInt();
            }
        }
        for(int i=0;i<vms;i++){
            costs[0][i]=vmsPer[0][i];
        }
        for(int i=1;i<tasks;i++){
            for(int j=0;j<vms;j++){
                costs[i][j]=0;
            }
        }
    }
    
   
    public int getTasks(){
        return tasks;
    }
    
    
    public int findMin(int[] table){
        int min=table[0];
        for(int i=1;i<vms;i++){
            if(table[i]<min){
                min = table[i];
            }
        }
        return min;
    }
    
    
    public boolean lineContainsZero(int line){
        for(int i=0;i<vms;i++){
            if(costs[line][i]==0){
                return true;
            }
        }
        return false;
    }
    
    
    public void produceCost(int line){
        if(line<tasks){
            if(line-1==0 || !lineContainsZero(line-1)){
                int[] table = new int[vms];
                for(int i=0;i<vms;i++){
                    for(int j=0;j<vms;j++){
                        table[j]=costs[line-1][j] + vmsPer[line][i] + vmsCom[i][j];
                    }
                    int min = findMin(table);
                    costs[line][i]=min;
                }
                produceCost(line+1);
            }
            else{
                produceCost(line-1);
            }
        }
    }
    
    
    public void printCost(){
        for(int i=0;i<tasks;i++){
            for(int j=0;j<vms;j++){
                System.out.print(costs[i][j]+"  ");
            }
            System.out.println();
        }
    }
    
    
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 1){
            System.out.println("Insert the input file");
            return;
	}
        TaskCosts obj = new TaskCosts(args[0]);
        obj.produceCost(obj.getTasks()-1);
        obj.printCost();
    }
}
