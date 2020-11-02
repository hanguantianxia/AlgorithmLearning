package simpletable;

import java.io.Serializable;
import java.util.Iterator;

public class BinarySearchST<Key extends Comparable<Key>, Value> implements ST<Key, Value>, Serializable {
    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }


    @Override
    public void put(Key key, Value val) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    @Override
    public Value get(Key key) {

        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return vals[i];
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        int pos = rank(key);
        if (pos < 0 || pos >= N){
            return;
        }
        N--;
        for(int i=pos;i<N;i++){
            keys[i] = keys[i+1];
            vals[i] = vals[i+1];
        }
    }

    @Override
    public boolean contains(Key key) {
        int pos = rank(key);
        if(pos<0 || pos >= N){
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Key min() {
        if(isEmpty())
            return null;
        return keys[0];
    }

    @Override
    public Key max() {
        if(isEmpty())
            return null;
        return keys[N-1];
    }

    @Override
    public Key floor(Key key) {
        if(isEmpty()) {
            return null;
        }
        int pos = rank(key);
        if(pos<0){
            return null;
        }
        if(pos>=N){
            return keys[N-1];
        }
        return keys[pos];
    }

    @Override
    public Key ceiling(Key key) {
        if(isEmpty()){
            return null;
        }
        int pos = rank(key);
        if(pos<0){
            return keys[0];
        }
        if(pos>=N){
            return null;
        }
        return keys[pos];
    }

    @Override
    public int rank(Key key) {
        int low = 0;
        int high = N - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                high = mid - 1;
            } else if (cmp > 0) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return low;
    }

    @Override
    public Key select(int k) {
        return keys[k];
    }


    @Override
    public int size(Key lo, Key hi) {
        return 0;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        int loPos = rank(lo);
        int hiPos = rank(hi);
        if (loPos >= N || hiPos < 0 || loPos > hiPos) {
            return null;
        }
        loPos = Math.max(0, loPos);
        hiPos = Math.min(N - 1, hiPos);

        class ForwardIterator implements Iterator<Key> {
            private int cur;
            private int hi;

            public ForwardIterator(int lo, int hi) {
                this.hi = hi;
                this.cur = lo;
            }


            @Override
            public boolean hasNext() {
                return cur <= hi;
            }

            @Override
            public Key next() {
                return keys[cur++];
            }

        }

        Iterator<Key> forwardIter = new ForwardIterator(loPos, hiPos);
        return new Iterable<>(){
            @Override
            public Iterator<Key> iterator() {
                return forwardIter;
            }
        };
    }


    @Override
    public Iterable<Key> keys() {
        return new Iterable<Key>() {
            @Override
            public Iterator<Key> iterator() {
                return new Iterator<Key>() {
                    private int x = 0;
                    @Override
                    public boolean hasNext() {
                        return x < N;
                    }

                    @Override
                    public Key next() {
                        return keys[x++];
                    }
                };
            }
        };
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }
}
