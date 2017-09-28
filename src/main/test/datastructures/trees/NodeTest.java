package datastructures.trees;

class NodeTest {
    public static void main(String[] args) {
        AvlTree tree = new AvlTree();

        System.out.println("Inserting values 1 to 10");
        for (int i = 1; i < 10; i++)
            tree.insert(i);

        System.out.print("Printing balance: ");
        tree.printBalance();
    }
}