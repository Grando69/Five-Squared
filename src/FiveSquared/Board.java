package FiveSquared;

import java.util.*;

public class Board {
    private final int size = 5;
    private static Set<Placement> boardPlacement = new HashSet<>();
    private static final Symbol[][] board = new Symbol[5][5];
    private static long count;

    public static void main(String[] args) {
        solve();
        //printBoardState();
        //possibleSolution();

    }

    public static void possibleSolution() {
        add(new Placement(0, 0, Orientation.HORIZONTAL_FLIPPED, AllPieces.getAllPieces().get(1)));
        add(new Placement(0, 3, Orientation.HORIZONTAL_FLIPPED, AllPieces.getAllPieces().get(3)));
        add(new Placement(1, 0, Orientation.HORIZONTAL, AllPieces.getAllPieces().get(8)));
        add(new Placement(1, 3, Orientation.VERTICAL, AllPieces.getAllPieces().get(5)));
        add(new Placement(1, 4, Orientation.VERTICAL_FLIPPED, AllPieces.getAllPieces().get(0)));
        add(new Placement(2, 0, Orientation.VERTICAL, AllPieces.getAllPieces().get(2)));
        add(new Placement(2, 1, Orientation.VERTICAL, AllPieces.getAllPieces().get(6)));
        add(new Placement(2, 2, Orientation.VERTICAL_FLIPPED, AllPieces.getAllPieces().get(4)));
        add(new Placement(4, 2, Orientation.HORIZONTAL, AllPieces.getAllPieces().get(7)));

        System.out.println(boardPlacement.size());
        printBoardState();

    }


    public static boolean _solve(List<Piece> piecesLeft, Piece toFit) {
        if (boardPlacement.size() == 9) {
            return true;
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (Orientation orientation : Orientation.values()) {
                    Placement placement = new Placement(i, j, orientation, toFit);
                    if (add(placement)) {

//

                        if (piecesLeft.isEmpty())
                            return true;

                        Piece nextToFit = piecesLeft.remove(0);
                        if (!_solve(piecesLeft, nextToFit)) {
                            piecesLeft.add(nextToFit);
                            boardPlacement.remove(placement);
                            //System.out.println(boardPlacement.size());
                            continue;
                        } else {
                            return true;
                        }
                    }
                }
            }
        }
        //boardPlacement.clear();
//        piecesLeft.add(toFit);
        return false;
    }


    public static void solve2() {
        while (boardPlacement.size() != 9) {
            List<Piece> allPieces = AllPieces.getAllPieces();

            Collections.shuffle(allPieces);

            allPieces.stream().forEach(piece -> {
                _solve(allPieces, piece);
            });
        }

        printBoardState();
    }


    public static void solve() {
        List<Piece> allPieces = AllPieces.getAllPieces();
        AllPieces.getAllPieces().stream().forEach(piece -> {

                    allPieces.remove(piece);
                    if (_solve(allPieces, piece)) {
                        printBoardState();
                    } else {
                        //System.out.println("adding back piece: "+ piece);
                        printBoardState();
                        boardPlacement.clear();
                        allPieces.add(piece);
                    }
                }
        );
    }

    /**
     * @param placement
     * @return true if the piece could be placed on the board
     */
    private static boolean add(Placement placement) {
        clearBoeard();
        putPiecesOnBoard();

        Piece piece = placement.getPiece();

        // check if this piece fits with the current board
        if (placement.getOrientation() == Orientation.HORIZONTAL || placement.getOrientation() == Orientation.HORIZONTAL_FLIPPED) {
            if ((placement.getCol() + piece.getSize()) <= 5) {
                for (int i = 0; i < piece.getSize(); i++) {
                    if (board[placement.getRow()][placement.getCol() + i] != null) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (placement.getOrientation() == Orientation.VERTICAL || placement.getOrientation() == Orientation.VERTICAL_FLIPPED) {
            if ((placement.getRow() + piece.getSize()) <= 5) {
                for (int i = 0; i < piece.getSize(); i++) {
                    if (board[placement.getRow() + i][placement.getCol()] != null) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }

        boardPlacement.add(placement);
        clearBoeard();
        putPiecesOnBoard();


        if (!validateBoard()) {
            boardPlacement.remove(placement);
            return false;
        }
        return true;
    }

    public static boolean validateBoard() {
        // assuming pieces are on the board

        for (int i = 0; i < 5; i++) {
            Symbol[] row = board[i];

            if (!checkForDuplicates(row)) {
//                System.out.println("row " + i + " has dups :" + Arrays.toString(row));
                return false;
            }

            Symbol[] column = new Symbol[5];
            for (int j = 0; j < 5; j++) {
                column[j] = board[j][i];
            }

            if (!checkForDuplicates(column)) {
//                System.out.println("Column " + i + " has  dups: " + Arrays.toString(column));
                return false;
            }
        }

        return true;
    }

    public static boolean checkForDuplicates(Symbol[] arr) {
        Set<Symbol> symbols = new HashSet<>();
        for (Symbol s : arr) {
            if (s == null) continue;
            if (!symbols.add(s))
                return false;
        }
        return true;
    }

    private static void clearBoeard() {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                board[i][j] = null;
    }

    public static void putPiecesOnBoard() {
        for (Placement placement : boardPlacement) {
            Piece piece = placement.getPiece();

            switch (placement.getOrientation()) {
                case HORIZONTAL, HORIZONTAL_FLIPPED -> {
                    for (int i = 0; i < piece.getSize(); i++) {
                        board[placement.getRow()][placement.getCol() + i] = placement.getSymols().get(i);
                    }

                }
                case VERTICAL, VERTICAL_FLIPPED -> {
                    for (int i = 0; i < piece.getSize(); i++) {
                        board[placement.getRow() + i][placement.getCol()] = placement.getSymols().get(i);
                    }

                }
            }
        }
    }

    public static void printBoardState() {
        putPiecesOnBoard();
        for (Symbol[] symbols : board) {
            System.out.println(Arrays.toString(symbols));
        }

        System.out.println("");

        boardPlacement.forEach(System.out::println);
        System.out.println("\n");
    }
}
