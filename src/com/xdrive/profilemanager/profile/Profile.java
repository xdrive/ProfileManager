package com.xdrive.profilemanager.profile;

/**
 * Class implements Profile. Holds deferent system settings like ringer voliume,
 * vibrating alert status and so on.
 * @author Dmytro Kovalenko <dmytro.kovalenko@gmail.com>
 */
public class Profile {
	private byte ringerVoliume;
	private boolean vibratingAlert;
	
	public byte getRingerVoliume() {
		return ringerVoliume;
	}
	public void setRingerVoliume(byte ringerVoliume) {
		this.ringerVoliume = ringerVoliume;
	}
	public boolean isVibratingAlert() {
		return vibratingAlert;
	}
	public void setVibratingAlert(boolean vibratingAlert) {
		this.vibratingAlert = vibratingAlert;
	}	
	
}
