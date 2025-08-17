import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame();

    JLabel textLabel = new JLabel();
    JPanel textJPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;

    public TicTacToe() {
        frame.setTitle("Tic Tac Toe");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top label
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.green);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setText("Current Player: " + currentPlayer);
        textLabel.setOpaque(true);

        textJPanel.setLayout(new BorderLayout());
        textJPanel.add(textLabel);

        frame.add(textJPanel, BorderLayout.NORTH);

        // Board
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        // Buttons
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.green);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;

                        JButton clickedTile = (JButton) e.getSource();
                        if (clickedTile.getText().equals("")) {
                            clickedTile.setText(currentPlayer);
                            CheckWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
                                textLabel.setText("Current Player: " + currentPlayer);
                            }
                        }
                    }
                });
            }
        }

        frame.setVisible(true);
    }

    public void CheckWinner() {
        // Check rows
        for (int r = 0; r < 3; r++) {
            if (!board[r][0].getText().equals("") &&
                board[r][0].getText().equals(board[r][1].getText()) &&
                board[r][0].getText().equals(board[r][2].getText())) {

                gameOver = true;
                textLabel.setText("Player " + board[r][0].getText() + " wins!");
                setWinner(board[r][0], board[r][1], board[r][2]);
                return;
            }
        }

        // Check columns
        for (int c = 0; c < 3; c++) {
            if (!board[0][c].getText().equals("") &&
                board[0][c].getText().equals(board[1][c].getText()) &&
                board[0][c].getText().equals(board[2][c].getText())) {

                gameOver = true;
                textLabel.setText("Player " + board[0][c].getText() + " wins!");
                setWinner(board[0][c], board[1][c], board[2][c]);
                return;
            }
        }

        // Check diagonals
        if (!board[0][0].getText().equals("") &&
            board[0][0].getText().equals(board[1][1].getText()) &&
            board[0][0].getText().equals(board[2][2].getText())) {

            gameOver = true;
            textLabel.setText("Player " + board[0][0].getText() + " wins!");
            setWinner(board[0][0], board[1][1], board[2][2]);
            return;
        }
        if (!board[0][2].getText().equals("") &&
            board[0][2].getText().equals(board[1][1].getText()) &&
            board[0][2].getText().equals(board[2][0].getText())) {

            gameOver = true;
            textLabel.setText("Player " + board[0][2].getText() + " wins!");
            setWinner(board[0][2], board[1][1], board[2][0]);
            return;
        }

        // Check for draw
        boolean draw = true;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c].getText().equals("")) {
                    draw = false;
                    break;
                }
            }
        }
        if (draw) {
            gameOver = true;
            textLabel.setText("DRAW!");
        }
    }

    void setWinner(JButton... tiles) {
        for (JButton t : tiles) {
            t.setBackground(Color.green);
            t.setForeground(Color.darkGray);
            t.setFont(new Font("Arial", Font.BOLD, 80));
        }
    }

    // Entry point
    public static void main(String[] args) {
        new TicTacToe();
    }
}
