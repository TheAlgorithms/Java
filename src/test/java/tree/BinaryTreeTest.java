package tree;

import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;

public class BinaryTreeTest {

    @Test
    public void traversesInOrder() {
        // given
        BinaryTree bt = new BinaryTree();
        List<Integer> seq = asList(3, 7, 2, 8, 1);
        seq.forEach(bt::put);
        // when
        List<BinaryTree.Node> nodes = bt.traverse(BinaryTree::inOrder);
        // then
        assertThat(getValues(nodes)).isEqualTo(asList(1, 2, 3, 7, 8));

        System.out.println("BinaryTree of " + seq + " inOrder traversal: " + getValues(nodes));
    }

    @Test
    public void traversesPreOrder() {
        // given
        BinaryTree bt = new BinaryTree();
        List<Integer> seq = asList(3, 7, 2, 8, 1);
        seq.forEach(bt::put);
        // when
        List<BinaryTree.Node> nodes = bt.traverse(BinaryTree::preOrder);
        // then
        assertThat(getValues(nodes)).isEqualTo(asList(3, 2, 1, 7, 8));

        System.out.println("BinaryTree of " + seq + " preOrder traversal: " + getValues(nodes));
    }

    @Test
    public void traversesPostOrder() {
        // given
        BinaryTree bt = new BinaryTree();
        List<Integer> seq = asList(3, 7, 2, 8, 1);
        seq.forEach(bt::put);
        // when
        List<BinaryTree.Node> nodes = bt.traverse(BinaryTree::postOrder);
        // then
        assertThat(getValues(nodes)).isEqualTo(asList(1, 2, 8, 7, 3));

        System.out.println("BinaryTree of " + seq + " postOrder traversal: " + getValues(nodes));
    }

    /**
     * Extracts values from node list
     *
     * @param nodes list of nodes
     * @return list of values
     */
    private List<Integer> getValues(List<BinaryTree.Node> nodes) {
        return nodes.stream().map(n -> n.data).collect(Collectors.toList());
    }
}
