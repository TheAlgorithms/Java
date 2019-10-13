class OptimizedBubbleSort {
  public static void main(String args[]) {
    int a[]={7,2,9,3,6,3,7,1,0,2,8};
    bubblesort(a);
    for (int i=0;i<a.length;i++) {
    System.out.print(a[i]+" ");
    }
  }
  public static void bubblesort(int[] a) {
    for(int i=1; i<a.length; i++) {
      boolean is_sorted = true;

      for(int j=0; j < a.length - i; j++) {
        if(a[j] > a[j+1]) {
           int temp = a[j];
           a[j] = a[j+1];
           a[j+1] = temp;
           is_sorted = false;
        }
      }
      if(is_sorted) return;
    }
  }
}
© 2019 GitHub, Inc.
