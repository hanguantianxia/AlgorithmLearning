import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestArrayList {
    public static void main(String[] args) {
        List<Integer> data = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        for(int i =0 ; i < 10 ; i++){
            data.add(random.nextInt(50));
        }
        System.out.println(data);
        data.add(0, 3);
        System.out.println(data);
    }
}
