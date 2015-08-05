
public class BinarySearchTree {
	private Node root;
	
	public BinarySearchTree()
	{
		root = null;
	}
	
	public Node search(Integer key)
	{
		Node p = root;
		while(p != null) {
			int result = key.compareTo(p.data);
            if(result == 0) {
                return p;
            } else if (result > 0){
                p = p.left;
            } else {
                p = p.right;
            }
		}
		return null;
	}
	
	public Node insert(Integer key)
	{
		Node p = root;
		Node parent = null;
		boolean isLeftChild = false;
		
		while(p != null){
			int result = key.compareTo(p.data);
			if(result == 0){
				return null;
			} else if (result < 0) {
				parent = p;
				isLeftChild = true;
				p = p.left;
			} else {
				parent = p;
				isLeftChild = false;
				p = p.right;
			}
		}
		Node newNode = new Node(key);
		if(parent == null){
			root = newNode;
		}else if(isLeftChild) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}
		
		return newNode;
	}
	
	public boolean delete(Integer key)
	{
		Node p = root;
		Node parent = null;
		boolean isLeftChild = false;
		
		while(p != null) {
			int result = key.compareTo(p.data);
			if(result == 0){
				if(p.left == null && p.right == null) {
					if(parent == null) {
						root = null;
					} else if(isLeftChild) {
						parent.left = null;
					} else {
						parent.right = null;
					}
				} else if ( p.left == null) {
					if(parent == null) {
						root = p.right;
					} else if(isLeftChild) {
						parent.left = p.right;
					} else {
						parent.right = p.right;
					}
				} else if(p.right == null) {
					if(parent == null) {
						root = p.left;
					} else if(isLeftChild) {
						parent.left = p.left;
					} else {
						parent.right = p.left;
					}
				} else {
					Node x = deleteMin(p, p.right);
					
					if(parent == null) {
						root = x;
					} else if(isLeftChild) {
						parent.left = x;
					} else {
						parent.right = x;
					}
					x.right = p.right;
					x.left = p.left;
				}
				return true;

            } else if (result < 0) {
		     	parent = p;
	     		isLeftChild = true;
		     	p = p.left;
	     	} else {
	     		parent = p;
			     isLeftChild = false;
	     		p = p.right;
		    }

	    }
	return false;
	}
	
	private static Node deleteMin(Node parent, Node p)
	{
		boolean isLeftChild = false;
		
		while(p.left != null) {
			parent = p;
			isLeftChild = true;
			p = p.left;
		}

		if(isLeftChild) {
			parent.left = p.right;
		} else {
			parent.right = p.right;
		}
		return p;
	}
	
}








