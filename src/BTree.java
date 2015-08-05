import java.io.*;

public class BTree {
	
	private abstract class Node
	{
		int serial;  // toStringで出力用のラベル
	}
	
	private class InternalNode extends Node
    {
        int nChilds;  // このノードが持っている子の数
	 	Node[] child; // 部分木
   		Integer[] low;// 各部分木の最小の要素
    	
    	private InternalNode()
    	{
    		serial = serialNumber++;
    		nChilds = 0;
    		child = new Node[MAX_CHILD];
    		low = new Integer[MAX_CHILD];
    	}
    	
    	private int locateSubtree(Integer key)
    	{
    		for(int i = nChilds -1; i > 0; i--) {
    			if(key.compareTo(low[i]) >= 0) {
    				return i;
    			}
    		}
    		return 0;
    	}
	}
	
	private class Leaf extends Node
	{
		Integer key;
		Object data;
		
		private Leaf(Integer key, Object data)
		{
			serial = serialNumber++;
			this.key = key;
			this.data = data;
		}
	}
	private Node root;
	private int serialNumber = 0;
	
	// searchメソッドで見つけた葉をcurrentLeafフィールドにセットする。
	// delete, insertメソッドが呼ばれるときもクリアされる
	private Leaf currentLeaf; 
	
	final private static int MAX_CHILD = 5;
	final private static int HALF_CHILD = ((MAX_CHILD+1)/2);
	
	public BTree()
	{
		root = null;
	}
	
	/**
	 * B木からkeyを探索する
	 * 発見した場合はcurrentLeafフィールドにsetし、発見したかどうかを返り値とする
	 */
	public boolean search(Integer key)
	{
		currentLeaf = null;
		
		if(root == null) {
			return false;
		} else {
			Node p = root;
			while( p instanceof InternalNode) {
				InternalNode node = (InternalNode)p;
				p = node.child[node.locateSubtree(key)];
			}
			
			Leaf leaf = (Leaf)p;
			if(key.compareTo(leaf.key) == 0) {
				currentLeaf = leaf;
				return true;
			} else {
				return false;
			}
		}
	}
	
	public Object getData()
	{
		if(currentLeaf == null) {
			return null;
		} else {
			return currentLeaf.data;
		}
	}
	
	/**
	 * セットに成功したらtrueを返す。
	 * 直前にsearchに失敗していたとき、insert, deleteが直前に呼ばれていた場合、falseを返す。
	 */
	public boolean setData(Object data)
	{
		if(currentLeaf == null) {
			return false;
		} else {
			currentLeaf.data = data;
			return true;
		}
	}
	
	private static class InsertAuxResult
	{
		Node newNode;
		Integer lowest;
		
		private InsertAuxResult(Node newNode, Integer lowest)
		{
			this.newNode = newNode;
			this.lowest = lowest;
		}
	}
	
