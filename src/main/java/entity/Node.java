package entity;

public class Node<T> {
    public Node<T> next;
    public Node<T> prev;
    public T data = null;
    public Node(T data){
        this.data = data;
    }
}
