package ha5;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public class BTree {

    public BTree(int rank) {
        this.rank = rank;
    }

    public class BTreeNode {
        private ArrayList<Integer> values = new ArrayList<>();
        private ArrayList<BTreeNode> children = new ArrayList<>();
        private BTreeNode parent;

        private BTreeNode(BTreeNode parent) {
            this.parent=parent;
        }

        public boolean isRoot() {
            return parent==null;
        }

        public boolean hasChildren() {
            return this.children.size()!=0;
        }

        public boolean contains(int value) {
            return values.contains(value);
        }

        public int overflow() {
            return values.size()-rank;
        }
    }

    /**
     * Der TreePointer. Hilft beim Iterieren durch den Tree, indem er das Navigieren übernimmt.
     */

    private class TreePointer {
        private BTreeNode ref;

        private TreePointer(BTreeNode start) {
            ref=start;
        }

        private int choose(int value) {
            if(ref.values.contains(value))
                return -1;
            if(ref.values.size()==0 || value<ref.values.get(0))
                return 0;
            for(int x=1; x<ref.values.size(); x++)
                if(value<ref.values.get(x))
                    return x;
            return ref.values.size();
        }

        private boolean reverse() {
            if(ref.parent==null)
                return false;
            ref=ref.parent;
            return true;
        }

        private boolean jumpToChild(int pos) {
            if(!ref.hasChildren() || pos<0 || pos>=ref.children.size())
                return false;
            ref=ref.children.get(pos);
            return true;
        }

        private boolean jump(int value) {
            return jumpToChild(choose(value));
        }

        private void insert(int value) {
            int choose = choose(value);
            if(choose==-1)
                return;
            ref.values.add(choose, value);
        }
    }

    public final int rank;

    private BTreeNode root = new BTreeNode(null);

    /**
     * Suchfunktion. Geht den Tree so lange durch, bis sie den Wert findet oder aus dem Tree jumpt.
     */

    public boolean search(int value) {
        TreePointer ptr = new TreePointer(root);
        while(!ptr.ref.contains(value))
            if(!ptr.jump(value))
                return false;
        return true;
    }

    /**
     * insert ...
     */

    public int insert(int value) {
        TreePointer ptr = new TreePointer(root);
        // Sucht die richtige Stelle...
        do {
            if (ptr.ref.contains(value))
                throw new ArithmeticException("Wert schon vorhanden!");
        } while(ptr.jump(value));
        ptr.insert(value);
        int x = ptr.ref.overflow();
        // checkt ob der Tree jetzt repariert werden muss...
        if(ptr.ref.overflow()<=0)
            return 0;
        // repariert:
        do {
            // wenn das der Root ist, dann wird ein übergeordneter neuer Root erzeugt und dieser Knoten geteilt...
            if(ptr.ref.parent==null) {
                ptr.ref.parent = new BTreeNode(null);
                ptr.ref.parent.children.add(ptr.ref);
                split(ptr.ref);
                this.root=ptr.ref.parent;
                // Dann ist die Aktion beendet.
                return 2;
            }
            // ansonsten wird einfach nur geteilt.
            split(ptr.ref);
            // dann wird zum nächsthöheren Knoten gesprungen
            ptr.reverse();
        } while(ptr.ref.overflow()==1); // hier wird gecheckt, ob der nächsthöere Knoten noch repariert werden muss.
        return 1;
    }


    /**
     * Diese Funktion splittet einen Knoten auf und teilt seine Values und Members auf zwei neue Knoten auf,
     * die dann in den Parent eingeordnet werden.
     */

    private void split(BTreeNode node) {
        int index = node.parent.children.indexOf(node);
        BTreeNode one = new BTreeNode(node.parent);
        BTreeNode two = new BTreeNode(node.parent);
        for(int x=0; x<node.values.size()/2; x++) {
            one.values.add(node.values.remove(0));
            if(!node.children.isEmpty())
                one.children.add(node.children.remove(0));
        }
        if(!node.children.isEmpty())
            one.children.add(node.children.remove(0));
        node.parent.values.add(index, node.values.remove(0));
        while(!node.values.isEmpty()) {
            two.values.add(node.values.remove(0));
            if(!node.children.isEmpty())
                two.children.add(node.children.remove(0));
        }
        node.parent.children.add(index, two);
        node.parent.children.add(index, one);
        node.parent.children.remove(node);
    }


}
