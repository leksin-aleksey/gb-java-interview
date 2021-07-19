package concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class ThreadSafeCounter extends Counter{
    private int counterValue;
    private int operationCounter;

    private static final ReadWriteLock lock;
    private static final Lock writeLock;
    private static final Lock readLock;

    static {
        lock = new ReentrantReadWriteLock();
        writeLock = lock.writeLock();
        readLock = lock.readLock();
    }

    private static ThreadSafeCounter counter;

    private ThreadSafeCounter() {}

    public static Counter instance() {
        try {
            writeLock.lock();
            if (counter == null) {
                counter = new ThreadSafeCounter();
            }
            return counter;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public int increment() {
        try {
            writeLock.lock();
            operationCounter++;
            return ++counterValue;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public int decrement() {
        try {
            writeLock.lock();
            operationCounter++;
            return --counterValue;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public int value() {
        try {
            readLock.lock();
            return counterValue;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public int ops() {
        try {
            readLock.lock();
            return operationCounter;
        } finally {
            readLock.unlock();
        }
    }
}
