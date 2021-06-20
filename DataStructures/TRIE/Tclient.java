package TRIE;

public class Tclient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TrieCode t=new TrieCode();
		t.insert("bad");
		t.insert("mad");
		t.insert("sad");
//		System.out.println(t.search1(".ad"));
		
		char[][] board= {{'o','a','a','n'},
				{'e','t','a','e'},
				 {'i','h','k','r'},
				      {'i','f','l','v'}};
		     String[] words={"oath","pea","eat","rain"};
		System.out.println(t.findWords(board, words));
	}

}
