import java.util.*;
public class Breakfast {
    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);

        arr.add("Lenovo: Hello");
        arr.add("Lenovo: Hello");
        arr.add("Dell: Test");
        arr.add("Acer: World");
        arr.add("Asus: Description");
        arr.add("MSI: Gaming");
        
        
        System.out.print("Enter the number: ");
        int input = scan.nextInt();
        scan.nextLine();
            
        switch(input) {
            case 1: //DISPLAY
                System.out.println("--Display--");
                for (String i : arr) {
                    System.out.println(i);
                } 
                break;

            case 2: //SORT ASC
                System.out.println("--SORT ASC--");
                Collections.sort(arr);
                for (String i : arr) {
                    System.out.println(i);
                }
                break;

            case 3: //SORT DESC
                System.out.println("--SORT DESC--");
                Collections.sort(arr, Collections.reverseOrder());
                for (String i : arr) {
                    System.out.println(i);
                }
                break;

            case 4: //COUNT
                System.out.println("--COUNT--");
                System.out.println(arr.size());
                break;

            case 5: //KEYSEARCH
                System.out.println("--KEYSEARCH--");
                System.out.print("Enter Search(Title): ");

                String title = scan.nextLine();
                
                int total = 0;

                for (String i: arr)
                {
                    if(i.contains(title))
                    {
                        System.out.println(i);
                        total++;
                    }
                }
                System.out.print("Total matches found: " + total);
                break;
            case 6:
                System.out.println("--DSEARCH--");
                System.out.print("Enter Search(Description): ");

                String desc = scan.nextLine();

                int dtotal = 0;

                for (String i: arr)
                {
                    if(i.contains(desc))
                    {
                        System.out.println(i);
                        dtotal++;
                    }
                }
                System.out.print("Total matches found: " + dtotal);
                break;
            default:
              // code block
          }
          scan.close();
    }
}
