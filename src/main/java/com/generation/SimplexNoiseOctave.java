package src.main.java.com.generation;

import java.util.Random;

/**
 * Implementation of a simplex noise octave.
 */
public class SimplexNoiseOctave {

	private static final Gradient[] GRADIENTS = {
			
		new Gradient( 1,  1,  0), new Gradient(-1,  1,  0), new Gradient( 1, -1,  0),
		new Gradient(-1, -1,  0), new Gradient( 1,  0,  1), new Gradient(-1,  0,  1),
		new Gradient( 1,  0, -1), new Gradient(-1,  0, -1), new Gradient( 0,  1,  1),
		new Gradient( 0, -1,  1), new Gradient( 0,  1, -1), new Gradient( 0, -1, -1)
	};
	private static final short P_SUPPLY[] = {
		
		151, 160, 137,  91,  90,  15, 131,  13, 201,  95,  96,  53, 194, 233,   7, 225,
		140,  36, 103,  30,  69, 142,   8,  99,  37, 240,  21,  10,  23, 190,   6, 148,
		247, 120, 234,  75,   0,  26, 197,  62,  94, 252, 219, 203, 117,  35,  11,  32,
		 57, 177,  33,  88, 237, 149,  56,  87, 174,  20, 125, 136, 171, 168,  68, 175,
		 74, 165,  71, 134, 139,  48,  27, 166,  77, 146, 158, 231,  83, 111, 229, 122,
		 60, 211, 133, 230, 220, 105,  92,  41,  55,  46, 245,  40, 244, 102, 143,  54,
		 65,  25,  63, 161,   1, 216,  80,  73, 209,  76, 132, 187, 208,  89,  18, 169,
		200, 196, 135, 130, 116, 188, 159,  86, 164, 100, 109, 198, 173, 186,   3,  64,
		 52, 217, 226, 250, 124, 123,   5, 202,  38, 147, 118, 126, 255,  82,  85, 212,
		207, 206,  59, 227,  47,  16,  58,  17, 182, 189,  28,  42, 223, 183, 170, 213,
		119, 248, 152,   2,  44, 154, 163,  70, 221, 153, 101, 155, 167,  43, 172,   9,
		129,  22,  39, 253,  19,  98, 108, 110,  79, 113, 224, 232, 178, 185, 112, 104,
		218, 246,  97, 228, 251,  34, 242, 193, 238, 210, 144,  12, 191, 179, 162, 241,
		 81,  51, 145, 235, 249,  14, 239, 107,  49, 192, 214,  31, 181, 199, 106, 157,
		184,  84, 204, 176, 115, 121,  50,  45, 127,   4, 150, 254, 138, 236, 205,  93,
		222, 114,  67,  29,  24,  72, 243, 141, 128, 195,  78,  66, 215, 61,  156, 180
	};
	private static final int LENGTH = P_SUPPLY.length * 2;
	private static final int SWAP_COUNT = 20;
	private static final double F2 = 0.5D * (Math.sqrt(3.0D) - 1.0D);
	private static final double G2 = (3.0D - Math.sqrt(3.0D)) / 6.0D;
	private static final double F3 = 1.0D / 3.0D;
	private static final double G3 = 1.0D / 6.0D;
	
	private final short[] p = P_SUPPLY.clone();
	private final short[] perm = new short[LENGTH];
	private final short[] permMod12 = new short[LENGTH];

	/**
	 * @param seed the seed that this octave uses to generate pseudo random numbers
	 */
	public SimplexNoiseOctave(long seed) {
		
		Random random = new Random(seed);
		
		for(int index = 0; index < SWAP_COUNT; index++) {
			
			int swapFrom = random.nextInt(this.p.length);
	        int swapTo = random.nextInt(this.p.length);

	        short temp = this.p[swapFrom];
	        this.p[swapFrom] = this.p[swapTo];
	        this.p[swapTo] = temp;
		}
		
		for(int index = 0; index < LENGTH; index++) {
			
			this.perm[index] = this.p[index & 255];
			this.permMod12[index] = (short)(this.perm[index] % 12);
		}
	}
	
