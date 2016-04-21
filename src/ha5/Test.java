package ha5;

/**
 * Created by mbronner on 19.04.16.
 */
public class Test {

    public static void main(String[] args) {
        BTree tree = new BTree(4);
        tree.insert(4);
        tree.insert(5);
        tree.insert(3);
        System.out.println(tree.search(4));
        System.out.println(tree.search(2));
    }

}
