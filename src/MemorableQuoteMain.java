import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;


public class MemorableQuoteMain 
{
    public static void main(String[] args) throws FileNotFoundException 
    {
        ArrayList<MemorableQuote> quotes = new ArrayList<>(); 
        MemorableQuoteDatabase database = new MemorableQuoteDatabase(quotes);
        MemorableQuoteDisplayShow display = new MemorableQuoteDisplayShow(database);

        String filename = "quotes.txt";
        
        database.readFromFile(filename);
        
        if(args[args.length-1].contains("category="))
        {   
            String[] splitCategory = args[args.length-1].split("=",2);
            ArrayList<MemorableQuote> search = database.searchQuotes(splitCategory[1]);

            database.setAllQuotes(search);
        }
        if(args[0].contains("all"))
        {
            for (MemorableQuote quote : database.getAllQuotes()) 
            {
                quote.setCount(quote.getCount() + 1);
                quote.printQuote();
            }
        }
        else if(args[0].contains("random"))
        {
            MemorableQuote quote = database.getRandomQuote();
            quote.setCount(quote.getCount() + 1);
            quote.printQuote();
            
        }
        else if(args[0].contains("search"))
        {
            ArrayList<MemorableQuote> search = database.searchQuotes(args[1]);

            for(MemorableQuote quote : search) 
            {
                quote.setCount(quote.getCount() + 1);
                quote.printQuote();
            }
        }
        else if(args[0].contains("display"))
        {
            if(args.length == 3)
            {
                if(args[1].contains("delay") && args[2].contains("max"))
                {
                    String[] splitDelay = args[1].split("=",2);
                    String[] splitMax = args[2].split("=",2);
                    display.setDelay(Integer.parseInt(splitDelay[1]));
                    display.setMax(Integer.parseInt(splitMax[1]));
                    display.execute();
                }
            }
            else
            {
                display.execute();
            }
        }
        else if(args[0].contains("add"))
        {   
            String[] splitQuotes =  args[args.length+1].split("@",4);

            quotes.add(new MemorableQuote(splitQuotes[0],splitQuotes[1],splitQuotes[2],Integer.parseInt(splitQuotes[3])));
        }
        else if(args[0].contains("delete"))
        {

        }
        else if(args[0].contains("modify"))
        {

        }
        else
        {
            System.out.println("Not Found");
        }

        database.writeToFile(filename);
    }
}

class MemorableQuoteDatabase 
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
        try 
        {
            FileOutputStream fos = new FileOutputStream(filename);
            PrintStream ps = new PrintStream(fos);

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

class MemorableQuoteDisplayShow 
{
    int delayBetweenQuotes = 3000;
    int maxQuotesToDisplay = 0;

    MemorableQuoteDatabase database;

    public MemorableQuoteDisplayShow(MemorableQuoteDatabase quotes)
    {
       this.database = quotes;
    }

    public void setDelay(int delay)
    {
        this.delayBetweenQuotes = delay;
    }

    public void setMax(int max)
    {
        this.maxQuotesToDisplay = max;
    }

    public int getDelay(int delay)
    {
        return delayBetweenQuotes;
    }

    public int getMax(int max)
    {
        return maxQuotesToDisplay;
    }

    public void execute()
    {
        this.maxQuotesToDisplay *= -1;

        while(this.maxQuotesToDisplay <= 0)
        {
            
            this.maxQuotesToDisplay++;

            MemorableQuote quote = database.getRandomQuote();

            quote.setCount(quote.getCount() + 1);
            quote.printQuote();

            try 
            {
                Thread.sleep(delayBetweenQuotes);
            } 
            catch(InterruptedException e)
            {
                // this part is executed when an exception (in this example InterruptedException) occurs
            } 
        } 
    }
}

class MemorableQuote 
{
    String quoteText;
    String quoteReference;
    String quoteCategory;
    int quoteCount;

    public Integer getCount()
    {
        return quoteCount;
    }

    public String getCategory()
    {
        return quoteCategory;
    }
    
    public String getQuote()
    {
        return quoteText;
    }

    public String getReference()
    {
        return quoteReference;
    }

    public void setCount(Integer count)
    {
        this.quoteCount = count;
    }

    public void setCategory(String category)
    {
        this.quoteCategory = category;
    }
    
    public void setQuote(String text)
    {
        this.quoteText = text;
    }

    public void setReference(String reference)
    {
        this.quoteReference = reference;
    }

    public MemorableQuote(String quote, String reference, String category, Integer count)
    {
        this.quoteText = quote;
        this.quoteReference = reference;
        this.quoteCategory = category;
        this.quoteCount = count;
    }

    public void printQuote()
    {
        if(quoteReference == "")
        {
            System.out.print("("+ quoteCount +") ");
            System.out.println(quoteText);
        }
        else
        {
            System.out.print("("+ quoteCount +") ");
            System.out.println(quoteText);
            System.out.println("--" + quoteReference);
        }
    }

    public boolean matches(String text)
    {   
        if (quoteText.toLowerCase().contains(text.toLowerCase()) || quoteReference.toLowerCase().contains(text.toLowerCase()) || quoteCategory.toLowerCase().contains(text.toLowerCase()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
