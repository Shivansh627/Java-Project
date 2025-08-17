import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;
    
    JFrame frame=new JFrame();

    JLabel textLabel = new JLabel();
    JPanel textJPanel = new JPanel();
    JPanel boarPanel = new JPanel();

    JButton[][] board=new JButton[3][3];
    String playerX="X";
    String playerO="O";
    String currentPlayer=playerX;

    boolean gameOver = false;



    public TicTacToe() {
        frame.setTitle("Tic Tac Toe");
        frame.setSize(boardWidth, boardHeight);
        
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.setVisible(true);
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.green);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setText("Tic Tac Toe");
        textLabel.setOpaque(true);

        textJPanel.setLayout(new BorderLayout());
        textJPanel.add(textLabel);

        frame.add(textJPanel,BorderLayout.NORTH);

        boarPanel.setLayout(new GridLayout(3,3));
        boarPanel.setBackground(Color.darkGray);
        frame.add(boarPanel);

        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++){
                JButton tile= new JButton();
                board[r][c]=tile;
                boarPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.green);
                tile.setFont(new Font("Arial",Font.BOLD,120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if(gameOver) return;
                        JButton tile= (JButton)e.getSource();
                        if(tile.getText()==""){

                            tile.setText(currentPlayer);
                            CheckWinner();
                            if(!gameOver) {
                                currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
                                textLabel.setText("Current Player: " + currentPlayer);
                            }
                        }

                    }
                });
            }
        }
    }
    public void CheckWinner() {
        // Check rows
        for (int r = 0; r < 3; r++) {
            if (!board[r][0].getText().equals("") && board[r][0].getText().equals(board[r][1].getText()) && board[r][0].getText().equals(board[r][2].getText())) {
                gameOver = true;
                textLabel.setText("Player " + board[r][0].getText() + " wins!");
                return;
            }
        }

        // Check columns
        for (int c = 0; c < 3; c++) {
            if (!board[0][c].getText().equals("") && board[0][c].getText().equals(board[1][c].getText()) && board[0][c].getText().equals(board[2][c].getText())) {
                gameOver = true;
                textLabel.setText("Player " + board[0][c].getText() + " wins!");
                return;
            }
        }

        // Check diagonals
        if (!board[0][0].getText().equals("") && board[0][0].getText().equals(board[1][1].getText()) && board[0][0].getText().equals(board[2][2].getText())) {
            gameOver = true;
            textLabel.setText("Player " + board[0][0].getText() + " wins!");
            return;
        }
        if (!board[0][2].getText().equals("") && board[0][2].getText().equals(board[1][1].getText()) && board[0][2].getText().equals(board[2][0].getText())) {
            gameOver = true;
            textLabel.setText("Player " + board[0][2].getText() + " wins!");
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
}
