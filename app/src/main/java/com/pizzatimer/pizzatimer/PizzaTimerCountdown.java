package com.pizzatimer.pizzatimer;

import android.app.Activity;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by bigmax on 4/3/2016.
 */
public class PizzaTimerCountdown extends CountDownTimer {
    public PizzaTimerCountdown (long times, long delay, Activity activity,TextView timertext) {
        super(times, delay);
        this.activity=activity; this.timertext=timertext;
    }

    public Activity activity;
    public TextView timertext;
    private long timerms=0;
    @Override
    public void onTick(long millisUntilFinished) {
        timertext.setText(numberToClock(millisUntilFinished / 1000 / 60) + ":" + numberToClock((millisUntilFinished / 1000 - (millisUntilFinished / 1000 / 60) * 60)));
        timerms=millisUntilFinished;


    }


    private String numberToClock (long value){
        if (value < 10) return "0"+value;
        else return ""+value;
    }

    @Override
    public void onFinish() {
        //this will sound the alarm tone
        //this will sound the alarm once, if you wish to
        //raise alarm in loop continuously then use MediaPlayer and setLooping(true)
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(activity.getApplicationContext(), alarmUri);
        ringtone.play();
        timertext.setText("done!");
    }

    public long getTimerms() {
        return timerms;
    }
}