	/**
	 * 指定されたノードに対して、keyを持つ要素を挿入する
	 * @param pnode 内部ノードpnodeのnth番目の子に対して挿入を行う
	 * @param nth
	 * @param key
	 * @param data
	 * @return
	 */
	private InsertAuxResult insertAux(InternalNode pnode, int nth, Integer key, Object data){
		Node thisNode;
		if(pnode == null) {
			thisNode = root;
		} else{
			thisNode = pnode.child[nth];
		}
		
		if(thisNode instanceof Leaf) {
			Leaf leaf = (Leaf)thisNode;
			
			if(leaf.key.compareTo(key) == 0) {
				return null;
			} else {
				Leaf newLeaf = new Leaf(key, data);
				if(key.compareTo(leaf.key) < 0) {
					if(pnode == null) {
						root = newLeaf;
					} else {
						pnode.child[nth] = newLeaf;
					}
					
					return new InsertAuxResult(leaf, leaf.key);
				} else{
					return new InsertAuxResult(newLeaf, key);
				}
			}
		} else {
			InternalNode node = (InternalNode)thisNode;
			int pos = node.locateSubtree(key); //何番目の部分木に挿入するか決める
			InsertAuxResult result = insertAux(node, pos, key, data);
			
			// もし分割が行われていなければ、そのまま戻る
			if(result == null || result.newNode == null) {
				return result;
			}
			
			// 分割が行われていた場合なので、nodeにそれ(result.newNode)を挿入する
			if(node.nChilds < MAX_CHILD) {  // 追加の余地があるか
				for (int i = node.nChilds -1; i > pos; i--) {
					node.child[i+1] = node.child[i];
					node.low[i+1] = node.low[i];
				}
				node.child[pos+1] = result.newNode;
				node.low[pos+1] = result.lowest;
				node.nChilds++;
				return new InsertAuxResult(null, null);
			} else { //　追加の余地がないのでnodeを２つに分割しなければならない
				InternalNode newNode = new InternalNode();
				
				if(pos < HALF_CHILD -1) {
					// 後半部分を新しいノードに移す
					for(int i = HALF_CHILD-1, j = 0; i< MAX_CHILD; i++, j++) {
						newNode.child[j] = node.child[i];
						newNode.low[j] = node.low[i];
					}
					// 前半部分の適切な位置にresult.newNodeを挿入する
					for(int i = HALF_CHILD-2; i > pos; i--) {
						node.child[i+1] = node.child[i];
						node.low[i+1] = node.low[i];
					}
					node.child[pos+1] = result.newNode;
					node.low[pos+1] = result.lowest;
				} else {
					// 後半部分を新しいノードに移してそちらにresult.newNodeを挿入する
					int j = MAX_CHILD - HALF_CHILD;
					for(int i = MAX_CHILD-1; i >= HALF_CHILD; i--) {
						if(i == pos) {
							newNode.child[j] = result.newNode;
							newNode.low[j--] = result.lowest;
						}
						newNode.child[j] = node.child[i];
						newNode.low[j--] = node.low[i];
					}
					if(pos < HALF_CHILD){
						newNode.child[0] = result.newNode;
						newNode.low[0] = result.lowest;
					}
				}
				node.nChilds = HALF_CHILD;
				newNode.nChilds = (MAX_CHILD + 1) - HALF_CHILD;
				
				return new InsertAuxResult(newNode, newNode.low[0]);
			}
		}
	}
	
	/**
	 * B木に要素を挿入する
	 *  
	 * @param key
	 * @param data
	 * @return T/F
	 */
	public boolean insert(Integer key, Object data)
	{
		currentLeaf = null;
		
		if(root == null) {
			root = new Leaf(key, data);
			return true;
		} else {
			InsertAuxResult result = insertAux(null, -1, key, data);

			if(result == null) {
				return false;
			}
			
			if(result.newNode != null) {
				InternalNode newNode = new InternalNode();
				newNode.nChilds = 2;
				newNode.child[0] = root;
				newNode.child[1] = result.newNode;
				newNode.low[1] = result.lowest;
				root = newNode;
			}
			return true;
		}
	}
	
