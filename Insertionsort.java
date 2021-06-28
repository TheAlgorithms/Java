import org.junit.Test;
import static org.junit.Assert.*;

public class AlgorithmsTest{

   @test
   public void test1(){
    Integer[] integers = {10};
    InsertionSort sort = new InsertionSort();
    sort.sort(integers);
    }

   @test
   public void test2(){
    Integer[] integers = {-1, 1, 0, 100};
    InsertionSort sort = new InsertionSort();
    sort.sort(integers);
    }

   @test
   public void test3){
    Integer[] integers = {100, 56, 15, 15};
    InsertionSort sort = new InsertionSort();
    sort.sort(integers);
    }

   @test
   public void test4(){
    String[] strings = {"k", "k"};
    InsertionSort sort = new InsertionSort();
    sort.sort(strings);
    }

   @test
   public void test5(){
    String[] strings = {};
    InsertionSort sort = new InsertionSort();
    sort.sort(strings);
    }

   @test
   public void test5(){
    String[] strings = {"a", "f", "x", "b", "k", "z"};
    InsertionSort sort = new InsertionSort();
    sort.sort(strings);
    }

}