package com.example.geoquiz;

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    private int mTextAnsId;
    public Question(int textResId, boolean answerTrue, int textAnsId) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        mTextAnsId = textAnsId;
    }
    public int getTextResId() {
        return mTextResId;
    }
    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }
    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }
    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public int getTextAnsId() {
        return mTextAnsId;
    }
    public void setTextAnsId(int textAnsId) {
        mTextAnsId = textAnsId;
    }

}
