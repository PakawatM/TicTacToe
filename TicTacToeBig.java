import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToeBig extends JFrame implements ActionListener {
private static final long serialVersionUID = 1L;

// กำหนดไซส์ของช่อง
private int size = 8 ;
private JButton[][] b;
private int turn = 0;

// กำหนดหน้าต่าง
public TicTacToeBig() {
    setTitle("Tic-Tac-Toe");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(500, 500);
    setLocation(200, 200);
    setVisible(true);

    b = new JButton[size][size];

    setLayout(new GridLayout(size, size));

    for (int row = 0; row < size; row++) {
        for (int col = 0; col < size; col++) {
            System.out.println("Adding button for position: " + row + ", " + col);

            b[row][col] = new JButton();
            b[row][col].setText(" ");
            b[row][col].addActionListener(this);
            b[row][col].setActionCommand(row + "," + col);
            add(b[row][col]);
        }
    }

    invalidate();
    validate();
}

@Override
public void actionPerformed(ActionEvent ae) {
    String sign;

    // เช็ค Turn ของ X และ O
    turn++;

    if (turn % 2 == 0) {
        sign = "O";
    } else {
        sign = "X";
    }

    // ใส่ X หรือ O ในปุ่ม
    JButton press = (JButton) ae.getSource();

    press.setText(sign);
    press.setEnabled(false);

    // จบเกมแล้วจะจบเลย ต้อง RUN ใหม่
    if (checkWin(press)) {
        JOptionPane.showMessageDialog(null, "Congratulations!\n" + sign + " has won!");

        System.exit(0);
    } else if (turn >= (size * size)) {
        JOptionPane.showMessageDialog(null, "To bad!\n No winners. ");

        System.exit(0);
    }
}

public boolean checkWin(JButton j) {
    String position[] = j.getActionCommand().split(",");

    int row = Integer.parseInt(position[0]);
    int col = Integer.parseInt(position[1]);

    System.out.println(b[row][col].getText() + " played @ " + row + ", " + col);

    String winner = b[row][col].getText() + b[row][col].getText() + b[row][col].getText();
    String field;

    // row
    field = "";

    for (int testCol = Math.max(0, col - 2); testCol < Math.min(size, col + 3); testCol++) {
        field += b[row][testCol].getText();
    }

    System.out.println("Testing row field: " + field);

    if (field.contains(winner)) {
        System.out.println("Row winner!");

        return true;
    }

    // col
    field = "";

    for (int testRow = Math.max(0, row - 2); testRow < Math.min(size, row + 3); testRow++) {
        field += b[testRow][col].getText();
    }

    System.out.println("Testing column field: " + field);

    if (field.contains(winner)) {
        System.out.println("Column winner!");

        return true;
    }

    // diagonals
    int lowerBound = 0;
    int upperBound = 0;

    // diagonal down
    field = "";

    // top left
    lowerBound = - Math.min(2, Math.min(col, row));

    // bottom right
    upperBound = Math.min(3, size - Math.max(row, col));

    System.out.println("Bounds: " + lowerBound + ", " + upperBound);

    for (int offset = lowerBound; offset < upperBound; offset++) {
        field += b[row + offset][col + offset].getText();
    }

    System.out.println("Testing diagonal down field: " + field);

    if (field.contains(winner)) {
        System.out.println("Diagonal down winner!");

        return true;
    }

    // diagonal up
    field = "";

    // bottom left
    // lowerBound = ?????????????;
    lowerBound = 0;

    // top right
    // upperBound = ?????????????;
    upperBound = 0;

    System.out.println("Bounds: " + lowerBound + ", " + upperBound);

    for (int offset = lowerBound; offset < upperBound; offset++) {
        // field += b[row +/- offset][col +/- offset].getText();
    }

    System.out.println("Testing diagonal up field: " + field);

    if (field.contains(winner)) {
        System.out.println("Diagonal up winner!");

        return true;
    }

    return false;
}

public static void main(String args[]) {
    TicTacToeBig t = new TicTacToeBig();
}
}