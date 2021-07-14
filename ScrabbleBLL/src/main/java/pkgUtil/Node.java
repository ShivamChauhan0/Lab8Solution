package pkgUtil;

public class Node<E> {

    private E value;
    private Node<E> nextNode;

    public Node(E value) {
        this.value = value;
    }

	public E getValue() {
		return value;
	}

	public Node<E> getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node<E> nextNode) {
		this.nextNode = nextNode;
	}
    
    
}