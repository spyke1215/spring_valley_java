import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class MemorableQuoteDatabase 
{
    ArrayList<MemorableQuote> quotes = new ArrayList<>();
    String category;

    public boolean readFromFile(String filename) throws FileNotFoundException
    {
        File myQuotes = new File(filename);
        Scanner myReader = new Scanner(myQuotes);
        while (myReader.hasNextLine()) 
        {
            String data = myReader.nextLine();

            String[] splitQuotes = data.split("@",4);

            quotes.add(new MemorableQuote(splitQuotes[0],splitQuotes[1],splitQuotes[2],Integer.parseInt(splitQuotes[3])));
        }
        myReader.close();

        return true;
    }

    public boolean writeToFile(String filename)
    {
        return true;
    }

    public boolean add(String filename, int position, String text)
    {
        try (FileReader fr = new FileReader(filename)) {
            int symbol;
            int count = 0;
            int i;
            String s;
            String lines[] = null;
            
            count = quotes.size();

            if (count<=0) 
            {
                return false;
            }

            lines = new String[count];

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

            } while (fr.ready());

        if ((position<0) || (position>=count)) 
        {
            return false;
        }

        lines[position-1] = text;

        FileOutputStream fs = new FileOutputStream(filename); // create a file stream
        try (PrintStream ps = new PrintStream(fs)) {
            // 3. The loop of writing the lines[] array to the file
            for (int x=0; x<lines.length; x++)
            {
                ps.println(lines[x]);
            }
        }
    
        
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return true;
    }

    public MemorableQuoteDatabase(ArrayList<MemorableQuote> list)
    {
        this.quotes = list;
    }
    
    public ArrayList<MemorableQuote> searchQuotes(String text)
    {
        ArrayList<MemorableQuote> matches = new ArrayList<>();
        
        for (MemorableQuote quote : quotes)
        {
            if(quote.matches(text))
            {
                matches.add(quote);
            } 
        }
        
        return matches;
    }

    public MemorableQuote getRandomQuote()
    {
        Random ran = new Random();
        int result = ran.nextInt(quotes.size());

        return quotes.get(result);
    }

    public void addQuote(MemorableQuote quote)
    {
        quotes.add(quote);
    }

    public void removeQuote(MemorableQuote quote)
    {
        quotes.remove(quote);
    }

    public int getQuoteCount()
    {
        return quotes.size();
    }

    public MemorableQuote getQuoteByIndex(int n)
    {
        return quotes.get(n);
    }

    public ArrayList<MemorableQuote> getAllQuotes()
    { 
        return quotes;
    }

    public void setAllQuotes(ArrayList<MemorableQuote> quotes)
    {
        this.quotes = quotes;
    }
}

