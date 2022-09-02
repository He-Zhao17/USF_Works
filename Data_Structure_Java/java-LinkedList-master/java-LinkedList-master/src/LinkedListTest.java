import java.lang.reflect.Type;

public class LinkedListTest {
    public static void main(String args[]) {
        //Test Integer.
        LinkedList test = new LinkedList();
        LinkedList.Node temp = test.new Node(1);
        LinkedList.Node temp2 = test.new Node(2);
        test.addInFront(0,temp);
        test.addInFront(1,temp2);
        test.print();
        test.remove(1);
        test.print();
        test.remove(0);
        test.print();
        //Test String
        test = new LinkedList();
        temp = test.new Node("K");
        temp2 = test.new Node("O");
        test.addInFront(0,temp);
        test.addInFront(1,temp2);
        test.print();
        test.remove(0);
        test.print();
        test.remove(0);
        test.print();

        test = new LinkedList();
        temp = test.new Node('k');
        temp2 = test.new Node('1');
        test.addInFront(0,temp);
        test.addInFront(1,temp2);
        LinkedList.SortedLinkedList sortedtest = test.new SortedLinkedList(test);
        sortedtest.sort().print();
        test.pop();
        test.print();




    }

}
