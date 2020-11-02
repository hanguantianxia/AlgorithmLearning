package maxpriorqueue;
import tree.BiDirectedTreeNode;

import java.util.*;

public class BinaryTreeMaxPriorQueue<Key extends Comparable<Key>> implements MaxPriorQueue<Key>{
    private BiDirectedTreeNode<Key> root;
    private int size;
    private Deque<BiDirectedTreeNode<Key>> noLeftQueue;

    public BinaryTreeMaxPriorQueue(){
        root = new BiDirectedTreeNode<>();
        noLeftQueue = new LinkedList<>();
        size = 0;
    }

    @Override
    public void insert(Key v) {
        if(size==0){
            root.val = v;
            noLeftQueue.add(root);
        }
        else{
            BiDirectedTreeNode<Key> newNode = new BiDirectedTreeNode<>(v, null, null);
            BiDirectedTreeNode<Key> firstNonLeft = noLeftQueue.peekFirst();
            if(firstNonLeft.left == null){
                firstNonLeft.addLeft(newNode);
            }
            else{
                firstNonLeft.addRight(newNode);
            }
            noLeftQueue.addLast(newNode);
            swim(newNode);
            if(firstNonLeft.left!=null && firstNonLeft.right!=null){
                noLeftQueue.pollFirst();
            }
        }
        size++;

    }

    @Override
    public Key delMax() {
        if(isEmpty()){
            return  null;
        }
        if(size()==1){
            size--;
            return root.val;
        }
        Key maxVal = root.val;
        BiDirectedTreeNode<Key> lastNode = noLeftQueue.getLast();
        exch(root, lastNode);
        // delete the node from tree
        BiDirectedTreeNode<Key> parent = lastNode.parent;
        if(parent.right==lastNode){
            parent.right = null;
        }
        else{
            parent.left = null;
        }
        if(!parent.hasChild()){
            noLeftQueue.addFirst(parent);
        }
        noLeftQueue.pollLast();

        sink(root);
        size--;
        return maxVal;
    }

    private void swim(BiDirectedTreeNode<Key> target){
        while(target.parent!=null){
            if(less(target.parent,target)){
                exch(target, target.parent);
                target = target.parent;
            }
            else{
                return;
            }
        }
    }

    private void sink(BiDirectedTreeNode<Key> target){
        while (target.hasChild()){
            if(target.left!=null && target.right!=null){
                BiDirectedTreeNode<Key> tmp = target.left;
                if(less(target.left, target.right)){
                    tmp = target.right;
                }
                if(less(target, tmp)){
                    exch(tmp, target);
                }
                else{
                    return;
                }
                target = tmp;
            }
            else {
                if (less(target, target.left)) {
                    exch(target, target.left);
                    target = target.left;
                }
                else{
                    return;
                }
            }
        }
    }

    private boolean less(BiDirectedTreeNode<Key> a, BiDirectedTreeNode<Key> b){
        return a.val.compareTo(b.val) < 0;
    }

    private void exch(BiDirectedTreeNode<Key> a, BiDirectedTreeNode<Key> b){
        Key tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }

    @Override
    public Key max() {
        if(isEmpty()){
            return null;
        }
        else{
            return root.val;
        }
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



    public static void main(String[] args) {
        MaxPriorQueue<Integer> prior = new BinaryTreeMaxPriorQueue<>();
        Map<Integer, Integer> res2Map = new HashMap<>();
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i < 10; i++){
            int randInt = random.nextInt(40);
            prior.insert(randInt);
            System.out.print(randInt + "\t");
        }
        System.out.println();


//        System.out.println(prior);
        while(!prior.isEmpty()) {
            System.out.print(prior.delMax() + "\t");
        }
//        System.out.println(prior);
        int res = res2Map.getOrDefault(2,0);
        System.out.println(res);
    }
}
