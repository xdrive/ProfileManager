package com.xdrive.profilemanager.condition;

import java.util.HashSet;
import java.util.Set;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.xdrive.profilemanager.profile.Profile;
import com.xdrive.profilemanager.rule.Rule;

/**
 * Implements condition. Controlls which profile should be turned on under 
 * what rule.
 * @author Dmytro_Kovalenko <dmytro.kovalenko@gmail.com>
 *
 */
@DatabaseTable(tableName = "condition")
public class Condition {
	@DatabaseField(generatedId = true)
	private Integer id;
	@DatabaseField
	private String name;
	private Profile profile;
	private Set<Rule> rules = new HashSet<Rule>();
	
	public Condition() {
		// Required for ORMLite
	}
	
	public Integer getId() {
		return id;
	}

	public Profile getProfile() {
		return profile;
	}
	
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	public Set<Rule> getRules() {
		return rules;
	}
	
	public void setRules(Set<Rule> rules) {
		this.rules = rules;
	}
	
	public void addRule(Rule rule) {
		this.rules.add(rule);
	}
	
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void checkRules() {
		for(Rule r : rules) {
			r.checkRule();
		}
	}
}
