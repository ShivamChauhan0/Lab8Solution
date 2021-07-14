package pkgUtil;

import java.util.AbstractList;

public class CircularLinkedList<E> extends AbstractList<E>   {

	private Node head = null;
	private Node tail = null;
	private Node currNode = null;
	private static int iCount = 0;

	public CircularLinkedList()
	{
		iCount = 0;
	}
	
	public E getCurrent()
	{
		return (E) currNode.getValue();
	}
	
	public E getNext()
	{
		Node currentNode = this.currNode;

		if (head == null) {
			return null;
		} else {
			do {
				if (currentNode.getValue() == this.currNode.getValue()) {
					this.currNode = currentNode.getNextNode();
					return (E) currentNode.getNextNode().getValue();
				}
				currentNode = currentNode.getNextNode();
			} while (currentNode != head);
			return null;
		}
		
	}
	public E getNextNode(E value)
	{
		Node currentNode = head;

		if (head == null) {
			return null;
		} else {
			do {
				if (currentNode.getValue() == value) {
					return (E) currentNode.getNextNode().getValue();
				}
				currentNode = currentNode.getNextNode();
			} while (currentNode != head);
			return null;
		}
		
	}
	public void addNode(E value) {
		Node newNode = new Node(value);

		if (head == null) {
			head = newNode;
		} else {
			tail.setNextNode(newNode);
		}

		tail = newNode;
		tail.setNextNode(head);
		this.currNode = newNode;
		iCount++;
	}

	public boolean containsNode(E searchValue) {
		Node currentNode = head;

		if (head == null) {
			return false;
		} else {
			do {
				if (currentNode.getValue() == searchValue) {
					return true;
				}
				currentNode = currentNode.getNextNode();
			} while (currentNode != head);
			return false;
		}
	}

	public void delete(E valueToDelete) {
		Node currentNode = head;
		if (head == null) { // the list is empty
			return;
		}
		do {
			Node nextNode = currentNode.getNextNode();
			if (nextNode.getValue() == valueToDelete) {
				if (tail == head) { // the list has only one single element
					head = null;
					tail = null;
				} else {
					currentNode.setNextNode(nextNode.getNextNode());

					if (head == nextNode) { // we're deleting the head
						head = head.getNextNode();
					}
					if (tail == nextNode) { // we're deleting the tail
						tail = currentNode;
					}
				}
				break;
			}
			currentNode = nextNode;
		} while (currentNode != head);
		iCount--;
	}


	@Override
	public E get(int index) {		
		Node currentNode = head;
		try
		{
			if (head == null) {
				throw new ArrayIndexOutOfBoundsException();
			} else {
				for (int i=0; i<index; i++)
				{
					currentNode = currentNode.getNextNode();
				}
				return (E) currentNode.getValue();
			}			
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			throw e;
		}		
	}

	@Override
	public int size() {
		return iCount;
	}
}