	private static boolean mergeNodes(InternalNode p, int x)
	{
		InternalNode a = (InternalNode)p.child[x];
		InternalNode b = (InternalNode)p.child[x+1];
		b.low[0] = p.low[x+1];
		
		int an = a.nChilds;
		int bn = b.nChilds;
		
		if(an + bn <= MAX_CHILD) {
			for(int i = 0; i< bn; i++) {
				a.child[i+an] = b.child[i];
				b.child[i] = null;
				a.low[i+an] = b.low[i];
			}
			a.nChilds += bn;
			return true;
		} else { 
			// 部分木aとbとで、ノードを再分配する
			int move;
			
			int n = (an + bn) /2;
			if(an > n) {
				move = an -n;
				// bの要素を右にずらす
				for (int i = bn -1; i>= 0; i--) {
					b.child[i+move] = b.child[i];
					b.low[i+move] = b.low[i];
				}
				// aからbへmove個の子を移動する
				for(int i = 0; i < move; i++) {
					b.child[i] = a.child[i+n];
					a.child[i+n] = null;
					b.low[i] = a.low[i+n];
				}
			} else {
				// 部分着bから部分木aへ移動する
				move = n - an;
				// bからaへmove個の子を移動する
				for(int i = 0; i < move; i++ ) {
					a.child[i+an] = b.child[i];
					a.low[i+an] = b.low[i];
				}
				// bの要素を左へ詰めあわせる
				for(int i = 0; i< bn -move; i++) {
					b.child[i] = b.child[i+move];
					b.child[i+move] = null;
					b.low[i] = b.low[i+move];
				}
			}
			// 子の個数を更新する
			a.nChilds = n;
			b.nChilds = an + bn -n;
			// 部分期bの最小値をノードpにセットする
			p.low[x+1] = b.low[0];
			return false;
		}
	}
	
	private enum Status {
		OK, 
		OK_REMOVED,
		OK_NEED_REORG,
		NOT_FOUND
	}
	
	private static Status deleteAux(Node thisNode, Integer key)
	{
		if(thisNode instanceof Leaf) {
			Leaf leaf = (Leaf)thisNode;
			
			if(leaf.key.compareTo(key) == 0) {
				return Status.OK_REMOVED;
			} else {
				return Status.NOT_FOUND;
			}
		} else {
			InternalNode node = (InternalNode)thisNode;
			boolean joined = false; // 再編成の結果、部分木が併合されたか
			int pos = node.locateSubtree(key);
			Status result = deleteAux(node.child[pos], key);
			if(result == Status.NOT_FOUND || result == Status.OK) {
				return result;
			}
			if(result == Status.OK_NEED_REORG) {
				int sub = (pos == 0) ? 0: pos -1;
				// 部分木subとsub+1を再編成する
				joined = mergeNodes(node, sub);
				// もし、subとsub+1が併合されていたら、部分木sub+1をnodeから削除する必要がある
				if (joined) {
					pos = sub+1;
				}
			}
			
			Status myResult = Status.OK;
			
			// 部分木posが削除された
			if (result == Status.OK_REMOVED || joined) {
				for (int i = pos; i<node.nChilds -1; i++) {
					node.child[i] = node.child[i+1];
					node.low[i] = node.low[i+1];
				}
				node.child[node.nChilds-1] = null;
				if(--node.nChilds < HALF_CHILD) {
					myResult = Status.OK_NEED_REORG;
				}
			}
			return myResult;
		}
	}
	
	/**
	 * B木から要素を削除する
	 * 
	 * @param key
	 * @return
	 */
	public boolean delete(Integer key)
	{
		currentLeaf = null;
		
		if(root == null) {
			return false;
		} else {
			// deleteAuxメソッドを呼び出して、keyを持つ要素を削除する
			Status result = deleteAux(root, key);
			if(result == Status.NOT_FOUND) {
				return false;
			}
			
			if(result == Status.OK_REMOVED) {
				root = null;
			} else if(result == Status.OK_NEED_REORG && ((InternalNode)root).nChilds == 1) {
				root = ((InternalNode)root).child[0];
			}
			return true;
		}
	}
	
	// B木の内容を出力する
	private static String toStringAux(Node p)
	{
		if(p instanceof Leaf) {
			Leaf l = (Leaf) p;
            return "Leaf #" + l.serial + "key=" + l.key;
		} else {
			InternalNode n = (InternalNode)p;
			String s = "Node #" + n.serial + " (" + n.nChilds + " childs): ";
			s += "#" + n.child[0].serial + " ";
			for(int i = 1; i < n.nChilds; i++) {
				s += toStringAux(n.child[i]) + "\n";
			}
			return s;
		}
	}
	
	public String toString()
	{
		if(root == null) {
			return "<木はからです>";
		} else {
			return toStringAux(root);
		}
	}
}
