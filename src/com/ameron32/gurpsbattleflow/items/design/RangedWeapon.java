package com.ameron32.gurpsbattleflow.items.design;

public class RangedWeapon extends Weapon{
    private static final long serialVersionUID = 2944636000386485087L;

    short maxRange, halfDmgRange;
    short accuracy;
    short bulk, rof;
    
    short crossbowST; // if applicable
    
//  RangedAttackOptions[] attackOptions;    
//  RangedAddons[] addons;
    
    public RangedWeapon(RangedWeapon source) {
        super(source);
        // TODO Auto-generated constructor stub
        
    }


}
