package com.pizzatimer.pizzatimer;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView timer;

    private String numberToClock (long value){
        if (value < 10) return "0"+value;
        else return ""+value;
    }

    long lasttime=0;

    boolean timerstarted=true;

    Button custom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button thirtymin= (Button) findViewById(R.id.thirtymin);
        custom= (Button) findViewById(R.id.custom);
        custom.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (timerstarted) {
                    lasttime = pizzatimer.getTimerms();
                    pizzatimer.cancel();
                    ((Button)v).setText("Start");
                    timerstarted = false;
                }
                else {
                    try {
                        int timeMin = Integer.parseInt(timer.getText().toString());
                        startTimerWithTime(timeMin * 60 * 1000);
                    } catch (NumberFormatException ex) {
                        startTimerWithTime(lasttime);
                    }
                }
            }
        });
        timer= (TextView) findViewById(R.id.textView);
        timer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        thirtymin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ontimeClick((Button) view);
            }

        });
        Button fortyfivemin= (Button) findViewById(R.id.fortyfivemin);
        fortyfivemin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ontimeClick((Button) view);
            }

        });
    }


    public void ontimeClick(Button button) {
        long timems;
        if (button.getId() == R.id.thirtymin){
            timems=30000*60;
        }
        else {
            timems=45000*60;
        }
if (pizzatimer != null){
    pizzatimer.cancel();
}
        pizzatimer= new PizzaTimerCountdown(timems, 1000, this, timer);
        pizzatimer.start();
    }

PizzaTimerCountdown pizzatimer;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void startTimerWithTime(long time) {
        pizzatimer.cancel();
        pizzatimer= new PizzaTimerCountdown(time, 1000, MainActivity.this, timer);
        pizzatimer.start();
        custom.setText("Stop");
        timerstarted = true;
    }
}
