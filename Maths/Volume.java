public class Volume{

	public static double cube(double sideLength){

		/*
		Volume of Cube = side ^ 3
		*/

		double cubeVolume = Math.pow(sideLength, 3);

		return cubeVolume;
	}

	public static double cuboid(double width, double height, double length){

		/*
		Volume of Cuboid = multiply all side's length
		*/

		double cuboidVolume = width * height * length;

		return cuboidVolume;
	}

	public static double cone(double radius, double height){

		/*
		Volume of Cone = (1/3) * [ pi * radius ^ 2 ] * height 
		*/

		double baseArea = radius * radius * Math.PI;
		double coneVolume = baseArea * height / 3;

		return coneVolume;
	}


	public static double triangularPrism(double triBase, double triHeight, double prismHeight){

		/*
		Volume of Triangular Prism = area of triangle * height
		*/

		double triPrismVol = (triBase * triHeight / 2) * prismHeight;

		return triPrismVol;	
	}

	public static double pyramid(double baseArea, double height){

		/*
		Volume of Pyramid = area of the base polygon * height
		*/

		double pyramidVol = baseArea * height / 3;

		return pyramidVol;
	}

	public static double sphere(double radius){

		/*
		Volume of Sphere = (4/3) * [ pi * radius ^ 2 ]
		*/

		double sphereVol = Math.PI * radius * radius * 4 / 3;

		return sphereVol;
	}

	public static double circularCylinder(double radius, double height){

		/*
		Volume of Circular Cylinder = [ pi * radius ^ 2 ] * height
		*/

		double cirCylinVol = Math.PI * radius * radius * height;

		return cirCylinVol;
	}

	public static void main(String[] args){
		/*

		Sample Print Results for each Calculations
		
		*/

		System.out.println("Cube: " + cube(2.4));
		System.out.println("Cuboid: " + cuboid(2.2, 3, 4.9));
		System.out.println("Cone: " + cone(2,3 ));
		System.out.println("Triangular Prism: " + triangularPrism(1, 2, 3));
		System.out.println("Pyramid: " + pyramid(10, 4.5));
		System.out.println("Sphere: " + sphere(2));
		System.out.println("Circular Cylinder: " + circularCylinder(3, 10));

		/*

		To Set the Number of Decimal Places

		*/

		System.out.printf("Cube: %.0f\n", cube(2.4)); 					  // 0 Decimals
		System.out.printf("Cuboid: %.2f\n", cuboid(2.2, 3, 4.9));			// 2 Decimals
		System.out.printf("Cone: %.3f\n", cone(2,3 ));					// 3 Decimals
		System.out.printf("Triangular Prism: %.4f\n", triangularPrism(1, 2, 3));	// 4 Decimals
		System.out.printf("Pyramid: %.5f\n", pyramid(10, 4.5));				// 5 Decimals
		System.out.printf("Sphere: %.6f\n", sphere(2));					// 6 Decimals
		System.out.printf("Circular Cylinder: %.7f\n", circularCylinder(3, 10));	// 7 Decimals
	}

}
