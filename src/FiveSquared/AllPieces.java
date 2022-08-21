package FiveSquared;

import java.util.ArrayList;
import java.util.List;

public class AllPieces {
    private static final List<Piece> allPieces = List.of(
            new Piece(3, List.of(Symbol.SWIRL, Symbol.FIVE_STAR, Symbol.SIX_STAR)),
            new Piece(3, List.of(Symbol.FIVE_STAR, Symbol.SIX_STAR, Symbol.SWIRL)),
            new Piece(3, List.of(Symbol.TRIANGLE, Symbol.CIRCLE, Symbol.SIX_STAR)),
            new Piece(2, List.of(Symbol.CIRCLE, Symbol.TRIANGLE)),
            new Piece(2, List.of(Symbol.TRIANGLE, Symbol.SIX_STAR)),
            new Piece(3, List.of(Symbol.CIRCLE, Symbol.SWIRL, Symbol.SIX_STAR)),
            new Piece(3, List.of(Symbol.CIRCLE, Symbol.FIVE_STAR, Symbol.SWIRL)),
            new Piece(3, List.of(Symbol.CIRCLE, Symbol.FIVE_STAR, Symbol.TRIANGLE)),
            new Piece(3, List.of(Symbol.FIVE_STAR, Symbol.TRIANGLE, Symbol.SWIRL))
    );

    public static List<Piece> getAllPieces(){
        return new ArrayList<>(allPieces);
    }

}
