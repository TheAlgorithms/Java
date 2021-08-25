package Maths;
public class Circumference {
    /**
    * Calculate Circumference for radius n
    *
    * @param radius1 Radius of the Circle
    * @return the Circumference of {@code n}
    * @throws Exception
   */

        public float circumference(float radius1) throws Exception{
            
            if( radius1<0 ){
                throw new Exception("Radius is Always Positive");
                
            }
            else{

                float circum = (float) (2*Math.PI*radius1);
                return circum;
            }
        }
    /* Driver Code */
    public static void main(String[] args) throws Exception{
        float radius1=5;      //Test case 1
        float radius2=10.50f; //Test case 2
        Circumference c=new Circumference();
        System.out.println("Circumference of Circle with Radius "+radius1+" is "+c.circumference(radius1));
                /* Circumference of Circle with Radius 5.0 is 31.415926 */
        
                System.out.println("Circumference of Circle with Radius "+radius2+" is "+c.circumference(radius2));
                /* Circumference of Circle with Radius 10.5 is 65.97344 */

    }

}
 