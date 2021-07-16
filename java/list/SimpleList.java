package list;

public interface SimpleList<E> {
    int size();

    boolean isEmpty();

    boolean contains(E e);

    boolean add(E e);

    E get(int index);

    E remove(int index);
}
