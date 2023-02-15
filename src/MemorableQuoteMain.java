import java.io.FileNotFoundException;
import java.util.ArrayList;


public class MemorableQuoteMain 
{
    public static void main(String[] args) throws FileNotFoundException 
    {
        MemorableQuoteDatabase database = new MemorableQuoteDatabase();
        MemorableQuoteDisplayShow display = new MemorableQuoteDisplayShow(database);

        String filename = "quotes.txt";
        
        database.readFromFile(filename);
        
        if(args[args.length-1].contains("category=")) //java MemorableQuoteMain.java *command* category=* 
        {   
            String[] splitCategory = args[args.length-1].split("=",2);
            ArrayList<MemorableQuote> search = database.searchQuotes(splitCategory[1]);

            database.setAllQuotes(search);
        }
        if(args[0].contains("all")) //java MemorableQuoteMain.java all
        {
            for (MemorableQuote quote : database.getAllQuotes()) 
            {
                quote.setCount(quote.getCount() + 1);
                quote.printQuote();
            }
        }
        else if(args[0].contains("random")) //java MemorableQuoteMain.java random
        {
            MemorableQuote quote = database.getRandomQuote();
            quote.setCount(quote.getCount() + 1);
            quote.printQuote();
            
        }
        else if(args[0].contains("reset")) //java MemorableQuoteMain.java reset
        {
            ArrayList<MemorableQuote> reset = database.searchQuotes(args[1]);

            for(MemorableQuote quote : reset) 
            {
                quote.setCount(quote.getCount() + 1);
                quote.setCount(0);
            }
        }
        else if(args[0].contains("search")) //java MemorableQuoteMain.java search *keyword*
        {
            ArrayList<MemorableQuote> search = database.searchQuotes(args[1]);

            for(MemorableQuote quote : search) 
            {
                quote.setCount(quote.getCount() + 1);
                quote.printQuote();
            }
        }
        else if(args[0].contains("display")) //java MemorableQuoteMain.java display delay=* max=*
        {
            if(args.length == 3 || args.length == 4)
            {
                if((args[1].contains("delay") && args[2].contains("max")) || args[3].contains("category"))
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
        else if(args[0].equals("add")) //java MemorableQuoteMain.java add *quote* @ *reference* @ *category*
        {
            StringBuffer sb = new StringBuffer();
            for(int i = 1; i < args.length; i++)
            { 
                if(i < args.length)
                {
                    sb.append(args[i]+ " ");
                }
                else
                {
                    sb.append(args[i]);
                }
            }

            String text = sb.toString();
            String[] parts = text.split("@");
            String quoteText = parts[0];
            String quoteRefernece = parts[1];
            String quoteCategory = parts[2];
            
            new MemorableQuote(quoteText, quoteRefernece, quoteCategory, 0);
        }
        else if(args[0].contains("delete")) //java MemorableQuoteMain.java delete *index*
        {
            MemorableQuote quote = database.getQuoteByIndex(Integer.parseInt(args[1]));
            database.removeQuote(quote);
        }
        else if(args[0].contains("modify")) //java MemorableQuoteMain.java modify *index* *quote* @ *reference* @ *category*
        {
            StringBuffer sb = new StringBuffer();
            for(int i = 2; i < args.length; i++)
            { 
                if(i < args.length)
                {
                    sb.append(args[i]+ " ");
                }
                else
                {
                    sb.append(args[i]);
                }
            }

            String text = sb.toString();
            String[] parts = text.split("@");
            String quoteText = parts[0];
            String quoteRefernece = parts[1];
            String quoteCategory = parts[2];
            
            MemorableQuote quote = database.getQuoteByIndex(Integer.parseInt(args[1]));
            quote.setQuote(quoteText);
            quote.setReference(quoteRefernece);
            quote.setCategory(quoteCategory);
            quote.setCount(0);
        }
        else
        {
            System.out.println("ERROR: Invalid argument/s");
        }

        database.writeToFile(filename);
    }
}