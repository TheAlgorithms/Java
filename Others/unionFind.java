//Eshraq Ibrahim 17/10/2017
//union find algorithm purpose is to find if there is a path between 2 objects or not

public class unionFind {
	private int id[];

	// constructor takes number of objects
	public unionFind(int n) {
		id = new int[n];
		// set id of each object to itself
		for (int i = 0; i < n; i++) {
			id[i] = i;
		}
	}

	/**
	 * connect 2 objects together
	 */
	public void union(final int n, final int m) {
		int nid = id[n];
		int mid = id[m];

		for (int i = 0; i < id.length; i++) {
			if (id[i] == nid) {
				id[i] = mid;
			}
		}
	}

	/**
	 * Find whether there is a path between these 2 Objects
	 */
	public boolean intersected(final int n, final int m) {
		// checks if the 2 objects have the same id
		return (id[n] == id[m]);
	}

}
