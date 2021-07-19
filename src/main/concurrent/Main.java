package concurrent;

public class Main {

    private final Runnable runnable1;
    private final Runnable runnable2;

    public Main() {
        runnable1 = () -> {
            int i = 0;
            while (i < 100){
                Counter counter = ThreadSafeCounter.instance();
                System.out.println(String.format("(%d %d) increment: %d -> %d", counter.ops(), i++, counter.value(), counter.increment()));
            }
        };
        runnable2 = () -> {
            int i = 0;
            while (i < 100){
                Counter counter = ThreadSafeCounter.instance();
                System.out.println(String.format("(%d %d) decrement: %d -> %d", counter.ops(), i++, counter.value(), counter.decrement()));
            }
        };
    }

    private void run(){
        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);
        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}
