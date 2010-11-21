package com.xdrive.profilemanager.profile;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Class implements Profile. Holds different system settings like ringer volume,
 * vibrating alert status and so on.
 * @author Dmytro Kovalenko <dmytro.kovalenko@gmail.com>
 */
@DatabaseTable(tableName = "profile")
public class Profile {
	@DatabaseField(columnName = "ringer_volume")
	private byte ringerVolume;
	@DatabaseField(columnName = "vibrator")
	private boolean vibratingAlert;
	
	public Profile() {
		// Empty args constructor required by ORMLite 
	}
	
	public byte getRingerVolume() {
		return ringerVolume;
	}
	public void setRingerVolume(byte ringerVolume) {
		this.ringerVolume = ringerVolume;
	}
	public boolean isVibratingAlert() {
		return vibratingAlert;
	}
	public void setVibratingAlert(boolean vibratingAlert) {
		this.vibratingAlert = vibratingAlert;
	}	
	
}
