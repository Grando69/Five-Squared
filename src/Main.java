public class Main {

    public static void main(String[] args) {
        for (int i = 4; i<= 20; i++) {
            ChestBoard chestBoard = new ChestBoard(i);
            long startTime = System.currentTimeMillis();
            chestBoard.solve_R(0);
            long endTime = System.currentTimeMillis();
            chestBoard.printBoardState();
            System.out.println("With i = " + i + " this took " + (endTime - startTime) / 1000f + "s");
        }
    }

}
