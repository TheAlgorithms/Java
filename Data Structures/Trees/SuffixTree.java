/**
 * Implementation of the Suffix Tree, tested with the string "senseses$"
 * */

import java.util.Hashtable;

public class SuffixTree {

    public static void main(String[] args) {
        SuffixTree suffixTree = new SuffixTree("senseses$");
        // Answer is in root, run the debugger to see its children or follow the print below
        Node root = suffixTree.getRoot();
        System.out.println(suffixTree);
    }



    private String string;
    private Node root;

    public SuffixTree(String string) {
        this.string = string;
        this.root = new Node(null);
        this.buildTree();
    }

    public Node getRoot() {
        return root;
    }

    private void buildTree() {
        for (int i = 0; i < this.string.length(); i++) {
            String currentSuffix = this.string.substring(i);

            Node destinationNode = null, parentNode = root;
            while (true) {
                // For every child of parentNode - look for a match
                int match = 0;
                for (Node t : parentNode.children.values()) {
                    match = numOfSameCharacters(currentSuffix, t.toMe);
                    if (match > 0) {
                        destinationNode = t;
                        break;
                    }
                }


                if (match == 0) { // There are no children matching the current suffix
                    // You can just add that current suffix as a direct child of parentNode
//                    System.out.println("Node : " + parentNode + " does not have any children that match : " + currentSuffix + " you can add a new edge here!");
                    parentNode.addChild(new Node(currentSuffix));
                    break;
                } else {

                    // There is a match, it can be split to 2 options
                    // 1. match is LESS than the edge -> break the edge and insert node
                    if (match < destinationNode.toMe.length()) {
//                        System.out.println("Breaking the edge from : " + parentNode + " to " + destinationNode + " and placing: " + currentSuffix.substring(0, match) + " there");


                        /* take that out to a function maybe? */
                        Node tempNode = new Node(destinationNode.toMe.substring(0, match));
                        parentNode.removeChild(destinationNode);
                        destinationNode.toMe = destinationNode.toMe.substring(match);
                        tempNode.addChild(destinationNode);
                        tempNode.addChild(new Node(currentSuffix.substring(match)));
                        parentNode.addChild(tempNode);


                        break;
                    } else {
                        // 2. match EQUALS to the edge -> dive down and keep looking
                        // Dive down : change the parentNode and repeat the process above
                        //              AND trim the currentSuffix
                        parentNode = destinationNode;
                        currentSuffix = currentSuffix.substring(match);

                        // No break, keep repeat process!
                    }


                }
            }
        }
    }

    private int numOfSameCharacters(String substring, String edge) {
        int ans = 0;
        for (int i = 0; i < Math.min(substring.length(), edge.length()); i++) {
            if (substring.charAt(i) == edge.charAt(i)) {
                // Characters match, continue looking
                ans++;
            } else {
                break;
            }
        }
        return ans;
    }

    private static class Node {
        private String toMe;
        private Hashtable<String, Node> children;

        public Node(String toMe) {
            if (toMe != null) {
                this.toMe = toMe;
            } else {
                this.toMe = "ROOT";
            }
            this.children = new Hashtable<>();
        }

        public void addChild(Node t) {
            this.children.put(t.toMe, t);
        }

        public void removeChild(Node t){
            this.children.remove(t.toMe);
        }

        public void removeChild(String key) {
            this.children.remove(key);
        }

        @Override
        public String toString() {
            return "["+toMe + "]";
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        print(this.root, sb);
        sb.append("\n");
        return sb.toString();
    }

    private void print(Node t, StringBuilder stringBuilder) {
        stringBuilder.append(t);
        for (Node c : t.children.values()) {
            print(c, stringBuilder);
        }
    }

}
