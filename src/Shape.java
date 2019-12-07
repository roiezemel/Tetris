import java.awt.*;

public class Shape {

    final static Color[] COLORS = {Color.red, Color.blue, Color.yellow, Color.orange, Color.magenta,
            Color.green, Color.pink, Color.darkGray};
    final static int[][][] SHAPES = { // All possible Tetris shapes.

            {
                    {0, 1, 0},
                    {1, 2, 1}
            },
            {
                    {1, 1, 2, 1},
            },
            {
                    {1, 0, 0},
                    {1, 2, 1}
            },
            {
                    {1, 1, 0},
                    {0, 2, 1}
            },
            {
                    {1, 1},
                    {1, 1}
            },
            {
                    {0, 1, 1},
                    {1, 2, 0}
            },
            {
                    {0, 0, 1},
                    {1, 2, 1}
            }

    };

    private int [][] shape;
    private Color color;
    private int[] center;
    private int colorIndex;
    private int x;
    private int y;

    public Shape() {

        // Set a random shape
        int index = (int)(Math.random() * SHAPES.length);
        color = COLORS[index];
        shape = SHAPES[index];
        colorIndex = index;

        // Set a random position
        x = (int)( Math.random() * (Board.BOARD_WIDTH - getWidth()) );
        y = -getHeight();
        center = getCenterPosition();
    }

    public void flip() {
        if (center[0] != -1)  {
            int[][] flipped = new int[shape[0].length][shape.length]; // Create new shape array with opposite dimensions.

            for (int i = 0; i < flipped.length; i++) {
                for (int j = 0; j < flipped[i].length; j++) {
                    flipped[i][j] = shape[flipped[i].length - j - 1][i]; // Put values in new positions.
                }
            }

            shape = flipped; // Update current shape.
            x = center[0] - (getWidth() - 1) / 2;
            y = center[1] - (getHeight() - 1) / 2;
        }
    }

    public boolean intersects(Shape shape, int[][] board) {
        for (int i = y; i < y + getHeight(); i++) {
            for (int j = x; j < x + getWidth(); j++) {
                if (shape.containsPoint(j, i) && i >= 0 && board[j][i] >= 10 && this.shape[i - y][j - x] != 0)
                    return true;
            }
        }
        return false;
    }

    public boolean containsPoint(int x, int y) {
        return x >= this.x && x < x + getWidth() && y >= this.y && y < y + getHeight();
    }

    private int[] getCenterPosition() {
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                if (shape[i][j] == 2)
                    return new int[] {x + j, y + i};
            }
        }
        return new int[] {-1};
    }

    public void setX(int x) {
        if (center[0] != -1)
            this.center[0] += x - this.x;
        this.x = x;
    }

    public void setY(int y) {
        if (center[0] != -1)
            this.center[1] += y - this.y;
        this.y = y;
    }

    public int[][] getShape() {
        return shape;
    }

    public int getWidth() {
        return shape[0].length;
    }

    public int getHeight() {
        return shape.length;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getColorIndex() {
        return colorIndex;
    }

    public void print() {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                System.out.print(shape[i][j]);
            }
            System.out.println();
        }
    }

}
