package com.ameron32.gurpsbattleflow;

public class References {

	public static final int NAME = 0;
	public static final int INITIALS = 1;
	public static final int DOWNLOAD_LOCATION = 2;
	public static final int FILENAME_LOCAL = 3;
	public static final int PAGE_OFFSET = 4;
	
	public static final String[][] references = 
		{
			{"Combat Steps","x","https://dl.dropbox.com/u/949753/GURPS/combatflowchart.csv","combatflowchart.csv", "0"},
			{"Basic Set","B","https://dl.dropbox.com/u/949753/GURPS/Resources/Official/GURPS 4th - Basic Set.pdf","G4eBS.pdf", "4"},
			{"High Tech","HT","https://dl.dropbox.com/u/949753/GURPS/Resources/Official/GURPS 4th - High Tech.pdf","G4eHT.pdf", "1"},
			{"Martial Arts","MA","https://dl.dropbox.com/u/949753/GURPS/Resources/Official/GURPS 4th - Martial Arts.pdf","G4eMA.pdf", "1"}
		};
	
	public static String getFileNameByInitials(String initials) {
		for (String[] record : references) {
			if (record[1].equalsIgnoreCase(initials))
				return record[3];
		}
		return null;
	}
	
	public static int getOffsetByInitials(String initials) {
		for (String[] record : references) {
			if (record[1].equalsIgnoreCase(initials))
				return Integer.parseInt(record[4]);
		}
		return 0;
	}
}
