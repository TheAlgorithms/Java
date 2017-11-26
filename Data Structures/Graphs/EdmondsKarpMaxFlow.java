import java.util.ArrayDeque;
import java.util.Queue;

/**
 * This implementation of Edmonds-Karp algorithms returns the maximum flow in a
 * Flow Network. It makes use of the Ford-Fulkerson method.
 * 
 * @author Lukas Keul
 * @author Florian Mercks
 */
public class EdmondsKarpMaxFlow {

	private long[][] edgeCapacity;
	private int[] parentNode;
	private boolean[] visitedNode;
	private long[][] maxFlowBetweenNodes;

	@SuppressWarnings("unused")
	private int numOfN, numOfE;

	public EdmondsKarpMaxFlow(int numberOfNodes, int numberOfEdges) {
		this.numOfN = numberOfNodes;
		this.numOfE = numberOfEdges;
		this.parentNode = new int[numOfN];
		this.visitedNode = new boolean[numOfN];
		this.edgeCapacity = new long[numOfN][numOfN];
		this.maxFlowBetweenNodes = new long[numOfN][numOfN];
	}

	/**
	 * Returns the maximum Flow in a Flow Network
	 * 
	 * @param flowSource
	 *            represents the starting point of a maximum flow measurement in
	 *            a flow network
	 * @param FlowTarget
	 *            represents the end point of a maximum flow measurement in a
	 *            flow network
	 * @return maximum flow in the considered flow network
	 */
	public long getMaxFlow(int flowSource, int flowTarget) {
		while (true) {
			final Queue<Integer> flowSourceQueue = new ArrayDeque<Integer>(); 
			flowSourceQueue.add(flowSource);

			// iterate through unvisited flowsources
			for (int i = 0; i < this.numOfN; ++i)
				visitedNode[i] = false;
			visitedNode[flowSource] = true;

			boolean isFlowSourceChecked = false;
			int currentFlowSource;

			// iterate throught the flowSourceQueue
			while (!flowSourceQueue.isEmpty()) {
				// give out the last Element of the Queue
				currentFlowSource = flowSourceQueue.peek();

				// if the currentFlowSource is the flowTarget, we are done
				// iterating
				if (currentFlowSource == flowTarget) {
					isFlowSourceChecked = true;
					break;
				}

				// remove last element from flowSourceQueue
				flowSourceQueue.remove();

				// iterate through the number of nodes to check capacity size
				for (int i = 0; i < numOfN; ++i) {
					// if capacity is greater than the expected flow
					// then add the node to the flowSourceQueue
					if (!visitedNode[i]
					        && edgeCapacity[currentFlowSource][i] > maxFlowBetweenNodes[currentFlowSource][i]) {

						visitedNode[i] = true;
						flowSourceQueue.add(i);
						parentNode[i] = currentFlowSource;
					}
				}
			}
			if (isFlowSourceChecked == false)
				break;

			// subtract the maximum flow between the nodes from the edge
			// capacity
			long holdPartialResults = edgeCapacity[parentNode[flowTarget]][flowTarget]
			        - maxFlowBetweenNodes[parentNode[flowTarget]][flowTarget];

			// do a breadth first search from target to source to find minimum
			// needed capacity
			for (int i = flowTarget; i != flowSource; i = parentNode[i])
				holdPartialResults = Math.min(holdPartialResults,
				        (edgeCapacity[parentNode[i]][i] - maxFlowBetweenNodes[parentNode[i]][i]));

			// add all minimum needed capacities to the maximum flow between the
			// source and the target node
			for (int i = flowTarget; i != flowSource; i = parentNode[i]) {
				maxFlowBetweenNodes[parentNode[i]][i] += holdPartialResults;
				maxFlowBetweenNodes[i][parentNode[i]] -= holdPartialResults;
			}
		}

		long result = 0;

		// return the resulting maximum flow between the flowSource and the
		// flowTarget
		for (int i = 0; i < numOfN; ++i)
			result += maxFlowBetweenNodes[flowSource][i];
		return result;
	}

	/**
	 * Adds an Edge to the flow network
	 * 
	 * @param fromNode
	 *            is the starting node of an edge
	 * @param toNode
	 *            is the ending node of an edge
	 * @param edgeCapacity
	 *            represents edge capacity used for the capacity function which
	 *            calculates the flow
	 */
	public void addEdge(int fromNode, int toNode, long edgeCapacity) {
		assert edgeCapacity >= 0;
		this.edgeCapacity[fromNode][toNode] += edgeCapacity;
	}

	/**
	 * main function to present the output for characters and integers
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// test 1 with integer input
		System.out.println("Test:");
		 EdmondsKarpMaxFlow edmondsKarpTest = new EdmondsKarpMaxFlow(7, 9);
	     edmondsKarpTest.addEdge(0, 3, 3);
	     edmondsKarpTest.addEdge(0, 1, 3);
	     edmondsKarpTest.addEdge(1, 2, 4);    	     
	     edmondsKarpTest.addEdge(2, 0, 3);
	     edmondsKarpTest.addEdge(2, 3, 1);
	     edmondsKarpTest.addEdge(2, 4, 2);
	     edmondsKarpTest.addEdge(3, 4, 2);
	     edmondsKarpTest.addEdge(4, 1, 1);
	     edmondsKarpTest.addEdge(4, 6, 1);
	     System.out.println("The maximum flow from flowSource to flowTarget is " + (edmondsKarpTest.getMaxFlow(1, 4)) + ".");
	}
}