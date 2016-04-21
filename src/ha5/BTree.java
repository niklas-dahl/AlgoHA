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
            return this.children.size()==this.values.size()+1;
        }

        public boolean contains(int value) {
            return values.contains(value);
        }

        public int overflow() {
            return values.size()-rank+1;
        }
    }

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

    public boolean search(int value) {
        TreePointer ptr = new TreePointer(root);
        while(!ptr.ref.contains(value))
            if(!ptr.jump(value))
                return false;
        return true;
    }

    public int insert(int value) {
        TreePointer ptr = new TreePointer(root);
        do {
            if (ptr.ref.contains(value))
                throw new ArithmeticException("Wert schon vorhanden!");
        } while(ptr.jump(value));
        if(ptr.ref.overflow()<0) {
            ptr.insert(value);
            return 0;
        }
        throw new NotImplementedException(); // TODO Fall 2 und 3
    }


}
