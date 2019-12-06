import java.awt.*;

public class Board {

    final static int BOARD_WIDTH = 23;
    final static int BOARD_HEIGHT = 33;
    final static int SQUARE_SIZE = 20;

    private int[][] board = new int[BOARD_WIDTH][BOARD_HEIGHT];
    private Shape currentShape;

    /**
     * Initialize board.
     */
    public void init() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }
    }

    public void paint(Graphics2D g2d) {

        // Fill all squares.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                g2d.setColor(Color.black);
                g2d.drawRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public Shape getCurrentShape() {
        return currentShape;
    }

}
