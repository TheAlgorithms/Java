package DataStructures.Graphs;
/*
Implementation of graph by using hashmap for vertices of class which contains hashmap for vertex and then algos like prims dijktsra ,depth for search and traversal ,breadth for search and traversal ,algo for cycle present or not ,connected or not ,if not connected then connect it
Test case
Graph gp=new Graph();
		gp.addVertex("A");
		gp.addVertex("B");
		gp.addVertex("C");
		gp.addVertex("D");
		gp.addVertex("E");
		gp.addVertex("F");
		gp.addVertex("G");
		gp.addEdge("A", "B", 2);
		gp.addEdge("A", "D", 10);
		gp.addEdge("B", "C", 3);
		gp.addEdge("C", "D", 1);
		gp.addEdge("D", "E", 8);
		gp.addEdge("E", "F", 5);
		gp.addEdge("E", "G", 6);
		gp.addEdge("F", "G", 4);
		
//		gp.display();
//		System.out.println(gp.numVertex());
//		System.out.println(gp.numEdge());
//		System.out.println(gp.containsEdge("A", "C"));
//
//		System.out.println(gp.containsEdge("E", "F"));
//		gp.removeEdge("D", "E");
//		gp.display();
//		gp.removeVertex("F");
//		gp.addVertex("F");
//		gp.display();
//		System.out.println(gp.hasPath("A", "F", new HashMap<>()));
//		System.out.println(gp.dfs("A", "F"));
//		gp.bft();
//		gp.dft();
//		gp.removeEdge("B","C");
//		gp.removeEdge("F","G");
//		System.out.println(gp.isConnected());
//		System.out.println(gp.isCyclic());
//		System.out.println(gp.isTree());
//		System.out.println(gp.getConnectedComp());
//		gp.prims().display();
		System.out.println(gp.Dijktsra("A"));



*/


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import Heaps.GenericHeap;
public class Graph {
	private class vertex{
		HashMap<String,Integer> nbrs=new HashMap<>();
	}
	HashMap<String,vertex> vertcs;
	public Graph(){
		vertcs=new HashMap<>();
	}
	public int numVertex() {
		return this.vertcs.size();
	}
	public boolean containsVertex(String vname) {
		return this.vertcs.containsKey(vname);
	}
	public void addVertex(String vname) {

		vertex vtx=new vertex();
		this.vertcs.put(vname,vtx);
	}
	public void removeVertex(String vname) {
		vertex vtx=this.vertcs.get(vname);
		ArrayList<String> keys=new ArrayList<>(vtx.nbrs.keySet());
		for(String key:keys) {
			vertex nbrvtx=this.vertcs.get(key);
			nbrvtx.nbrs.remove(vname);
		}
		this.vertcs.remove(vname);
	}
	
	public int numEdge() {
		int count=0;
		ArrayList<String> keys=new ArrayList<>(this.vertcs.keySet());
		for(String key:keys) {
			vertex vtx=this.vertcs.get(key);
			count+=vtx.nbrs.size();
		}
		return count/2;
	}
	public boolean containsEdge(String vname1,String vname2) {
		vertex vtx1=this.vertcs.get(vname1);
		vertex vtx2=this.vertcs.get(vname2);
		if(vtx1==null||vtx2==null||!vtx1.nbrs.containsKey(vname2))
			return false;
		return true;
	}
	public void addEdge(String vname1,String vname2,int cost) {
		vertex vtx1=this.vertcs.get(vname1);
		vertex vtx2=this.vertcs.get(vname2);
		if(vtx1==null||vtx2==null||vtx1.nbrs.containsKey(vname2))
			return;
		vtx1.nbrs.put(vname2,cost);
		vtx2.nbrs.put(vname1,cost);
	}
	public void removeEdge(String vname1,String vname2) {
		vertex vtx1=this.vertcs.get(vname1);
		vertex vtx2=this.vertcs.get(vname2);
		if(vtx1==null||vtx2==null||!vtx1.nbrs.containsKey(vname2))
			return;
		vtx1.nbrs.remove(vname2);
		vtx2.nbrs.remove(vname1);
	}
	
