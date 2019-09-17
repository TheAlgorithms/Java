import java.util.*;

class Node{
	double frequency;
	char ch;
	Node rightChild;
	Node leftChild;
	Node(double frequency, char ch, Node left, Node right){
		this.frequency = frequency;
		this.ch = ch;
		leftChild = left;
		rightChild = right;
	}
}
class Code{
	char ch;
	String code; 
	Code(){
		code = "";
		ch = '$';
	}
}
public class HuffmanCoding{
	static int count = 0;
	static Scanner sc = new Scanner(System.in);
	public static void main(String args[]){
		HuffmanCoding huffman = new HuffmanCoding();
		int n;
		System.out.println("Enter the number of nodes");
		n = sc.nextInt();
		ArrayList<Node> nodeList = new ArrayList<Node>();
		huffman.readNodes(n, nodeList);
	/*	for(Node i : nodeList){
			System.out.println(i.ch+ " " + i.frequency);
		}*/
		huffman.huffmanTree(nodeList);
		Code myCode[] = new Code[n];
		for(int i = 0; i < n; i++){
			myCode[i] = new Code();
		}
		Node root = nodeList.get(0);
	//	huffman.traversal(root);
		huffman.traversalTree(root, myCode, n);
		for(Code i : myCode){
			System.out.println("Character = "+i.ch+" Code = "+i.code);
		}
	}
	public ArrayList<Node> readNodes(int n, ArrayList<Node> nodeList){
		while(n-- != 0){
			System.out.println("Enter the character");
			char ch = sc.next().charAt(0);
			System.out.println("Enter the frequency");
			double f = sc.nextDouble();
			Node temp = new Node(f, ch, null, null);
			System.out.println(temp.ch + " " + temp.frequency);
			nodeList.add(temp);
		}
		return nodeList;
	}
	public void huffmanTree(ArrayList<Node> nodeList){
		while(nodeList.size() > 1){
			sort(nodeList);
			Node n1 = nodeList.get(0);
			Node n2 = nodeList.get(1);
			double f = n1.frequency + n2.frequency;
			Node newNode = new Node(f, '$', n1, n2);
			nodeList.remove(0);
			nodeList.set(0, newNode);
		}
	}
	void sort(ArrayList<Node> nodeList){
		Node temp;
		for(int i = 0; i < nodeList.size(); i++){
			for(int j = 0; j < nodeList.size() - i - 1; j++){
				if(nodeList.get(j).frequency >  nodeList.get(j+1).frequency){
					temp = nodeList.get(j);
					nodeList.set(j, nodeList.get(j+1));
					nodeList.set(j+1, temp);		
				}
			}
		}
	}
	void traversalTree(Node root, Code myCode[], int n){
		if(root.leftChild != null || root.rightChild != null){
			if(count == n)
				return;
			myCode[count].code += 0;
			traversalTree(root.leftChild, myCode, n);
			if(count == n)
				return;
			myCode[count].code += "1";
			traversalTree(root.rightChild, myCode, n);
			if(count == n)
		      		return;	       
			myCode[count].code = myCode[count].code.substring(0,myCode[count].code.length() - 1);
		}
		else{
			String prevCode = myCode[count].code;
			myCode[count].ch = root.ch;
			count++;
			if(count == n)
				return;
			myCode[count].code = prevCode.substring(0, prevCode.length() - 1);
		}
	}
}
