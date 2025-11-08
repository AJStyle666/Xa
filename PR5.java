import java.util.*;

/**
 * EightQueensFixed.java
 * Usage: javac EightQueensFixed.java && java EightQueensFixed
 * Example: fixed queen at (0,0). Rows/cols are 0-based.
 */
public class PR5 {
    public static boolean solveWithFixed(int fixedRow, int fixedCol) {
        final int N = 8;
        if (fixedRow < 0 || fixedRow >= N || fixedCol < 0 || fixedCol >= N)
            throw new IllegalArgumentException("fixedRow/Col must be in [0,7]");

        int[] queenCol = new int[N];
        Arrays.fill(queenCol, -1);
        boolean[] cols = new boolean[N];
        boolean[] diag1 = new boolean[2 * N];
        boolean[] diag2 = new boolean[2 * N];

        queenCol[fixedRow] = fixedCol;
        cols[fixedCol] = true;
        diag1[fixedCol - fixedRow + N] = true;
        diag2[fixedRow + fixedCol] = true;

        boolean solved = placeRow(0, N, queenCol, cols, diag1, diag2, fixedRow);
        if (solved) {
            printBoard(queenCol);
        } else {
            System.out.println("No solution with fixed queen at (" + fixedRow + "," + fixedCol + ")");
        }
        return solved;
    }

    private static boolean placeRow(int row, int N, int[] queenCol,
                                    boolean[] cols, boolean[] diag1, boolean[] diag2,
                                    int fixedRow) {
        if (row == N) return true;
        if (row == fixedRow) return placeRow(row + 1, N, queenCol, cols, diag1, diag2, fixedRow);
        for (int c = 0; c < N; c++) {
            if (!cols[c] && !diag1[c - row + N] && !diag2[row + c]) {
                queenCol[row] = c;
                cols[c] = true;
                diag1[c - row + N] = true;
                diag2[row + c] = true;
                if (placeRow(row + 1, N, queenCol, cols, diag1, diag2, fixedRow)) return true;
                // backtrack
                queenCol[row] = -1;
                cols[c] = false;
                diag1[c - row + N] = false;
                diag2[row + c] = false;
            }
        }
        return false;
    }

    private static void printBoard(int[] queenCol) {
        int N = queenCol.length;
        System.out.println("8-Queens Solution:");
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                System.out.print((queenCol[r] == c) ? " Q" : " .");
            }
            System.out.println();
        }
        System.out.println("Positions (row,col):");
        for (int r = 0; r < N; r++) System.out.print(" (" + r + "," + queenCol[r] + ")");
        System.out.println("\n");
    }

    public static void main(String[] args) {
        // Example: fixed queen at row 0, col 0
        solveWithFixed(0, 0);

        // You can try other fixed positions:
        // solveWithFixed(3, 4);
    }
}
