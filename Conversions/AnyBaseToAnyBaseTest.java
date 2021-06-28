import static Java.Conversions.AnyBaseToAnyBase.base2base;
public final class Testbase2base 
{
    @Test
    public void base2base1() {assertEquals("21202",base2base("HaNoi",1 ,3 ));}
    
    @Test
    public void base2base2() {assertEquals("11434",base2base( "BacHo",2, 5));}
    
    @Test
    public void base2base3() {assertEquals("44434422210",base2base( "nguyenducviet",3,5 ));}
    
    @Test
    public void base2base4() {assertEquals("8CHH021",base2base("NguyenDucViet", 5, 20 ));}
    
    @Test
    public void base2base5() {assertEquals("47645012",base2base("viet260199",4, 9));}
  
}
