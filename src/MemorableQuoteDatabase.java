import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MemorableQuoteDatabase 
{
    ArrayList<MemorableQuote> quotes = new ArrayList<>();
    ArrayList<MemorableQuote> temp = new ArrayList<>();
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
        try 
        {
            FileOutputStream fos = new FileOutputStream(filename);
            PrintStream ps = new PrintStream(fos);

            if(temp.size() > 0)
            {
                quotes = temp;
            }

            for (MemorableQuote quote : quotes) 
            {
                String text = quote.getQuote() + "@" + quote.getReference() + "@" + quote.getCategory() + "@" + quote.getCount();
                ps.println(text);
            }
            ps.close();
            fos.close();
        
            return true;
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("File not found: " + filename);
            return false;
        } 
        catch (IOException e) 
        {
            System.out.println("Error writing to file: " + filename);
            return false;
        }
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
        this.temp = this.quotes;
        this.quotes = quotes;
    }

    // public MemorableQuoteDatabase(ArrayList<MemorableQuote> list)
    // {
    //     this.quotes = list;
    // }
}