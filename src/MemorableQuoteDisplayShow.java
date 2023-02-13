public class MemorableQuoteDisplayShow 
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

            database.getRandomQuote().printQuote();

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
