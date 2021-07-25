package pingpong;

import java.util.function.Consumer;

public class PingPongGame {

    private final Runnable ping;
    private final Runnable pong;

    //Init with PingPong.PONG to start printing with 'ping'
    private PingPong lock = PingPong.PONG;

    public PingPongGame() {
        Consumer<PingPong> consumer = this::printName;

        ping = () -> consumer.accept(PingPong.PING);
        pong = () -> consumer.accept(PingPong.PONG);

    }

    private void printName(PingPong name){
        while (true) {
            try {
                synchronized (this) {
                    while (lock == name) {
                        wait();
                    }
                    System.out.println(name.name().toLowerCase());
                    lock = name;
                    notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run(){
        Thread t1 = new Thread(ping);
        Thread t2 = new Thread(pong);
        t1.start();
        t2.start();
    }

    private enum PingPong {
        PING, PONG
    }
}
