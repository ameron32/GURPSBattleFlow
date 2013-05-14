package com.ameron32.gurpsbattleflow.damage;

public class Roll {
    public static final int DIE = 0;
    public static final int BONUS = 1;
    short[] roll = new short[2];
    
    public Roll (int die, int bonus) {
        roll[0] = (short)die;
        roll[1] = (short)bonus;
    }
    
    public short[] getRoll () {
        return roll;
    }
    
    public short getDie () {
        return roll[0];
    }
    
    public short getBonus () {
        return roll[1];
    }
}

