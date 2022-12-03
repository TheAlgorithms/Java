import java.util.*;
/**
 * This class implements a main function that takes input from the user and stores them in arrays
 * There are 3 algos here namely KnapsackFrac for Fractional quantities, Knapsack01 for 0 or 1 quantity and then Knapsack01DP from dynamic programming
 */
public class KnapsackInput {

    public static void KnapsackFrac(float a,Float arr1[],Float arr2[],Float[] arr3){
        float arr4[]= new float[arr1.length];
        int i=0;
        while(sum(arr4)<a){
            int highestpriority = indexmax(arr3);
            arr3[highestpriority]=(float) 0;
            arr4[i]=arr1[highestpriority];
            i++;
        }
        float quan=1;
        if(sum(arr4)>a){
            int last=last(arr4);
            float constant=sum(arr4)-arr4[last];
            quan=(a-constant)/arr4[last];
        }float totalp=0;
        for(int k=0;k<=last(arr4);k++){
            float quantity=1;
            if(k==last(arr4)){
                quantity = quan;
            }else{
                quantity = 1;
            }
            totalp=totalp+(quantity*arr2[Arrays.<Float>asList(arr1).indexOf(arr4[k])]);
            System.out.println(quantity+" quantity of Item "+(Arrays.<Float>asList(arr1).indexOf(arr4[k])+1)+" of weight "+arr1[Arrays.<Float>asList(arr1).indexOf(arr4[k])]+" Kgs.");
        }
        System.out.println("Total profit gain -> "+totalp+" Rs.");
    }
    
    public static void Knapsack01(float a,Float arr1[],Float arr2[],Float[] arr3){
        float arr4[]= new float[arr1.length];
        int i=0;
        while(sum(arr4)<a){
            int highestpriority = indexmax(arr3);
            arr3[highestpriority]=(float) 0;
            arr4[i]=arr1[highestpriority];
            i++;
        }
        if(sum(arr4)>a){
            int last=last(arr4);
            float constant=sum(arr4)-arr4[last];
            if(Arrays.<Float>asList(arr1).contains(a-constant)){
                if(pass(arr4, a-constant)){
                    arr4[last]=0;
                }else{
                    arr4[last]=arr1[Arrays.<Float>asList(arr1).indexOf(a-constant)];
                }
            }else{
                arr4[last]=(float) 0;
            }
        }float totalp=0;
        for(int k=0;k<=last(arr4);k++){
            totalp=totalp+(arr2[Arrays.<Float>asList(arr1).indexOf(arr4[k])]);
            System.out.println("Item "+(Arrays.<Float>asList(arr1).indexOf(arr4[k])+1)+" of weight "+arr1[Arrays.<Float>asList(arr1).indexOf(arr4[k])]+" Kgs.");
        }
        System.out.println("Total profit gain -> "+totalp+" Rs.");
    }

    public static void Knapsack01DP(int no_items,float max_weight,Float arr1[],Float arr2[]) {
        float answer= 0;float weight=0;
        String finalstring="";
        for(int i=0;i<Math.pow(2, no_items);i++){
            weight=0;
            String computation = Integer.toBinaryString(i);
            String computation_basis = ("0".repeat(4-computation.length()))+Integer.toBinaryString(i);
            for(int j=0;j<computation_basis.length();j++){
                weight=weight+(arr1[j]*(Integer.valueOf(Character.toString(computation_basis.charAt(j)))));
            }
            if(weight<=max_weight && weight>=answer){
                answer=weight;
                finalstring=computation_basis;
            }
        }
        float totalp=0;
        for(int k=0;k<no_items;k++){
            totalp=totalp+(arr2[k]*(Integer.valueOf(Character.toString(finalstring.charAt(k)))));
            if(finalstring.charAt(k)=='1'){
                System.out.println("Item "+(k+1)+" of weight "+arr1[k]+" Kgs.");
            }
        }
        System.out.println("Total profit gain -> "+totalp+" Rs.");
    }

    /**
     * @param arr1
     * @param arr2
     * @param arr3
     * prints out table of inputs and there ratio
     */
    public static void Printtable(Float arr1[],Float arr2[],Float arr3[]) {   
        System.out.println("Item No.       Weight         Profit         P/W Ratio\n");
        for(int j=0;j<arr1.length;j++){
            System.out.println("\n"+(j+1)+" ".repeat(15-Integer.toString(j).length())+arr1[j]+" ".repeat(15-String.valueOf(arr1[j]).length())+arr2[j]+" ".repeat(15-String.valueOf(arr2[j]).length())+arr3[j]);
        }
    }

    public static void Printarray(float arr1[]) {   
        for(int j=0;j<arr1.length;j++){
            System.out.println(arr1[j]);
        }
    }

    public static boolean pass(float [] arr, float n) {
        for(int i=0;i<arr.length;i++){
            if(arr[i]==n){
                return true;
            }else{continue;}
        }return false;
    }

    public static float sum(float arr[]){
        float sum=0;
        for(int i=0;i<arr.length;i++){
            sum=sum+arr[i];
        }
        return sum;
    }

    public static int last(float arr[]){
        int last=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]>0){
                last=i;
            }
        }
        return last;
    }

    public static int indexmax(Float[] arr) {
        float max=arr[0];
        for(int i=0;i<arr.length;i++){
            if(arr[i]>max){
                max=arr[i];
            }
        }
        return Arrays.<Float>asList(arr).indexOf(max);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter max possible weight-> ");
        float max=sc.nextFloat();
        System.out.print("Enter number of items-> ");
        int n=sc.nextInt();
        Float wgarr[] = new Float[n];
        Float parr[]= new Float[n];
        Float ratio[] = new Float[n];
        Float ratiocopy[] = new Float[n];
        for(int i=0;i<n;i++){
            System.out.print("\nFor item "+(i+1)+"->\nWeight (in kg)-> ");
            wgarr[i]=sc.nextFloat();
            System.out.print("Profit (in Rs.)-> ");
            parr[i]=sc.nextFloat();
            ratiocopy[i]=ratio[i]=parr[i]/wgarr[i];
        }sc.close();
        System.out.println("\nHence, we have -> \n");
        Printtable(wgarr, parr, ratio);
        System.out.println("\nThus, Required Composition in fractions -> \n");
        KnapsackFrac(max, wgarr, parr, ratio);
        System.out.println("\nand in 0/1 -> \n");
        Knapsack01(max, wgarr, parr, ratiocopy);
        System.out.println("\nand in 0/1 DP -> \n");
        Knapsack01DP(n, max, wgarr, parr);
    }
}
