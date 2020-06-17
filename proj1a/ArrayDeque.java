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
    private int nextFirst; // The to-be-used index of box during addFirst()
    private int nextLast; // The to-be-used index of box during addLast()

    /* Creates an empty array deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        arraySize = 0;
        nextFirst = 0; // Start from arbitrary position
        nextLast = 1;
    }

    public boolean isEmpty() {
        return arraySize == 0;
    }

    private boolean isFull() {
        return arraySize == items.length;
    }

    public int size() {
        return arraySize;
    }

    /**
    Print the items in the deque from first to last, separated by a space.
    Once all the items have been printed, print out a new line.
    */
    public void printDeque() {
        for (int i = plusOne(nextFirst + 1); i != minusOne(nextLast); i = plusOne(i + 1)) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    /* Compute the index during addFirst() */
    private int minusOne(int index) {
        return (index + items.length) % items.length;
    }

    /* Compute the index during addLast()*/
    private int plusOne(int index) {
        return index % items.length;
    }

    /* Reorder the old array, put the first element in the index-0 box of the new array */
    private void resize(int cap) {
        T[] newItems =  (T[]) new Object[cap];
        // Compute the index of the first element in the old array
        int oldIndex = plusOne(nextFirst + 1);
        for (int i = 0; i < arraySize; i++) {
            newItems[i] = items[oldIndex];
            oldIndex += 1; // Move onto the next element in the old array
            oldIndex = plusOne(oldIndex);
        }
        items = newItems;
        nextFirst = cap - 1;
        nextLast = arraySize;
    }

    public void addFirst(T i) {
        items[nextFirst] = i;
        nextFirst = minusOne(nextFirst - 1);
        arraySize++;

        if (isFull()) {
            resize(2 * items.length);
        }
    }

    public void addLast(T i) {
        items[nextLast] = i;
        nextLast = plusOne(nextLast + 1);
        arraySize++;
        if (isFull()) {
            resize(2 * items.length);
        }
    }

    public T removeFirst() {
        if (!isEmpty()) {
            nextFirst = plusOne(nextFirst + 1);
            T delItem = items[nextFirst];
            items[nextFirst] = null;
            arraySize--;
            if (items.length >= 16 && arraySize < items.length / 4) {
                resize(items.length / 2);
            }
            return delItem;
        } else {
            return null;
        }
    }

    public T removeLast() {
        if (!isEmpty()) {
            nextLast = minusOne(nextLast - 1);
            T delItem = items[nextLast];
            items[nextLast] = null;
            arraySize--;
            if (items.length >= 16 && arraySize < items.length / 4) {
                resize(items.length / 2);
            }
            return delItem;
        } else {
            return null;
        }
    }

    /*
    Get the item at the given index, where 0 is the front,
    1 is the next item, and so forth. If no such item exists,
    returns null. Must not alter the deque.
    */
    public T get(int index) {
        if (index < 0 || index >= arraySize) {
            // throw new IllegalArgumentException("Illegal Index!");
            return null;
        } else {
            int start = plusOne(nextFirst + 1);
            return items[(start + index) % items.length];
        }
    }
}
