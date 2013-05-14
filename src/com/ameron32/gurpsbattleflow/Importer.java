package com.ameron32.gurpsbattleflow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.ameron32.libgurps.Advantage;


public class Importer {
	public enum ImportType {
		Advantage, Skill, 
		Item, 
		MeleeWeapon, RangedWeapon, 
		Armor, Shield,
		
		FlowChart
	}

	private CsvReader fileReader;
	/**
	 * Reads a CSV file and returns a list of classes depending on the ImportType provided.
	 * @param path
	 * @param appendToThisList
	 * @param type
	 * @return
	 */
	public List readCSVIntoList(String path, List appendToThisList,
			ImportType type) {

		if (appendToThisList != null) {
			try {
				int ver = 153;

				if (fileReader == null) fileReader = new CsvReader(path);
				fileReader.readHeaders();

				while (fileReader.readRecord()) {
					switch (type) {
/*
					case Advantage:
						readAdv(appendToThisList, ver);
						break;

					case Skill:
						readSkill(appendToThisList, ver);
						break;
*/
						
						
					case FlowChart:
						readStep(appendToThisList, ver);
						break;
					}
				}

				fileReader.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
//				Log.e("CsvRead failed FNF: ", e.toString());
			} catch (IOException e) {
				e.printStackTrace();
//				Log.e("CsvRead failed IO: ", e.toString());
			}
		}
		fileReader = null;
		return appendToThisList;
	}

	private void readStep(List list, int ver) 
			throws IOException, FileNotFoundException {
		Step oneStep = new Step(
				getString("step"),
				ver,
				getString("goTo"),
				getString("splitTo"),
				getString("refs"),
				getString("picture"),
				getString("description")
				);
		list.add(oneStep);
	}


	private void readAdv(List list, int ver)
			throws IOException, FileNotFoundException {

	    /**
	     * Import version 155
	     */
		Advantage oneAdvantage = new Advantage(
                getInt("iId"), getString("sAorD"), getString("sName"), getString("sAdvType"),
                getString("sSuperType"), getString("sBookCost"), getInt("iPage"),
                getBoolean("isLeveled"), getBoolean("isMultiCost"), getBoolean("isVariableCost"),
                getInt("iBaseCost"), getString("sMultiCost"), getInt("iPerLevelCost"),
                getString("sPerLevelMultiCost"), getBoolean("hasNotes"), getBoolean("isFakeCost"),
                getInt("iCalcCost"), getBoolean("isForbidden"), getString("sListPMSESM"),
                getString("sRefs"), getString("sDescription"));

//				getInt("id"), 
//				ver,
//				getString("aORd"),
//				getString("advTypeString"),
//				getString("superTypeString"),
//				getString("superTypeString"),
//				getString("cost"),
//				getInt("pageInt"),
//				getBoolean("isLeveled"),
//				getBoolean("hasNotes"),
//				getBoolean("isFakeCost"),
//				getInt("calcCost"),
//				getBoolean("isPhysical"),
//				getBoolean("isMental"),
//				getBoolean("isSocial"),
//				getBoolean("isExotic"),
//				getBoolean("isSuper"),
//				getBoolean("isMundane"),
//				getBoolean("isForbidden"),
//				getString("description"));  
		        
		list.add(oneAdvantage);
	}
/*
	private void readSkill(List list, int ver)
			throws IOException, FileNotFoundException {

		Skill oneSkill = new Skill(
				getInt("id"), 
				ver,
				getString("nameString"),
				getBoolean("isLeveled"),
				getString("description"));
		list.add(oneSkill);
	}
*/
	
	private String tmp;
	private boolean getBoolean(String s) throws IOException, FileNotFoundException {
		tmp = fileReader.get(s);
		return tmp.equalsIgnoreCase("true");
	}
	private int getInt(String s) throws IOException, FileNotFoundException {
		tmp = fileReader.get(s);
		return Integer.parseInt(tmp);
	}
	private double getDouble(String s) throws IOException, FileNotFoundException {
		tmp = fileReader.get(s);
		return Double.parseDouble(tmp);
	}
	private String getString(String s) throws IOException, FileNotFoundException {
		return fileReader.get(s);
	}
}
