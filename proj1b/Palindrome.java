public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    };

    /**
    Decide if a deque is palindrome
    */
    private boolean recursionHelper1(Deque<Character> deque) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        } else {
            return deque.removeFirst() == deque.removeLast() && recursionHelper1(deque);
        }
    }

    public boolean isPalindrome(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        deque = wordToDeque(word);
        return recursionHelper1(deque);
    }

    /**
     Decide if a deque is palindrome
    */
    private boolean recursionHelper2(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        } else {
            Character c1 = (Character) deque.removeFirst();
            Character c2 = (Character) deque.removeLast();
            return cc.equalChars(c1, c2) && recursionHelper2(deque, cc);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = new LinkedListDeque<>();
        deque = wordToDeque(word);
        return recursionHelper2(deque, cc);
    }
}
