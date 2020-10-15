package com.example.popquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Matematika extends AppCompatActivity {
    int currentQuestionIndex;
    int totalCorrect;
    int totalQuestions;
    ArrayList<Question> questions;

       ImageView questionImageView;
       TextView questionTextView;
       TextView questionsRemainingTextView;
       Button answer0button, answer1button, answer2button, answer3button, submitButton, backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matematika);

         questionImageView = findViewById(R.id.iv_main_question_image);
         questionTextView = findViewById(R.id.tv_main_question_title);
         questionsRemainingTextView = findViewById(R.id.tv_main_questions_remaining_count);
         answer0button = findViewById(R.id.btn_main_answer_0);
         answer1button = findViewById(R.id.btn_main_answer_1);
         answer2button = findViewById(R.id.btn_main_answer_2);
         answer3button = findViewById(R.id.btn_main_answer_3);
         submitButton = findViewById(R.id.btn_main_submit_answer);
         backButton = findViewById(R.id.backButton_id);

         answer0button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 onAnswerSelected(0);
             }
         });
         answer1button.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v) {
                 onAnswerSelected(1);
             }
         });
         answer2button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 onAnswerSelected(2);
             }
         });
         answer3button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 onAnswerSelected(3);
             }
         });
         submitButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 onAnswerSubmission();
             }
         });
         startNewGame();
         backButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i = new Intent(Matematika.this, MainActivity.class);
                 startActivity(i);
             }
         });
    }
    public void displayQuestion(Question question) {
         questionImageView.setImageResource(question.imageId);
         questionTextView.setText(question.questionText);
         answer0button.setText(question.answer0);
         answer1button.setText(question.answer1);
         answer2button.setText(question.answer2);
         answer3button.setText(question.answer3);
    }
    public void displayQuestionsRemaining(int questionsRemaining) {
        questionsRemainingTextView.setText(String.valueOf(questionsRemaining));
    }
    public void onAnswerSelected(int answerSelected) {
        Question currentQuestion = getCurrentQuestion();
        currentQuestion.playerAnswer = answerSelected;
        answer0button.setText(currentQuestion.answer0);
        answer1button.setText(currentQuestion.answer1);
        answer2button.setText(currentQuestion.answer2);
        answer3button.setText(currentQuestion.answer3);


          switch (answerSelected) {
              case 0:
                answer0button.setText("✔ " + currentQuestion.answer0);
                break;
              case 1:
                answer1button.setText("✔ " + currentQuestion.answer1);
                 break;
              case 2:
                answer2button.setText("✔ " + currentQuestion.answer2);
                break;
              case 3:
                answer3button.setText("✔ " + currentQuestion.answer3);
          }
    }
    public void onAnswerSubmission() {
        Question currentQuestion = getCurrentQuestion();
            if (currentQuestion.isCorrect()) {
                totalCorrect = totalCorrect + 1;
            }
                if (currentQuestion.playerAnswer == -1) {
                    return;
                }
                questions.remove(currentQuestion);
                displayQuestionsRemaining(questions.size());

           if (questions.size() == 0) {
               String gameOverMessage = getGameOverMessage(totalCorrect, totalQuestions);

               AlertDialog.Builder gameOverDialogBuilder = new AlertDialog.Builder(Matematika.this);
               gameOverDialogBuilder.setCancelable(false);
               gameOverDialogBuilder.setTitle("Kviz završen");
               gameOverDialogBuilder.setMessage(gameOverMessage);
               gameOverDialogBuilder.setPositiveButton("Igrati ponovno!", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       startNewGame();
                   }
               }).setNegativeButton("Izađi", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       finish();
                   }
               });
               gameOverDialogBuilder.create().show();
           } else {
               chooseNewQuestion();
               displayQuestion(getCurrentQuestion());
           }
    }
    public void startNewGame() {
              questions = new ArrayList<>();

         Question question0 = new Question(R.drawable.img_mat_que1, "Ako pomožite prvi broj 17 sa drugim brojem 5, koliki je rezultat ?", "32", "85", "49", "92", 1);
         Question question1 = new Question(R.drawable.img_mat_que1, "U brojniku stoji broj 27, a u nazivniku 3, koliki je konačan rezultat ?", "5", "3", "9", "0.3", 2);
         Question question2 = new Question(R.drawable.img_mat_que1, "Postoji šest geometrijskih likova: trokut, pravokutnik, kvadrat, krug, šesterokut i ?", "Kvadar", "Kocka", "Piramida", "Romb", 3);
         Question question3 = new Question(R.drawable.img_mat_que1, "Pitagorin poučka je površina kvadrata nad hipotenuzom pravokutnog trokuta koje je jednaka zbroju čega ?", "površina kvadrata nad katetama", "površinom kateta nad kvadratom", "zbroju kateta", "Kvadrat kateta", 0);
         Question question4 = new Question(R.drawable.img_mat_que1, "Ako devet desetina pomnožimo sa broj tri, koji rezultat dobijemo? ", "27/10", "27/30", "15/10", "60", 0);
         Question question5 = new Question(R.drawable.img_mat_que1, "Kako se zove pravac koja dodiruje kružnicu u samo jednoj točki ?", "Normala", "Tangenta", "Cosinus", "Pravi kut", 1);
         Question question6 = new Question(R.drawable.img_mat_que1, "Polupravci koji omeđuju kut još se nazivaju kako ?", "Pravi kut", "Šiljaste linije", "Krakovi kuta", "Paraelni pravic", 2);
         Question question7 = new Question(R.drawable.img_mat_que1, "Trigonometrija funkcija koja se za neki kut definira kao odnos duljina hipotenuze i pripadajuće katete nad njime konstruiranim pravokutnim kutem. Čija je ovo definicija ?", "Tangest", "Kvadrat", "Arcus tanges", "Kosinus", 3);
         Question question8 = new Question(R.drawable.img_mat_que1, "Kojim slovom označujemo skup prirodnih bojeva ?", "Q", "Z", "N", "R", 2);
         Question question9 = new Question(R.drawable.img_mat_que1, "Koliko je 20% od broja 550 ?", "100", "125", "110", "150", 2);
         questions.add(question0);
          questions.add(question1);
           questions.add(question2);
            questions.add(question3);
             questions.add(question4);
              questions.add(question5);
               questions.add(question6);
                questions.add(question7);
                 questions.add(question8);
                  questions.add(question9);

         totalCorrect = 0;
         totalQuestions = questions.size();

         while(questions.size() > 11) {
             int randomIndex = generateRandomNumber(questions.size());
             questions.remove(randomIndex);
         }
         Question firstQuestion = chooseNewQuestion();
         displayQuestionsRemaining(questions.size());
         displayQuestion(firstQuestion);
    }
    Question chooseNewQuestion() {
        int newQuestionIndex = generateRandomNumber(questions.size());
        currentQuestionIndex = newQuestionIndex;
        return questions.get(currentQuestionIndex);
    }
    int generateRandomNumber(int max) {
        double randomNumber = Math.random();
        double result = max * randomNumber;
        return (int) result;
    }
    Question getCurrentQuestion() {

             Question currentQuestion = questions.get(currentQuestionIndex);
             return currentQuestion;
    }
    String getGameOverMessage(int totalCorrect, int totalQuestions) {
        if (totalCorrect == totalQuestions) {
            return "Bravo! Točno si riješio sva pitanja";
        }  else {
            return "Točno si riješio " + totalCorrect + " pitanja od " + totalQuestions;

        }
    }


}