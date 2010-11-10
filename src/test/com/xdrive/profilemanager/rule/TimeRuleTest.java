package test.com.xdrive.profilemanager.rule;

import java.util.Calendar;
import java.util.Date;

import com.xdrive.profilemanager.rule.Rule;
import com.xdrive.profilemanager.rule.TimeRule;

import junit.framework.TestCase;

public class TimeRuleTest extends TestCase {
	Rule timeRule;
	
	@Override
	protected void setUp() throws Exception {
		/*Date tmpDate = new Date(0);
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(tmpDate);
        startCalendar.set(Calendar.HOUR, 11);
        startCalendar.set(Calendar.MINUTE, 00);
                
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(tmpDate);
        endCalendar.set(Calendar.HOUR, 23);
        endCalendar.set(Calendar.MINUTE, 59);
                
        byte weekDaysMask = TimeRule.EVERYDAY;
        
        timeRule = new TimeRule(startCalendar.getTime(), endCalendar.getTime(), 
        		weekDaysMask);
       */		
	}
	
	public void testCheckRule() {
		// occurs every day, starts one minute before current and ends one after 
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
        
        timeRule = new TimeRule(startCalendar.getTime(), endCalendar.getTime(), 
        		weekDaysMask);
		assertTrue(timeRule.checkRule());
		
		// occurs every day, starts one minute after current time, 
		// ends 2 minutes before current  
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
	}

}
