import java.util.*;

public class MemorableQuoteDatabase 
{
    ArrayList<MemorableQuote> quotes = new ArrayList<>();

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
}

