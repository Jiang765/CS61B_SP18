import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    /**
    randomly call StudentArrayDeque and ArrayDequeSolution
    methods until they disagree on an output.
    StdRandom.uniform(N): Return random integers from [0,N)
    */
    @Test
    public void testArray() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        String message = "";

        for (int i = 0; i < 50; i += 1) {
            int number = StdRandom.uniform(100);
            sad.addLast(number);
            ads.addLast(number);
            message += "addLast(" + number + ")\n";
            assertEquals(message, ads.get(i), sad.get(i));

        }

        for (int i = 0; i < 50; i += 1) {
            int number = StdRandom.uniform(100);
            sad.addFirst(number);
            ads.addFirst(number);
            message += "addFirst(" + number + ")\n";
            assertEquals(message, ads.get(0), sad.get(0));
        }

        for (int i = 0; i < 50; i += 1) {
            Integer r1 = ads.removeFirst();
            Integer r2 = sad.removeFirst();
            message += "removeFirst()\n";
            assertEquals(message, r1, r2);
        }

        for (int i = 0; i < 50; i += 1) {
            Integer r1 = ads.removeLast();
            Integer r2 = sad.removeLast();
            message += "removeLast()\n";
            assertEquals(message, r1, r2);
        }
    }
}
