public class OffByOne implements CharacterComparator  {
    @Override
    public boolean equalChars(char x, char y) {
        if (97 <= x && x <= 122 || 65 <= x && x <= 90) {
            return x - y == 1 || x - y == -1;
        } else if (97 <= y && y <= 122 || 65 <= y && y <= 90) {
            return x - y == 1 || x - y == -1;
        } else {
            return false;
        }
    }
}
