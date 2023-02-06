public class Counter {
    String name;
    private int value = 0;

    public Counter(String name) 
    {
        this.name = name;
        System.out.println("Counter '"+ name + "' created");
    }

    public void increment() 
    {
        value++;
    }

    public int getValue()
    {
        return value;
    }

    public void report()
    {
        System.out.println("Value of "+ name + ": "+ value);
    }
}

