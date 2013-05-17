package com.ameron32.test.testcontent;

import java.io.Serializable;

//import org.apache.commons.lang3.text.WordUtils;

public class Skill implements Serializable {
	private static final long serialVersionUID = 7662647146533261471L;
	
    private int importVersion = 0;
	
	private int id;
	private int ver;
	private String nameString;
	private boolean isLeveled;
	private String description;

	public Skill(int id, int ver, String nameString, boolean isLeveled,
			String description) {
		this.id = id;
		this.ver = ver;
		this.nameString = nameString;
		this.isLeveled = isLeveled;
		this.description = description;
	}

	
	/** Getters and Setters */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public boolean isLeveled() {
		return isLeveled;
	}

	public void setLeveled(boolean isLeveled) {
		this.isLeveled = isLeveled;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Skill [id=" + id + "\n" + "ver=" + ver + "\n" + "nameString="
				+ nameString + "\n" + "isLeveled=" + isLeveled + "\n" + "description="
				+ "\n" 
//				+ WordUtils.wrap(convertBars(description), 70)
				+ "]";
	}
	
	private String convertBars(String s) {
		char[] conversion = s.toCharArray();
		for (int i = 0; i < conversion.length; i++) {
			if (conversion[i] == '|') conversion[i] = '\n';
		}
		return String.copyValueOf(conversion);
	}
		
}
