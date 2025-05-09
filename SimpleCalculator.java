
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends JFrame implements ActionListener {

    private JTextField number1Field, number2Field, resultField;
    private JButton addBtn, subtractBtn, multiplyBtn, divideBtn, clearBtn;

    // ✅ Correct constructor name
    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Heading
        JLabel heading = new JLabel(" Simple Calculator");
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(heading, gbc);

        // Label and Text Field: Number 1
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Number 1:"), gbc);

        number1Field = new JTextField(10);
        gbc.gridx = 1;
        add(number1Field, gbc);

        // Label and Text Field: Number 2
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Number 2:"), gbc);

        number2Field = new JTextField(10);
        gbc.gridx = 1;
        add(number2Field, gbc);

        // Label and Text Field: Result
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Result:"), gbc);

        resultField = new JTextField(10);
        resultField.setEditable(false);
        gbc.gridx = 1;
        add(resultField, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        addBtn = new JButton("Add");
        addBtn.setToolTipText("Add the two numbers");
        addBtn.addActionListener(this);
        buttonPanel.add(addBtn);

        subtractBtn = new JButton("Subt.");
        subtractBtn.setToolTipText("Subtract Number 2 from Number 1");
        subtractBtn.addActionListener(this);
        buttonPanel.add(subtractBtn);

        multiplyBtn = new JButton("Multi.");
        multiplyBtn.setToolTipText("Multiply the two numbers");
        multiplyBtn.addActionListener(this);
        buttonPanel.add(multiplyBtn);

        divideBtn = new JButton("Divide");
        divideBtn.setToolTipText("Divide Number 1 by Number 2");
        divideBtn.addActionListener(this);
        buttonPanel.add(divideBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setToolTipText("Clear all fields");
        clearBtn.addActionListener(this);
        buttonPanel.add(clearBtn);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String num1Text = number1Field.getText();
        String num2Text = number2Field.getText();

        try {
            if (num1Text.isEmpty() || num2Text.isEmpty()) {
                throw new IllegalArgumentException("Both numbers must be entered.");
            }

            double num1 = Double.parseDouble(num1Text);
            double num2 = Double.parseDouble(num2Text);
            double result = 0;

            if (e.getSource() == addBtn) {
                result = num1 + num2;
            } else if (e.getSource() == subtractBtn) {
                result = num1 - num2;
            } else if (e.getSource() == multiplyBtn) {
                result = num1 * num2;
            } else if (e.getSource() == divideBtn) {
                if (num2 == 0) {
                    throw new ArithmeticException("Cannot divide by zero.");
                }
                result = num1 / num2;
            } else if (e.getSource() == clearBtn) {
                number1Field.setText("");
                number2Field.setText("");
                resultField.setText("");
                return;
            }

            resultField.setText(String.valueOf(result));

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (ArithmeticException ae) {
            JOptionPane.showMessageDialog(this, ae.getMessage(), "Math Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException iae) {
            JOptionPane.showMessageDialog(this, iae.getMessage(), "Input Missing", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleCalculator::new);
    }
}
