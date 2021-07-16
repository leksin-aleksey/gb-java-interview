package list;


import java.util.Arrays;

public class ArrayList<E> implements SimpleList<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private int capacity;
    private int size = 0;

    private Object[] array;

    public ArrayList() {
        capacity = DEFAULT_CAPACITY;
        array = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 0){
            throw new RuntimeException("Out of bounds exception");
        }
        this.capacity = capacity;
        array = new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E e) {
        if (size == capacity){
            array = Arrays.copyOf(array, capacity = newCapacity(capacity));
        }
        array[size++] = e;
        return true;
    }

    @Override
    public boolean contains(E e) {
        for (Object elem : array){
            if (elem.equals(e)){
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index > size || index < 0){
            throw new RuntimeException("Out of bounds exception");
        }
        return (E) array[index];
    }

    @Override
    public E remove(int index) {
        /* TODO replace with System.arraycopy */
        if (index >= size || index < 0){
            throw new RuntimeException("Out of bounds exception");
        }

        E oldValue = (E) array[index];
        for (int i = 0; i < size - index - 1; i++){
            array[index + i] = array[index + i + 1];
        }

        array[--size] = null;

        return oldValue;
    }

    private int newCapacity(int capacity){
        return capacity * 2;
    }
}
