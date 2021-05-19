package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mText, sText;
    Button addBtn, pauseBtn;
    TextView timerView;

    int totalSec = 0;
    boolean countDownStopped = false;
    TimerAsyncTask tasync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = findViewById(R.id.mm);
        sText = findViewById(R.id.ss);
        addBtn = findViewById(R.id.addBtn);
        pauseBtn = findViewById(R.id.pauseBtn);
        timerView = findViewById(R.id.timerLbl);
        pauseBtn.setEnabled(false);
        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String s = mText.getText().toString();
        int min = 0, sec = 0;
        if(!s.isEmpty()) {
            min = Integer.valueOf(s);
        }
        s = sText.getText().toString();
        if(!s.isEmpty()) {
            sec = Integer.valueOf(s);
        }
        while(sec > 60 ) {
            sec -= 60;
            min++;
        }
        while(min > 0 ) {
            totalSec += 60;
            min--;
        }
        totalSec += sec;
        printTime(totalSec);
        startCountDown();
    }

    private void printTime(int time) {
        String value;
        int m = 0;
        while(time > 60) {
            m++;
            time -= 60;
        }
        if(m < 10) {
            value = "0"+m;
        } else {
            value = String.valueOf(m);
        }
        value += " : " + (time < 10 ? "0"+time : time);
        timerView.setText(
                value
        );
    }
    private void startCountDown() {
        tasync = new TimerAsyncTask();
        pauseBtn.setEnabled(true);
        tasync.execute(totalSec);
    }

    public void onPauseClick(View view) {
        if (countDownStopped) {
            startCountDown();
            if (totalSec > 0 ) {
                pauseBtn.setText(R.string.pause);
            }
        } else {
            stopCountDown();
            if (totalSec > 0 ) {
                pauseBtn.setText("RESUME");
            }
            totalSec--;
        }
        countDownStopped = !countDownStopped;
    }

    private void stopCountDown() {
        tasync.cancel(true);
        if(totalSec == 0) {
            timerView.setText(R.string.time_up);
            pauseBtn.setEnabled(false);
        }

    }

    class TimerAsyncTask extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(Integer... integers) {

            for(int i = integers[0]; i >= 0; i-- ) {
                countSec();
                publishProgress(i-1);
            }
            return  null;
        }

        private void countSec() {
            long currentTime = System.currentTimeMillis();
            while(System.currentTimeMillis() < currentTime + 1000) ;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int currentSec = values[0];
            totalSec--;
            Log.d("Hello", ""+currentSec+"   "+totalSec);

            if(currentSec <= 0) {
                Log.d("Hello", "stop "+totalSec);
                stopCountDown();
                return;
            }
            printTime(currentSec);
        }
    }
}