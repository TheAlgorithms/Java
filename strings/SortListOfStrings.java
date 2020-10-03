/*

Given a list of strings or names, sort them in alphabetical order. (Using Trie data structure)

For example:
Input: {"turing","dijkstra","knuth"}
Output: {"dijkstra","knuth","turing"}

*/

import java.util.*;

class TrieNode{
	int countEndOfWords=0;
	TrieNode children[];

	TrieNode(){
		countEndOfWords=0;
		children=new TrieNode[26];
	}

}

class SortListOfStrings{

	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		List<String> words=new ArrayList<>();

		System.out.println("Number of words:");                        //Taking input
		int n=sc.nextInt();

		System.out.println("Words before sorting:");
		for(int i=0;i<n;i++){
			words.add(sc.next());
        }
        
        System.out.println();

		sort(words,n);                                                 //Calling sort function

		System.out.println("Words after sorting:");                    //Displaying output
		for(int i=0;i<n;i++){
			System.out.println(words.get(i));
		}

		sc.close();

	}

   
	public static void sort(List<String> words,int n){

		TrieNode root=new TrieNode();

		while(!words.isEmpty()){
			String word=words.remove(0);

			insert(word,root);                                   //Inserting all the words to the Trie data structure
		}

		getWords(words,root,"");                                 //Getting the words from Trie and putting them in list

	}



     /*
        This function inserts all the words in the list into the Trie.

    */
	public static void insert(String word,TrieNode root){              //Inserting all the words to the Trie data structure

		if(word.length()==0){
			root.countEndOfWords++;
		}

		TrieNode temp=root;

		for(int i=0;i<word.length();i++){
			int ch=word.charAt(i)-'a';
			if(temp.children[ch]==null){
				temp.children[ch]=new TrieNode();
			}
			temp=temp.children[ch];
		}

		temp.countEndOfWords++;

	}


     /*
        This function gets all the words from the Trie in lexicographical order and adds them back to the list.

    */
	public static void getWords(List<String> words,TrieNode node,String str){                //Getting the words from Trie and putting them in list

		
		for(int i=0;i<node.countEndOfWords;i++){
			words.add(str);
		}

		for(int i=0;i<26;i++){
			
			if(node.children[i]!=null){
				char ch=(char)('a'+i);
				getWords(words, node.children[i], str+ch);
			}
			
		}

	}

}


/*   Time Complexity:  O(|S1|+|S2|+|S3|+...)
             where S1, S2, S3, ... are the words in the list.

*/