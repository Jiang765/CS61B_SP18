package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // Note that the local variable
        // here shadows the field we inherit from AbstractBoundedQueue, so
        // you'll need to use this.capacity to set the capacity.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        this.capacity = capacity;
        this.fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        } else {
            rb[last] = x;
            last = updateIndex(++last);
            this.fillCount++;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // Dequeue the first item. Don't forget to decrease fillCount and update first
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            T item = rb[first];
            rb[first] = null;
            first = updateIndex(++first);
            this.fillCount--;
            return item;
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // Return the first item. None of your instance variables should change.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            return rb[first];
        }
    }

    private int updateIndex(int index) {
        if (index >= this.capacity) {
            return this.capacity - index;
        } else if (index < 0) {
            return this.capacity + index;
        } else {
            return index;
        }
    }

    // When you get to part 5, implement the needed code to support iteration.
    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        int numRemain;
        int current;

        ArrayIterator() {
            numRemain = fillCount();
            current = first;
        }

        @Override
        public boolean hasNext() {
            return numRemain > 0;
        }

        @Override
        public T next() {
            T item = rb[current];
            numRemain--;
            current = updateIndex(++current);
            return item;
        }

    }
}
