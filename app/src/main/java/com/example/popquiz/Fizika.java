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

public class Fizika extends AppCompatActivity {

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
        setContentView(R.layout.activity_fizika);

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
                Intent i = new Intent(Fizika.this, MainActivity.class);
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

            AlertDialog.Builder gameOverDialogBuilder = new AlertDialog.Builder(Fizika.this);
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

        Question question0 = new Question(R.drawable.img_fizika, "Koliku ima približnu težinu u svemiru tijelo od 50kg na zemlji ?", "500N", "500kg", "50N", "0N", 3);
        Question question1 = new Question(R.drawable.img_fizika, "Zakon poluge je otkrio koji znanstvenik ? ", "Nikola Tesla", "Isaac Newton", "Arhimed", "Thomas Edison", 2);
        Question question2 = new Question(R.drawable.img_fizika, "Jedinica za energiju nazvana je po kojem fizičaru ?", "Hawking", "Einstein", "Joule", "Newton", 2);
        Question question3 = new Question(R.drawable.img_fizika, "Parni stroj je usavršio i izumio ? ", "Celsius", "Watt", "Einsten", "Arhimed", 1);
        Question question4 = new Question(R.drawable.img_fizika, "Koja se pretvorba energije događa padanjem jabuke sa stabla ? ", "Svijetlosna u kemijsku", "Potencijalna u kinetičku", "Kinetička u potencijalnu", "Svjetlosna u kinetičku", 1);
        Question question5 = new Question(R.drawable.img_fizika, "Koja od sljedećih tvari provodi električnu struju ?", "Guma", "Slana voda","Destilirana voda","Željezo", 1);
        Question question6 = new Question(R.drawable.img_fizika, "Električni napon mjerima kojim uređajem? ", "Voltmetar", "Ampermetar", "Dinamometar", "Uređaj za buku", 0);
        Question question7 = new Question(R.drawable.img_fizika, "Koliko bi nam trebalo da pređemo put od 240km/h, vozeći 60km/h ?", "6h", "4h", "7h", "9h", 1);
        Question question8 = new Question(R.drawable.img_fizika, "Koliki put svijetlost prođe u 1 sekundi ?", "300m", "300 000 000km", "300 000km", "300 000m", 2);
        Question question9 = new Question(R.drawable.img_fizika, "Ubrzanje je derivacija kojih komponenti ?  ", "Brzine po putu", "Brzine po vremenu", "Vremenu po putu", "Putu po brzini", 1);
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