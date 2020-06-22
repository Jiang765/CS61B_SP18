import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = new OffByOne();
    static CharacterComparator offByN = new OffByN(5);

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("noon"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("toohottohoot"));

        assertTrue(palindrome.isPalindrome("flhke", offByOne));
        assertTrue(palindrome.isPalindrome("ba", offByOne));
        assertTrue(palindrome.isPalindrome("aabb", offByOne));
        assertTrue(palindrome.isPalindrome("aabbb", offByOne));
        assertFalse(palindrome.isPalindrome("abc", offByOne));
        assertTrue(palindrome.isPalindrome("detrude", offByOne));
        assertTrue(palindrome.isPalindrome("a", offByOne));

        assertTrue(palindrome.isPalindrome("binding", offByN));
    }
}
