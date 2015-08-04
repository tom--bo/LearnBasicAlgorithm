
public class BinaryTreeNode {
	String label;
	BinaryTreeNode left;
	BinaryTreeNode right;
	
	BinaryTreeNode(String label, BinaryTreeNode left, BinaryTreeNode right)
	{
		this.left = left;
		this.right = right;
		this.label = label;
	}
	
	public void traversePreorder(BinaryTreeNode p) 
	{
		if (p == null)
			return;
		System.out.println("Node: " + p.label + "に立ち寄りました");
		traversePreorder(p.left);
		traversePreorder(p.right);
	}

	public void traverseInorder(BinaryTreeNode p)
	{
		if(p == null)
			return;
		traverseInorder(p.left);
		System.out.println("Node: " + p.label + "に立ち寄りました");
		traverseInorder(p.right);
	}
	
	public void traversePostorder(BinaryTreeNode p)
	{
		if(p ==null)
			return;
		traverseInorder(p.left);
		traverseInorder(p.right);
		System.out.println("Node: " + p.label + "に立ち寄りました");
	}

}








