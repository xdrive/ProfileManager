package com.xdrive.profilemanager.rule;

import java.util.Calendar;
import java.util.Date;
import android.text.format.Time;
/**
 * Class implements Time based rules. Supports times, week days and 
 * both type rules
 * @author Dmytro Kovalenko <dmytro.kovalenko@gmail.com>
 *
 */
public class TimeRule implements Rule {	
	private Date startTime;
	private Date endTime;
	
	private byte weekDaysMask;
	
	public final static byte MONDAY 	= 1;   // 00000001
	public final static byte TUESDAY 	= 2;   // 00000010
	public final static byte WEDNESDAY 	= 4;   // 00000100
	public final static byte THURSDAY 	= 8;   // 00001000
	public final static byte FRIDAY 	= 16;  // 00010000
	public final static byte SATURDAY 	= 32;  // 00100000
	public final static byte SUNDAY 	= 64;  // 01000000
	public final static byte EVERYDAY 	= 127; // 01111111
	
	public TimeRule(Date startTime, Date endTime, byte weekDaysMask) {
		this.startTime 		= startTime;
		this.endTime		= endTime;
		this.weekDaysMask	= weekDaysMask;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public void setWeekDaysMask(byte weekDaysMask) {
		this.weekDaysMask = weekDaysMask;
	}
	
	public byte getWeekDaysMask() {
		return weekDaysMask;
	}
	
	@Override
	public boolean checkRule() {
		// Converting current date&time to 1 Jan 1970 and current time.
		// So for ex. 10 Nov 2010 22:59:12 will be converted to 
		// 1 Jan 1970 22:59:12 we are then able to compare this time with our 
		// rule start and end time
		Calendar currentCalendar = Calendar.getInstance();
		Calendar tempCalendar = Calendar.getInstance();
		Date currentTime;
		
		currentCalendar.setTime(new Date(0));
		currentCalendar.set(Calendar.HOUR_OF_DAY, 
				tempCalendar.get(Calendar.HOUR_OF_DAY)); 
		currentCalendar.set(Calendar.MINUTE, tempCalendar.get(Calendar.MINUTE));
		
		currentTime = currentCalendar.getTime();
		
		if (currentTime.after(startTime) && currentTime.before(endTime)) {
			return true;
		} else {
			return false;
		}
	}
}