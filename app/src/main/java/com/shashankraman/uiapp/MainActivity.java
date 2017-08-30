package com.shashankraman.uiapp;

import android.opengl.Visibility;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridLayout buttons;

    Button button;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;

    TextView textView;
    TextView sumTextView;
    TextView scoreTextView;
    TextView answerTextView;
    TextView timerTextView;

    ArrayList<Integer> answers;
    RelativeLayout gameLayout;
    int correctLocation;
    int score = 0;
    int numberOfQuestions;

    public void start(View view)
    {
        button.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(playAgainButton);
    }

    public void chooseAnswer(View view)
    {
        if(view.getTag().toString().equals("" + correctLocation))
        {
            score++;
            answerTextView.setText("Correct!");
        }
        else
        {
            answerTextView.setText("Incorrect!");
        }
        numberOfQuestions++;
        scoreTextView.setText(score + "/" + numberOfQuestions);
        generateQuestion();
    }

    public void generateQuestion()
    {
        int first = (int) (Math.random() * 50 + 1);
        int second = (int) (Math.random() * 50 + 1);
        int answer = first + second;

        sumTextView.setText(first + " + " + second);
        correctLocation = (int) (Math.random() * 4);

        answers = new ArrayList<Integer>();

        for(int i = 0; i < 4; i++)
        {
            if(i == correctLocation) answers.add(answer);
            else
            {
                int incorrect = (int) (Math.random() * 100 + 1);
                while(incorrect == answer)
                {
                    incorrect = (int) (Math.random() * 100 + 1);;
                }
                answers.add(incorrect);
            }
        }
        button0.setText("" + answers.get(0));
        button1.setText("" + answers.get(1));
        button2.setText("" + answers.get(2));
        button3.setText("" + answers.get(3));
    }

    public void playAgain(View view)
    {
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText("0/0");
        answerTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished)
            {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish()
            {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                answerTextView.setText("Game over. You got " + score + " out of " + numberOfQuestions);
            }
        }.start();
        generateQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        answerTextView = (TextView) findViewById(R.id.answerTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);

        button0 = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        gameLayout = (RelativeLayout) findViewById(R.id.gameLayout);
        buttons = (GridLayout) findViewById(R.id.gridLayout);
    }
}
