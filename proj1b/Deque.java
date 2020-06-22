/**
The methods inside interface must be public,
therefore the keyword public can be omitted.
*/
public interface Deque<T> {
    public int size();
    public boolean isEmpty();
    public void addFirst(T i);
    public void addLast(T i);
    public T removeFirst();
    public T removeLast();
    public T get(int index);
    public void printDeque();
}
