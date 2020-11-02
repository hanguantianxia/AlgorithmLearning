package tree;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class BiDirectedTreeNode<T>  {
        public BiDirectedTreeNode<T> left;
        public BiDirectedTreeNode<T> right;
        public BiDirectedTreeNode<T> parent;
        public T val;

        public BiDirectedTreeNode(){
             left = right = parent = null;
             val = null;
        }

        public BiDirectedTreeNode(T val, BiDirectedTreeNode left, BiDirectedTreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
            parent = null;
        }

        public void addLeft(BiDirectedTreeNode left){
            left.parent = this;
            this.left = left;
        }

        public void addRight(BiDirectedTreeNode right){
            this.right = right;
            right.parent = this;
        }

        public boolean hasChild(){
            return this.left != null || this.right !=null;
        }

    @Override
    public String toString() {
        return "BiDirectedTreeNode{" +
                "val=" + val +
                '}';
    }

}
