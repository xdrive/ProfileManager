package com.xdrive.profilemanager.condition;

import java.util.HashSet;
import java.util.Set;

import android.R.bool;

/**
 * Manages conditions. checkes them and if they meet their rules activates 
 * profiles
 * @author Dmytro_Kovalenko <dmytro_kovalenko@gmail.com>
 *
 */
public class ConditionManager {
	private Set<Condition> conditions = new HashSet<Condition>();

	public Set<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(Set<Condition> conditions) {
		this.conditions = conditions;
	}	
	
	public void checkRules() {
		// TODO implement check for each condition in collection conditions		
	}
}
