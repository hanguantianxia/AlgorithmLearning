package maxpriorqueue;

public interface MaxQueue<Key extends  Comparable<Key>>{
    void insert(Key v);
    Key max();
    Key delMax();
    boolean isEmtpy();
    int size();

}
