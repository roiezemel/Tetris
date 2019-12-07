import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JPanel implements KeyListener {

    // Members
    Board board = new Board();

    // Flags:
    boolean flip = false;
    boolean gameOver = false;

    // Game variables
    int wait = 50; // Speed
    int move = 0;

    public Main() {

        // Set window
        JFrame frame = new JFrame("Tetris Game");
        frame.add(this);
        frame.setSize(Board.BOARD_WIDTH * Board.SQUARE_SIZE + 17, Board.BOARD_HEIGHT * Board.SQUARE_SIZE + 40);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Add listeners
        this.setFocusable(true);
        this.addKeyListener(this);

        // Initialize board
        board.init();
        board.addShape();

        // Start game loop
        while (board.isLineEmpty(0)) {

            if (move != 0) {
                board.moveShapeHorizontally(move);
            }
            else
                board.moveShapeDown();

            if (flip) {
                board.flip();
                flip = false;
            }

            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }

        gameOver = true;
        repaint();
    }

    @Override
    public void paint(Graphics g) {

        // Cast g to Graphics2D object.
        Graphics2D g2d = (Graphics2D) g;

        // Set background.
        g2d.setColor(Color.darkGray);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Paint board.
        board.paint(g2d);

        // Game Over
        if (gameOver) {
            g2d.setFont(new Font("David", Font.BOLD, 50));
            g2d.setColor(Color.lightGray);
            g2d.fillRect(55, 255, 270, 60);
            g2d.setColor(Color.red);
            g2d.drawString("Game Over!", 60, 300);
            g2d.setStroke(new BasicStroke(3));
            g2d.setColor(Color.black);
            g2d.drawRect(55, 255, 270, 60);
        }

    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP)
            flip = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            wait = 10;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            move = 1;
            wait = 50;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            move = -1;
            wait = 50;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            wait = 100;
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            wait = 100;
            move = 0;
        }
    }
}
