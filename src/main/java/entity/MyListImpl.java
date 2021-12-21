package entity;
import java.lang.Iterable;
import java.util.Arrays;
import java.util.Iterator;

public class MyListImpl<T> implements IMyList<T>, Iterable<T>  {
    private Node<T> head, tail;
    private int size;

    public MyListImpl(){
        head = null;
        tail = null;
    }

    private boolean isEmpty(){
        return head == null;
    }

    @Override
    public void add(T e) {
        Node<T> temp = new Node<T>(e);
        if (isEmpty()) tail = temp;
        else head.prev = temp;
        temp.next = head;
        head = temp;
        size++;
    }

    private void removeByIndex(int index) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        Node<T> cursor = head;
        int i = 0;
        while(i!=(index - 1)) {
            i++;
            cursor = cursor.next;
        }
        if (cursor == head && cursor == tail){
            head = null;
            tail = null;
            cursor.data = null;
            size--;
        }
        if (cursor == head) {
            head = cursor.next;
            cursor.next = null;
            cursor.data = null;
            size--;
            return;
        }
        if (cursor == tail){
            tail = cursor.prev;
        } else {
            cursor.next.prev = cursor.prev;
            cursor.prev.next = cursor.next;
        }
        cursor.next = null;
        cursor.prev = null;
        cursor.data = null;
        size--;
    }


    @Override
    public void clear() {
        if (head == null){
            System.out.println("Error!!!");
            return;
        }
        for (int i = 0; i < size; i++) {
            removeByIndex(i + 1);
        }
        head = null;
        tail = null;
    }

    public T getByIndex(int index) {
        if (head == null) {
            System.out.println("Error!!!");
            return null;
        }
        Node<T> cursor = head;
        for (int i = 0; i < size; i++) {
            if (i + 1 == index) {
                return cursor.data;
            }
            cursor = cursor.next;
        }
        return null;
    }

    private void removeFromHead(){
        if (head.next == null) {
            tail = null;
        } else {
            head.next.prev = null;
        }
        head = head.next;
        size--;
    }

    private void removeFromTail(){
        if (head.next == null) {
            head = null;
        } else {
            tail.prev.next = null;
        }
        tail = tail.prev;
        size--;
    }

    @Override
    public boolean remove(T o) {
        Node<T> cursor = head;

        while (cursor.data != o){
            cursor = cursor.next;
            if (cursor == null) return false;
        }
        if (head == cursor) {
            removeFromHead();
        } else {
            cursor.prev.next = cursor.next;
        }

        if (tail == cursor) {
            removeFromTail();
        } else {
            cursor.next.prev = cursor.next;
        }
        return true;
    }

    @Override
    public Object[] toArray() {
        if (head == null) {
            System.out.println("Error!!!");
            return null;
        }
        Object[] array = new Object[size];
        Node<T> cursor = head;
        for (int i = 0; i < size; i++) {
            array[i] = cursor.data;
            cursor = cursor.next;
        }
        return array;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(T o) {
        if (head == null) {
            System.out.println("List is empty");
            return false;
        }
        Node<T> cursor = head;
        for (int i = 0; i < size; i++) {
            if (cursor.data.equals(o)) {
                return true;
            }
            cursor = cursor.next;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }



    private class IteratorImpl implements Iterator<T>{
        Node<T> cursor;
        int current;
        boolean calledNext = false;
        boolean removed = false;

        public IteratorImpl() {
            current = 0;
        }

        @Override
        public boolean hasNext() {
            if(head == null) return false;
            else return current < size;
        }

        @Override
        public T next() {
            if(hasNext() && current == 0){
                removed = false;
                calledNext = true;
                current++;
                cursor = head;
                return cursor.data;
            }
            if(hasNext()){
                removed = false;
                calledNext = true;
                cursor = cursor.next;
                current++;
                return cursor.data;
            }
            return null;
        }

        @Override
        public void remove() {
            if(current == 0 || removed || !calledNext){
                System.out.println("error!!!");
                throw new IllegalStateException();
            }
            if(current >= 1 && current <= size){
                removeByIndex(current);
                cursor = cursor.next;
                current--;
                removed = true;
            }
        }
    }
}