	public void display() {
		ArrayList<String> keys=new ArrayList<>(this.vertcs.keySet());
		for(String key:keys) {
			vertex vtx=this.vertcs.get(key);
			System.out.println(key+"  :=  "+vtx.nbrs);
		}
	}
	
	public boolean hasPath(String source ,String dest,HashMap<String,Boolean> processed) {
		processed.put(source, true);
		if(containsEdge(source,dest)) {
			return true;
		}
		vertex vtx=this.vertcs.get(source);
		ArrayList<String> keys=new ArrayList<>(vtx.nbrs.keySet());
		for(String key:keys) {
			if(!processed.containsKey(key) && hasPath(key,dest,processed))
				return true;
		}
		return false;

	}
	private class pair{
		String vname;
		String psf;
	}
	public boolean bfs(String source,String dest) { // breadth first search
		HashMap<String ,Boolean> processed=new HashMap<>();

		LinkedList<pair> queue=new LinkedList<>();
		pair sp=new pair();
		sp.vname=source;
		sp.psf=source;
		queue.addLast(sp);

		while(!queue.isEmpty()) {
			pair rp=queue.removeFirst();
			if(processed.containsKey(rp.vname))
				continue;
			processed.put(rp.vname,true);

			if(containsEdge(rp.vname,dest)) {
				System.out.println(rp.psf+dest);
				return true;
			}
			vertex vtx=this.vertcs.get(rp.vname);
			ArrayList<String> nbrs=new ArrayList<>(vtx.nbrs.keySet());
			for(String nbr:nbrs) {
				if(!processed.containsKey(nbr)) {
					pair np=new pair();
					np.vname=nbr;
					np.psf=rp.psf+nbr;
					queue.addLast(np);
				}
			}
		}
		return false;
	}
	public boolean dfs(String source,String dest) { //deapth first search
		LinkedList<pair> stack=new LinkedList<>();
		HashMap<String,Boolean> processed=new HashMap<>();
		pair sp=new pair();
		sp.vname=source;
		sp.psf=source;
		stack.addFirst(sp);
		while(!stack.isEmpty()) {
			pair rp=stack.removeFirst();
			if(processed.containsKey(rp.vname))
				continue;
			processed.put(rp.vname,true);
			if(containsEdge(rp.vname,dest)) {
				System.out.println(rp.psf+dest);
				return true;
			}
			vertex vtx=this.vertcs.get(rp.vname);
			ArrayList<String > nbrs=new ArrayList<>(vtx.nbrs.keySet());
			for(String nbr:nbrs) {
				if(!processed.containsKey(nbr)) {
					pair np=new pair();
					np.vname=nbr;
					np.psf=rp.psf+nbr;
					stack.addFirst(np);
				}
			}

		}
		return false;
	}
	public void bft() { //breadth first traversal
		HashMap<String ,Boolean> processed=new HashMap<>();
		LinkedList<pair> queue=new LinkedList<>();
		ArrayList<String> keys=new ArrayList<>(this.vertcs.keySet());
		for(String key:keys) {
			if(processed.containsKey(key))
				continue;
			pair sp=new pair();
			sp.vname=key;
			sp.psf=key;
			queue.addLast(sp);

			while(!queue.isEmpty()) {
				pair rp=queue.removeFirst();
				if(processed.containsKey(rp.vname))
					continue;
				processed.put(rp.vname,true);

				System.out.println(rp.vname+"  via  "+rp.psf);
				
				vertex vtx=this.vertcs.get(rp.vname);
				ArrayList<String> nbrs=new ArrayList<>(vtx.nbrs.keySet());
				for(String nbr:nbrs) {
					if(!processed.containsKey(nbr)) {
						pair np=new pair();
						np.vname=nbr;
						np.psf=rp.psf+nbr;
						queue.addLast(np);
					}
				}
			}
		}
	}
	public void dft() { //deapt first traversal
		HashMap<String ,Boolean> processed=new HashMap<>();
		LinkedList<pair> stack=new LinkedList<>();
		ArrayList<String> keys=new ArrayList<>(this.vertcs.keySet());
		for(String key:keys) {
			pair sp=new pair();
			sp.vname=key;
			sp.psf=key;
			stack.addFirst(sp); 

			while(!stack.isEmpty()) {
				pair rp=stack.removeFirst();
				if(processed.containsKey(rp.vname))
					continue;
				processed.put(rp.vname,true);

				System.out.println(rp.vname+"  via  "+rp.psf);
				
				vertex vtx=this.vertcs.get(rp.vname);
				ArrayList<String> nbrs=new ArrayList<>(vtx.nbrs.keySet());
				for(String nbr:nbrs) {
					if(!processed.containsKey(nbr)) {
						pair np=new pair();
						np.vname=nbr;
						np.psf=rp.psf+nbr;
						stack.addFirst(np);
					}
				}
			}
		}
	}
	
	
	public boolean isCyclic() {
		HashMap<String ,Boolean> processed=new HashMap<>();
		LinkedList<pair> queue=new LinkedList<>();
		ArrayList<String> keys=new ArrayList<>(this.vertcs.keySet());
		for(String key:keys) {
			if(processed.containsKey(key))
				continue;
			pair sp=new pair();
			sp.vname=key;
			sp.psf=key;
			queue.addLast(sp);

			while(!queue.isEmpty()) {
				pair rp=queue.removeFirst();
				if(processed.containsKey(rp.vname))
					return true;
				processed.put(rp.vname,true);
			
				vertex vtx=this.vertcs.get(rp.vname);
				ArrayList<String> nbrs=new ArrayList<>(vtx.nbrs.keySet());
				for(String nbr:nbrs) {
					if(!processed.containsKey(nbr)) {
						pair np=new pair();
						np.vname=nbr;
						np.psf=rp.psf+nbr;
						queue.addLast(np);
					}
				}
			}
		}
		return false;
	}
	public boolean isConnected() {
		int flag=0;
		HashMap<String ,Boolean> processed=new HashMap<>();
		LinkedList<pair> queue=new LinkedList<>();
		ArrayList<String> keys=new ArrayList<>(this.vertcs.keySet());
		for(String key:keys) {
			if(processed.containsKey(key))
				continue;
			flag++;
			pair sp=new pair();
			sp.vname=key;
			sp.psf=key;
			queue.addLast(sp);

			while(!queue.isEmpty()) {
				pair rp=queue.removeFirst();
				if(processed.containsKey(rp.vname))
					continue;
				processed.put(rp.vname,true);
			
				vertex vtx=this.vertcs.get(rp.vname);
				ArrayList<String> nbrs=new ArrayList<>(vtx.nbrs.keySet());
				for(String nbr:nbrs) {
					if(!processed.containsKey(nbr)) {
						pair np=new pair();
						np.vname=nbr;
						np.psf=rp.psf+nbr;
						queue.addLast(np);
					}
				}
			}
		}
		if(flag>=2)
			return false;
		else 
			return true;
	}
	public boolean isTree() {
		return !isCyclic()&&isConnected();
	}
	public ArrayList<ArrayList<String>> getConnectedComp() {
		ArrayList<ArrayList<String>> ans=new ArrayList<>();
		HashMap<String ,Boolean> processed=new HashMap<>();
		LinkedList<pair> queue=new LinkedList<>();
		ArrayList<String> keys=new ArrayList<>(this.vertcs.keySet());
		for(String key:keys) {
			if(processed.containsKey(key))
				continue;
			ArrayList<String> subans=new ArrayList<>();
			pair sp=new pair();
			sp.vname=key;
			sp.psf=key;
			queue.addLast(sp);

			while(!queue.isEmpty()) {
				pair rp=queue.removeFirst();
				if(processed.containsKey(rp.vname))
					continue;
				processed.put(rp.vname,true);

				subans.add(rp.vname);
				
				vertex vtx=this.vertcs.get(rp.vname);
				ArrayList<String> nbrs=new ArrayList<>(vtx.nbrs.keySet());
				for(String nbr:nbrs) {
					if(!processed.containsKey(nbr)) {
						pair np=new pair();
						np.vname=nbr;
						np.psf=rp.psf+nbr;
						queue.addLast(np);
					}
				}
			}
			ans.add(subans);
		}
		return ans;
	}
	private class PrimsPair implements Comparable<PrimsPair>{
		String vname;
		String acqvname;
		int cost;
		public int compareTo(PrimsPair o) {
			return o.cost-this.cost;
		}
		
	}
	public Graph prims() {
		HashMap<String ,PrimsPair> map=new HashMap<>();
		GenericHeap<PrimsPair> heap=new GenericHeap<>();
		Graph mst=new Graph();
		for(String vrtx:this.vertcs.keySet()) {
			PrimsPair np=new PrimsPair();
			np.acqvname=null;
			np.vname=vrtx;
			np.cost=Integer.MAX_VALUE;
			heap.add(np);
			map.put(vrtx, np);
		}
		while(!heap.isEmpty()) {
			PrimsPair rp=heap.remove();
			map.remove(rp.vname);
			
			if(rp.acqvname==null) {
				mst.addVertex(rp.vname);
			}else {
				mst.addVertex(rp.vname);
				mst.addEdge(rp.vname, rp.acqvname, rp.cost);
			}
			
			for(String nbr:this.vertcs.get(rp.vname).nbrs.keySet()) {
				if(map.containsKey(nbr)) {
					//old cost that is from diff path stored in map
					int oc=map.get(nbr).cost;
					// cost that present vname need cost to go to nbr 	
					int nc=this.vertcs.get(rp.vname).nbrs.get(nbr);
					if(nc<oc) {
						PrimsPair gp=map.get(nbr);
						gp.acqvname=rp.vname;
						gp.cost=nc;
						heap.updatePriority(gp);
					}
				}
			}
			
		}
		
		
		
		return mst;
	}

