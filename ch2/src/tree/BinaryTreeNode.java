package tree;

public class BinaryTreeNode<T> {
    public BinaryTreeNode<T> left;
    public BinaryTreeNode<T> right;
    T val;

    public BinaryTreeNode(T val, BinaryTreeNode<T> left, BinaryTreeNode<T> right){
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public BinaryTreeNode(T val){
        this.val = val;
        this.left = this.right = null;
    }

    public BinaryTreeNode(){
        left = null;
        right = null;
    }
}
