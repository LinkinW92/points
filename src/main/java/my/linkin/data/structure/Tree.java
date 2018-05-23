package my.linkin.data.structure;

import java.util.List;
import com.google.common.collect.Lists;

public abstract class Tree<T> {

	
	List<Node> nodes = Lists.newArrayList();
	
	
	/**
	 * 初始化树
	 * */
	public Tree(T data) {
		Node first = new Node(data);
		first.parent = null;
		first.right = null;
		first.left = null;
		nodes.add(first);
		
	}
	
	/**
	 * 返回父节点
	 * @param
	 * @return node root
	 * */
	public Node root() {
		for(Node node : nodes) {
			if(node.parent == null)return node;
		}
		return null;
	}
	
	/**
	 * 比较俩节点值的大小
	 * @param T data1
	 * @param T data2
	 * @return boolean 
	 * */
	protected  boolean compare(T data1, T data2) {
		return Integer.valueOf(data1.toString()) > Integer.valueOf(data2.toString());
	}
	
	
	/**
	 * 比较俩节点值是否相等
	 * @param T data1
	 * @param T data2
	 * @return boolean 
	 * */
	protected  boolean isEqual(T data1, T data2) {
		return Integer.valueOf(data1.toString()) == Integer.valueOf(data2.toString());
	}
	
	
	/**
	 * 判断节点是否存在,实际是一个查找过程
	 * @param T data 节点值
	 * @return Node node 返回该节点对象
	 * */
	protected abstract Node isExist(T data);
	
	
	/**
	 * 插入一个节点, 返回插入节点的父节点
	 * @param node 待插入节点
	 * @return 插入节点的父节点
	 * */
	protected abstract Node insert(T t);
	
	/**
	 * 删除一个节点
	 * @param node 待删除节点
	 * @return
	 * */
	protected abstract void delete(T data);
	
	/**
	 * 判断一个树是否为空
	 * @param
	 * @return boolean 是否为空
	 * 
	 * */
	protected  boolean isEmpty() {
		return nodes.size() == 0;
	};
	
	
	/**
	 * 计算树节点个数
	 * @param
	 * @return int 节点个数
	 * */
	
	protected int size() {
		return nodes.size();
	};
	
	
	/**
	 * 判断是否为叶子节点
	 * */
	protected boolean isLeaf(Node node) {
		return node.right == null && node.left == null;
	}
	
	/**
	 * 判断是否为根节点
	 * */
	protected boolean isRoot(Node node) {
		return node.parent == null;
	}
	
	
	/**
	 * 创建一个叶子节点
	 * */
	Node leaf(T data, Node parent) {
		Node leaf = new Node(data);
		leaf.parent = parent;
		leaf.left = null;
		leaf.right = null;
		return leaf;
	}
	
	class Node {
		T data;
		Node parent;
		Node right;
		Node left;
		Node(T data) {
			this.data = data;
		}
	}
}

