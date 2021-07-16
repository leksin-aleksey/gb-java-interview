package list;

public class LinkedList<E> implements SimpleList<E>{

    private int size = 0;

    private Node<E> firstElem;
    private Node<E> lastElem;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E e) {
        Node<E> temp = firstElem;
        while (temp != null){
            if (temp.elem.equals(e)){
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public boolean add(E e) {
        Node<E> newLast = new Node<>(e);
        newLast.next = null;
        newLast.prev = lastElem;
        if (size++ == 0){
            firstElem = newLast;
        } else {
            lastElem.next = newLast;
        }
        lastElem = newLast;
        return true;
    }

    @Override
    public E get(int index) {
        checkRange(index);

        Node<E> temp = firstElem;
        for (int i = 0; i < index; i++){
            temp = temp.next;
        }
        return temp.elem;
    }

    @Override
    public E remove(int index) {
        checkRange(index);

        E result = null;

        int i = 0;

        Node<E> temp = firstElem;
        while (temp != null){
            if (index == 0){
                firstElem = temp.next;
            }
            if (index == size - 1){
                lastElem = temp.prev;
            }
            if (index == i){
                Node<E> tempPrev = temp.prev;
                Node<E> tempNext = temp.next;
                if (tempPrev != null){
                    tempPrev.next = tempNext;
                }
                if (tempNext != null){
                    tempNext.prev = tempPrev;
                }
                result = temp.elem;
                temp = null;
            } else {
                temp = temp.next;
                i++;
            }
        }
        size--;
        return result;
    }

    private void checkRange(int index){
        if (index < 0 || index >= size){
            throw new RuntimeException("Out of bounds exception");
        }
    }

    static class Node<E>{
        private E elem;
        private Node next;
        private Node prev;

        public Node(E elem) {
            this.elem = elem;
        }
    }
}
