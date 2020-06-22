public class Palindrome {
    Deque<Character> deque = new LinkedListDeque<>();

    public Deque<Character> wordToDeque(String word) {
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    };

    /**
    Decide if a deque is palindrome
    */
    private boolean recursionHelper1(Deque deque) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        } else {
            return deque.removeFirst() == deque.removeLast() && recursionHelper1(deque);
        }
    }

    public boolean isPalindrome(String word) {
        deque = wordToDeque(word);
        return recursionHelper1(deque);
    }

    private boolean recursionHelper2(Deque deque, CharacterComparator cc) {
        if (deque.size() == 0) {
            return true;
        } else if (deque.size() == 1) {
            deque.removeFirst();
            return true;
        } else {
            Character c1 = (Character) deque.removeFirst();
            Character c2 = (Character) deque.removeLast();
            return cc.equalChars(c1, c2) && recursionHelper2(deque, cc);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        deque = wordToDeque(word);
        return recursionHelper2(deque, cc);
    }
}
