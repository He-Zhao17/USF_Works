import org.w3c.dom.Node;

import java.lang.reflect.Type;

public class LinkedList<Type> implements LinkedListStack<Type>, LinkedListQueue<Type>{

    private Node head; // cb - should be Node<Type>
    private Node tail;

    @Override
    public void enqueue(Type a) {
        Node<Type> temp = new Node<>(a);
        addInFront(length(), temp);
    }

    @Override
    public Type dequeue() {
        Node<Type> temp = get(0);
        remove(0);
        return temp.data;
    }

    @Override
    public void push(Type a) {
        Node<Type> temp = new Node<>(a);
        addInFront(0, temp);
    }

    @Override
    public Type pop() {
        Node<Type> temp = get(0);
        remove(0);
        return temp.data;
    }


    class Node<Type> implements Comparable{
        Type data;
        Node next;
        Node prev;
        Node tail; // you will need to figure out how to hook this up.



        Node(Type d) {
            data = d;
        }
        void setNext(Node a) {
            next = a;
        }
        Node getNext() {
            return this.next;
        }
        void setPrev(Node a) {
            prev = a;
        }
        Node getPrev() {
            return prev;
        }
        void setTail(Node a) {
            tail = a;
        }
        Node getTail() {
            return tail;
        }
        void setData(Type a) {
            data = a;
        }
        Type getData() {
            return data;
        }

