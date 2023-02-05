public class Concatenation {
    public static void main(String[] args){
        String str = "";
        for(int i = 0; i <= 100000; i++)
        {
            str += "x";
        }
        System.out.println(str);
    }
}
