pacakge ArraysList;

class sum{
  
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    ArrayList<Integer> a = new ArrayList<Integer>();
    int n = scan.nextInt();
    int sum=0;
    for(int i=0;i<n;i++){
      a.add(i)=scan.nextInt();
      sum = sum+a.get(i);
    }
    System.out.println(sum);
  }
}
