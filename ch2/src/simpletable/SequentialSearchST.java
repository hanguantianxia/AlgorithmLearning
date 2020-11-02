package simpletable;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SequentialSearchST <Key extends Comparable<Key>, Value> implements ST<Key, Value>, Iterable<SequentialSearchST.Node>{
    private Node first;
    private int size;
    private Key min;
    private Key max;

    @Override
    public Iterator<SequentialSearchST.Node> iterator() {
        return new Iterator<SequentialSearchST.Node>(){
          private Node cur = first;
          public boolean hasNext(){return cur!=null;}
          public Node next(){
              Node returnNode = cur;
              cur = cur.next;
              return returnNode;
          }
        };
    }

    @Override
    public boolean isEmpty() {
        return first==null;
    }

    class Node{
        Key key;
        Value val;
        Node next;
        public Node(Key key, Value val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public SequentialSearchST(){
        first = null;
        size = 0;
    }

    public Value get(Key key){
        for(Node x = first; x!=null; x=x.next){
            if(key.equals(x.val)){
                return x.val;
            }
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        Node last = first;
        for(Node x=first; x!=null; x=x.next){
            if(key.equals(x.key)){
                if(last == first){
                    first = x.next;
                }
                else{
                    last.next = x.next;
                }
                return;
            }
            last = x;
        }
    }

    @Override
    public boolean contains(Key key) {
        for(Node x=first; x!=null; x=x.next){
            if(key.equals(x.key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Key min() {
        return min;
    }

    @Override
    public Key max() {
        return max;
    }

    @Override
    public Key floor(Key key) {
        return null;
    }

    @Override
    public Key ceiling(Key key) {
        return null;
    }

    @Override
    public int rank(Key key) {
        return 0;
    }

    @Override
    public Key select(int k) {
        return null;
    }

    @Override
    public void deleteMin() {
        delete(min());
    }

    @Override
    public void deleteMax() {
        delete(max());
    }

    @Override
    public int size(Key lo, Key hi) {
        return 0;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    @Override
    public Iterable<Key> keys() {

        return null;
    }


    public void put(Key key, Value val){
        for(Node x=first; x!=null; x=x.next){
            if(key.equals(x.val)){
                x.val = val;
                return;
            }
            first = new Node(key, val, first); // not shot ,create a new node
            size++;
        }
    }

}
