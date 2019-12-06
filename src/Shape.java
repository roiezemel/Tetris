import java.awt.*;

public class Shape {

    final static Color[] COLORS = {Color.red, Color.blue, Color.yellow, Color.orange, Color.magenta, Color.green, Color.pink};
    final static int[][][] SHAPES = { // All possible Tetris shapes.

            {
                    {0, 0, 1, 0, 0},
                    {1, 1, 1, 1, 1}
            },
            {
                    {1, 1, 1, 1, 1},
            },
            {
                    {1, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1}
            },
            {
                    {1, 1, 0},
                    {0, 1, 1}
            },
            {
                    {1, 1},
                    {1, 1}
            },
            {
                    {0, 1, 1},
                    {1, 1, 0}
            },
            {
                    {0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1}
            }

    };

    private int [][] shape;
    private Color color;

    public Shape() {

        // Set a random shape
        int index = (int)(Math.random() * SHAPES.length);
        color = COLORS[index];
        shape = SHAPES[index];
    }

    public void flip() {
        int[][] flipped = new int[shape[0].length][shape.length]; // Create new shape array with opposite dimensions.

        for (int i = 0; i < flipped.length; i++) {
            for (int j = 0; j < flipped[i].length; j++) {
                flipped[i][j] = shape[flipped[i].length - j - 1][i]; // Put values in new positions.
            }
        }

        shape = flipped; // Update current shape.
    }

    public int getWidth() {
        return shape[0].length;
    }

    public int getHeight() {
        return shape.length;
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
