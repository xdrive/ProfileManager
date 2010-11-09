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
	private Time startTime;
	private Time endTime;
	
	private byte weekDaysMask;
	
	public final static byte MONDAY 	= 1;   // 00000001
	public final static byte TUESDAY 	= 2;   // 00000010
	public final static byte WEDNESDAY 	= 4;   // 00000100
	public final static byte THURSDAY 	= 8;   // 00001000
	public final static byte FRIDAY 	= 16;  // 00010000
	public final static byte SATURDAY 	= 32;  // 00100000
	public final static byte SUNDAY 	= 64;  // 01000000
	public final static byte EVERYDAY 	= 127; // 01111111
	
	public TimeRule(Time startTime, Time endTime, byte weekDaysMask) {
		this.startTime 		= startTime;
		this.endTime		= endTime;
		this.weekDaysMask	= weekDaysMask;
	}
	
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	
	public Time getStartTime() {
		return startTime;
	}
	
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
	public Time getEndTime() {
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
		// TODO review this code if the time is set to current but date is 
		// set to 1 Jan 1970
		Time currentTime = new Time();
		if (currentTime.after(startTime) && currentTime.before(endTime)) {
			return true;
		} else {
			return false;
		}
	}
}
