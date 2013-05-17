/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ameron32.test.testcontent;

import java.io.Serializable;

public class Advantage implements Serializable {
	private static final long serialVersionUID = 2591651398215964681L;

	private String advTypeString;
	private String aORd;
	private int calcCost;
	private String cost;
	private String description;
	private boolean hasNotes;
	private int id;
	private boolean isExotic;
	private boolean isFakeCost;
	private boolean isForbidden;
	private boolean isLeveled;
	private boolean isMental;
	private boolean isMundane;
	private boolean isPhysical;
	private boolean isSocial;
	private boolean isSuper;
	// user-defined variables
	private String myNotes;
	private String nameString;
	private int pageInt;
	private String superTypeString;
	// imported variables
	private int ver;

	/*
	 * Advantages are created by the AdvantageEditor by loading a .CSV file into
	 * the system. MOST variables are imported through this process. Other
	 * variables are set by the user after the fact.
	 */

	public Advantage(int id, int ver, String aORd, String nameString,
			String advTypeString, String superTypeString, String cost,
			int pageInt, boolean isLeveled, boolean hasNotes,
			boolean isFakeCost, int calcCost, boolean isPhysical,
			boolean isMental, boolean isSocial, boolean isExotic,
			boolean isSuper, boolean isMundane, boolean isForbidden,
			String description) {
		this.id = id;
		this.ver = ver;
		this.aORd = aORd;
		this.nameString = nameString;
		this.advTypeString = advTypeString;
		this.superTypeString = superTypeString;
		this.cost = cost;
		this.pageInt = pageInt;
		this.isLeveled = isLeveled;
		this.hasNotes = hasNotes;
		this.isFakeCost = isFakeCost;
		this.calcCost = calcCost;
		this.isPhysical = isPhysical;
		this.isMental = isMental;
		this.isSocial = isSocial;
		this.isExotic = isExotic;
		this.isSuper = isSuper;
		this.isMundane = isMundane;
		this.isForbidden = isForbidden;
		this.description = description;
	}

	public int getVer() {
		return ver;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setAorD(String aORd) {
		this.aORd = aORd;
	}

	public String getAorD() {
		return aORd;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public String getNameString() {
		return nameString;
	}

	public void setAdvTypeString(String advTypeString) {
		this.advTypeString = advTypeString;
	}

	public String getAdvTypeString() {
		return advTypeString;
	}

	public void setSuperTypeString(String superTypeString) {
		this.superTypeString = superTypeString;
	}

	public String getSuperTypeString() {
		return superTypeString;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getCost() {
		return cost;
	}

	public void setPageInt(int pageInt) {
		this.pageInt = pageInt;
	}

	public int getPageInt() {
		return pageInt;
	}

	public void setIsLeveled(boolean isLeveled) {
		this.isLeveled = isLeveled;
	}

	public boolean getIsLeveled() {
		return isLeveled;
	}

	public void setHasNotes(boolean hasNotes) {
		this.hasNotes = hasNotes;
	}

	public boolean getHasNotes() {
		return hasNotes;
	}

	public void setIsFakeCost(boolean isFakeCost) {
		this.isFakeCost = isFakeCost;
	}

	public boolean getIsFakeCost() {
		return isFakeCost;
	}

	public void setCalcCost(int calcCost) {
		this.calcCost = calcCost;
	}

	public int getCalcCost() {
		return calcCost;
	}

	public void setIsPhysical(boolean isPhysical) {
		this.isPhysical = isPhysical;
	}

	public boolean getIsPhysical() {
		return isPhysical;
	}

	public void setIsMental(boolean isMental) {
		this.isMental = isMental;
	}

	public boolean getIsMental() {
		return isMental;
	}

	public void setIsSocial(boolean isSocial) {
		this.isSocial = isSocial;
	}

	public boolean getIsSocial() {
		return isSocial;
	}

	public void setIsExotic(boolean isExotic) {
		this.isExotic = isExotic;
	}

	public boolean getIsExotic() {
		return isExotic;
	}

	public void setIsSuper(boolean isSuper) {
		this.isSuper = isSuper;
	}

	public boolean getIsSuper() {
		return isSuper;
	}

	public void setIsMundane(boolean isMundane) {
		this.isMundane = isMundane;
	}

	public boolean getIsMundane() {
		return isMundane;
	}

	public void setIsForbidden(boolean isForbidden) {
		this.isForbidden = isForbidden;
	}

	public boolean getIsForbidden() {
		return isForbidden;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getMyNotes() {
		return myNotes;
	}

	public void setMyNotes(String myNotes) {
		this.myNotes = myNotes;
	}

	@Override
	public String toString() {
		return "Advantage [id=" + id + ", aORd=" + aORd + ", nameString="
				+ nameString + ", advTypeString=" + advTypeString
				+ ", superTypeString=" + superTypeString + ", cost=" + cost
				+ ", pageInt=" + pageInt + ", isLeveled=" + isLeveled
				+ ", hasNotes=" + hasNotes + ", isFakeCost=" + isFakeCost
				+ ", calcCost=" + calcCost + ", isPhysical=" + isPhysical
				+ ", isMental=" + isMental + ", isSocial=" + isSocial
				+ ", isExotic=" + isExotic + ", isSuper=" + isSuper
				+ ", isMundane=" + isMundane + ", isForbidden=" + isForbidden
				+ "]" + "\n" + description;
	}



	/**
	 * Copy Constructor
	 */
	public Advantage(Advantage source) {
		this.advTypeString = source.advTypeString;
		this.aORd = source.aORd;
		this.calcCost = source.calcCost;
		this.cost = source.cost;
		this.description = source.description;
		this.hasNotes = source.hasNotes;
		this.id = source.id;
		this.isExotic = source.isExotic;
		this.isFakeCost = source.isFakeCost;
		this.isForbidden = source.isForbidden;
		this.isLeveled = source.isLeveled;
		this.isMental = source.isMental;
		this.isMundane = source.isMundane;
		this.isPhysical = source.isPhysical;
		this.isSocial = source.isSocial;
		this.isSuper = source.isSuper;
		this.myNotes = source.myNotes;
		this.nameString = source.nameString;
		this.pageInt = source.pageInt;
		this.superTypeString = source.superTypeString;
		this.ver = source.ver;
	}
}
