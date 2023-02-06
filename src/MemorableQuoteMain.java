import java.util.*;

public class MemorableQuoteMain {
    public static void main(String[] args) {

        ArrayList<MemorableQuote> quotes = new ArrayList<>();   

        quotes.add(new MemorableQuote("The greatest glory in living lies not in never falling, but in rising every time we fall@Nelson Mandela@1@8"));
        quotes.add(new MemorableQuote("The way to get started is to quit talking and begin doing@Walt Disney@1@2"));
        quotes.add(new MemorableQuote("Your time is limited, so don't waste it living someone else's life. Don't be trapped by dogma which is living with the results of other people's thinking@Steve Jobs@2@1"));

        MemorableQuoteDatabase random = new MemorableQuoteDatabase(quotes);

        System.out.println(random.getRandomQuote().getQuote());
    }
}
