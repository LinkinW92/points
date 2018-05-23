package my.linkin.data.structure;


/**
 * 平衡二叉树的实现
 * @author linkin
 * */
public class AVL<T> extends Tree<T>{

	public AVL(T data) {
		super(data);
	}

	//通过广度优先遍历或深度优先遍历来查找节点
	@Override
	protected Node isExist(T data) {
		return null;
	}

	@Override
	protected Tree<T>.Node insert(T t) {
		return null;
	}

	@Override
	protected void delete(T data) {
	}
}
