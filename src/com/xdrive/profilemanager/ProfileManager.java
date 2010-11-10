package com.xdrive.profilemanager;

import java.util.Calendar;
import java.util.Date;

import com.xdrive.profilemanager.rule.Rule;
import com.xdrive.profilemanager.rule.TimeRule;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileManager extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);     
        /*
        Date tmpDate = new Date(0);
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(tmpDate);
        startCalendar.set(Calendar.HOUR, 11);
        startCalendar.set(Calendar.MINUTE, 00);
        
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(tmpDate);
        endCalendar.set(Calendar.HOUR, 23);
        endCalendar.set(Calendar.HOUR, 59);
        
        byte weekDaysMask = TimeRule.EVERYDAY;
        
        Rule timeRule = new TimeRule(startCalendar.getTime(), endCalendar.getTime(), weekDaysMask);
        TextView text = (TextView) findViewById(R.id.hello);
        text.setText(((Boolean)timeRule.checkRule()).toString());
        */
    }
}