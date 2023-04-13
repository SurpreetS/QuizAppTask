
/*
            Name        :  Surpreet Singh
            Student ID  :  218663803
            Unit No.    :  SIT305

 */



package com.example.quizapptask;

//importing libraries

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    //Declaring variables
    Button submitButton;
    EditText question;
    TextView name, questionTracker;
    int questionCounter=0;
    int scoreCounter=0;
    String[] answersList;

    ProgressBar progressBar ;

    //Override function for the onCreate method and its called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2); //setting the layout from the activity_main2 file

        //Referencing to the XML file UI elements
        question = findViewById(R.id.editTextTextMultiLine);
        submitButton = findViewById(R.id.button2);
        name = findViewById(R.id.textView6);
        questionTracker =findViewById(R.id.textView2);
        progressBar = findViewById(R.id.progressBar4);
        progressBar.setMax(3);
        progressBar.setProgress(questionCounter+1);


        //Retrieving the Intent object from the main activity
        Intent valueFromIntent = getIntent();
        String value = valueFromIntent.getStringExtra("personName");
        name.setText("Welcome " + value + " !");
        questionTracker.setText((questionCounter+1) + "/3");

        //Calling the quizQuestion method to display the first quiz question
        quizQuestion();


        //onClickListener for the submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String buttonText = submitButton.getText().toString();  //getting the text which is written on the button

                //if the button text is submit
                if( buttonText.equals("Submit")){
                    questionCounter++; // incrementing the question counter

                    //checking if the question is the last question of the quiz
                    if (questionCounter ==3 ) {
                        checkAnswer(v); //calling the checkAnswer method to check the answer
                        submitButton.setText("Finish");  // changing the button text to finish
                        //if the user press the finish button then it will lead to another activity to display score
                        submitButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent newIntent = new Intent (MainActivity2.this, MainActivity4.class); //creating new intent object
                                //adding values to intent object so that can be used in the next activity
                                newIntent.putExtra("personName", value);
                                newIntent.putExtra("scoreData", scoreCounter);

                                startActivity(newIntent); // starting the next activity

                            }
                        });
                    //else if the question is not the last question
                    }else {
                        //then change the button text to next
                        submitButton.setText("Next");
                        //calling the checkAnswer method to check the answer
                        checkAnswer(v);

                    }


                }
                //else if the button text is next
                if( buttonText.equals("Next")){
                    questionTracker.setText((questionCounter+1) + "/3"); // setting the question tracker to display the current question number to the user
                    submitButton.setText("Submit"); //changing the button text back to submit for the next question
                    quizQuestion(); //call quizQuestion to get the next question
                    progressBar.setProgress(questionCounter+1); //setting the progressbar according to the question number user is answering

                }



            }
        });




    }




    //quizQuestion to get the question for the quiz
    public void quizQuestion(){

        String[] quizQuestions = {"What is 2+2?", "What is 3*2?", "What is 9-3?"}; //string array of questions
        String[][] options = {{"5", "4", "6"},{"7", "4", "6"},{"6", "4", "2"}}; //2D array of the answers of each question
        answersList = new String[]{"4", "6", "6"}; //answer lest for the questions

        //Updating the question with the new question
        question.setText(quizQuestions[questionCounter]);
        RadioGroup answerRadioGroup = findViewById(R.id.RadioGroup); // linking the radioGroup object to the XML file radiogroup
        answerRadioGroup.removeAllViews(); //removing the radioButtons from the radioGroup of the previous question
            for(int j = 0; j < 3; j++){

                RadioButton radioButton = new RadioButton(this); //creating new radiobutton for the answers of new question
                radioButton.setId(j); // Setting the id of the radiobutton
                radioButton.setText(options[questionCounter][j]); //setting the answer to the radiobutton
                answerRadioGroup.addView(radioButton); //adding the radiobutton to the radioGroup

            }



    }

    //method to check the answer selected by the user
    public void checkAnswer(View view) {
        // initialising the radioGroup variable with the radioGroup from the XML layout
        RadioGroup radioGroup = findViewById(R.id.RadioGroup);
        //Declaring variables
        RadioButton correctRadioButton;
        int correctAnswerButtonId ;

        // for loop for looping in the radioGroup
        for(int i =0; i < radioGroup.getChildCount(); i++){

            correctRadioButton = (RadioButton) radioGroup.getChildAt(i);//getting the current radio button from the radioGroup

            String answer = correctRadioButton.getText().toString(); //getting the text of the current radioButton
            //if condition to match the if the current radio button has the correct answer
            if(answer == answersList[questionCounter-1]){

                correctAnswerButtonId = correctRadioButton.getId(); //if the radioButton has the correct answer then getting its ID
                correctRadioButton = findViewById(correctAnswerButtonId); //linking to radioButton to the XML file radioButton


                int selectedAnswerButton = radioGroup.getCheckedRadioButtonId(); //Getting the id of the radiButton selected by the user
                RadioButton radioButton = findViewById(selectedAnswerButton); //linking to radioButton to the XML file radioButton

                //if condition to check the selected radiobutton is same as the Correct radio button
                if(correctAnswerButtonId == selectedAnswerButton){

                    scoreCounter++; //incrementing the score if the answer is correct
                    radioButton.setBackgroundColor(Color.GREEN); //changing the color of the selected radiobutton to green

                //if the answer is incorrect
                }else{
                    radioButton.setBackgroundColor(Color.RED); //changing the color of the selected radiobutton to red
                    correctRadioButton.setBackgroundColor(Color.GREEN); //changing the color of the correct radiobutton to green
                }

            }


        }


    }
}