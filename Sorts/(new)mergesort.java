package algorithm_homework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class mergesort {
		
    public static void merge (int[] array1, int[] array2, int[] array3) {

    	 int array1index = 0;
         int array2index = 0;
         int array3index = 0;     
         while (array1index < array1.length && array2index < array2.length ) {
         	if ( array1[array1index] < array2[array2index]) {
             	array3[array3index] = array1[array1index];
                     array1index++; } 
             else {
             	array3[array3index] = array2[array2index];
                     array2index++; }
             array3index++; 
         }
         if(array1index < array1.length) {
             while (array1index < array1.length) {
             	array3[array3index] = array1[array1index];
             	array1index++;
                     array3index++;
             }
         }
         if(array2index < array2.length) {
             while (array2index < array2.length) {
             	array3[array3index] = array2[array2index];
             	array2index++;
                     array3index++;
             }
         }
}

public static void merge_sort (int[] array) {
        int n = array.length;       
        if (n == 1) return;       
        int[] array1 = new int[n/2];
        int[] array2 = new int[n - n/2];
        for (int i = 0; i< n/2; i++) {
                array1[i] = array[i];
        }
        for (int i = 0; i< n - n/2; i++) {
                array2[i] = array[i + n/2];
        }       
        merge_sort(array1);
        merge_sort(array2);      
        merge(array1, array2, array);
}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileReader reader = new FileReader("C:\\Users\\CSE\\Desktop\\homwork\\input.txt");
    	BufferedReader rd = new BufferedReader(reader);
    	  int index = 0;
    	  int maxsize;
    	  String line = null;
    	  String getLine = "";
    	  while ((line = rd.readLine()) != null) {
    		   System.out.println(line);
    		   getLine = getLine + line;
    		   // 읽어서 최종 String에 저장시킨다
    		  }
    		  rd.close();
    		  StringTokenizer st = new StringTokenizer(getLine, " ");
    		  maxsize = st.countTokens();
    		  int[] array = new int[maxsize];
    		  while (st.hasMoreTokens()) {
    		  array[index]=Integer.parseInt(st.nextToken());
    		   // 짤라준 조각들을 배열에 저장
    		  index++;
    		  }  
    		  long start = System.currentTimeMillis(); //시작하는 시점 계산
    		  merge_sort(array);
    		  long end = System.currentTimeMillis(); //프로그램이 끝나는 시점 계산
    		  System.out.println( "실행 시간 : " + ( end - start )/1000.0 +"초");
    		  String st_list = "";
    		  FileWriter writer = new FileWriter("C:\\Users\\CSE\\Desktop\\homwork\\201302491_output.txt");
    		  // 정렬한 값들을 저장할 파일 생성
    		  for (int i = 0; i < array.length; i++) {
    		   st_list = st_list + " " + array[i];
    		   // 배열에서 하나씩 불러서 뒤에다 차곡차곡 연결한다( 파일에 넣을땐 string으로 넣어야 하기 떄문에 )
    		  }
    		  writer.write(st_list);
    		  writer.close();
    		  System.out.println("정렬종료");
    		     	    		  
	}

}

