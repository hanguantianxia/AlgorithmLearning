package simpeltable;

public class BST<Key extends Comparable<Key> , Value> implements ST<Key, Value>{
    private Node root;

    private class Node{
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int N){
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }


    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value val){
        if(root==null){
            return new Node(key,val,1);
        }
        int cmp = key.compareTo(root.key);
        if(cmp < 0){
            root.left = put(x.left, key, val);
        }
        else if(cmp > 0){
            root.right = put(x.right, key, val);
        }
        else{
            x.val = val;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node root, Key key){
        if(root==null) return null;
        int cmp = key.compareTo(root.key);
        if(cmp < 0){
            return get(root.left, key);
        }
        else if (cmp > 0){
            return get(root.right, key);
        }
        else{
            return root.val;
        }
    }

    @Override
    public void delete(Key key) {

    }

    @Override
    public boolean contains(Key key) {
        return false;
    }

    @Override
    public int size() {
        return size(root);
    }
    private int size(Node root){
        if(root==null){
            return 0;
        }
        else{
            return root.N;
        }
    }

    @Override
    public Key min() {
        return min(root).key;
    }
    private Node min(Node root){
        if(root.left==null){
            return root;
        }
        else{
            return min(root.left);
        }
    }

    @Override
    public Key max() {
        return max(root).key;
    }

    private Node max(Node root){
        if(root.right==null){
            return root;
        }
        else{
            return max(root.right);
        }
    }

    @Override
    public Key floor(Key key) {
        Node x = floor(root, key);
        if(x==null){
            return null;
        }
        return x.key;
    }

    private Node floor(Node root, Key key){
        if(root==null){
            return null;
        }
        int cmp = key.compareTo(root.key);

        if(cmp == 0){
            return root;
        }

        if(cmp < 0){
            return floor(root.left, key);
        }

        Node t = floor(root.right, key);
        if (t != null){
            return t;
        }
        else{
            return root;
        }

    }

    @Override
    public Key ceiling(Key key) {
        return null;

    }

    private Node ceiling(Node root, Key key){
        if(root==null) return null;
        int cmp = key.compareTo(root.key);
        if(cmp < 0){
            
        }
    }

    @Override
    public int rank(Key key) {
        return 0;
    }

    @Override
    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node root, int k){
        if(root == null) return null;
        int curSize = size(root.left);
        if(k > curSize){
            return select(root.right, k - curSize - 1);
        }
        else  if(curSize < k){
            return select(root.left, k);
        }
        else{
            return root;
        }
    }



    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }
}
