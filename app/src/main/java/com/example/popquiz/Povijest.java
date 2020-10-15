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

public class Povijest extends AppCompatActivity {

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
        setContentView(R.layout.activity_povijest);

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
                Intent i = new Intent(Povijest.this, MainActivity.class);
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

            AlertDialog.Builder gameOverDialogBuilder = new AlertDialog.Builder(Povijest.this);
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

        Question question0 = new Question(R.drawable.img_povijest, "Koje godine je osnovano Rimsko Carstvo ?", "27. pr. Kr.", "150 pr. Kr.", "15", "102", 0);
        Question question1 = new Question(R.drawable.img_povijest, "Kako se zove prvi rimski car? ", "Aurelije", "Tiberije", "August", "Oton", 2);
        Question question2 = new Question(R.drawable.img_povijest, "Koje godine je započeo 1. Svjetski rat ?", "1919. godine", "1915. godine", "1920- godine", "1918. godine", 3);
        Question question3 = new Question(R.drawable.img_povijest, "Napad na koju državu se označavao kao početak 2. Svjetskog rata? ", "Veliku Britaniju", "Njemačku", "Poljsku", "Španjolsku", 2);
        Question question4 = new Question(R.drawable.img_povijest, "Kada je započeo Domovinski rat? ", "1990. godine", "1991. godine", "1989. godine", "1995. godine", 1);
        Question question5 = new Question(R.drawable.img_povijest, "Koje godine je pao Berlinski zid?", "1991. godine", "1975. godine ","1960. godine ","1668. godine", 0);
        Question question6 = new Question(R.drawable.img_povijest, "Kako se zvao prvi Hrvatski Kralj ?", "Kralj Zvonimir", "Kralj Luka", "Kralj Tomislav", "Kralj Ivan", 2);
        Question question7 = new Question(R.drawable.img_povijest, "Kristofor Kolumbo je 1942. godine otkrio koju zemlju?", "Indiju", "Ameriku", "Aziju", "Skandinaviju", 1);
        Question question8 = new Question(R.drawable.img_povijest, "Koje godine se prekida veliko Kinesko Carstvo koje je započelo davne 221. godine pr. Kr. kada je svrgnuta dinastija Qing?", "650. godine", "1453. godine", "1780. godine", "1912. godine", 3);
        Question question9 = new Question(R.drawable.img_povijest, "Kako se zove prvi predsjednik Sjedinjenih Američkih Država?", "John Adams", "James Madison", "George Washington", "Barack Obama", 2);
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