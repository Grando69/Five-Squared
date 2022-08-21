package FiveSquared;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Placement {
    private final int row;
    private final int col;
    private final Orientation orientation;
    private final Piece piece;

    public Placement(int row, int col, Orientation orientation, Piece piece) {
        this.row = row;
        this.col = col;
        this.orientation = orientation;
        this.piece = piece;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Piece getPiece() {
        return piece;
    }

    public List<Symbol> getSymols() {
        if(orientation.equals(Orientation.HORIZONTAL) || orientation.equals(Orientation.VERTICAL)) {
            return piece.getSymbols();
        } else {
            Deque<Symbol> deque = new ArrayDeque<>();
            piece.getSymbols().forEach(symbol -> deque.addFirst(symbol));
            return deque.stream().toList();
        }
    }

    @Override
    public String toString() {
        return "Placement{" +
                "row=" + row +
                ", col=" + col +
                ", orientation=" + orientation +
                ", piece=" + piece +
                '}';
    }
}

