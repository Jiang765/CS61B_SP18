/**
Your operations are subject to the following rule
1. add and remove operations must not involve any looping or recursion.
A single such operation must take “constant time”,
i.e. execution time should not depend on the size of the deque
2. get must use iteration, not recursion.
3. size must take constant time.
4. The amount of memory that your program uses at any given time
must be proportional to the number of items.
For example, if you add 10,000 items to the deque, and then remove 9,999 items,
the resulting size should be more like a deque with 1 item than 10,000.
Do not maintain references to items that are no longer in the deque.

public LinkedListDeque() : Creates an empty linked list deque.
public T getRecursive(int index) : Same as get, but uses recursion.
*/

public class LinkedListDeque<T> {
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        public Node(T i, Node p, Node n) {
            this.item = i;
            this.prev = p;
            this.next = n;
        }

        public Node(T i) {
            this(i, null, null);
        }

        public Node() {
            this(null, null, null);
        }
    }

    private final Node dummyHead; // circular sentinel
    private int listSize;

    /* Creates an empty linked list deque. */
    public LinkedListDeque() {
        dummyHead = new Node();
        dummyHead.prev = dummyHead;
        dummyHead.next = dummyHead;
        listSize = 0;
    }

    /* Returns the number of items in the deque. */
    public int size() {
        return listSize;
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return listSize == 0;
    }

    /* Adds an item of type T to the front of the deque. */
    public void addFirst(T i) {
        dummyHead.next = new Node(i, dummyHead, dummyHead.next);
        dummyHead.next.next.prev = dummyHead.next;
        listSize++;
    }

    /* Adds an item of type T to the back of the deque. */
    public void addLast(T i) {
        dummyHead.prev = new Node(i, dummyHead.prev, dummyHead);
        dummyHead.prev.prev.next = dummyHead.prev;
        listSize++;
    }

    /**
    Removes and returns the item at the front of the deque.
    If no such item exists, returns null.
    */
    public T removeFirst() {
        if (!isEmpty()) {
            Node delNode = dummyHead.next;
            dummyHead.next = dummyHead.next.next;
            dummyHead.next.prev = dummyHead;
            delNode.next = null;
            delNode.prev = null;
            listSize--;
            return delNode.item;
        } else {
            return null;
        }
    }

    /**
    Removes and returns the item at the back of the deque.
    If no such item exists, returns null.
    */
    public T removeLast() {
        if (!isEmpty()) {
            Node delNode = dummyHead.prev;
            dummyHead.prev = dummyHead.prev.prev;
            dummyHead.prev.next = dummyHead;
            delNode.next = null;
            delNode.prev = null;
            listSize--;
            return delNode.item;
        } else {
            return null;
        }
    }

    /* use iteration, not recursion. */
    public T get(int index) {
        if (index < 0 || index >= listSize) {
            // throw new IllegalArgumentException("Illegal Index!");
            return null;
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.item;
    }

    /** Method 1:
    private Node getRecursiveNode(int index) {
        if (index == 0) {
            return dummyHead.next;
        } else {
            return getRecursiveNode(index - 1).next;
        }
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= listSize) {
            // throw new IllegalArgumentException("Illegal Index!");
            return null;
        }
        if (index == 0) {
            return getRecursiveNode(0).item;
        } else {
            return getRecursiveNode(index - 1).next.item;
        }
    }
    */

    // Method 2:
    private T getRecursive(Node currentItem, int index) {
        if (index == 0) {
            return currentItem.item;
        } else {
            return getRecursive(currentItem.next, index - 1);
        }
    }

    /* Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (index >= listSize) {
            return null;
        }
        return getRecursive(dummyHead.next, index);
    }

    /* Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        Node cur = dummyHead.next;
        for (int i = 0; i < listSize; i++) {
            System.out.print(cur.item + " ");
            cur = cur.next;
        }
        System.out.println();
    }
}
