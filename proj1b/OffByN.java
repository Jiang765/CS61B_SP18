public class OffByN implements CharacterComparator {
    private int diff;

    public OffByN(int N) {
        diff = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (97 <= x && x <= 122 || 65 <= x && x <= 90) {
            return x - y == diff || x - y == -diff;
        } else if (97 <= y && y <= 122 || 65 <= y && y <= 90) {
            return x - y == diff || x - y == -diff;
        } else {
            return false;
        }
    }
}
