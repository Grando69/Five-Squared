package FiveSquared;

import java.util.List;

public class Piece {
    private final int size;
    List<Symbol> symbols;

    public Piece(int size, List<Symbol> symbols) {
        this.size = size;
        this.symbols = symbols;
    }

    public List<Symbol> getSymbols() {
        return symbols;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "" + symbols.toString();
    }
}
