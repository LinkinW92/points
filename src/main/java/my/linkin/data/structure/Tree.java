package my.linkin.data.structure;

public class Tree<T> {

}

class Node<T> {
	T data;
	Node<T> parent;
	
	public Node(T data) {
		this.data = data;
	}
}
