package Maths;


public class Volume 
{

    /**
     *
     * @param radius
     * @return
     * @throws Exception
     */
    public float Sphere(float radius) throws Exception{

        if( radius<0 ){
            throw new Exception("Non-Negative Number Expected");

        }
        else{
            float vol_sphere;
            vol_sphere = (float) ((float) (4.0/3.0)*(radius*radius*radius*Math.PI));
            return vol_sphere;
        }
    }
     
    /**
     *
     * @param radius
     * @param height
     * @return
     * @throws Exception
     */
    public float Cylinder(float radius, float height) throws Exception{

        if( radius<0 || height<0 ){
            throw new Exception("Non-Negative Number Expected");
        }
        else{
            float vol_cylinder = (float) (Math.PI*Math.pow(radius, 2)*height);
            return vol_cylinder;
        }
    }
     
    /**
     *
     * @param radius
     * @param height
     * @return
     * @throws Exception
     */
    public float Cone(float radius, float height) throws Exception{

        if( radius<0 || height<0){
            throw new Exception("Non-Negative Number Expected");
        }
        else{
            float vol_cone = (float) (height/3*Math.PI*Math.pow(radius, 2));
            return vol_cone;
        }
    }
     
    /**
     *
     * @param length
     * @param width
     * @param height
     * @return
     * @throws Exception
     */
    public float Cuboid(float length, float width, float height) throws Exception{

        if( length <0 || width <0 || height <0 ){
            throw new Exception("Non-Negative Number Expected");
        }
        else{
            float vol_cuboid = (float) (length*width*height);
            return vol_cuboid;
        }
    }
        
    /**
     *
     * @param length
     * @return
     * @throws Exception
     */
    public float Cube(float length) throws Exception{

        if( length <0){
            throw new Exception("Non-Negative Number Expected");
        }
        else{
            float vol_cube = (float) (Math.pow(length,3));
            return vol_cube;
        }
        
    }
    
    
     /* Test Code */
    public static void main(String[] args) throws Exception{
        
    /* VOLUME OF SPHERE*/   
        int rad_sphere = 8;
        Volume vol = new Volume();
        /* Volume of sphere with radius=8 is approximately 2144.6606*/
        System.out.println("Volume of Sphere with Radius "+rad_sphere+" is "+vol.Sphere(rad_sphere));
        
    /* VOLUME OF CONE*/   
        float rad_cone = 26.9f;
        float hig_cone = 13.2f;
        /* Volume of cone with radius=26.9 & height=13.2 is approximately 10002.467*/
        /* Cone takes parameter as Cone(Radius, Height)*/
        System.out.println("Volume of Cone with Radius "+rad_cone+" Height "+hig_cone+" is "+vol.Cone(rad_cone, hig_cone));
        
    /* VOLUME OF CYLINDER*/   
        float rad_cylinder = 9.9f;
        float hig_cylinder = 7.6f;   
        /* Volume of cylinder with radius=9.9 & height=7.6 is approximately 2340.0967*/
        /* Cylinder takes parameter as Cylinder(Radius, Height)*/
        System.out.println("Volume of Cylinder with Radius "+rad_cylinder+" Height "+hig_cylinder+" is "+vol.Cylinder(rad_cylinder, hig_cylinder));
        
    /* VOLUME OF CUBE*/
        float len_cube=15.7f;
        /* Volume of cube with length=15.7 is approximately 3869.8928*/
        System.out.println("Volume of Cube of Length "+len_cube+" is "+vol.Cube(len_cube));
        
    /* VOLUME OF CUBOID*/
        float len_cuboid =8.9f;
        float wid_cuboid =10;
        int hig_cuboid =12;
        /* Volume of cuboid with length=8.9, width=10 & height=12 is approximately 1068*/
        /* Cuboid takes parameter as Cuboid(Length, Width, Height)*/
        System.out.println("Volume of Cube of Length "+len_cuboid+" Width "+wid_cuboid+" height "+hig_cuboid+" is "+vol.Cuboid(len_cuboid, wid_cuboid, hig_cuboid));
        

    }
    
}
