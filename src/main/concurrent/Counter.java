package concurrent;

public abstract class Counter {
    /* Get counter value */
    public abstract int value();

    /* Total number of operations applied on counter */
    public abstract int ops();

    public abstract int increment();

    public abstract int decrement();

}
