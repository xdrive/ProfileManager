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
    }
}