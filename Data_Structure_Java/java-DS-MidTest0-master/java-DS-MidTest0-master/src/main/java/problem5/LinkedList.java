package problem5;

/** A class representing a linked list. */
public class LinkedList {
	private Node head, tail;

	/** Constructor */
	public LinkedList() {
		head = null;
		tail = null;
	}

	/**
	 * Creates a new node with the given element and adds it to the back of the
	 * list
	 */
	public void append(int elem) {
		Node newNode = new Node(elem);
		if (tail != null) {
			tail.setNext(newNode);
			tail = newNode;
		} else {
			head = tail = newNode;
		}

	}

	/** Prints all the nodes in the link list */
	public void printNodes() {
		Node current = head;
		while (current != null) {
			System.out.print(current.elem() + " ");
			current = current.next();
		}

	}

	/**
	 * Build a new linked list from "this" linked list by including elements larger than a given threshold
	 * @param threshold threshold
	 * @return head of the new linked list
	 */
	public Node elementsLargerThanThres(int threshold) {
		Node newListHead = null; // head of the new linked list
		Node newListTail = null; // tail of the new linked list
		Node current = head; // head of "this" linked list
		// current will iterate over "this" list
		// FILL IN CODE


		return newListHead;
	}

	// You can use this main method to test your code */
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.append(40);
		list.append(16);
		list.append(3);
		list.append(30);

		list.printNodes();
		System.out.println();
		Node newHead = list.elementsLargerThanThres(15);
		// pring elements in the new linked list
		Node current = newHead;
		while (current != null) {
			System.out.print(current.elem() + " ");
			current = current.next();
		}

	}

}
