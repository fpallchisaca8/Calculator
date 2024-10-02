import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField textField;
    private JButton[] numberButtons = new JButton[10];
    private JButton addButton, subButton, mulButton, divButton, equButton, clrButton;
    private JPanel panel;

    private Calculator calculator = new Calculator();

    public CalculatorGUI() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(new Font("Arial", Font.BOLD, 24));
        textField.setEditable(false);
        add(textField);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("x");
        divButton = new JButton("/");
        equButton = new JButton("=");
        clrButton = new JButton("Clear");

        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        subButton.setFont(new Font("Arial", Font.BOLD, 20));
        mulButton.setFont(new Font("Arial", Font.BOLD, 20));
        divButton.setFont(new Font("Arial", Font.BOLD, 20));
        equButton.setFont(new Font("Arial", Font.BOLD, 20));
        clrButton.setFont(new Font("Arial", Font.BOLD, 20));

        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);
        equButton.addActionListener(this);
        clrButton.addActionListener(this);

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
            numberButtons[i].addActionListener(this);
        }

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(numberButtons[0]);
        panel.add(clrButton);
        panel.add(equButton);
        panel.add(divButton);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(numberButtons[i].getText()));
                return;
            }
        }
        JButton clickedButton = (JButton) e.getSource();
        String buttonText = clickedButton.getText();
        String currentText = textField.getText();

        // Check if the clicked button is an operator
        if (buttonText.equals("+") || buttonText.equals("-") || buttonText.equals("*") || buttonText.equals("/")) {
            textField.setText(currentText + " " + buttonText + " ");
        } else if (buttonText.equals("=")) {
            // Perform calculation when the equals button is clicked
            String[] tokens = currentText.split(" ");
            if (tokens.length == 3) {
                // Ensure the expression is valid (operand1 operator operand2)
                int num1 = Integer.parseInt(tokens[0]);
                char operator = tokens[1].charAt(0);
                int num2 = Integer.parseInt(tokens[2]);

                calculator.setNum1(num1);
                calculator.setNum2(num2);
                calculator.setOperator(operator);

                try {
                    int result = calculator.calculate();
                    textField.setText(String.valueOf(result));
                } catch (ArithmeticException error) {
                    textField.setText("Error");
                }
            }
        } else if (buttonText.equals("Clear")) {
            // Clear the text field when the Clear button is clicked
            textField.setText("");
        }
    }
    public static void main(String[] args) {
        new CalculatorGUI();
    }

}
