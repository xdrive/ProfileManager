package com.xdrive.profilemanager;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OpenHelperManager.SqliteOpenHelperFactory;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.xdrive.profilemanager.condition.Condition;
import com.xdrive.profilemanager.condition.ConditionManager;
import com.xdrive.profilemanager.data.DatabaseHelper;
import com.xdrive.profilemanager.profile.Profile;
import com.xdrive.profilemanager.profile.ProfileElement;
import com.xdrive.profilemanager.rule.Rule;
import com.xdrive.profilemanager.rule.TimeRule;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ProfileManager extends OrmLiteBaseActivity<DatabaseHelper> {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //insertSomeStuff();
        showSomeStuff();
        /*
        AudioManager mAudio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //mAudio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                
        mAudio.setStreamVolume(AudioManager.STREAM_RING, 10, AudioManager.FLAG_PLAY_SOUND);
        Integer audioVolume = mAudio.getStreamVolume(AudioManager.STREAM_RING);
        TextView tv = (TextView) findViewById(R.id.hello);
		tv.append(audioVolume.toString());
		*/
    }
    
    private void insertSomeStuff() {
    	try {
    		Dao<Condition, Integer> conditionDAO = getHelper().getConditionDAO();
    		Dao<TimeRule, Integer> timeRuleDAO = getHelper().getTimeRuleDAO();
    		Dao<Profile, Integer> profileDAO = getHelper().getProfileDAO();
    		Dao<ProfileElement, Integer> profileElementDAO = getHelper().getProfileElementDAO();
    		
    		// Create Profile
    		Profile profile = new Profile("Test Profile");
            profileDAO.create(profile);
            
            // Create Profile Element
            ProfileElement profileElement = new ProfileElement(
            		ProfileElement.VOLUME_SETTING,
            		"5",
            		profile.getId());
            profileElementDAO.create(profileElement);
    		
            // Create Condition
    		Condition condition = new Condition();
    		condition.setName("Test Condition");
    		condition.setProfile(profile);
    		conditionDAO.create(condition);
    		
    		// Create Time Rule(as condition rule)
    		Date tmpDate = new Date(0);
            Calendar startCalendar = Calendar.getInstance();
            int currentHour = startCalendar.get(Calendar.HOUR_OF_DAY);
            int currentMinute = startCalendar.get(Calendar.MINUTE);
                       
            startCalendar.setTime(tmpDate);
            startCalendar.set(Calendar.HOUR_OF_DAY, currentHour);
            startCalendar.set(Calendar.MINUTE, currentMinute - 1);
                    
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(tmpDate);
            endCalendar.set(Calendar.HOUR_OF_DAY, currentHour);
            endCalendar.set(Calendar.MINUTE, currentMinute + 1);
                    
            byte weekDaysMask = TimeRule.EVERYDAY;
            
            TimeRule timeRule = new TimeRule(startCalendar.getTime(), 
            		endCalendar.getTime(),
            		weekDaysMask,
            		condition.getId());
            timeRuleDAO.create(timeRule);            
            
    	}  catch (SQLException e) {
    		Log.e(ProfileManager.class.getSimpleName(), "Database error");
		}
    }
    
    private void showSomeStuff() {
    	TimeRule timeRule = null;
    	ConditionManager conditionManager = new ConditionManager(getHelper());
    	conditionManager.fetchActiveConditions();
    	StringBuilder sb = new StringBuilder();
    	SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    	
    	for (Condition condition : conditionManager.getConditions()) {
    		sb.append("--------------------------------\n");
    		sb.append("Condition name: "). append(condition.getName()).append("\n");
    		sb.append("Rules: \n");
			int i = 0;
			for(Rule rule : condition.getRules()) {
				sb.append(rule.getClass().getSimpleName()).append("\n");
				if (rule instanceof TimeRule) {
					timeRule = (TimeRule) rule;
					sb.append("Start time: ")
					.append(format.format(timeRule.getStartTime()))
					.append(", end time: ")
					.append(format.format(timeRule.getEndTime())).append("\n");
				}	
				i++;
			}
			sb.append("Profile: \n");
			Profile profile = condition.getProfile();
			sb.append(profile.getName()).append("\n");
			for(ProfileElement element : profile.getElements()) {
				sb.append("type: ").append(element.getElementType())
					.append(", value: ").append(element.getElementValue())
					.append("\n");
			}
			sb.append("--------------------------------\n");			
    	}
    	TextView tv = (TextView) findViewById(R.id.hello);
		tv.setText(sb);    	
    }
}