public class Node implements Comparable<Node> {
	
	private int from ;// the point the point I am now 
	private int to;//the point I want to reach
	private double distance;// the cost 
	
	//constructor Node
	public Node( int from, int to, double distance){
		this.from = from;
		this.to = to;
		this.distance = distance;
	}
	
	public int compareTo(Node n) {
		int d1 = (int)distance * 1000;
		int d2 = (int)n.getDistance() * 1000;
		return d1 - d2;
		
	}
	//toString 
	public String toString() {
		return from + " " + to + " " + distance;
	}
	
	//setFrom
	public void setFrom(int frm) {
		from = frm;
	}
	
	//getFrom
	public int getFrom() {
		return from;
	}
	
	//setTo
	public void setTo(int too) {
		to = too;
	}
	
	//getTo
	public int getTo() {
		return to;
	}
	
	//setDistance 
	public void setDistance(double dist) {
		distance = dist;
	}
	
	//getDistance
	public double getDistance() {
		return distance;
	}
	
	
}
