import java.util.*;
public class SimpleProductDatabase {
    public static void main(String[] args) {
           
        ArrayList<String> dynamicArray = new ArrayList<String>();
        dynamicArray.add("Mitshubishi Adventure:800000");
        dynamicArray.add("Lamborghini Aventador:100000000");
        dynamicArray.add("Mustang Mach E:500000");
        dynamicArray.add("Tesla Model X:750000");

        for(int n=0; n < dynamicArray.size(); n++) {
            String x = dynamicArray.get(n);
            String[] product = x.split(":",2);

            System.out.print("Product: "+product[0]);
            System.out.print(", ");
            System.out.println("Price: "+product[1]);

        }
    }
}
