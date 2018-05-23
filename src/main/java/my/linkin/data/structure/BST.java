package my.linkin.data.structure;

import java.security.NoSuchProviderException;

/***
 * BST(Binary Search Tree) 二叉搜索树(二叉搜索树里不存在值相等的节点)
 * @author linkin
 * */
public class BST<T> extends Tree<T>{

	public BST(T data) {
		super(data);
	}

	@Override
	protected Node isExist(T data) {
		Node root = root();
		while(null != root) {
			if(compare(root.data, data)) {
				if(isEqual(root.data, data))break;
				else {
					if(root.left == null) return null;
					else {
						root = root.left;
					}
				}
			}else {
				if(isEqual(root.data, data))break;
				else {
					if(root.right == null) return null;
					else {
						root = root.right;
					}
				}
			}
		}
		return root;
	}

	@Override
	protected Tree<T>.Node insert(T data) {
		Node leaf = null;
		//若树为空， 则直接进行初始化
		if(isEmpty())new BST<T>(data);
		Node root = root();
		while(null != root) {
			if(compare(data, root.data)) {
				if(root.right != null) {
					root = root.right;
				}else {
					leaf = leaf(data, root);
				}
			}else {
				if(root.left != null) {
					root = root.left;
				}else {
					leaf = leaf(data, root);
				}
			}
		}
		//列表里加入新增节点
		nodes.add(leaf);
		return leaf;
	}

	
	/**
	 * 节点删除分多种：
	 * 1. 删除根节点
	 * 2. 删除叶子节点
	 * 3. 删除的节点非叶子节点也非根节点， 仅有左子树
	 * 4. 删除的节点非叶子节点也非根节点， 仅有右子树
	 * 5. 删除的节点非叶子节点也非根节点， 有左子树和右子树
	 * */
	@Override
	protected void delete(T data) {
		Node node = isExist(data);
		if(node == null) return;
		//叶子节点直接删除， 修改父节点子节点的指针
		if(isLeaf(node)) {
			Node parent = node.parent;
			if(isEqual(parent.right.data, data)) parent.right = null;
			else {
				parent.left = null;
			}
			//如果该树仅有一个节点
			if(isRoot(node))nodes.clear();
			return;
		}
		//删除该节点， 该节点子节点成为原节点父节点的子节点
		if(node.right == null && node.left != null) {
			Node parent = node.parent;
			//删除的节点为左节点
			if(isEqual(parent.left.data, node.data)) {
				parent.left = node.left;
			}else {
				parent.right = node.left;
			}
			return;
		}
		if(node.right != null && node.left == null) {
			Node parent = node.parent;
			//删除的节点为左节点
			if(isEqual(parent.left.data, node.data)) {
				parent.left = node.right;
			}else {
				parent.right = node.right;
			}
			return;
		}
		/**
		 * 删除的节点存在左右子树时
		 * 此时选择被删除节点右子树的最左节点来替换该节点即可.
		 * */
		Node root = node.right;//以该节点右子树第一个节点为起始节点， 查找右子树最左节点
		while(null != root) {
			if(isLeaf(root))break;
			if(root.left != null){
				root = root.left;
				continue;
			}
			if(root.right != null) {
				root = root.right;
				continue;
			}
			
		}
		//删除右子树最左节点
		root.parent.left = null;
		//替换
		node.data = root.data;
	}
}
