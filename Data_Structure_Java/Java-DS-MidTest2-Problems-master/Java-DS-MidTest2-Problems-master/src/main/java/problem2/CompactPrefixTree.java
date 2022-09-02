package problem2;

/** Problem 2: Fill in the code in the private method countWords(Node root)
 * that recursively counts the number of valid words in the compact prefix tree
 * specified by the given root.
 * Please refer to the pdf for an example. The number of valid words in the tree shown in the pdf is 9
 * (the words are "cart", "carts", "case", "cat", "doge", "doghouse", "wrath", "wrist", "wristle").
 * Note that your method does not need to print words, just count them (so this is a simpler problem).
 * The word "dog" was not actually added as a valid word to this tree,
 * that is why it is not included in the count.
 *
 * Hint: You can count valid words by counting the leaves of the tree and
 * by counting inner nodes that have isWord = true.
 * See the body of the method for additional hints. This method must be recursive.
 **
 * For the tree given in the main method (same as shown in the pdf), the number of words should be 9.
 * Note that your code should be general and work on other compact prefix trees.
 */
public class CompactPrefixTree  {
    private Node root;

    /** Count valid words in the compact prefix tree */
    public int countWords() {
        return countWords(this.root);
    }

    /** Adds a given word to the dictionary.
     * @param word the word to add to the dictionary
     */
    public void add(String word) {
        root = add(word.toLowerCase(), root); // Calling private add method
    }

    // ---------- Private helper methods ---------------
    private int countWords(Node root) {
        // First base case: provided to you
        if (root == null)
            return 0;

        // Second base case: if the node is a leaf node
        // FILL IN CODE
        /*if (root.children == null) {
            return 0;
        } else {
            boolean t = false;
            for (Node r : root.children) {
                if (r != null) {
                    t = true;
                    break;
                }
            }
            if (!t) {
                return 0;
            }
        }*/
        // Recursive case: recursively count the number of leaves in the children and add to count.
        int count = 0;
        // FILL IN CODE
        for (Node r : root.children) {
            if (r == null) {
                continue;
            } else {
                count += countWords(r);
            }
        }


        // Don't forget to add a 1 if the root's isWord flag is true
        if (root.isWord) {
            count++;
        }

        return count;
    }

    /**
     *  A private add method that adds a given string to the tree
     * @param s the string to add
     * @param node the root of a tree where we want to add a new string

     * @return a reference to the root of the tree that contains s
     */
    private Node add(String s, Node node) {
        if (node == null)  { // base case: tree is null
            node = new Node();
            node.prefix = s;
            node.isWord = true;
            return node;
        }

        int indexDifference = findIndexDifference(s, node.prefix);

        if (indexDifference == node.prefix.length())  { //s equals or starts with node prefix
            if (s.length() > indexDifference) {
                int index = ((int) s.charAt(indexDifference)) - ((int) 'a');
                node.children[index] = add(s.substring(indexDifference), node.children[index]);
                return node;
            }
            node.isWord = true;
            return node;
        }

        String firstRemainder = node.prefix.substring(0, indexDifference);
        String secondRemainder = node.prefix.substring(indexDifference);

        Node newNode = new Node();
        newNode.prefix = firstRemainder;

        int index = ((int) node.prefix.charAt(indexDifference)) - ((int) 'a');
        newNode.children[index] = node;
        node.prefix = secondRemainder;

        if (indexDifference == s.length()) {
            newNode.isWord = true;
        }
        else {
            index = ((int) s.charAt(indexDifference)) - ((int) 'a');
            newNode.children[index] = add(s.substring(indexDifference), null);
        }
        return newNode ;

    }


    /**
     * Finds an index of the first character where strings s1 and s2 differ.
     * @param s1 the first string
     * @param s2 the second string
     * @return the index of the first character where the strings are different
     */
    private int findIndexDifference(String s1, String s2) {

        int index = 0;
        while (index < s1.length() && index < s2.length() &&  s1.charAt(index) == s2.charAt(index)) {
            index++;

        }
        return index;
    }


    // --------- Private class Node ------------
    // Represents a node in a compact prefix tree
    private class Node {
        String prefix; // prefix stored in the node
        Node children[]; // array of children (26 children)
        boolean isWord; // true if by concatenating all prefixes on the path from the root to this node, we get a valid word

        Node() {
            isWord = false;
            prefix = "";
            children = new Node[26]; // initialize the array of children
        }

    }

    public static void main(String[] args) {
        // This is the tree shown in the pdf
        // You can test your code on it.
        CompactPrefixTree dict = new CompactPrefixTree();
        dict.add("cat");
        dict.add("cart");
        dict.add("carts");
        dict.add("case");
        dict.add("doge");
        dict.add("doghouse");
        dict.add("wrist");
        dict.add("wrath");
        dict.add("wristle");
        String hardedCodedStringForTree = "ca" + System.lineSeparator() +
                "  rt*" + System.lineSeparator() +
                "   s*" + System.lineSeparator() +
                "  se*" + System.lineSeparator() +
                "  t*" + System.lineSeparator() +
                " dog" + System.lineSeparator() +
                "  e*" + System.lineSeparator() +
                "  house*" + System.lineSeparator() +
                " wr" + System.lineSeparator() +
                "  ath*" + System.lineSeparator() +
                "  ist*" + System.lineSeparator() +
                "   le*";
        System.out.println(hardedCodedStringForTree); // shows you the tree you are testing on
        System.out.println(dict.countWords()); // should print 9
    }

}
