package entity;
import java.util.Iterator;

public interface IMyList<T> {
    public void add(T e);
    public void clear();
    public boolean remove(T o);
    public Object[] toArray();
    public int size();
    public boolean contains(T o);
    public Iterator<T> iterator();
}
