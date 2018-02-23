package com.madrimas.guessthenumber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int anIntToGuess;
    private int numberOfTries;
    public static final String EXTRA_MESSAGE = "com.madrimas.guessthenumber.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateRandomInt();
    }

    private void generateRandomInt() {
        Random random = new Random();
        anIntToGuess = random.nextInt(101);
        System.out.println("Wylosowana liczba to: " + anIntToGuess);
        numberOfTries = 1;
    }

    public void checkAnswer(View view){
        EditText editText = (EditText) findViewById(R.id.editText);
        int answer = Integer.parseInt(editText.getText().toString());

        TextView answerCheck = (TextView) findViewById(R.id.textView);

        if(answer == anIntToGuess){
            handleWin(answerCheck);
        }
        else if(answer > anIntToGuess){
            answerCheck.setText("Za dużo! Próbuj dalej!");
            numberOfTries++;
        }
        else{
            answerCheck.setText("Za mało! Próbuj dalej!");
            numberOfTries++;
        }
    }

    private void handleWin(TextView answerCheck) {
        answerCheck.setText("");
        Intent intent = new Intent(this, EndGame.class);
        intent.putExtra(EXTRA_MESSAGE, Integer.toString(numberOfTries));
        startActivity(intent);
        generateRandomInt();
    }
}
