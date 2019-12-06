import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JPanel implements KeyListener {

    Board board = new Board();

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

        // Start painting
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
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
