import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Board {

    final static int BOARD_WIDTH = 15;
    final static int BOARD_HEIGHT = 25;
    final static int SQUARE_SIZE = 25;

    private int[][] board = new int[BOARD_WIDTH][BOARD_HEIGHT];
    LinkedList<Shape> shapes = new LinkedList<>();
    private Shape currentShape;

    /**
     * Initialize board.
     * Fills the board with 7 - the last index of Shapes.COLORS, which means the board is clear.
     */
    public void init() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 7; // 7 is the last index of Shape.COLORS
            }
        }
    }

    public void addShape() {
        currentShape = new Shape();
        shapes.add(currentShape);
    }

    public void moveShapeHorizontally(int m) {
        eraseCurrentShape();
        currentShape.setX(currentShape.getX() + m);
        if (currentShape.getX() >= Board.BOARD_WIDTH - currentShape.getWidth() + 1 || currentShape.getX() < 0 || isIntersecting())
            currentShape.setX(currentShape.getX() - m);
        redrawCurrentShape();
    }

    public void moveShapeDown() {
        eraseCurrentShape();
        currentShape.setY(currentShape.getY() + 1);
        if (currentShape.getY() >= Board.BOARD_HEIGHT - currentShape.getHeight() + 1 || isIntersecting()) {
            currentShape.setY(currentShape.getY() - 1);
            redrawCurrentShape();
            LinkedList<Integer> fullLines = findFullLines();
            if (!fullLines.isEmpty()) {
                for (int i : fullLines)
                    deleteLine(i);
            }
            addShape();
        }
        else
            redrawCurrentShape();
    }

    public void flip() {
        eraseCurrentShape();
        currentShape.flip();
        if (currentShape.getX() >= Board.BOARD_WIDTH - currentShape.getWidth() + 1 ||
                currentShape.getY() >= Board.BOARD_HEIGHT - currentShape.getHeight() + 1 || isIntersecting()) {
            for (int i = 0; i < 3; i++)
                currentShape.flip();
        }
        redrawCurrentShape();
    }

    private void redrawCurrentShape() {
        for (int i = currentShape.getX(); i < currentShape.getX() + currentShape.getWidth(); i++) {
            for (int j = currentShape.getY(); j < currentShape.getY() + currentShape.getHeight(); j++) {

                // Adding ten only so that the current shape will distinguishable from other shapes
                if (j >= 0 && currentShape.getShape()[j - currentShape.getY()][i - currentShape.getX()] != 0)
                    board[i][j] = currentShape.getColorIndex() + 10;
            }
        }
    }

    private void eraseCurrentShape() {
        for (int i = currentShape.getX(); i < currentShape.getX() + currentShape.getWidth(); i++) {
            for (int j = currentShape.getY(); j < currentShape.getY() + currentShape.getHeight(); j++) {
                if (j >= 0 && board[i][j] >= 10 && currentShape.getShape()[j - currentShape.getY()][i - currentShape.getX()] != 0)
                    board[i][j] = 7;
            }
        }
    }

    private void deleteLine(int line) {
        int[][] newBoard = new int[BOARD_WIDTH][BOARD_HEIGHT];
        for (int i = 0; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                if (j <= line)
                    newBoard[i][j] = board[i][j - 1];
                else
                    newBoard[i][j] = board[i][j];
            }
        }
        for (int i = 0; i < board.length; i++) {
            newBoard[i][0] = 7;
        }
        board = newBoard;
    }

    public void paint(Graphics2D g2d) {

        // Fill all squares.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                g2d.setColor(Shape.COLORS[board[i][j] >= 10 ? board[i][j] - 10 : board[i][j]]);
                g2d.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                g2d.setColor(Color.black);
                g2d.drawRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    private LinkedList<Integer> findFullLines() {
        LinkedList<Integer> lines = new LinkedList<>();
        for (int i = 0; i < board[0].length; i++) {
            if (isLineFull(i)) {
                lines.add(i);
            }
        }
        return lines;
    }

    public boolean isLineFull(int line) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][line] == 7)
                return false;
        }
        return true;
    }

    public boolean isIntersecting() {
        for (int i = 0; i < shapes.size() - 1; i++) {
            if (currentShape.intersects(shapes.get(i), board))
                return true;
        }
        return false;
    }

    public boolean isLineEmpty(int line) {
        for (int i = 0; i < shapes.size() - 1; i++) {
            if (shapes.get(i).containsPoint(i, line))
                return false;
        }
        return true;
    }

    public int[][] getBoard() {
        return board;
    }

    public Shape getCurrentShape() {
        return currentShape;
    }

}
