package TRIE;
import java.util.*;
public class TrieCode {

	public class Node{
		Node[] child;
		boolean word;
		String name;
		Node(){
			this.child=new Node[26];
			this.word=false;
			this.name="";
//			root=new Node();
		}
	}
	
	Node root=new Node();
	
	public void insert(String s) {
		
		Node curr=root;
		int n=s.length();
		for(int i=0;i<s.length();i++) {
			char ch=s.charAt(i);
			
			if(curr.child[ch-'a']==null) 
				curr.child[ch-'a']=new Node();
			
			curr=curr.child[ch-'a'];
		}
		curr.word=true;
	     curr.name=s;
		
	}
/////////////////////////////////////////////////////////////	
	public boolean search(String s) {
		Node curr=root;
		int n=s.length();
		for(int i=0;i<s.length();i++) {
			char ch=s.charAt(i);
			
			if(curr.child[ch-'a']!=null) {
				curr=curr.child[ch-'a'];
			}else {
				return false;
			}
			
		}
		if(curr.word>0) {
			return true;
		}
		return false;
	}
//////////////////////////////////LEETCODE 211
    public  boolean search1(String word) {
        Node Curr=root;
        return Mysearch(Curr,word,0);
        
    }
    
    public boolean Mysearch(Node node,String s,int idx){
    	if(node==null) return false;
        if(idx ==s.length()){
            if(node.word>0){
                return true;
            }else{
                return false;
            }
        }
       if(s.charAt(idx)=='.'){
           for(int i=0;i<26;i++){
               if(node.child[i]!=null){
                   boolean curr1=Mysearch(node.child[i],s,idx+1);
                   if(curr1){
                       return true;
                   }       
               }
           }
       }else if(node.child[s.charAt(idx)-'a']!=null){
               
               boolean curr=Mysearch(node.child[s.charAt(idx)-'a'],s,idx+1);
                      if(curr){
                          return true;
                      }
           }
       
        return false;
    }
	/// leetcode 212//////////////////////////////////////////////////////////////
    
    ArrayList<String > ans;
 public List<String> findWords(char[][] board, String[] words) {
        
        // if(board.length==0 || board[0].length==0 || words.length==0) return new ArrayList<>();
        
     ans=new ArrayList<>();
        
        root=new Node();
        
        for(int i=0;i<words.length;i++){
            insert(words[i]);// making of trie
        }
       
        for(int i=0;i<board.length;i++){
            for(int j=0; j<board[0].length;j++){
                 
                if(root.child[board[i][j]-'a']!=null)
                      dfs(i,j,root.child[board[i][j]-'a'],board);
            }
        }    
        return ans;
    }
 //////////////////////////////////////////////////////////////////////////
//    public void insert(String s){
//        Node curr=root;
//        for(int i=0;i<s.length();i++){
//            int idx=s.charAt(i)-'a';
//            
//            if(curr.child[idx]==null)
//                 curr.child[idx]=new Node();
//            
//            curr=curr.child[idx];
//        }
//        curr.word=true;
//        curr.name=s;
//    }
// ///////////////////////////////////////////////////////////////////////////////
    int[][] dirs={{0,1},{1,0},{0,-1},{-1,0}};
    public void dfs(int i,int j,Node node,char[][] board){
        
        if(node.word==true){
            ans.add(node.name);
            node.word=false;
            // return;
        }
        
        char ch=board[i][j];
        board[i][j]='#';
        for(int d=0;d<dirs.length;d++){
            int x=i+dirs[d][0];
            int y=j+dirs[d][1];
            
            if(x>=0 && x<board.length && y>=0 && y<board[0].length && board[x][y]!='#' ){// valid spot
                
                if(node.child[board[x][y]-'a']!=null)
                      dfs(x,y,node.child[board[x][y]-'a'],board);
                
            }
        }
        board[i][j]=ch;
    } 
}
