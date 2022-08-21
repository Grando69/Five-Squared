package FiveSquared;

public enum Symbol {
    CIRCLE,
    SWIRL,
    SIX_STAR,
    TRIANGLE,
    FIVE_STAR;

    @Override
    public String toString() {
        String current = super.toString();
        int spaceSize = 9 - current.length();
        int prefixSize = spaceSize / 2;
        int suffixSize = (spaceSize + 1) / 2;

        return 9 > current.length()
                ? " ".repeat(prefixSize) + current + " ".repeat(suffixSize)
                : current;
    }
}
