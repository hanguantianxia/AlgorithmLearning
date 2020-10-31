import java.util.Random;

import linklist.LinkNode;

public class LinkedSort {
    public static <T extends Comparable> LinkNode<T> sort(LinkNode<T> head){
        if(head==null||head.next==null){
            return head;
        }
        LinkNode<T> mid = getMid(head);
        LinkNode<T> leftHead = mid.next;
        mid.next = null;
        LinkNode<T> newLeftHead = sort(head);
        LinkNode<T> newRightHead = sort(leftHead);
        return merge(newLeftHead, newRightHead);
    }




    private static <T extends Comparable> LinkNode getMid(LinkNode<T> start){
        /**
         * Mid寻找 部分需要通过快慢指针找到后面所以
         */

        LinkNode<T> fast = start;
        LinkNode<T> slow = start;
        while (fast!=null && fast.next!=null && fast.next.next !=null)  {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;

    }
    public static  boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    public static  <T extends Comparable> LinkNode<T> merge(LinkNode<T> link1,  LinkNode<T> link2){
        LinkNode<T> newHead = new LinkNode<>();

        while(link1!=null && link2!=null){
            if(less(link1.val, link2.val)){
                newHead.next = link1;
                link1 = link1.next;
                newHead = newHead.next;
            }
            else{
                newHead.next = link2;
                link2 = link2.next;
                newHead = newHead.next;
            }
        }
        if(link1!=null){
            newHead.next = link1;
        }
        if (link2!=null) {
            newHead.next = link2;
        }

        return newHead.next;

    }

    public static void main(String[] args) {
        // initial the data
        Integer[] data = new Integer[5];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(200);
        }
        LinkNode<Integer> testData = initTestData(data);
       
        printLinkList(testData);
        testData = sort(testData);
        printLinkList(testData);

    }

    public static <T extends Comparable> LinkNode<T> initTestData(T[] data){
        LinkNode<T> newHead = new LinkNode<>();
        LinkNode<T> point = newHead;

        for(int i =0;i<data.length;i++){
            LinkNode<T> tmp = new LinkNode<>(data[i]);
            point.next = tmp;
            point = point.next;
        }
        return newHead.next;
    }

    public static <T> void printLinkList(LinkNode<T> linkList){
        while (linkList!=null) {
            System.out.print(linkList.val + "\t");
            linkList = linkList.next;
        }
        System.out.println();
    }
}
