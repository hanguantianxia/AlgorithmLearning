package simpletable;

public interface ST <Key extends Comparable<Key>, Value>{
    /**
     * put the (key, value) pair into table
     * @param key the key obj
     * @param value the value obj
     */
    void put(Key key, Value value);

    /**
     * put the value
     * @param key the key obj
     * @return the value
     */
    Value get(Key key);

    /**
     * delete the key and its value in the table
     * @param key the key to delete
     */
    void delete(Key key);

    /**
     * check the key is in the table
     * @param key the key to check
     * @return the boolean type of res , true means have
     */
    boolean contains(Key key);

    /**
     * return the size of table at this time
     * @return
     */
    int size();

    /**
     *
     * @return
     */
    Key min();

    /**
     *
     * @return
     */
    Key max();

    /**
     *
     * @param key
     * @return
     */
    Key floor(Key key);

    Key ceiling(Key key);

    int rank(Key key);
    Key select(int k);
    default void deleteMin(){
        delete(min());
    }
    default void deleteMax(){
        delete(max());
    }
    default int size(Key lo, Key hi){
        if(hi.compareTo(lo)<0){
            return 0;
        }
        else if(contains(hi)){
            return rank(hi) - rank(lo) + 1;
        }
        else{
            return rank(hi) - rank(lo);
        }
    }
    Iterable<Key> keys(Key lo, Key hi);
    default Iterable<Key> keys(){
        return keys(min(), max());
    }

    boolean isEmpty();
}
