import java.util.Random;
public class MergeBottomUp {
    
    public static void sort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];

        for (int i = 1; i < a.length / 2; i++) {
            for (int j = 0; j < a.length - i; j+=i + i) {
                merge(a, j, j+i-1, Math.min(j+2*i-1, a.length-1), aux);
            }
        }
    }


    public static void merge(Comparable[] a, int low, int mid, int high,Comparable[] aux){
        int i = low;
        int j = mid+1;

        for( int k = low; k <= high; k++){
            aux[k] = a[k];
        }

        for (int k = low; k <= high; k++) {
            if(i>mid){
                a[k] = aux[j++];
            }
            else if(j>high){
                a[k] = aux[i++];
            }
            else if(less(aux[i], aux[j])){
                a[k] = aux[i++];
            }
            else{
                a[k] = aux[j++];
            }

        }

    }
    
    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {
        Random rand = new Random(System.currentTimeMillis());
        Integer[] data = new Integer[20];

        for(int i=0;i < data.length;i++){
            data[i] = rand.nextInt(40);
        }
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
        MergeTopDown.sort(data);
        System.out.print("\n");

        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }

    }
}