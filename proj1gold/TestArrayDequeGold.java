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

        for (int i = 0; i < 1000; i += 1) {
            int number = StdRandom.uniform(1000);
            sad.addLast(number);
            ads.addLast(number);
            for (int j = 0; j < ads.size() - 1; j++) {
                assertEquals("addLast is incorrect！Expected: " + ads.get(j)
                        + " Actual: " + sad.get(j), ads.get(j), sad.get(j));
            }
        }

        for (int i = 0; i < 1000; i += 1) {
            int number = StdRandom.uniform(1000);
            sad.addFirst(number);
            ads.addFirst(number);
            for (int j = 0; j < ads.size() - 1; j++) {
                assertEquals("addFirst is incorrect！Expected: " + ads.get(j)
                        + " Actual: " + sad.get(j), ads.get(j), sad.get(j));
            }
        }

        for (int i = 0; i < 1000; i += 1) {
            Integer r1 = ads.removeFirst();
            Integer r2 = sad.removeFirst();
            assertEquals("removeFirst is incorrect！Expected: " + r1 + " Actual: " + r2, r1, r2);
        }

        for (int i = 0; i < 1000; i += 1) {
            Integer r1 = ads.removeLast();
            Integer r2 = sad.removeLast();
            assertEquals("removeLast is incorrect！Expected: " + r1 + " Actual: " + r2, r1, r2);
        }
    }
}
