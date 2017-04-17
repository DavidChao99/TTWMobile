package com.example.david.twistthemobile;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by David on 4/07/2017.
 */

public class TimerActivity extends AppCompatActivity {
    private static final String TAG = "TimerActivity";
    private TextView mTextView;

    //Firebase Parameters
    private DatabaseReference mDatabase;


    //User Stopwatch Parameters
    final int MSG_START_TIMER = 0;
    final int MSG_STOP_TIMER = 1;
    final int MSG_UPDATE_TIMER = 2;
    final int REFRESH_RATE = 50;
    StopWatch timer = new StopWatch();
    boolean hasStarted = false;


    //using handler to interact with ui thread to update timer
    Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_START_TIMER:
                    timer.startTimer(); //start timer
                    mHandler.sendEmptyMessage(MSG_UPDATE_TIMER);
                    break;

                case MSG_UPDATE_TIMER:
                    mTextView.setText(""+ timer.getElapsedTime());
                    mHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIMER,REFRESH_RATE); //text view is updated every second,
                    break;                                  //though the timer is still running
                case MSG_STOP_TIMER:
                    mHandler.removeMessages(MSG_UPDATE_TIMER); // no more updates.
                    timer.stopTimer();//stop timer
                    mTextView.setText(""+ timer.getElapsedTime());
                    mDatabase.child("times").child("one").child("David").setValue(timer.getElapsedTime());
                    break;

                default:
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference();

        mTextView = (TextView) findViewById(R.id.TimerText);

        LinearLayout layout = (LinearLayout) findViewById(R.id.rootlayout);
        layout.setOnClickListener(mStartListener);



    }

    View.OnClickListener mStartListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(hasStarted) {
                mHandler.sendEmptyMessage(MSG_STOP_TIMER);
                hasStarted = false;
            }
            else {
                mHandler.sendEmptyMessage(MSG_START_TIMER);
                mDatabase.child("times").child("one").child("David").setValue("Start");
                hasStarted = true;
            }
        }
    };

//    View.OnClickListener mStopListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            mHandler.sendEmptyMessage(MSG_STOP_TIMER);
//        }
//    };




}
