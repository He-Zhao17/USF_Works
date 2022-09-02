package problem6;

// Problem 6 (3 pts):
// Find and fix bugs in the remove(int i) method that deletes a node at the given index.
// For instance, when the input list is 2, 40, 16, 3, 30, and we call the remove
// method with index = 1, the resulting list should be
// 2, 16, 3, 30 since we removed the element at the index 1.
// Make sure to handle different edge cases. The method returns the value in the deleted node.

// The solution to this problem must be submitted to github.

/** A class representing a linked list. */
public class LinkedList {
	private Node head, tail;

	/** Constructor */
	public LinkedList() {
		head = null;
		tail = null;
	}

	/** Removes the element at the given index.
	 * @param i the index at which to delete the element
	 * @return returns the integer value in the deleted node
	 */
	public int remove(int i) {
		// Fix the bugs
		int count = 0;
		while ((head != null) && (count < i - 1)) {
			head = head.next();
			count++;
		}
		if (head != null) {
			int res = head.next().elem();
			head.setNext(head.next().next());
			return res;
		}
		return -1;
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
		System.out.println();

	}


	// You can use this main method to test your code */
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.append(2);
		list.append(40);
		list.append(16);
		list.append(3);
		list.append(30);
		System.out.println("Input list: ");
		list.printNodes();
		System.out.println("After removing an element at index 3");
		list.remove(3);
		list.printNodes();
		list.remove(0);
		System.out.println("After removing an element at index 0");
		list.printNodes();
		System.out.println();

	}

}
