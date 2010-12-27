package com.xdrive.profilemanager.condition;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.xdrive.profilemanager.ProfileManager;
import com.xdrive.profilemanager.data.DatabaseHelper;
import com.xdrive.profilemanager.profile.Profile;
import com.xdrive.profilemanager.profile.ProfileElement;
import com.xdrive.profilemanager.rule.Rule;

import android.util.Log;

/**
 * Manages conditions. Checks them and if they meet their rules activates 
 * profiles
 * @author Dmytro_Kovalenko <dmytro_kovalenko@gmail.com>
 *
 */
public class ConditionManager {
	private Set<Condition> conditions = new HashSet<Condition>();
	private DatabaseHelper helper;

	public ConditionManager(DatabaseHelper helper) {
		this.helper = helper;
	}
	
	public Set<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(Set<Condition> conditions) {
		this.conditions = conditions;
	}	
	
	public void fetchActiveConditions() {
		try {
			List<Condition> conditionsList = helper.getConditionDAO().queryForAll();
			conditions = new HashSet<Condition>(conditionsList);
			for(Condition condition : conditions) {
				Set<Rule> rules = helper.getConditionRules(condition);
				condition.setRules(rules);
				helper.getProfileDAO().refresh(condition.getProfile());
				Profile profile = condition.getProfile();
				Set<ProfileElement> elements = helper.getProfileElements(profile);
				profile.setElements(elements);				
			}			
		} catch (SQLException e) {
			Log.e(ProfileManager.class.getSimpleName(), "Error fetching conditions");
		}		
	}
	
	public void checkRules() {
		for(Condition c : conditions) {
			c.checkRules();
		}
	}
}
