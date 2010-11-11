package test.com.xdrive.profilemanager.rule;

import java.util.Calendar;
import java.util.Date;

import com.xdrive.profilemanager.rule.Rule;
import com.xdrive.profilemanager.rule.TimeRule;

import junit.framework.TestCase;

public class TimeRuleTest extends TestCase {
	Rule timeRule;
	public void testCheckRule() {
		// occurs every day, starts one minute before current and ends one after 
		Date tmpDate = new Date(0);
        Calendar startCalendar = Calendar.getInstance();
        int currentHour = startCalendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = startCalendar.get(Calendar.MINUTE);
        int currentWeekDay = startCalendar.get(Calendar.DAY_OF_WEEK);
        
        startCalendar.setTime(tmpDate);
        startCalendar.set(Calendar.HOUR_OF_DAY, currentHour);
        startCalendar.set(Calendar.MINUTE, currentMinute - 1);
                
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(tmpDate);
        endCalendar.set(Calendar.HOUR_OF_DAY, currentHour);
        endCalendar.set(Calendar.MINUTE, currentMinute + 1);
                
        byte weekDaysMask = TimeRule.EVERYDAY;
        
        timeRule = new TimeRule(startCalendar.getTime(), endCalendar.getTime(), 
        		weekDaysMask);
		assertTrue(timeRule.checkRule());
		
		// occurs every day, starts one minute after current time, 
		// ends 2 minutes after current  
		startCalendar.setTime(tmpDate);
        startCalendar.set(Calendar.HOUR_OF_DAY, currentHour);
        startCalendar.set(Calendar.MINUTE, currentMinute + 1);
                
        endCalendar.setTime(tmpDate);
        endCalendar.set(Calendar.HOUR_OF_DAY, currentHour);
        endCalendar.set(Calendar.MINUTE, currentMinute + 2);
                
        weekDaysMask = TimeRule.EVERYDAY;
        
        timeRule = new TimeRule(startCalendar.getTime(), endCalendar.getTime(), 
        		weekDaysMask);
		assertFalse(timeRule.checkRule());
		
		// occurs on every previous day, starts one minute before current time, 
		// ends 2 minutes after current  
		startCalendar.setTime(tmpDate);
        startCalendar.set(Calendar.HOUR_OF_DAY, currentHour);
        startCalendar.set(Calendar.MINUTE, currentMinute - 1);
                
        endCalendar.setTime(tmpDate);
        endCalendar.set(Calendar.HOUR_OF_DAY, currentHour);
        endCalendar.set(Calendar.MINUTE, currentMinute + 2);
                
        if (currentWeekDay > 1) {
        	currentWeekDay--;
        } else {
        	currentWeekDay = Calendar.SATURDAY;
        }
        weekDaysMask = (byte) (1 << (currentWeekDay - 1));
                
        timeRule = new TimeRule(startCalendar.getTime(), endCalendar.getTime(), 
        		weekDaysMask);
		assertFalse(timeRule.checkRule());
	}

}
