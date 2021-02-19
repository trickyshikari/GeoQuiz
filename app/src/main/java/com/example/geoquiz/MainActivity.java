package com.example.geoquiz;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mTextQuiz;
    private TextView mTextPoints;
    private static final String TAG="QuizActivity";
    private static final String KEY_INDEX = "index";
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_1, false, R.string.answer_1),
            new Question(R.string.question_2, true, R.string.answer_2),
            new Question(R.string.question_3, false, R.string.answer_3),
            new Question(R.string.question_4, false, R.string.answer_4),
            new Question(R.string.question_5, false, R.string.answer_5),
            new Question(R.string.question_6, true, R.string.answer_6),
            new Question(R.string.question_7, true, R.string.answer_7),
            new Question(R.string.question_8, true, R.string.answer_8)
    };
    private Question[] mCurrentQuestionBank = new Question[4];
    private int mCurrentIndex = 0;
    private int mCurrentPoints = 0;
    private int mCurrentLength = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextQuiz = (TextView)findViewById(R.id.TextQuiz);
        generateQuestionBank();
        updateQuestion();
        addListenerOnClickButtonTrueFalse();
    }

    public void generateQuestionBank(){
        int arr[] = new int[8];
        for (int i = 0; i < 8; i++){
            arr[i] = i;
        }

        shuffleArray(arr);

        for(int i = 0; i < 4; i++){
            mCurrentQuestionBank[i] = mQuestionBank[arr[i]];
        }
    }

    public static void shuffleArray(int[] a) {
        int n = a.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(a, i, change);
        }
    }

    private static void swap(int[] a, int i, int change) {
        int temp = a[i];
        a[i] = a[change];
        a[change] = temp;
    }

    public void addListenerOnClickButtonTrueFalse(){
        mTrueButton = (Button)findViewById(R.id.buttonTrue);
        mFalseButton = (Button)findViewById(R.id.buttonFalse);
        mNextButton = (ImageButton)findViewById(R.id.buttonNext);
        mPrevButton = (ImageButton)findViewById(R.id.buttonPrev);
        mTextQuiz = (TextView)findViewById(R.id.TextQuiz);
        mTextPoints = (TextView)findViewById(R.id.TextPoints);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mCurrentLength;
                updateQuestion();
            }
        });
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex - 1) % mCurrentLength;
                if(mCurrentIndex<0)mCurrentIndex=mCurrentLength-1;
                updateQuestion();
            }
        });
    }
    private void updateQuestion(){
        int question = mCurrentQuestionBank[mCurrentIndex].getTextResId();
        mTextQuiz.setText(question);
    }

    private void updatePoints(){
        mTextPoints.setText("" + mCurrentPoints);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mCurrentQuestionBank[mCurrentIndex].isAnswerTrue();
        int answerId = mCurrentQuestionBank[mCurrentIndex].getTextAnsId();
        int color;if(userPressedTrue == answerIsTrue){
            mCurrentPoints++;
            updatePoints();
            color = Color.argb(255, 50, 205, 50);
        }else{
            color = Color.argb(255, 139, 0, 0);
        }


        for(int i = mCurrentIndex; i < mCurrentLength-1; i++){
            mCurrentQuestionBank[i] = mCurrentQuestionBank[i+1];
        }
        mCurrentLength--;

        Toast toast = Toast.makeText(this,answerId,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,0);
        View view = toast.getView();
        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(color);
        toast.show();

        if(mCurrentLength == 0){
            Toast.makeText(this,"Игра окончена\nОчки: " + mCurrentPoints,Toast.LENGTH_SHORT).show();
        }
    }
}