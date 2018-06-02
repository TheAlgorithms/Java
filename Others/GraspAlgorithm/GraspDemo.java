import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


//============================================== Decomposition =======================================


public class GraspDemo {
	
	// a static list for Node class 
	//contains all the edges of the graph
	//for both directions for example 1->2 2->1
	private static List<Node> list;
	
	
	
//================================================= ReadTheFile ======================================	
	
	
	
	
	//this method throws input output Exceptions and FileNotFoundException .
	//Also this method read the Undirected Graph file with name distance.txt 
	private static void readFile(String file) throws FileNotFoundException, IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
	    // reads each line of the file 
	    while ((line = br.readLine()) != null) {
	    	
	    	//make a Stringtokenizer  object
	    	StringTokenizer tok = new StringTokenizer(line);
	    	
	    	/** I get every element of the line I'm running now
	    	 * Also i am doing casting 
	    	 */
	        String from = (String) tok.nextElement();
	        String to = (String) tok.nextElement();
	        String distance = (String) tok.nextElement();
	        
	        //I create a new object by properly calling the constructor
	        Node node1 = new Node(Integer.parseInt(from),Integer.parseInt(to),Double.parseDouble(distance));
	        Node node2 = new Node(node1.getTo(),node1.getFrom(),node1.getDistance());
	        list.add(node1);
	        list.add(node2);
	    }
	    br.close();
	}
	
	
	
//============================================ isExist ===============================================
	
	
	
	private static boolean isExist(int to, List<Node> Sol) {
		/*
		 * I check if the to I've taken in the current repeat
		 *  is equal to the from of a node I've already got
		 *  in order to avoid cycle
		 */
		for(Node n : Sol) {
			if(n.getFrom() == to) {
				return true;
			}
		}
		return false;
				
	}
	 
	
	
	
//===============================fillCandidate =====================================================	
	
	
	private static void fillCandidate(int from, List<Node> cand, List<Node> Sol) {
		//i search all nodes with from which i have use
		for(Node n  : list) {
			if(n.getFrom() == from && !isExist(n.getTo(),Sol)) {
				cand.add(n);
			}
		}
	}
	

	
	
//==================================== firstPhase =================================================	
	
	
	
	
	
	private static List<Node> firstPhase(){
		
		List<Node> Solution = new ArrayList<Node>();
		List<Node> Candidate = new ArrayList<Node>();
		fillCandidate(1, Candidate, Solution);
		while(true) {
			Collections.sort(Candidate);
			if(Candidate.size() == 0) {
				break;
			}
			else if(Candidate.size() == 1) {
				Solution.add(Candidate.get(0));
				break;
			}
			else if(Math.random() < 0.5) {
				Solution.add(Candidate.get(0));
			}
			else{
				Solution.add(Candidate.get(1));
			}
			
			Candidate.clear();// i empty it 
			//i call with suitable way the fillCandidate list
			fillCandidate(Solution.get(Solution.size()-1).getTo(),Candidate,Solution);
		}
		int from = Solution.get(Solution.size()-1).getTo();
		for(Node n : list) {
			if(n.getFrom() == from && n.getTo() == 1) {
				Solution.add(n);
				break;
			}
		}
		
		return Solution;
		
	
	}
	
	
	
	
	
//================================================== convertSolToArray =============================
	
	
	
	
	public static int[] convertSolToArray(List<Node> sol){
		
		int[] array = new int[sol.size() + 1];
		array[0] = sol.get(0).getFrom();
		array[1] = sol.get(0).getTo();
		for(int i = 1; i < sol.size(); i++){
			array[i + 1] = sol.get(i).getTo();
		}
		
		return array;
		
	}
	
	
	
	
