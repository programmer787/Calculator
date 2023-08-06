package com.example.mycalcu; // Zastąp "yourappname" swoją nazwą pakietu aplikacji

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private StringBuilder input = new StringBuilder();
    private double num1 = 0;
    private double num2 = 0;
    private char operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.textViewResult);

        Button buttonClear = findViewById(R.id.buttonClear);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonSubtract = findViewById(R.id.buttonSubtract);
        Button buttonEquals = findViewById(R.id.buttonEquals);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button button0 = findViewById(R.id.button0); // Dodany przycisk z cyfrą 0

        // Obsługa kliknięcia przycisków cyfr
        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                input.append(button.getText().toString());
                updateResultText();
            }
        };

        button0.setOnClickListener(numberClickListener); // Dodanie obsługi cyfry 0
        button1.setOnClickListener(numberClickListener);
        button2.setOnClickListener(numberClickListener);
        button3.setOnClickListener(numberClickListener);
        button4.setOnClickListener(numberClickListener);
        button5.setOnClickListener(numberClickListener);
        button6.setOnClickListener(numberClickListener);
        button7.setOnClickListener(numberClickListener);
        button8.setOnClickListener(numberClickListener);
        button9.setOnClickListener(numberClickListener);

        // Obsługa kliknięcia przycisków operacji matematycznych
        View.OnClickListener operatorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.length() > 0) {
                    num1 = Double.parseDouble(input.toString());
                    input.setLength(0);
                }
                operator = ((Button) v).getText().charAt(0);
            }
        };

        buttonAdd.setOnClickListener(operatorClickListener);
        buttonSubtract.setOnClickListener(operatorClickListener);
        buttonMultiply.setOnClickListener(operatorClickListener); // Poprawiona obsługa mnożenia
        buttonDivide.setOnClickListener(operatorClickListener);

        // Obsługa kliknięcia przycisku "="
        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.length() > 0) {
                    num2 = Double.parseDouble(input.toString());
                    input.setLength(0);
                }
                double result = calculateResult(num1, num2, operator);
                input.append(result);
                updateResultText();
            }
        });

        // Obsługa kliknięcia przycisku "C" (czyści wprowadzone dane)
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setLength(0);
                updateResultText();
            }
        });
    }

    // Funkcja oblicza wynik na podstawie dwóch liczb i operatora
    private double calculateResult(double num1, double num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    return Double.NaN; // Dzielenie przez zero zwraca wartość NaN (Not a Number)
                }
            default:
                return 0;
        }
    }

    // Aktualizuje wyświetlany tekst wyniku
    private void updateResultText() {
        textViewResult.setText(input.toString());
    }
}