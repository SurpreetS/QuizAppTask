package com.example.quizapptask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent valueFromIntent = getIntent();
        String selectedAnswerButton = valueFromIntent.getStringExtra("selectedAnswerId");

        checkAnswer(selectedAnswerButton);
    }

    public void checkAnswer(String selectedAnswerButton) {
        // Get the selected answer from radio buttons

        RadioButton radioButton = findViewById(Integer.parseInt(selectedAnswerButton) );
        String selectedAnswer = radioButton.getText().toString();

        // Compare selected answer with the correct answer
        String correctAnswer = "4";
        boolean isCorrect = selectedAnswer.equals(correctAnswer);

        // Change the color of the answer based on whether it's correct or not

        if (isCorrect) {
             radioButton.setBackgroundColor(Color.GREEN);
        } else {
            radioButton.setBackgroundColor(Color.RED);

        }
    }
}