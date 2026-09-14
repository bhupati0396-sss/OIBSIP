package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView display;

    double firstNumber = 0;
    String operator = "";
    boolean newNumber = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Number buttons
        int[] numberIds = {
                R.id.btn0,
                R.id.btn1,
                R.id.btn2,
                R.id.btn3,
                R.id.btn4,
                R.id.btn5,
                R.id.btn6,
                R.id.btn7,
                R.id.btn8,
                R.id.btn9
        };

        for (int id : numberIds) {

            Button button = findViewById(id);

            button.setOnClickListener(v -> {

                if (newNumber) {
                    display.setText("");
                    newNumber = false;
                }

                display.append(button.getText().toString());
            });
        }

        // Decimal button
        Button decimal = findViewById(R.id.btnDecimal);

        decimal.setOnClickListener(v -> {

            if (newNumber) {
                display.setText("0");
                newNumber = false;
            }

            if (!display.getText().toString().contains(".")) {
                display.append(".");
            }
        });

        // Operators
        Button add = findViewById(R.id.btnAdd);
        Button subtract = findViewById(R.id.btnSubtract);
        Button multiply = findViewById(R.id.btnMultiply);
        Button divide = findViewById(R.id.btnDivide);

        add.setOnClickListener(v -> setOperator("+"));
        subtract.setOnClickListener(v -> setOperator("-"));
        multiply.setOnClickListener(v -> setOperator("*"));
        divide.setOnClickListener(v -> setOperator("/"));

        // Equals
        Button equals = findViewById(R.id.btnEquals);

        equals.setOnClickListener(v -> calculate());

        // Clear
        Button clear = findViewById(R.id.btnClear);

        clear.setOnClickListener(v -> {

            display.setText("0");
            firstNumber = 0;
            operator = "";
            newNumber = true;
        });

        // Backspace
        Button backspace = findViewById(R.id.btnBackspace);

        backspace.setOnClickListener(v -> {

            String text = display.getText().toString();

            if (text.equals("Error")) {

                display.setText("0");
                newNumber = true;

            } else if (text.length() > 1) {

                display.setText(
                        text.substring(0, text.length() - 1)
                );

            } else {

                display.setText("0");
                newNumber = true;
            }
        });
    }

    // Store first number and operator
    private void setOperator(String op) {

        String text = display.getText().toString();

        if (text.equals("Error")) {
            return;
        }

        firstNumber = Double.parseDouble(text);
        operator = op;
        newNumber = true;
    }

    // Calculate answer
    private void calculate() {

        if (operator.isEmpty()) {
            return;
        }

        String text = display.getText().toString();

        if (text.equals("Error") || text.isEmpty()) {
            return;
        }

        double secondNumber = Double.parseDouble(text);
        double result;

        switch (operator) {

            case "+":
                result = firstNumber + secondNumber;
                break;

            case "-":
                result = firstNumber - secondNumber;
                break;

            case "*":
                result = firstNumber * secondNumber;
                break;

            case "/":

                if (secondNumber == 0) {
                    display.setText("Error");
                    operator = "";
                    newNumber = true;
                    return;
                }

                result = firstNumber / secondNumber;
                break;

            default:
                return;
        }

        if (result == (long) result) {

            display.setText(
                    String.valueOf((long) result)
            );

        } else {

            display.setText(
                    String.valueOf(result)
            );
        }

        firstNumber = result;
        operator = "";
        newNumber = true;
    }
}
