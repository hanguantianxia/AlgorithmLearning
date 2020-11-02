package maxpriorqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ArrayMaxPriorQueue<Key extends Comparable<Key>> implements MaxPriorQueue<Key> {
    /**
     *
     */
    private final List<Key> listHeap;
    private boolean increase;
    private int size = 0;

    public ArrayMaxPriorQueue(){
        listHeap = new ArrayList<>();
        listHeap.add(null);
        increase = true;
        size  = 0;
    }
    public ArrayMaxPriorQueue(int max){
        listHeap = new ArrayList<>(max);
        listHeap.add(0, null);

        increase = true;
    }

    public void setIncrease(boolean increase) {
        this.increase = increase;
    }

    public boolean isIncrease() {
        return increase;
    }

    public List<Key> getListHeap() {
        return listHeap;
    }

    @Override
    public void insert(Key v) {
        size++;
        listHeap.add(v);
        swim(size);
    }

    @Override
    public Key max() {
        if(isEmpty()){
            return null;
        }
        return listHeap.get(1);
    }

    @Override
    public Key delMax() {
        if(isEmpty()){
            return null;
        }
        Key maxNode = max();

        exch(1,size--);
        listHeap.remove(size+1);
        sink(1);
        return maxNode;
    }

    @Override
    public boolean isEmpty() {
        if(size==0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public int size() {
        return size;
    }

    private boolean less(int i, int j){
        return listHeap.get(i).compareTo(listHeap.get(j)) < 0;
    }

    private void exch(int i, int j){
        Key t = listHeap.get(i);
        listHeap.set(i, listHeap.get(j));
        listHeap.set(j, t);
    }

    private void swim(int k){
        while (k > 1 && less(k/2, k)){
            exch(k/2, k);
            k = k / 2;
        }
    }

    private void sink(int k){
        while(2 * k <= size){
            int j = 2 * k;
            if(j < size && less(j,j+1)){
                j++;
            }
            if(!less(k,j)) {
                break;
            }
            exch(k,j);
            k = j;
        }
    }

    @Override
    public String toString() {
        return listHeap + "";
    }

    public static void main(String[] args) {
        MaxPriorQueue<Integer> prior = new ArrayMaxPriorQueue<>();
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i < 10; i++){
            prior.insert(random.nextInt(40));
        }
        System.out.println(prior);
        while(!prior.isEmpty()) {
            System.out.print(prior.delMax() + "\t");
        }
        System.out.println(prior);
    }
}
