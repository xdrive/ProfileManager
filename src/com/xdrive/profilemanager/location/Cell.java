package com.xdrive.profilemanager.location;

import java.util.ArrayList;
import java.util.List;

import com.xdrive.profilemanager.rule.CellLocationRule;


/**
 * Represents GSM cell object
 * @author Dmytro Kovalenko <dmytro.kovalenko@gmail.com>
 *
 */
public class Cell {
	// Cell id
	private int cid;
	// Location area code
	private int lac;
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getLac() {
		return lac;
	}
	public void setLac(int lac) {
		this.lac = lac;
	}
		
}
