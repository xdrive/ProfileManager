package com.xdrive.profilemanager.profile;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Represents Model for Profile Element(Volume, Vibrating etc.)
 * @author Dmytro_Kovalenko
 */
@DatabaseTable(tableName = "profile_element")
public class ProfileElement {
	@DatabaseField(generatedId = true)
	private Integer id; 
	@DatabaseField(columnName = ProfileElement.PROFILE_ID_FIELD, canBeNull = false)
	private Integer profileId;
	@DatabaseField(columnName = "element_type")
	private Integer elementType;
	@DatabaseField(columnName = "element_value")
	private String elementValue;
	
	public final static int VOLUME_SETTING = 1;
	public final static String PROFILE_ID_FIELD = "profile_id";
	
	public ProfileElement() {
		// required by ORMLite
	}

	public ProfileElement(Integer elementType, String elementValue, Integer profileId) {
		this.elementType 	= elementType;
		this.elementValue 	= elementValue;
		this.profileId 		= profileId;
	}
	
	public Integer getId() {
		return id;
	}

	public Integer getElementType() {
		return elementType;
	}

	public void setElementType(Integer elementType) {
		this.elementType = elementType;
	}

	public String getElementValue() {
		return elementValue;
	}

	public void setElementValue(String elementValue) {
		this.elementValue = elementValue;
	}
	
	/**
	 * Executes particular profile element i.e. sets required volume value
	 */
	public void setProfileElement() {
		GeneralProfileElement element;
		switch (elementType) {
		case ProfileElement.VOLUME_SETTING:
				element = new VolumeProfileElement(elementValue);
				element.setup();
			break;
		}
	}
	
}
