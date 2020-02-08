package com.treasurehunt;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class RoundProgressActivity extends AppCompatActivity {


    ImageButton scanBtn,hintBtn, backButton;
    TextView question_textView,hint;
    Button countDown;
    String barcode;
    List<Question> questions;
    static int idx;
    int score;
    long absoluteSeconds;

    LinearLayout hintLayout,mainLayout;


    CountDownTimer timer;
    void countdownTimer(int countdownMins,int countdownSecs) {
        long min = countdownMins * 60000;
        long sec = countdownSecs * 1000;
        long milliseconds = min+sec;
        timer = null;
        timer = new CountDownTimer(milliseconds, 1000) {

            public void onTick(long millisUntilFinished) {

                long mins = millisUntilFinished / 60000;
                long secs = millisUntilFinished % 60000 / 1000;
                absoluteSeconds=(mins*60)+secs;
                String display = String.format("%02d:%02d", mins, secs);
                countDown.setText(display);
            }
            public void onFinish() {
                //countdownTimer();
                startActivity(new Intent(RoundProgressActivity.this,RoundEndActivity.class));
            }
        }.start();
    }
    boolean mCurrentTime = DateUtils.isToday();

    public static final String DATE_FORMAT_1 = "hh:mm a";

    public static String getcurrenttime()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_1);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date today = Calendar.getInstance().getTime();
        return dateFormat.format(today);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        scanBtn = findViewById(R.id.scan_btn);
        hintBtn = findViewById(R.id.hint_btn);
        question_textView = findViewById(R.id.question_tv);
        countDown = findViewById(R.id.count_down_btn);
        hintLayout = findViewById(R.id.hint_layout);
//        questions = getQuestionsList();
        hint = findViewById(R.id.hint);
        countDown.bringToFront();
        backButton = findViewById(R.id.back_button);
        //ScanActivity.getBarcodeResult();
        int min=0,sec=10;//TODO logic for this.
        countdownTimer(min,sec);
        hintLayout.setVisibility(View.GONE);

    }

    public void onScanClick(View view){
        startActivity(new Intent(RoundProgressActivity.this,ScanActivity.class));
        this.barcode = ScanActivity.getBarcodeResult();
        //while(!timeUp()){
        if(this.barcode.equals(questions.get(idx).getBarcode()) ){
            increaseScore();
            displayNextQuestion(questions.get(idx));
        }


    }

    public boolean timeUp(){
        return this.absoluteSeconds <= 0;
    }


    void displayNextQuestion(Question nextQuestion){
        question_textView.setText(nextQuestion.getQuestion());
    }

    void getNextQuestion(){
     //   Table table = new Table(getApplicationContext());

    }

    List<Question> getQuestionsList(){
        Table table = new Table(getApplicationContext());
        List<Question> final_question_set= new ArrayList<>();
        List<Question> allQuestions= table.getQuestions();

        //Implement some algo for question selection

        return final_question_set;
    }

    void increaseScore(){
        //has time and score
    }

    public void onClickHint(View view){
        hint.setText(questions.get(idx).getHint());
        hintLayout.setVisibility(View.VISIBLE);
    }

    public void backImage(View view){
        hintLayout.setVisibility(View.GONE);
        mainLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        //TODO fix this!
        super.onBackPressed();
    }
}
