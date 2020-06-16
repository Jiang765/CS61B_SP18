/**
Your operations are subject to the following rules:
1. add and remove must take constant time, except during resizing operations.
2. get and size must take constant time.
3. The starting size of your array should be 8.
4. The amount of memory that your program uses at any given time
must be proportional to the number of items.
For example, if you add 10,000 items to the deque, and then remove 9,999 items,
you shouldn’t still be using an array of length 10,000ish.
For arrays of length 16 or more, your usage factor should always be at least 25%.
For smaller arrays, your usage factor can be arbitrarily low.

public ArrayDeque() : Creates an empty array deque.
*/

public class ArrayDeque<T> {
    private T[] items;
    private int arraySize;

    /* Creates an empty array deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        arraySize = 0;
    }

    public boolean isEmpty() {
        return arraySize == 0;
    }

    public boolean isFull() {
        return arraySize == items.length;
    }

    public void resize(int cap) {
        T[] newItems =  (T[]) new Object[cap];
        System.arraycopy(items, 0, newItems, 0, arraySize);
        items = newItems;
    }

    public void addFirst(T i) {
        if (arraySize >= 0) {
            System.arraycopy(items, 0, items, 1, arraySize);
        }
        items[0] = i;
        arraySize++;
        if (arraySize / items.length < 0.25 || arraySize / items.length > 0.5) {
            resize(arraySize * 2);
        }
    }

    public void addLast(T i) {
        items[arraySize] = i;
        arraySize++;
        if (arraySize / items.length < 0.25 || arraySize / items.length > 0.5) {
            resize(arraySize * 2);
        }
    }

    public T removeFirst() {
        if (!isEmpty()) {
            T delItem = items[0];
            if (arraySize - 1 >= 0) {
                System.arraycopy(items, 1, items, 0, arraySize - 1);
            }
            items[arraySize - 1] = null;
            arraySize--;
            if (arraySize / items.length < 0.25 || arraySize / items.length > 0.5) {
                resize(arraySize * 2);
            }
            return delItem;
        } else {
            return null;
        }
    }

    public T removeLast() {
        if (!isEmpty()) {
            T delItem = items[arraySize];
            items[arraySize] = null;
            arraySize--;
            if (arraySize / items.length < 0.25 || arraySize / items.length > 0.5) {
                resize(arraySize * 2);
            }
            return delItem;
        } else {
            return null;
        }
    }

    public T get(int index) {
        if (index < 0 || index >= arraySize) {
            // throw new IllegalArgumentException("Illegal Index!");
            return null;
        } else {
            return items[index];
        }
    }

    public int size() {
        return arraySize;
    }
}


