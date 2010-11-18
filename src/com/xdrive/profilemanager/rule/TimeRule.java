package com.xdrive.profilemanager.rule;

import java.util.Calendar;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Class implements Time based rules. Supports times, week days and 
 * both type rules
 * @author Dmytro Kovalenko <dmytro.kovalenko@gmail.com>
 *
 */
@DatabaseTable(tableName="time_rule")
public class TimeRule implements Rule {
	@DatabaseField(generatedId=true)
	private Integer id;
	@DatabaseField(columnName = "start_time")
	private Date startTime;
	@DatabaseField(columnName = "end_time")
	private Date endTime;
	@DatabaseField(columnName = "week_days_mask")
	private byte weekDaysMask;
	@DatabaseField(columnName = "condition_id", canBeNull = false)
	private Integer conditionId;
	
	public final static byte SUNDAY 	= 1;   // 00000001
	public final static byte MONDAY 	= 2;   // 00000010
	public final static byte TUESDAY 	= 4;   // 00000100
	public final static byte WEDNESDAY 	= 8;   // 00001000
	public final static byte THURSDAY 	= 16;  // 00010000
	public final static byte FRIDAY 	= 32;  // 00100000
	public final static byte SATURDAY 	= 64;  // 01000000
	public final static byte EVERYDAY 	= 127; // 01111111
	
	public final static String CONDITION_ID_FIELD = "condition_id";
	
	public TimeRule() {
		// Empty arg constructore required for ORMLite
	}
	
	public TimeRule(Date startTime, Date endTime, byte weekDaysMask, Integer conditionId) {
		this.startTime 		= startTime;
		this.endTime		= endTime;
		this.weekDaysMask	= weekDaysMask;
		this.conditionId	= conditionId;
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
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getConditionId() {
		return conditionId;
	}

	public void setConditionId(Integer conditionId) {
		this.conditionId = conditionId;
	}

	/**
	 * Checks if current day of week and time fits rule
	 * @return boolean true if current day of week and time fits the rule
	 * 
	 */
	@Override
	public boolean checkRule() {
		if (this.checkWeekDay()) {
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
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if current week day is included to the rule's weekDaysMask
	 * @return boolean true if current week day is in mask
	 */
	private boolean checkWeekDay() {
		int currentWeekDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		byte currentWeekDayMask = (byte) (TimeRule.SUNDAY << (currentWeekDay - 1));
		if ((currentWeekDayMask & this.weekDaysMask) != 0) {
			return true;
		} else { 
			return false;
		}
	}
}