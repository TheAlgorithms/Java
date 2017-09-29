

import java.util.ArrayList;
import java.util.LinkedList;

public class HashMap<K,V> {
	public class hmnodes{ //HashMap nodes 
		K key;
		V value;
	}
	
	private int size=0; //size of hashmap
	private LinkedList<hmnodes> buckets[];  //array of addresses of list
	
	public HashMap(){
		buckets=new LinkedList[4]; //initially create bucket of any size 
		for(int i=0;i<4;i++)
			buckets[i]=new LinkedList<>(); 
	}
	
	public void put(K key,V value) throws Exception{
		int bi=bucketIndex(key); //find the index,the new key will be inserted in linklist at that index
		int fountAt=find(bi,key); //check if key already exists or not 
		if(fountAt==-1){
			hmnodes temp=new hmnodes(); //if doesn't exist create new node and insert 
			temp.key=key;
			temp.value=value;
			buckets[bi].addLast(temp);
			this.size++;
		}else{
			buckets[bi].get(fountAt).value=value;//if already exist modify the value
		}
		
		double lambda = (this.size*1.0)/this.buckets.length;
		if(lambda>2.0){
			rehash();  //rehashing function which will increase the size of bucket as soon as lambda exceeds 2.0
		}
		
		return;
	}
	

	public V get(K key) throws Exception{
		int bi=bucketIndex(key);
		int fountAt=find(bi,key);
		if(fountAt==-1){
			return null;
		}else{
			return buckets[bi].get(fountAt).value;
		}
	}
	
	public V remove(K key) throws Exception{
		int bi=bucketIndex(key);
		int fountAt=find(bi,key);
		if(fountAt==-1){
			return null;
		}else{
			this.size--;
			return buckets[bi].remove(fountAt).value;
		}
	}
	
	public boolean containskey(K key) throws Exception{
		int bi=bucketIndex(key);
		int fountAt=find(bi,key);
		if(fountAt==-1){
			return false;
		}else{
			return true;
		}
	}
	
	public int size(){
		return this.size;
	}
	
	
	public boolean isempty(){
		return this.size==0;
	}
	
	public ArrayList<K> keyset() throws Exception{
		ArrayList<K> arr=new ArrayList<>();
		for(int i=0;i<buckets.length;i++){
			for(int j=0;j<buckets[i].size();j++){
				arr.add(buckets[i].get(j).key);
			}
		}
		return arr;
	}
	
	public ArrayList<V> valueset() throws Exception{
		ArrayList<V> arr=new ArrayList<>();
		for(int i=0;i<buckets.length;i++){
			for(int j=0;j<buckets[i].size();j++){
				arr.add(buckets[i].get(j).value);
			}
		}
		return arr;
	}
	
	public void display() throws Exception{
		for(int i=0;i<buckets.length;i++){
			System.out.print("Bucket: "+i+" ");
			for(int j=0;j<buckets[i].size();j++){
				hmnodes temp=buckets[i].get(j);
				System.out.print("["+temp.key+"->"+temp.value+"]");
			}
			System.out.println();
		}
	}
	
	public int find(int bi,K key) throws Exception{
		for(int i=0;i<buckets[bi].size();i++){
			if(key.equals(buckets[bi].get(i).key))
				return i;
		}
		return -1;
	}
	
	public int bucketIndex(K key) throws Exception{
		int bi=key.hashCode();
		return Math.abs(bi%buckets.length);
	}

	private void rehash() throws Exception{
		LinkedList<hmnodes> ob[]= buckets;
		buckets=new LinkedList[ob.length*2];
		for(int i=0;i<ob.length*2;i++)
			buckets[i]=new LinkedList<>();
		
		size = 0;
		for(int i=0;i<ob.length;i++){
			for(int j=0;j<ob[i].size();j++){
				put(ob[i].get(j).key,ob[i].get(j).value);
			}
		}
		
	}
}