        Boolean equals(Node e) {                // cb - no need to check types - just see if the data.equals(e.data)
            if (e.getData().getClass() == data) {
                if (data instanceof Integer || data instanceof Double || data instanceof Character || data instanceof Long) {
                    if (data == e.getData()) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    if (data.equals(e.getData())) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }

        @Override
        public int compareTo(Object o) {        // cb - same comment as above. If you have generics set up correctly, you don't need to
                                                // do all of this.
            if (o instanceof Node) {
                Node a = (Node)o;
                if (a.getData() instanceof Integer || a.getData() instanceof Double || a.getData() instanceof Long) {
                    if (this.getData() instanceof Integer || this.getData() instanceof Double || this.getData() instanceof Long) {
                        if ((Double)this.getData() - (Double)a.getData() < 0) {
                            return -1;
                        } else if ((Double)this.getData() - (Double)a.getData() > 0) {
                            return 1;
                        } else if ((Double)this.getData() - (Double)a.getData() == 0) {
                            return 0;
                        } else {
                            return 0;
                        }
                    } else if (this.getData() instanceof Character || this.getData() instanceof String) {
                        return 1;
                    } else {
                        return 1;
                    }
                } else if (a.getData() instanceof Character || a.getData() instanceof String) {
                    if (this.getData() instanceof Integer || this.getData() instanceof Double || this.getData() instanceof Long) {
                        return -1;
                    } else if (this.getData() instanceof Character || this.getData() instanceof String) {
                        char ct,ca;
                        String strt,stra;
                        if (this.getData() instanceof Character && a.getData() instanceof Character) {
                            ca = (Character)a.getData();
                            ct = (Character)this.getData();
                            return Integer.valueOf(ct) - Integer.valueOf(ca);
                        } else if (this.getData() instanceof String && a.getData() instanceof Character) {
                            return 1;
                        } else if (this.getData() instanceof Character && a.getData() instanceof String) {
                            return -1;
                        } else if (this.getData() instanceof String && a.getData() instanceof String) {
                            strt = (String)this.getData();
                            stra = (String)a.getData();
                            int k;
                            if (strt.length() >= stra.length()) {
                                k = stra.length();
                            } else {
                                k = strt.length();
                            }
                            for (int i = 0; i < k; i++) {
                                if (Integer.valueOf(strt.charAt(i)) > Integer.valueOf(stra.charAt(i))) {
                                    return 1;
                                } else if (Integer.valueOf(strt.charAt(i)) < Integer.valueOf(stra.charAt(i))) {
                                    return -1;
                                }
                            }
                            return strt.length() - stra.length();
                        }
                    } else {
                        return 1;
                    }
                } else {
                    if (this.getData() instanceof Integer || this.getData() instanceof Double || this.getData() instanceof Long || this.getData() instanceof Character || this.getData() instanceof String) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
                return 0;
            }
            return 0;
        }
    }

    class SortedLinkedList {        // cb - this should be in a separate file. -1
        LinkedList unsorted;

        void setUnsorted(LinkedList a) {
            unsorted = a;
        }
        LinkedList getUnsorted() {
            return unsorted;
        }

        SortedLinkedList(LinkedList a) {
            unsorted = a;
        }

        LinkedList sort() {     // why return a LinkedList? also not parameterized.
            if (unsorted.length() == 0 || unsorted.length() == 1) {
                return unsorted;
            } else {
                for (int i = 1; i < unsorted.length(); i++) {
                    for (int j = 0; j < i; j++) {
                        if (unsorted.get(i).compareTo(unsorted.get(j)) <= 0) {
                            Node k = new Node(unsorted.get(i).getData());
                            unsorted.remove(i);
                            unsorted.addInFront(j, k);
                        }
                    }
                }
                return unsorted;
            }
        }
    }

    public LinkedList() {           // cb - Why is this here? Not generic.
        head = null;
        tail = null;
    }


   // your methods will go here.
    public void addInFront(int index, Node addnode) {
        if (length() == 0) {
            head = new Node(0);
            tail = new Node(0);
            head.setNext(addnode);
            addnode.setPrev(head);
            head.setTail(LinkedList.this.tail);
            LinkedList.this.tail.setPrev(addnode);
            addnode.setNext(LinkedList.this.tail);
            LinkedList.this.tail.setTail(LinkedList.this.tail);
            addnode.setTail(LinkedList.this.tail);
        } else if (index >= 0 && index <= length() - 1) {
            Node temp = get(index);
            addnode.setPrev(temp.getPrev());
            temp.getPrev().setNext(addnode);
            addnode.setNext(temp);
            temp.setPrev(addnode);
            addnode.setTail(LinkedList.this.tail);
        } else if (index == length()) {
            Node temp = LinkedList.this.tail;
            addnode.setPrev(temp.getPrev());
            temp.getPrev().setNext(addnode);
            addnode.setNext(temp);
            temp.setPrev(addnode);
            addnode.setTail(LinkedList.this.tail);
        } else {
            throw new IllegalArgumentException("Incorrect index.");
        }
    }

    public void print() {
        StringBuffer str = new StringBuffer();
        if (length() == 0) {
            System.out.println("");
        } else {
            Node s = head.next;
            while(s.getNext() != null) {
                str.append(s.data);
                str.append(", ");
                s = s.next;
            }
            str.deleteCharAt(str.length()-1);
            str.deleteCharAt(str.length()-1);
            System.out.println(str);
        }
    }

    public int length(){
        if (head == null || head.getNext() == LinkedList.this.tail) {
            return 0;
        } else {
            Node temp = head.next;
            int len = 1;
            while (temp.getNext() != LinkedList.this.tail) {
                len += 1;
                temp = temp.getNext();
            }
            return len;
        }
    }

    public void remove(int index) {
        if (length() == 0) {
            System.out.println("Error. Empty linkedlist.");
        } else if (index < 0 || index >= length()) {
            throw new IllegalArgumentException("Incorrect index.");
        } else {
            Node s = head;
            Node n = new Node(0);
            for (int i = 0; i < index + 1; i++) {
                s = s.next;
            }
            s.getPrev().setNext(s.getNext());
            s.getNext().setPrev(s.getPrev());
            s.setPrev(n);
            s.setNext(n);
        }
    }
    public Node get(int index) {
        if (length() == 0) {
            System.out.println("Error. Empty linkedlist.");
            return new Node(0);
        } else if (index < 0 || index >= length()) {
            throw new IllegalArgumentException("Incorrect index.");
        } else {
            Node temp = head.getNext();
            for (int i = 0; i < index; i++) {
                temp = temp.getNext();
            }
            return temp;
        }
    }



    public boolean contains(Type e) {
        for (int i = 0; i < length(); i++) {
            if ((get(i).getData().getClass() == e.getClass()) || get(i).getData() == e) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuffer str = new StringBuffer();
        if (length() == 0) {
            System.out.println("");
        } else {
            Node s = head.next;
            while(s.getNext() != null) {
                str.append(s.data);
                s = s.next;
            }
            String str1 = str.toString();
            return str1;
        }
        return "";
    }






}
