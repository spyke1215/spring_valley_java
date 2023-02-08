public class MemorableQuote 
{
    String quoteText;
    String quoteReference;
    String quoteCategory;

    int count;

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

    public MemorableQuote(String quote, String reference, String category)
    {
        this.quoteText = quote;
        this.quoteReference = reference;
        this.quoteCategory = category;
    }

    public void printQuote()
    {
        count++;
        if(quoteReference == "")
        {
            System.out.print("("+count+") ");
            System.out.println(quoteText);
        }
        else
        {
            System.out.print("("+count+") ");
            System.out.println(quoteText);
            System.out.println("--" + quoteReference);
        }
    }

    public boolean matches(String text)
    {   
        if (quoteText.toLowerCase().contains(text.toLowerCase()) || quoteReference.toLowerCase().contains(text.toLowerCase()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
