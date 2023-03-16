package com.mhmmdyzci.simplecalculator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText numberOne;
    EditText numberTwo;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         numberOne = findViewById(R.id.numberOne);
         numberTwo = findViewById(R.id.numberTwo);
         resultText = findViewById(R.id.resultText);

    }
    public void sum(View view){
        if(numberOne.getText().toString().matches("") || numberTwo.getText().toString().matches(" ")){
            resultText.setText("Enter number!");
        } else {
            int number1 = Integer.parseInt(numberOne.getText().toString());
            int number2 = Integer.parseInt(numberTwo.getText().toString());
            int result = number1 + number2 ;
            resultText.setText("Result: " + result );

        }


    }
    public void deduct(View view){
        if(numberOne.getText().toString().matches("") || numberTwo.getText().toString().matches(" ")){
            resultText.setText("Enter number!");
        } else {
            int number1 = Integer.parseInt(numberOne.getText().toString());
            int number2 = Integer.parseInt(numberTwo.getText().toString());
            int result = number1 - number2 ;
            resultText.setText("Result: " + result );

        };

    }
    public void multiply(View view){
        if(numberOne.getText().toString().matches("") || numberTwo.getText().toString().matches(" ")){
            resultText.setText("Enter number!");
        } else {
            int number1 = Integer.parseInt(numberOne.getText().toString());
            int number2 = Integer.parseInt(numberTwo.getText().toString());
            int result = number1 * number2 ;
            resultText.setText("Result: " + result );

        };

    }
    public void divide(View view){
        if(numberOne.getText().toString().matches("") || numberTwo.getText().toString().matches(" ")){
            resultText.setText("Enter number!");
        } else {
            int number1 = Integer.parseInt(numberOne.getText().toString());
            int number2 = Integer.parseInt(numberTwo.getText().toString());
            int result = number1 / number2 ;
            resultText.setText("Result: " + result );

        };

    }
}