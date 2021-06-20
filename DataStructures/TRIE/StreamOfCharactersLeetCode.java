package TRIE;

public class StreamOfCharactersLeetCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	class StreamChecker {
	    public class Node{
	        char ch;
	        Node[] arr;
	        boolean we;
	        String word;
	        Node(char ch){
	            this.ch=ch;
	            this.arr=new Node[26];
	        }
	    }
	    Node root=new Node('#');
	    StringBuilder sb=new StringBuilder();
	    public void build(String s){
	        Node temp=root;
	        
	        for(int i=s.length()-1;i>=0;i--){
	            char c=s.charAt(i);
	            int idx=(int)(c-'a');
	            
	            if(temp.arr[idx]==null){
	                // Node n=;
	                temp.arr[idx]=new Node(c);
	            }
	            temp=temp.arr[idx];
	        }
	        
	        temp.we=true;
	        temp.word=s;
	        
	    }
	    
	    public boolean find(char c){
	        sb.append(c);
	        
	        Node temp=root;
	        for(int i=sb.length()-1;i>=0 && temp!=null;i--){
	            char ch=sb.charAt(i);
	            
	            temp=temp.arr[ch-'a'];
	            if(temp!=null && temp.we==true){
	                return true;
	            }
	        }
	        return false;
	    }
	    public StreamChecker(String[] words) {
	        
	        for(int i=0;i<words.length;i++){
	            
	            build(words[i]);
	        }
	    }
	    
	    public boolean query(char l) {
	      return  find(l);
	    }
	}

	/**
	 * Your StreamChecker object will be instantiated and called as such:
	 * StreamChecker obj = new StreamChecker(words);
	 * boolean param_1 = obj.query(letter);
	 */

}
