package linklist;

public class LinkNode<T> {
    public  LinkNode<T> next;
    public T val;

    public LinkNode(T val){
        this.val = val;
        this.next = null;
    }

    public LinkNode(){
        this.next = null;
    }
    public LinkNode(T val, LinkNode<T> next){
        this.val = val;
        this.next = next;
    }

    public T getVal(){
        return val;
    }
    
    public LinkNode<T> getNext() {
        return next;
    }

    @Override
    public String toString() {
        return "LinkNode{" +
                "next=" + next +
                ", val=" + val +
                '}';
    }
}

