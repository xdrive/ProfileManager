package com.xdrive.profilemanager.profile;

public class VolumeProfileElement implements GeneralProfileElement {
	Byte volume;
	
	public VolumeProfileElement(String value) {
		volume = Byte.parseByte(value);
	}
	
	@Override
	public void setup() {
		// TODO add method realization
	}
	
}
