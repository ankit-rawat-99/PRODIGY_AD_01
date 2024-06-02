package com.example.prodigy_ad_01;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView resultTV, solutionTV;
    MaterialButton buttonC, buttonBrackOpen, buttonBrackClose;
    MaterialButton buttonDivide, buttonMultiple, buttonPlus, buttonMinus, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTV = findViewById(R.id.result_tv);
        solutionTV = findViewById(R.id.solution_tv);

        assignID(buttonC, R.id.button_C);
        assignID(buttonBrackOpen, R.id.button_open_bracket);
        assignID(buttonBrackClose, R.id.button_close_bracket);
        assignID(buttonDivide , R.id.button_division);
        assignID(buttonMultiple, R.id.button_multiplication);
        assignID(buttonPlus, R.id.button_addition);
        assignID(buttonMinus, R.id.button_subtraction);
        assignID(buttonEquals, R.id.button_equal);
        assignID(button0, R.id.button_0);
        assignID(button1, R.id.button_1);
        assignID(button2, R.id.button_2);
        assignID(button3, R.id.button_3);
        assignID(button4, R.id.button_4);
        assignID(button5, R.id.button_5);
        assignID(button6, R.id.button_6);
        assignID(button7, R.id.button_7);
        assignID(button8, R.id.button_8);
        assignID(button9, R.id.button_9);
        assignID(buttonAC, R.id.button_AC);
        assignID(buttonDot, R.id.button_dot);
    }

    void assignID(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTV.getText().toString();

        if (buttonText.equals("AC")) {
            solutionTV.setText("");
            resultTV.setText("0");
            return;
        }

        if (buttonText.equals("=")) {
            solutionTV.setText(resultTV.getText());
            return;
        }

        if (buttonText.equals("C")) {
            if (!dataToCalculate.isEmpty()) {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }
        } else {
            if (dataToCalculate.equals("0")) {
                dataToCalculate = buttonText;
            } else {
                dataToCalculate += buttonText;
            }
        }

        solutionTV.setText(dataToCalculate);
        String finallyResult = getResult(dataToCalculate);

        if (!finallyResult.equals("Error")) {
            resultTV.setText(finallyResult);
        }
    }

    String getResult(String data) {
        try {
            Context context = org.mozilla.javascript.Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finallyResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            return finallyResult;
        } catch (Exception e) {
            return "Error";
        }
    }
}
