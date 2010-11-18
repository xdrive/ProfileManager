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
import com.xdrive.profilemanager.data.DatabaseHelper;
import com.xdrive.profilemanager.rule.Rule;
import com.xdrive.profilemanager.rule.TimeRule;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ProfileManager extends OrmLiteBaseActivity<DatabaseHelper> {
	static {
		OpenHelperManager.setOpenHelperFactory(new SqliteOpenHelperFactory() {
			public OrmLiteSqliteOpenHelper getHelper(Context context) {
				return new DatabaseHelper(context);
			}
		});
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //insertSomeStuff();
        showSomeStuff();
    }
    
    private void insertSomeStuff() {
    	try {
    		Dao<Condition, Integer> conditionDAO = getHelper().getConditionDAO();
    		Dao<TimeRule, Integer> timeRuleDAO = getHelper().getTimeRuleDAO();
    		
    		Condition condition = new Condition();
    		condition.setName("Test Condition");
    		conditionDAO.create(condition);
    		
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
    	try {
    		Dao<Condition, Integer> conditionDAO = getHelper().getConditionDAO();
            List<Condition> list = conditionDAO.queryForAll();
            TimeRule timeRule = null;
            
            StringBuilder sb = new StringBuilder();
			sb.append("got ").append(list.size()).append(" entries").append("\n");

			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			// if we already have items in the database
			for(Condition cond : list) {
				Set<Rule> rules = getHelper().getConditionRules(cond);
				// output the first one				 
				sb.append("--------------------------------\n");
				//sb.append("[").append(i).append("] = ").append(cond.toString()).append("\n");
				sb.append("Condition name: "). append(cond.getName()).append("\n");
				sb.append("Rules: \n");
				int i = 0;
				for(Rule condRule : rules) {
					sb.append(condRule.getClass().getSimpleName()).append("\n");
					if (condRule instanceof TimeRule) {
						timeRule = (TimeRule) condRule;
						sb.append("Start time: ")
							.append(format.format(timeRule.getStartTime()))
							.append(", end time:")
							.append(format.format(timeRule.getEndTime())).append("\n");
						
					}
					sb.append("--------------------------------\n");
					i++;
				}
			}
			TextView tv = (TextView) findViewById(R.id.hello);
			tv.setText(sb);
    	} catch (SQLException e) {
    		Log.e(ProfileManager.class.getSimpleName(), "Database error");
		}
    }
}