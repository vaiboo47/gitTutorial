import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    private JTextField textField;
    private StringBuilder currentInput;
    private double result = 0;
    private String operator = "=";
    private boolean calculating = true;

    public Calculator() {
        setTitle("Java Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center window

        currentInput = new StringBuilder();
        textField = new JTextField("0");
        textField.setFont(new Font("Arial", Font.BOLD, 24));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);

        // Panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        // Button texts
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            panel.add(button);
        }

        setLayout(new BorderLayout(10, 10));
        add(textField, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        if ("0123456789.".contains(input)) {
            if (calculating) {
                currentInput.setLength(0); // clear input
                calculating = false;
            }
            currentInput.append(input);
            textField.setText(currentInput.toString());
        } else if ("+-*/".contains(input)) {
            compute(Double.parseDouble(currentInput.toString()));
            operator = input;
            calculating = true;
        } else if (input.equals("=")) {
            compute(Double.parseDouble(currentInput.toString()));
            operator = "=";
            textField.setText(String.valueOf(result));
            calculating = true;
        } else if (input.equals("C")) {
            result = 0;
            currentInput.setLength(0);
            textField.setText("0");
            operator = "=";
            calculating = true;
        }
    }

    private void compute(double number) {
        switch (operator) {
            case "+": result += number; break;
            case "-": result -= number; break;
            case "*": result *= number; break;
            case "/": 
                if (number != 0)
                    result /= number;
                else
                    textField.setText("Error: Divide by 0");
                break;
            case "=": result = number; break;
        }
        textField.setText(String.valueOf(result));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}
