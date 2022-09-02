Lab 5. 30 points.

Due Oct 11, 11:59pm.

In this Lab, we'll be implementing a Linked List, using the starter code provided here. We'll use generics, and an inner class for the node. For each step, please create unit tests that demonstrate your code.
                                                                                                                                             


1. (3 points) Implement the addInFront, print() and remove() methods. Create a unit test that tests your list with both Strings and Integers. 

2. (3 points) Implement contains().

3. (4 points) Implement the prev and tail references so that you have a doubly linked list. Modify remove() accordingly.

4. (5 points) Now let's subclass LinkedList to make a SortedLinkedList. It will keep all items in sorted order using insertionSort. You should implement the Comparable interface on the Node class.

<del>5. (5 points) Implement the List interface for your LinkedList. This will require adding an Iterator to your LinkedList, as we did in the previous lab.</del>

6. (5 points) Now create Stack and Queue interfaces that your LinkedList implements. Stack should implement push() and pop(), and Queue should implement enqueue() and dequeue();

7. (5 points) Last, let's return to our Hangman project and refactor our code to take advantage of what we've learned about lists. I've provided a correct solution for you - you're welcome to use your own code if you prefer. Replace the correctGuesses, incorrectGuesses and partialSolution in Scoreboard with Lists of Strings. Fix updatePartialSolution to work with your List.
