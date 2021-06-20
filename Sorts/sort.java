package Arrays;

class sort{
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();
    int[] a = new int[n];
    for(int i=0;i<b;i++){
      a[i]=scan.nextInt();
    }
    int temp;
    for(int i=0;i<n;i++){
      for(int j=i+1;j<n;j++){
        if(a[i]>a[j]){
          temp=a[j];
          a[j]=a[i];
          a[i]=temp;
        }
      }
    }
    for(int j=0;j<n;j++){
    System.out.print(a[j]+" ");
  }
}
