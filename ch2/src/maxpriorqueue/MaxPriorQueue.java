package maxpriorqueue;

public interface MaxPriorQueue<Key extends  Comparable<Key>>{
    void insert(Key v);
    Key max();
    Key delMax();
    boolean isEmpty();
    int size();
}
