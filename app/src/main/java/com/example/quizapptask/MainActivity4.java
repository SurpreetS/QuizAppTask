
/*
            Name        :  Surpreet Singh
            Student ID  :  218663803
            Unit No.    :  SIT305

 */

package com.example.quizapptask;


//importing libraries
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {

    //  Defining variables for the widgets
    TextView updateName;
    TextView score;
    Button takeNewQuiz;
    Button finish;

    //Override function for the onCreate method and its called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4); //setting the layout from the activity_main4 file

        //Referencing to the XML file UI elements
        updateName = findViewById(R.id.textView27);
        score = findViewById(R.id.textView30);
        takeNewQuiz = findViewById(R.id.button7);
        finish = findViewById(R.id.button8);


        //Retrieving the Intent object from the MainActivity2
        Intent valueFromIntent = getIntent();
        String value = valueFromIntent.getStringExtra("personName"); //Extracting the value of the username
        int scoreValue = valueFromIntent.getIntExtra("scoreData",0);  //Extracting the Score value
        updateName.setText("Congratulations " + value + " !"); //updating the text field with congratulation statement
        score.setText( scoreValue + "/3");  //updating the score

        //OnClickListener for the takeNewQuiz button
        takeNewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent (MainActivity4.this, MainActivity.class); //creating an intent object for starting the next activity
                myIntent.putExtra("personName", value); //adding value to the intent object so that can be used in the next activity
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); //clearing all the other activities
                startActivity(myIntent); //starting the new activity
                finish(); //clearing the current activity
            }
        });

        //OnClickListener for the Finish button
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finishAffinity(); // Close all activities
                System.exit(0); //Exit the app
            }
        });

    }
}