//====================================================== swap =======================================
	
	
	
	

	
	public static int[] swap(int pos1, int pos2, int[]array){
		int[]copyArray = new int[array.length];
		for(int i = 0; i < array.length; i++){
			copyArray[i] = array[i];
		}
		int temp = copyArray[pos1];
		copyArray[pos1] = copyArray[pos2];
		copyArray[pos2] = temp;
		
		return copyArray;
	}
	
	

	
	

//====================================================findNeighbourhood ================================	

	
	
	
	
	public static List<int[]> findNeighbourhood(int[] initialArray){
		List<int[]> neighbor = new ArrayList<int[]>();
		for(int i=1; i<initialArray.length -1; i++){
			for(int j=1; j<initialArray.length - 1; j++){
				if(i == j){
					continue;
				}
				neighbor.add(swap(i,j,initialArray));
			}
		}
		
		return neighbor;
	}
	
	
	
//==================================================================== addNodeToList    =========================
	
	
	
	
	public static void addNodeToList(List<Node> l,int from, int to){
		for(Node n : list){
			if((n.getFrom()==from) && (n.getTo()==to)){
				l.add(n);
				break;
			}
		}
	}
	
	
	
//================================================ convertArrayToList   =====================================
	
	/*
	 * oysistika paizw etsi giati h xrhsh ths listas me bohthaei na vriskw tis 
	 * synolikes apostaseis 
	 * wste na brw thn kalyterh lysh
	 * 
	 */
	
	public static List<Node> convertArrayToList(int[] array){
		
		List<Node> l = new ArrayList<Node>();
		/*
		 * dinw thn lista poy einai adeia arxika gia na moy balei mesa to node poy antistoixei se dyo 
		 * theseis toy arxikoy pinaka 
		 */
		addNodeToList(l,0,1);
		for(int i =1; i < array.length - 1; i++){
			addNodeToList(l,i,i+1);
		}
		return l;
	}

	
	
	
//====================================================== pathDistance ===========================================	
	
	
	
	public static double pathDistance(List<Node> list){
		
		double dist = 0;
		for(Node n : list){
			dist += n.getDistance();
		}
		return dist;
		
	}
	
//==================================================================== printList  ====================	
	
	
	
	
	public static void printList(List<Node> list){
		for(Node n : list) {
			System.out.println(n);
		}
		System.out.println("total distance: "+ pathDistance(list));
	}

	
	
	
	
	
//====================================================== ImprovementPhase ===========================
	
	
	
	public static void improvementPhase(List<Node> sol){
		
		int[] initialArray = convertSolToArray(sol);
		List<int[]> neighbor = findNeighbourhood(initialArray);
		List<List<Node>> listNeighbour = new ArrayList<List<Node>>();
		
		/*
		 *	I get the tables that exist in the neighborhood and turn each of them into a node list
		 *	 and add all lists which represents in each table i put in the neighbour list 
		 */
		for(int i=1; i< neighbor.size();i++){
			listNeighbour.add(convertArrayToList(neighbor.get(i)));
		}
		/*
		 * vriskoyme th diadromh me thn mikroterh apostash poy yparxei sth listneighbor
		 */
		double mindist = pathDistance(listNeighbour.get(0));
		int minpos = 0;
		for(int i = 0; i < listNeighbour.size(); i++){
			double dist = pathDistance(listNeighbour.get(i));
			if(dist < mindist){
				mindist = dist;
				minpos = i;
			}
		}
		double distSol = pathDistance(sol);
		
		if(distSol < mindist){
			printList(sol);
		}else{
			printList(listNeighbour.get(minpos));
		}
		
	}
	
	
	
	
//============================================= main class ============================================
	
	
	
	public static void main(String[] args) {
		
		//I'm building a new object ArrayList
		list = new ArrayList<Node>();
		List<Node> solution;
		
		//i handle the exceptions 
		try {
			readFile("distance.txt");
		}
		catch(FileNotFoundException fnf) {
			fnf.printStackTrace();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		solution = firstPhase();
		
		improvementPhase(solution);
		
	}

}
