import java.util.*;

public class MemorableQuoteDatabase {

    ArrayList<MemorableQuote> quotes = new ArrayList<>();

    public MemorableQuoteDatabase(ArrayList<MemorableQuote> list)
    {
        this.quotes = list;
    }

    public MemorableQuote getRandomQuote()
    {
        Random ran = new Random();
        int result = ran.nextInt(quotes.size());

        return quotes.get(result);
    }

    public void addQuote(MemorableQuote quote)
    {
        
    }

    public void removeQuote(MemorableQuote quote)
    {

    }

    // public int getQuoteCount()
    // {
        // return 1;
    // }

    // public MemorableQuote getQuoteByIndex(int n)
    // {
        // return ;
    // }

    // public ArrayList<MemorableQuote> getAllQuotes()
    // {
        // return;
    // }

}

