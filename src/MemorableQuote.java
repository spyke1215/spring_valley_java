import java.util.ArrayList;

public class MemorableQuote 
{
    String quoteText;
    String quoteReference;

    ArrayList<Integer> counted = new ArrayList<Integer>();

    public void getCount(int index)
    {
        counted.get(index);
    }
    
    public String getQuote()
    {
        return quoteText;
    }

    public String getReference()
    {
        return quoteReference;
    }

    public MemorableQuote(String quote, String reference)
    {
        this.quoteText = quote;
        this.quoteReference = reference;
    }

    public void printQuote()
    {
        if(quoteReference == "")
        {
            System.out.println(quoteText);
        }
        else
        {
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
