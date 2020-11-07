package simpeltable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BST<Key extends Comparable<Key>, Value> implements ST<Key, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public BST() {
        root = null;
    }

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node root, Key key, Value val) {
        if (root == null) {
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = put(root.left, key, val);
        } else if (cmp > 0) {
            root.right = put(root.right, key, val);
        } else {
            root.val = val;
        }
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    @Override
    public Value get(Key key) {
        return get(root, key).val;
    }

    private Node get(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            return get(root.left, key);
        } else if (cmp > 0) {
            return get(root.right, key);
        } else {
            return root;
        }
    }

    @Override
    public void deleteMin() {
        if (isEmpty())
            return;
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    @Override
    public void deleteMax() {
        if (isEmpty()) {
            return;
        }
        root = deleteMax(root);
    }

    private Node deleteMax(Node root) {
        if (root.right == null) {
            return root.left;
        }
        root.right = deleteMax(root.right);
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    @Override
    public void delete(Key key) {
        if (root == null) {
            return;
        }
        root = delete(root, key);
    }

    private Node delete(Node root, Key key) {
        if (root == null) {
            return null;
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = delete(root.left, key);
        } else if (cmp > 0) {
            root.right = delete(root.right, key);
        } else {
            //attention!
            if (root.right == null) {
                return root.left;
            }
            if (root.left == null) {
                return root.right;
            }
            Node minNode = min(root.right);
            minNode.left = root.left;
            minNode.right = deleteMin(root);
            root = minNode;
        }

        root.N = size(root.left) + size(root.right) + 1;
        return root;

    }

    @Override
    public boolean contains(Key key) {
        return false;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node root) {
        if (root == null) {
            return 0;
        } else {
            return root.N;
        }
    }

    @Override
    public Key min() {
        if (isEmpty()) {
            return null;
        }
        return min(root).key;
    }

    private Node min(Node root) {
        if (root.left == null) {
            return root;
        } else {
            return min(root.left);
        }
    }

    @Override
    public Key max() {
        if (isEmpty()) {
            return null;
        }
        return max(root).key;
    }

    private Node max(Node root) {
        if (root.right == null) {
            return root;
        } else {
            return max(root.right);
        }
    }

    @Override
    public Key floor(Key key) {
        if (isEmpty()) {
            return null;
        }
        Node x = floor(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    private Node floor(Node root, Key key) {
        if (root == null) {
            return null;
        }
        int cmp = key.compareTo(root.key);

        if (cmp == 0) {
            return root;
        }

        if (cmp < 0) {
            return floor(root.left, key);
        }

        Node t = floor(root.right, key);
        if (t != null) {
            return t;
        } else {
            return root;
        }

    }

    @Override
    public Key ceiling(Key key) {
        return ceiling(root, key).key;

    }

    private Node ceiling(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            return root;
        }
        if (cmp > 0) {
            return ceiling(root.right, key);
        }
        Node t = ceiling(root.left, key);
        if (t == null) {
            return root;
        }
        return t;
    }

    @Override
    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node root, Key key) {
        if (root == null) {
            return 0;
        }
        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            return size(root.left);
        } else if (cmp > 0) {
            return 1 + root.left.N + rank(root.right, key);
        } else {
            return rank(root.left, key);
        }
    }

    @Override
    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node root, int k) {
        if (root == null) return null;
        int curSize = size(root.left);
        if (k > curSize) {
            return select(root.right, k - curSize - 1);
        } else if (curSize < k) {
            return select(root.left, k);
        } else {
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

    @Override
    public Iterable<Key> keys() {
        Queue<Key> q = new LinkedList<>();
        keys(root, q);
        return q;
    }

    private void keys(Node root, Queue<Key> q) {
        if (root == null) {
            return;
        }
        keys(root.left, q);
        q.add(root.key);
        keys(root.right, q);
    }

    public static void main(String[] args) {
        int[] keys = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        char[] vals = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        BST<Integer, Character> bst = new BST<>();
        for (int i = 0; i < keys.length; i++) {
            bst.put(keys[i], vals[i]);
        }
        show(bst);
        bst.put(-1, '3');
        show(bst);
        System.out.println(bst.get(1));
        bst.delete(3);
//        System.out.println(bst.get(3));
        show(bst);

    }

    public static <E extends Comparable<E>, T> void show(BST<E, T> bst) {
        Iterable<E> keysQ = bst.keys();
        Iterator<E> keysIter = keysQ.iterator();
        while (keysIter.hasNext()) {
            System.out.print(keysIter.next() + "\t");
        }
        System.out.println();
    }
}
