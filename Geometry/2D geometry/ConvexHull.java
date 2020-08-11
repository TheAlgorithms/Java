import java.util.Random;

public class ConvexHull {
	public static final int MAX_POLY_VERTEX_COUNT = 64;

	public int vertexCount;
	public Vector2D[] vertices = Vector2D.arrayOf(MAX_POLY_VERTEX_COUNT);
	public Vector2D[] normals = Vector2D.arrayOf(MAX_POLY_VERTEX_COUNT);

	/**
	 * Takes an array of vertices and pass that to set method which constructs a
	 * convex hull from them.
	 * 
	 * @param verts
	 */
	public ConvexHull(Vector2D... verts) {
		set(verts);
	}

	private void set(Vector2D[] verts) {
		int rightMost = 0;
		float highestXCoord = verts[0].x;
		for (int i = 1; i < verts.length; ++i) {
			float x = verts[i].x;

			if (x > highestXCoord) {
				highestXCoord = x;
				rightMost = i;
			} else if (x == highestXCoord) {
				if (verts[i].y < verts[rightMost].y) {
					rightMost = i;
				}
			}
		}

		int[] hull = new int[MAX_POLY_VERTEX_COUNT];
		int outCount = 0;
		int indexHull = rightMost;

		for (;;) {
			hull[outCount] = indexHull;

			int nextHullIndex = 0;
			for (int i = 1; i < verts.length; ++i) {
				if (nextHullIndex == indexHull) {
					nextHullIndex = i;
					continue;
				}

				Vector2D e1 = verts[nextHullIndex].sub(verts[hull[outCount]]);
				Vector2D e2 = verts[i].sub(verts[hull[outCount]]);
				float c = Vector2D.cross(e1, e2);
				if (c < 0.0f) {
					nextHullIndex = i;
				}

				if (c == 0.0f && e2.lengthSq() > e1.lengthSq()) {
					nextHullIndex = i;
				}
			}

			++outCount;
			indexHull = nextHullIndex;

			if (nextHullIndex == rightMost) {
				vertexCount = outCount;
				break;
			}
		}

		for (int i = 0; i < vertexCount; ++i) {
			vertices[i].set(verts[hull[i]]);
		}
	}

	public static void main(String[] args) {
		int r = new Random().nextInt(40) + 10;

		// Initialise vertex count with a random integer between 3 and
		// ConvexHull.MAX_POLY_VERTEX_COUNT
		int vertexCount = new Random().nextInt(ConvexHull.MAX_POLY_VERTEX_COUNT - 3) + 3;

		Vector2D[] vertices = Vector2D.arrayOf(vertexCount);

		// Initialise vertices with random vectors with a maximum length of r
		for (int j = 0; j < vertexCount; j++) {
			vertices[j].set(new Random().nextInt(2 * r) - r, new Random().nextInt(2 * r) - r);
		}

		new ConvexHull(vertices);
	}
}