	private class DijktsraPair implements Comparable<DijktsraPair>{
		String vname;
		String psf;
		int cost;
		public int compareTo(DijktsraPair o) {
			return o.cost-this.cost;
		}
		
	}
	public HashMap<String,Integer> Dijktsra(String source) {
		HashMap<String ,DijktsraPair> map=new HashMap<>();
		GenericHeap<DijktsraPair> heap=new GenericHeap<>();
		HashMap<String,Integer> ans =new HashMap<>();
		for(String vrtx:this.vertcs.keySet()) {
			DijktsraPair np=new DijktsraPair();
			np.psf="";
			np.vname=vrtx;
			np.cost=Integer.MAX_VALUE;
			if(vrtx==source) {
				np.cost=0;
				np.psf=source;
			}
			heap.add(np);
			map.put(vrtx, np);
		}
		while(!heap.isEmpty()) {
			DijktsraPair rp=heap.remove();
			map.remove(rp.vname);
			
			ans.put(rp.vname,rp.cost);
			
			for(String nbr:this.vertcs.get(rp.vname).nbrs.keySet()) {
				if(map.containsKey(nbr)) {
					//old cost that is from diff path stored in map
					int oc=map.get(nbr).cost;
					// cost that present vname need cost to go to nbr 	
					int nc=rp.cost+this.vertcs.get(rp.vname).nbrs.get(nbr);
					if(nc<oc) {
						DijktsraPair gp=map.get(nbr);
						gp.psf=rp.psf+nbr;
						gp.cost=nc;
						heap.updatePriority(gp);
					}
				}
			}
			
		}
		
		
		
		return ans;
	}

}
