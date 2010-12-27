package com.xdrive.profilemanager.profile;

import java.util.HashSet;
import java.util.Set;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Class implements Profile. Holds different system settings like ringer volume,
 * vibrating alert status and so on.
 * @author Dmytro Kovalenko <dmytro.kovalenko@gmail.com>
 */
@DatabaseTable(tableName = "profile")
public class Profile {
	@DatabaseField(generatedId=true)
	private Integer id;
	@DatabaseField
	private String name;
	Set<ProfileElement> elements = new HashSet<ProfileElement>();
	
	public Profile() {
		// Empty args constructor required by ORMLite 
	}	
	
	public Profile(String name) {
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ProfileElement> getElements() {
		return elements;
	}

	public void setElements(Set<ProfileElement> elements) {
		this.elements = elements;
	}

}