	/**
	 * A method with the functionality of {@link Math#floor(double)} but it is faster.
	 * @param x the value
	 * @return the result
	 * @see Math#floor(double)
	 */
	private static final int fastfloor(double x) {
		
		int xAsInt = (int)x;
		return x < xAsInt ? xAsInt - 1 : xAsInt;
	}

	/**
	 * Dot function for a gradient.
	 * @param gradient the gradient
	 * @param x X
	 * @param y Y
	 * @return the dot value
	 */
	private static final double dot(Gradient gradient, double x, double y) {
		
		return gradient.x * x + gradient.y * y;
	}

	/**
	 * Dot function for a gradient.
	 * @param gradient the gradient
	 * @param x X
	 * @param y Y
	 * @param z Z
	 * @return the dot value
	 */
	private static final double dot(Gradient gradient, double x, double y, double z) {
		
		return gradient.x * x + gradient.y * y + gradient.z * z;
	}
	
	/**
	 * Makes a two dimensional noise.
	 * @param x X
	 * @param y Y
	 * @return the noise
	 */
	public double noise(double x, double y) {

		double n0;
		double n1;
		double n2;
		
		double s = (x + y) * F2;
		
		int i = fastfloor(x + s);
		int j = fastfloor(y + s);
		
		double t = (i + j) * G2;
		
		double X0 = i - t;
		double Y0 = j - t;
		
		double x0 = x - X0;
		double y0 = y - Y0;
		
		int i1;
		int j1;
		
		if(x0 > y0) {
			
			i1 = 1;
			j1 = 0;
			
		} else {
			
			i1 = 0;
			j1 = 1;
		}
		
		double x1 = x0 - i1 + G2;
		double y1 = y0 - j1 + G2;
		double x2 = x0 - 1.0D + 2.0D * G2;
		double y2 = y0 - 1.0D + 2.0D * G2;
		
		int ii = i & 0xFF;
		int jj = j & 0xFF;
		
		int gi0 = this.permMod12[ii + this.perm[jj]];
		int gi1 = this.permMod12[ii + i1 + this.perm[jj + j1]];
		int gi2 = this.permMod12[ii + 1 + this.perm[jj + 1]];
		
		double t0 = 0.5D - x0 * x0 - y0 * y0;
		
		if(t0 < 0) {
		
			n0 = 0.0;
		
		} else {
			
			t0 *= t0;
			n0 = t0 * t0 * dot(GRADIENTS[gi0], x0, y0);
		}
		
		double t1 = 0.5D - x1 * x1 - y1 * y1;
		
		if(t1 < 0.0D) {
			
			n1 = 0.0;
			
		} else {
			
			t1 *= t1;
			n1 = t1 * t1 * dot(GRADIENTS[gi1], x1, y1);
		}
		
		double t2 = 0.5D - x2 * x2 - y2 * y2;
		
		if(t2 < 0.0D) {
			
			n2 = 0.0D;
		
		} else {
			
			t2 *= t2;
			n2 = t2 * t2 * dot(GRADIENTS[gi2], x2, y2);
		}
		
		return 70.0D * (n0 + n1 + n2);
	}
	
