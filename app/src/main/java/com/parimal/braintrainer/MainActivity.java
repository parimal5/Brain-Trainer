package com.parimal.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout gameLayout;
    Button goButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView sumTextView;
    TextView resultTextView;
    TextView scoreTextView;
    TextView timerTextView;
    ArrayList<Integer> answer = new ArrayList<>();
    int locationOfCorrectAnswer;
    int score;
    int numberOfQuestion;

    public void startGoButton(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playGame(findViewById(R.id.resultTextView));// We put any view here doesn't matter we just have to call

    }

    public void answerOption(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("Correct !");
            score++;
        } else {
            resultTextView.setText("Wrong :(");
        }
        numberOfQuestion++;
        scoreTextView.setText(score + "/" + numberOfQuestion);
        newQuestion();
    }

    public void playGame(View view) {
        score = 0;
        numberOfQuestion = 0;
        scoreTextView.setText(score + "/" + numberOfQuestion);
        playAgainButton.setVisibility(View.INVISIBLE);
        newQuestion();
        timerTextView.setText("30s");
        resultTextView.setText("");
        CountDownTimer countDownTimer = new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
                button0.setEnabled(true);
                button1.setEnabled(true);
                button2.setEnabled(true);
                button3.setEnabled(true);
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Time's Up!!");
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();
    }

    public void newQuestion() {
        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumTextView.setText(a + " + " + b);

        locationOfCorrectAnswer = random.nextInt(4);
        answer.clear();

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer)
                answer.add(a + b);
            else {
                int wrongAnswer = random.nextInt(41);
                while (a + b == wrongAnswer)
                    wrongAnswer = random.nextInt(41);
                answer.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton = findViewById(R.id.playAgainButton);
        sumTextView = findViewById(R.id.sumTextiew);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        gameLayout = findViewById(R.id.gameLayout);

        gameLayout.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.VISIBLE);
    }
}