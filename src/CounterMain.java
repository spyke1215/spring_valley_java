public class CounterMain {
    public static void main(String[] args) {
        Counter counter1 = new Counter("First counter");
        Counter counter2 = new Counter("Second counter");
        counter1.increment();
        counter1.increment();
        counter1.increment();
        counter1.report();
        counter2.report();
    }
}
