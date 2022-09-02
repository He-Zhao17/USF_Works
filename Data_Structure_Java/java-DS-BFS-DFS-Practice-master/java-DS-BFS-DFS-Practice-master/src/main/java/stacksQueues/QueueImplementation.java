package stacksQueues;

/** Write a method that implements a Queue using two stacks.
 * You need to implement enqueue, dequeue and empty() methods using
 * only the methods of the Stack: push(), pop(), and empty().
 * You may NOT use any extra memory apart from two stacks.
 */
public class QueueImplementation {
    private Stack stack1 = new ListStack();
    private Stack stack2 = new ListStack();

    public void enqueue(Object item) {
        // FILL IN CODE
        stack1.push(item);
    }

    public Object dequeue() {
        // FILL IN CODE
        while(!stack1.empty()) {
            stack2.push(stack1.pop());
        }
        if (stack2.empty()) {
            return null;
        } else {
            return stack2.pop();
        }
        //return null; // change
    }

    public boolean empty() {
        // FILL IN CODE
        if (stack1.empty() && stack2.empty()) {
            return true;
        } else {
            return false;
        }
        //return false; // change
    }

    public static void main(String[] args) {
        QueueImplementation queue = new QueueImplementation();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue(6);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.empty());

    }
}
