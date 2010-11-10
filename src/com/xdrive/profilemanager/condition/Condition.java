package com.xdrive.profilemanager.condition;

import java.util.HashSet;
import java.util.Set;

import com.xdrive.profilemanager.profile.Profile;
import com.xdrive.profilemanager.rule.Rule;

/**
 * Implements condition. Controlls which profile should be turned on under 
 * what rule.
 * @author Dmytro_Kovalenko <dmytro.kovalenko@gmail.com>
 *
 */
public class Condition {
	private Profile profile;
	private Set<Rule> rules = new HashSet<Rule>();
	
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
	
	public void checkRules() {
		for(Rule r : rules) {
			r.checkRule();
		}
	}
}
