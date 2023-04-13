
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
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    //  Defining variables for the widgets
    EditText name;
    Button startQuizButton;
    TextView textViewUpdate;

    //Override function for the onCreate method and its called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  //setting the layout from the activity_main file

        //Referencing to the XML file UI elements
        name= findViewById(R.id.editTextTextPersonName);
        startQuizButton = findViewById(R.id.button);
        textViewUpdate = findViewById(R.id.textView);

        //Retrieving the Intent object from the final score activity if the user wants to take another quiz
        Intent valueFromIntent = getIntent();
        String valueFromSecondActivity = valueFromIntent.getStringExtra("personName"); //Extracting the value of the username to welcome back him

        //checking if the value from another activity is not null
        if(valueFromSecondActivity != null){
            name.setText(valueFromSecondActivity); // Auto filling the username
            textViewUpdate.setText("Welcome Back"); // updating the text ot welcome back
        }


        //onClickListener for the startQuizButton
        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String personName = name.getText().toString(); // getting the person name which user has put in
                Intent myIntent = new Intent (MainActivity.this, MainActivity2.class); //creating an intent object for starting the next activity
                myIntent.putExtra("personName", personName); //adding value to the intent object so that can be used in the next activity
                startActivity(myIntent); //starting the next activity
            }
        });
    }




}