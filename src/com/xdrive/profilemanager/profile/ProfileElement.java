package com.xdrive.profilemanager.profile;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "profile_element")
public class ProfileElement {
	@DatabaseField(generatedId = true)
	private Integer id; 
	@DatabaseField(columnName = "element_type")
	private Integer elementType;
	@DatabaseField(columnName = "element_value")
	private String elementValue;
	
	final static int VOLUME_SETTING = 1;
	
	public ProfileElement() {
		// required by ORMLite
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
	
	
	
}
