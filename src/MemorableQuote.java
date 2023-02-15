public class MemorableQuote 
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
