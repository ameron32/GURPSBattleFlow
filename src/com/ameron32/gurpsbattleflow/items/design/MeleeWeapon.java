package com.ameron32.gurpsbattleflow.items.design;

public class MeleeWeapon extends Weapon {
    private static final long serialVersionUID = 5732058216003189081L;
 
    short maxReach;         // FIXME does this exist for defense? if attacked, do you need reach? I do not know.
    short parry;
    
//  MeleeAttackOptions[] attackOptions;    
//  MeleeAddons[] addons;

    public MeleeWeapon(MeleeWeapon source) {
        super(source);
        // TODO auto-method. replace with accurate method
        
    }


    
    
    
    
    
    
    
    
    
    
    /**
     * REFERENCES
     */
    public static final int REACH_CLOSE_COMBAT = 0;
    
}