	/**
	 * Makes a three dimensional noise.
	 * @param x X
	 * @param y Y
	 * @param z Z
	 * @return the noise
	 */
	public double noise(double x, double y, double z) {
		
		double n0;
		double n1;
		double n2;
		double n3;
		
		double s = (x + y + z) * F3;
		
		int i = fastfloor(x + s);
		int j = fastfloor(y + s);
		int k = fastfloor(z + s);
		
		double t = (i + j + k) * G3;
		
		double X0 = i - t;
		double Y0 = j - t;
		double Z0 = k - t;
		
		double x0 = x - X0;
		double y0 = y - Y0;
		double z0 = z - Z0;
		
		int i1;
		int j1;
		int k1;
		int i2;
		int j2;
		int k2;
		
		if(x0 >= y0) {
			
			if(y0 >= z0) {
				
				i1 = 1;
				j1 = 0;
				k1 = 0;
				i2 = 1;
				j2 = 1;
				k2 = 0;
			
			} else if(x0 >= z0) {
				
				i1 = 1;
				j1 = 0;
				k1 = 0;
				i2 = 1;
				j2 = 0;
				k2 = 1;
				
			} else {
				
				i1 = 0;
				j1 = 0;
				k1 = 1;
				i2 = 1;
				j2 = 0;
				k2 = 1;
			}
		
		} else {
			
			if(y0 < z0) {
				
				i1 = 0;
				j1 = 0;
				k1 = 1;
				i2 = 0;
				j2 = 1;
				k2 = 1;
				
			} else if(x0 < z0) {
				
				i1 = 0;
				j1 = 1;
				k1 = 0;
				i2 = 0;
				j2 = 1;
				k2 = 1;
				
			} else {
				
				i1 = 0;
				j1 = 1;
				k1 = 0;
				i2 = 1;
				j2 = 1;
				k2 = 0;
			}
		}
		
		double x1 = x0 - i1 + G3;
		double y1 = y0 - j1 + G3;
		double z1 = z0 - k1 + G3;
		
		double x2 = x0 - i2 + 2.0D * G3;
		double y2 = y0 - j2 + 2.0D * G3;
		double z2 = z0 - k2 + 2.0D * G3;
		
		double x3 = x0 - 1.0D + 3.0D * G3;
		double y3 = y0 - 1.0D + 3.0D * G3;
		double z3 = z0 - 1.0D + 3.0D * G3;
		
		int ii = i & 0xFF;
		int jj = j & 0xFF;
		int kk = k & 0xFF;
		
		int gi0 = this.permMod12[ii + this.perm[jj + this.perm[kk]]];
		int gi1 = this.permMod12[ii + i1 + this.perm[jj + j1 + this.perm[kk + k1]]];
		int gi2 = this.permMod12[ii + i2 + this.perm[jj + j2 + this.perm[kk + k2]]];
		int gi3 = this.permMod12[ii + 1 + this.perm[jj + 1 + this.perm[kk + 1]]];
		
		double t0 = 0.6D - x0 * x0 - y0 * y0 - z0 * z0;
		
		if(t0 < 0) {
			
			n0 = 0.0D;
		
		} else {
			
			t0 *= t0;
			n0 = t0 * t0 * dot(GRADIENTS[gi0], x0, y0, z0);
		}
		
		double t1 = 0.6D - x1 * x1 - y1 * y1 - z1 * z1;
		
		if(t1 < 0) {
			
			n1 = 0.0D;
			
		} else {
			
			t1 *= t1;
			n1 = t1 * t1 * dot(GRADIENTS[gi1], x1, y1, z1);
		}
		
		double t2 = 0.6D - x2 * x2 - y2 * y2 - z2 * z2;
		
		if(t2 < 0) {
			
			n2 = 0.0D;
			
		} else {
			
			t2 *= t2;
			n2 = t2 * t2 * dot(GRADIENTS[gi2], x2, y2, z2);
		}
		
		double t3 = 0.6D - x3 * x3 - y3 * y3 - z3 * z3;
		
		if(t3 < 0) {
			
			n3 = 0.0D;
		
		} else {
			
			t3 *= t3;
			n3 = t3 * t3 * dot(GRADIENTS[gi3], x3, y3, z3);
		}
		
		return 32.0D * (n0 + n1 + n2 + n3);
	}
	
	/**
	 * Represents a gradient.
	 * Inner classes are faster than arrays.
	 */
	private static final class Gradient {
		
		private final double x;
		private final double y;
		private final double z;
		
		/**
		 * @param x X
		 * @param y Y
		 * @param z Z
		 */
		private Gradient(double x, double y, double z) {
			
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}
