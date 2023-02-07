import java.util.*;

public class MemorableQuoteMain 
{
    public static void main(String[] args) 
    {
        ArrayList<MemorableQuote> quotes = new ArrayList<>(); 
        MemorableQuoteDatabase database = new MemorableQuoteDatabase(quotes);
        MemorableQuoteDisplayShow display = new MemorableQuoteDisplayShow(database);
        
        database.addQuote(new MemorableQuote("The greatest glory in living lies not in never falling, but in rising every time we fall","Nelson Mandela"));
        database.addQuote(new MemorableQuote("The way to get started is to quit talking and begin doing","Walt Disney"));
        database.addQuote(new MemorableQuote("Your time is limited, so don't waste it living someone else's life. Don't be trapped by dogma which is living with the results of other people's thinking","Steve Jobs"));

        if(args[0].contains("all"))
        {
            for (MemorableQuote quote : database.getAllQuotes()) 
            {
                quote.printQuote();
            }
        }
        else if(args[0].contains("random"))
        {
            database.getRandomQuote().printQuote();
        }
        else if(args[0].contains("search"))
        {
            ArrayList<MemorableQuote> search = database.searchQuotes(args[1]);

            for(MemorableQuote quote : search) 
            {
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
        else
        {
            System.out.println("Test");
        }
    }
}
