import java.util.ArrayList;

public class JavaDynamicArray {
    public static void main(String[] args) {
        ArrayList<String> dynamicArray = new ArrayList<String>();
        dynamicArray.add("First string");
        dynamicArray.add("Second string");
        dynamicArray.add("Third string");
        dynamicArray.add("Fourth string");
        for(int n=0; n < dynamicArray.size(); n++) {
            System.out.println(dynamicArray.get(n));
        }
    }
}
