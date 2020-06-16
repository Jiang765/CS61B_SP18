public class test {
    public static void main(String[] args) {
        // LinkedListDeque<Integer> ll = new LinkedListDeque<>();
        ArrayDeque<Integer> ll = new ArrayDeque<>();
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addFirst(5);
        // ll.printDeque();

        ll.removeFirst();
        // ll.printDeque();

        ll.removeLast();
        // ll.printDeque();

        System.out.println(ll.get(0));
        System.out.println(ll.get(1));
        System.out.println(ll.get(2));

//        System.out.println(ll.getRecursive(0));
//        System.out.println(ll.getRecursive(1));
//        System.out.println(ll.getRecursive(2));
    }
}
