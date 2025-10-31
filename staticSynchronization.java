class Table{
    
    synchronized static void printTable(int n){
        
        for (int i = 1; i <=3; i++){
            
            System.out.println(n * i);
            try {
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

class Thread1 extends Thread{
    
    public void run() {
        Table.printTable(1);
    }
}

class Thread2 extends Thread {
    public void run() {
        Table.printTable(10);
    }
}

public class staticSynchronization {
    
    public static void main(String[] args){
        
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();
        t1.start();
        t2.start();
    }
}
