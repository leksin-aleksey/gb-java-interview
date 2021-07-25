package concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class ThreadSafeCounter extends Counter{
    private volatile int counterValue;
    private volatile int operationCounter;

    private final Lock writeLock;
    private final Lock readLock;

    public ThreadSafeCounter() {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        writeLock = lock.writeLock();
        readLock = lock.readLock();
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
