import java.util.*;
import java.io.*;
public class MemorableQuotes
{
    static StringBuffer stringBufferOfData = new StringBuffer();
    static String filename = "quotes.txt";
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        if(args.length == 0)
        {
            System.out.println("Error: No arguments");
        }
        else
        {
            ArrayList<String> quotes = new ArrayList<String>();     
            ArrayList<String> tempQuotes = new ArrayList<String>(); 
        
            createQuotes(quotes);
            int argsLimit = args.length;
            String category = args[argsLimit-1];

            if(category.contains("category="))
            {
                int counter = 0;
                
                for(int i = 0; i < quotes.size(); i++)
                {
                    String[] argument = args[argsLimit-1].split("=", 2);
                    String[] categ = quotes.get(i).split("@", 3);

                    if(argument[1].equals(categ[2]))
                    {
                        tempQuotes.add(counter++, quotes.get(i));
                    }
                }
                quotes.clear();
                quotes = tempQuotes;
            }

            if(args[0].equals("random"))
            {
                printQuote(getRandomQuotes(quotes));
            }

            else if(args[0].equals("add"))
            {
                addQuotes(args);
            }
            
            else if(args[0].equals("delete"))
            {
                if (args[1].contains("position="))
                {
                    String[] split = args[1].split("=",2);
                    int position = Integer.parseInt(split[1]);
                    boolean res = modifyQuotes(position-1, "", filename);

                    if (res)
                        System.out.println("File deleted successfully");
                    else
                        System.out.println("File not deleted");
                }
                else
                {
                    System.out.println("Missing Argument (position=)");
                }
            }

            else if(args[0].equals("modify"))
            {
                if (args[1].contains("position=") && args[2].contains("text="))
                {
                    String[] splitPosition = args[1].split("=",2);
                    String[] splitText = args[2].split("=",2);
                    int position = Integer.parseInt(splitPosition[1]);
                    String text = "";

                    args[2] = splitText[1];

                    for(int i = 2; i < args.length; i++)
                    {
                        text += args[i]+" ";
                    }

                    boolean res = modifyQuotes(position, text, filename);
   
                    if (res)
                        System.out.println("File modified successfully");
                    else
                        System.out.println("File not modified");
                }
            }   

            else if(args[0].equals("all"))
            {
                for(int i = 0; i < quotes.size(); i++)
                {
                    printQuote(quotes[i]);
                }   
            }

            else if(args[0].equals("search"))
            {
                searchQuote(quotes, args[1]);
            }

            else if(args[0].equals("csearch"))
            {
                cSearchQuote(quotes, args[1]);
            }

            else if(args[0].equals("display"))
            {
                int total = args.length;
                if(total == 2)
                {
                    if(args[1].contains("delay="))
                    {
                        System.out.println("delay");
                        String[] split = args[1].split("=", 2);
                        int max = 100000;
                        int delay =  Integer.parseInt(split[1]);
                        
                        displayQuotes(quotes, delay, max);
                    }
                    else if(args[1].contains("max="))
                    {
                        String[] split = args[1].split("=", 2);
                        int max = Integer.parseInt(split[1]);
                        int delay = 3;
                        
                        displayQuotes(quotes, delay, max);
                    }
                    else
                    {
                        int delay = 3;
                        int max = 1000;
                        displayQuotes(quotes, delay, max);
                    }
                }
                else if (total >= 3)
                {
                    if(args[1].contains("delay=") && args[2].contains("max="))
                    {
                        String[] split1 = args[1].split("=", 2);
                        String[] split2 = args[2].split("=", 2);

                        int delay =  Integer.parseInt(split1[1]);
                        int max =  Integer.parseInt(split2[1]);
                        
                        displayQuotes(quotes, delay, max);
                    }
                    else if(args[1].contains("max=") && args[2].contains("delay="))
                    {
                        String[] split1 = args[1].split("=", 2);
                        String[] split2 = args[2].split("=", 2);

                        int delay =  Integer.parseInt(split2[1]);
                        int max =  Integer.parseInt(split1[1]);
                        
                        displayQuotes(quotes, delay, max);
                    }
                }
                else
                {
                    int delay = 3;
                    int max = 1000;
                    displayQuotes(quotes, delay, max);
                }
            }

            else
            {
                System.out.println("ERROR: Invalid input");
            }
        }
    }

    static void addQuotes(String[] args)
    {
        try
        {
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            fw.write("\n");
            for(int i = 1; i < args.length; i++)
            {  
                fw.write(args[i]+" ");//appends the string to the file
            }
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
        
    }

    public static int countQuotes(String filename) throws IOException {
        // 1. Delcare internal variables
        int count = 0; // the number of lines in the file - the result
        FileReader fr = null;
        int symbol;
      
        try {
            // 2. Trying to open a file for reading.
            fr = new FileReader(filename);
        
            // The cycle of reading characters from a file and counting their number
            do 
            {
                // Read character from file
                symbol = fr.read();
        
                // Check if there is a line break character
                if ((char)symbol == '\n')
                count++; // Increase the number of lines in the file by 1
            } while (fr.ready()); // Check for end of file
        }
        catch (IOException e)
        {
          // 3. If the file is not open, then display the appropriate message
          System.out.println("I/O error: " + e);
        }
        finally {
            // 4. Close file if it was open
            try 
            {
              if (fr!=null) 
              {
                fr.close();
              }
            }
            catch (IOException e) 
            {
              System.out.println("File close error.");
            }
        }
      
        // 5. Return the result
        return count;
      }

    public static String[] getLineQuotes(String filename) throws IOException 
    {
        // 1. Declare internal variables
        int count; // number lines in the file
        String lines[] = null; // array of lines - result
        FileReader fr = null;
        String s; // additional variable - line
        int symbol;
        int i;
        
        // 2. Get the number of lines in a file - invoke the CountLinesInFile() function
        count = countQuotes(filename);
        
        // 3. Checking if there are lines in the file
        if (count<=0) return null;
        
        // 4. Allocate memory for count lines
        lines = new String[count];
        
        // 5. Reading data from a file and creating an array of lines
        try 
        {
            // 5.1. Trying to open a file for reading.
            fr = new FileReader(filename);
        
            // 5.2. The cycle of reading characters from a file and creating an array of lines
            s = "";
            i = 0;
            do 
            {
                // Read character from file
                symbol = fr.read();
            
                // Check for end of line character
                if (((char)symbol == '\n')) 
                {
                    // delete from s character '\n'
                    s = s.substring(0, s.length()-1);
            
                    // Add string s to array of strings
                    lines[i] = s;
                    s = "";
                    i++; // Increase the number of lines in the file by 1
                }
                else 
                {
                    // add a character to a string
                    s = s + (char)symbol;
                }
            } while (fr.ready()); // Checking the end of the file

        }
        catch (IOException e) {
            // 5.3. If the file is not open, then display the appropriate message
            System.out.println("I/O error: " + e);
        }
        finally {
            // 5.4. Close file if it was open
            try 
            {
                if (fr!=null) 
                {
                    fr.close();
                }
            }
            catch (IOException e) 
            {
                System.out.println("File close error.");
            }
        }
        // 6. Return the result
        return lines;
    }

    public static boolean modifyQuotes(int position, String str, String filename) throws IOException 
    {
        // 1. Perform the necessary checks.
        // Is the position value correct?
        int count = countQuotes(filename); // number lines in the file
        if ((position<0) || (position>=count)) return false;

        // 2. Get a list of file lines
        String lines[] = getLineQuotes(filename);

        // 3. Replace string at position
        lines[position] = str;

        // 4. Write modified list of lines back to file
        writeLineQuotes(lines, filename);

        return true;
    }

    public static void writeLineQuotes(String lines[], String filename) throws IOException {

        // 1. Declare internal variables
        FileOutputStream fs = null;
        PrintStream ps = null;
      
        try {
          // 2. Create instances of classes FileOutputStream, PrintStream
          fs = new FileOutputStream(filename); // create a file stream
          ps = new PrintStream(fs); // associate file stream with PrintStream output stream
      
          // 3. The loop of writing the lines[] array to the file
          for (int i=0; i<lines.length; i++)
            ps.println(lines[i]);
        }
        catch (IOException e) {
          // If the error opening the file or other error
          System.out.println("I/O error: " + e);
        }
        finally {
          if (fs!=null) {
            try {
              fs.close();
            }
            catch (IOException e2) {
              System.out.println("Error closing " + filename);
            }
          }
      
          if (ps!=null) {
            ps.close();
          }
        }
      }

    static void displayQuotes(ArrayList<String> quotes, int delay, int max)
    {
        delay *= 1000;
        int counter = 1;
        while(counter <= max)
        {
            System.out.print("("+(counter++)+")");
            printQuote(getRandomQuotes(quotes));
            try 
            {
                Thread.sleep(delay);
            } 
            catch(InterruptedException e)
            {
                // this part is executed when an exception (in this example InterruptedException) occurs
            } 
        }  
    }

    static void printQuote(String quotes, int position)
    {

        String[] split = quotes.split("@", 4);
        System.out.println(split[0]);
        System.out.print("-- " + split[1]);
        System.out.print(" (count:"+split[3]+")");
        System.out.println();
        int count = Integer.parseInt(split[3]);
        count++;
        String combine = split[0]+"@"+split[1]+"@"+split[2]+"@"+count;

        boolean res = modifyQuotes(position, combine ,filename);
    }

    static void searchQuote(ArrayList<String> quotes, String args)
    {
        for(String i : quotes)
        {
            String[] split = i.split("@", 2);

            if(split[1].toLowerCase().contains(args.toLowerCase()))
            {
                printQuote(i);
            }
        }
    }

    static void cSearchQuote(ArrayList<String> quotes, String args)
    {
        for(String i : quotes)
        {
            String[] split = i.split("@",2);

            if(split[0].toLowerCase().contains(args.toLowerCase()))
            {
                printQuote(i);
            }
        }
    }

    static void createQuotes(ArrayList<String> quotes)
    {
        try 
        {
            File myQuotes = new File(filename);
            Scanner myReader = new Scanner(myQuotes);
            while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            quotes.add(data);
            }
            myReader.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    static String getRandomQuotes(ArrayList<String> quotes)
    {   
        Random ran = new Random();
        int result = ran.nextInt(quotes.size());
        return new Pair<ArrayList<String>, int>(quotes.get(result), result);
    }   
}