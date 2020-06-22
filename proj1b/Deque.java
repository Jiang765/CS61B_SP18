/**
The methods inside interface must be public,
therefore the keyword public can be omitted.
*/
public interface Deque<T> {
    int size();
    boolean isEmpty();
    void addFirst(T i);
    void addLast(T i);
    T removeFirst();
    T removeLast();
    T get(int index);
    void printDeque();
}
