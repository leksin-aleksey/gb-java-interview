package concurrent;

public class Main {

    private final Runnable runnable1;
    private final Runnable runnable2;

    public Main() {
        Counter counter = new ThreadSafeCounter();

        runnable1 = () -> {
            for (int i = 0; i < 100; i++){
                System.out.printf("(%d %d) increment: %d -> %d%n", counter.ops(), i++, counter.value(), counter.increment());
            }
        };
        runnable2 = () -> {
            for (int i = 0; i < 100; i++){
                System.out.printf("(%d %d) decrement: %d -> %d%n", counter.ops(), i++, counter.value(), counter.decrement());
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
