import java.util.ArrayList;
import java.util.Arrays;

public class ChestBoard {
    private final int size;
    private int[][] board;

    public ChestBoard(int size) {
        this.size = size;
        board = new int[size][size];
    }

    public void printBoardState(){
        for (int[] arr : board){
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("-----------------------");
    }

    public boolean putQueen(int row, int col){
        if (board[row][col] == 0){
            board[row][col] = 1;
            return true;
        }
        return false;
    }

    public boolean validateBoard() {
        for (int i = 0; i < size; i++) {
            if (getSumOfArray(board[i]) > 1) {
                return false;
            }
            int[] col = new int[size];
            for (int j = 0; j < size; j++) {
                col[j] = board[j][i];
                if (board[i][j] == 1){
                    if (checkDiagonals(board, i, j)){
                        return false;
                    }
                }
            }
            if (getSumOfArray(col) > 1) {
                return false;
            }

        }
            return true;

    }

    public int getSumOfArray(int[] arr){
        return Arrays.stream(arr).sum();
    }

    public boolean checkDiagonals(int[][] board, int row, int col){
        boolean allChecked = false;
        ArrayList<Integer> diag = new ArrayList<>();
        int level = 0;

        while (!allChecked){
            level++;
            int upLeft = -1;
            int upRight = -1;
            int downLeft = -1;
            int downRight = -1;

            if ((row - level) >= 0 && (col-level) >= 0){
                upLeft = board[row-level][col-level];
                diag.add(upLeft);
            }if ((row - level) >= 0 && (col+level) < size){
                upRight = board[row-level][col+level];
                diag.add(upRight);
            }if ((row + level) < size && (col-level) >= 0){
                downLeft = board[row+level][col-level];
                diag.add(downLeft);
            }if ((row + level) < size && (col+level) < size){
                downRight = board[row+level][col+level];
                diag.add(downRight);
            }

            if ((upLeft + upRight + downRight + downLeft) == -4) {
                allChecked = true;
            }
        }
        int sum = 0;
        for(int element: diag){
            sum += element;
        }
        return sum >= 1;
    }


    public boolean solve_R(int col){
        if (getQueenAmount() == size){
            return true;
        }
        for (int row = 0; row < size; row++){
            if (!putQueen(row, col)) continue;
            if (validateBoard()){
//                printBoardState();
                if(!solve_R(col+1)){
                    board[row][col] = 0;
                } else{
                    return true;
                }
            } else{
                board[row][col] = 0;;
            }
        }

        return false;
    }

    public int getQueenAmount(){
        int amount = 0;
        for (int i = 0; i<size; i++){
            for (int j = 0; j<size; j++){
                if (board[i][j] == 1) amount++;
            }
        }
        return amount;
    }
}
