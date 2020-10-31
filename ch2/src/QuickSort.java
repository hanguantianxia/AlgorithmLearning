import java.util.Random;
import utils.StdRandom;
public class QuickSort {
    public static <T extends Comparable> void sort(T[] data){
        StdRandom.shuffle(data);
        sort(data, 0, data.length-1);
    }
    public static  <T extends Comparable> void sort(T[] data, int low, int high){
        int j = partition(data, low, high);
        sort(data, low, j-1);
        sort(data, j+1, high);
    }

    public static <T extends Comparable> int partition(T[] data, int low, int high){

        int lt = low;
        int i = low + 1;
        int gt = high;
        T v =  data[low];

        while(i <= gt){
            int cmp = data[i].compareTo(v);
            if(cmp>0){
                arrayExec(data, i, gt--);
            }
            else if(cmp<0){
                arrayExec(data,i++,lt++);
            }
            else{
                i++;
            }
        }
        return gt;
    }

    public static <T extends Comparable> boolean less(T a, T b){
        return a.compareTo(b) < 0;
    }

    public static <T extends Comparable> void arrayExec(T[] data, int i, int j){
        T  tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
}
