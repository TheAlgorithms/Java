class BinaryTreeNode<T> {
	T data;
	BinaryTreeNode<T> left;
	BinaryTreeNode<T> right;
	
	public BinaryTreeNode(T data) {

		this.data=data;
	}
}

public class TreeFromInorderandPreorder {


	public static BinaryTreeNode<Integer> BinaryTreeConstruct(int[] in,int sin,int ein,int[] pre,int spre,int epre){
		if(sin>ein || spre>epre) {
			return null;
		}
		int rootElem=pre[spre];
		BinaryTreeNode<Integer> root=new BinaryTreeNode<Integer>(rootElem);
		int rootPos=-1;
		for(int i=sin;i<=ein;i++) {
			if(in[i]==rootElem) {
				 rootPos=i;
			}
		}
		int count=rootPos-sin;
		root.left=BinaryTreeConstruct(in,sin,rootPos-1,pre,spre+1,spre+count);
		root.right=BinaryTreeConstruct(in, rootPos+1, ein,pre, spre+count+1, epre);
	return root;	
	}

}
