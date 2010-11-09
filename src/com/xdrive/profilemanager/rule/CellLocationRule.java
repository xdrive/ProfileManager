package com.xdrive.profilemanager.rule;

import java.util.HashSet;
import java.util.Set;

import com.xdrive.profilemanager.location.Cell;

public class CellLocationRule implements LocationRule {
	private Set<Cell> cells = new HashSet();

	public Set<Cell> getCells() {
		return cells;
	}

	public void setCells(Set<Cell> cells) {
		this.cells = cells;
	}

	public void addCell(Cell cell) {
		cells.add(cell);
	}
	
	public boolean checkRule() {
		// TODO Auto-generated method stub
		return false;
	}
}
