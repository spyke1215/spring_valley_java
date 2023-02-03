public class JavaStaticArray {
    public static void main(String args[]) {
        String[] strings;
        strings = new String[] {
            "First sting",
            "Second string",
            "Third string",
            "Fourth string",
        };

        strings[0] = "Replaced first string";

        String input = args[0];

        if(input.equals("normal"))
        {
            System.out.println("Normal:");
            for(int n=0; n < strings.length; n++) {
                System.out.println(strings[n]);
            }
        }
        else if (input.equals("reverse"))
        {
            System.out.println("Reversed:");
            for(int n=strings.length - 1; n >= 0; n--) {
                System.out.println(strings[n]);
            }
        }
        else
        {
            System.out.println("ERROR: Invalid input");
        }
    }
}
