package com.ameron32.gurpsbattleflow.character;

import java.io.Serializable;

import com.ameron32.gurpsbattleflow.damage.*;
import com.ameron32.gurpsbattleflow.items.DamageReceiver;
import com.ameron32.gurpsbattleflow.items.DamageReducer;
import com.ameron32.gurpsbattleflow.items.Inventory;

public class CharacterRecord implements Serializable, DamageReducer, DamageReceiver {
    private static final long serialVersionUID = 3258519085442584964L;

    // TODO going down the inputs and making simple data loaders
    // TODO don't forget to use a "clone" constructor
    // TODO DB isn't right, double check it
    // TODO generate getters and setters AFTER item implementation
    
    /**
     * How to use CharacterRecord | Creating a CharacterRecord: So, you create a
     * character in GURPS using a pretty standard order of operations. Let's
     * design a CharacterRecord in the same order.
     * 
     * @author klemeilleur
     */
    
    
    /**
     * INPUTS
     */
    CharacterType characterType;
    short st, dx, iq, ht, 
        enhancedMove, enhancedDodge, enhancedParry, enhancedBlock,
        strikingST, liftingST,
        shieldSkill,            // convert to Skill[shield]
        sm;
    boolean combatRef;
//  MeleeAttack[] mAttackOptions;   // stored within MeleeWeapon
    String shield;              // covert to item(shield)
//  RangedAttack[] rAttackOptions;  // stored within RangedWeapon
//  Skill[] skills;
//  Trait[] traits;             // aka Advantages
//  EquippedArmor[] armor;
//  EquippedWeapon[] weapons;
    Inventory inventory;
    
    short genericArmor;         // replace with inventory + equipment
    float extraWeight;          // replace with inventoried items
    
    /**
     * Create a new CharacterRecord. 
     * Use setEnhanced(), setStrikingST(), setLiftingST(), and setSizeModifier()
     * if needed.
     * Use finalize() to process the auto-calculate fields. 
     * 
     * @param st
     * @param dx
     * @param iq
     * @param ht
     */
    public CharacterRecord (int st, int dx, int iq, int ht) {
        this.st = (short) st;
        this.dx = (short) dx;
        this.iq = (short) iq;
        this.ht = (short) ht;
        enhancedMove = enhancedDodge = enhancedParry = enhancedBlock = 0;
        strikingST = liftingST = 0;
        sm = 0;
    }
    
    public void setEnhanced(int... enhancement) {
        for (int e : enhancement) {
            switch (e) {
                case MOVE_ENHANCED:
                    enhancedMove = 1;
                    break;
                case DODGE_ENHANCED:
                    enhancedDodge = 1;
                    break;
                case PARRY_ENHANCED:
                    enhancedParry = 1;
                    break;
                case BLOCK_ENHANCED:
                    enhancedBlock = 1;
                    break;
            }
        }
    }
    public void setStrikingST (int strikingST) {
        this.strikingST = (short) strikingST;
    }
    public void setLiftingST (int liftingST) {
        this.liftingST = (short) liftingST;
    }
    public void setSizeModifier (int sm) {
        this.sm = (short) sm;
    }
    
    public void finalize() {
        calculate();
    }
    
    
    /**
     * Duplicate an existing character record.
     * 
     * @param record
     */
    public CharacterRecord(CharacterRecord source) {
        this.characterType = source.characterType;
        this.st = source.st;
        this.dx = source.dx;
        this.iq = source.iq;
        this.ht = source.ht;
        this.enhancedMove = source.enhancedMove;
        this.enhancedDodge = source.enhancedDodge;
        this.enhancedParry = source.enhancedParry;
        this.enhancedBlock = source.enhancedBlock;
        this.strikingST = source.strikingST;
        this.liftingST = source.liftingST;
        this.shieldSkill = source.shieldSkill;
        this.sm = source.sm;
        this.combatRef = source.combatRef;
//        this.mAttackOptions = source.mAttackOptions;
        this.shield = source.shield;
//        this.rAttackOptions = source.rAttackOptions;
        this.genericArmor = source.genericArmor;
        this.extraWeight = source.extraWeight;
        this.thrust = source.thrust;
        this.swing = source.swing;
        this.basicLift = source.basicLift;
        this.combatLoad = source.combatLoad;
        this.basicMove = source.basicMove;
        this.move = source.move;
        this.dodge = source.dodge;
        this.hp = source.hp;
        this.will = source.will;
        this.per = source.per;
        this.fatigue = source.fatigue;
        this.block = source.block;
        this.db = source.db;
        this.basicSpeed = source.basicSpeed;
        this.encMulti = source.encMulti;
        this.inventory = source.inventory;
    }
    

    /**
     * CALCULATABLES
     */
    Roll thrust, swing;
    short basicLift, combatLoad,  
        basicMove, move, dodge, 
        hp, will, per, fatigue, 
        block, db;
    float basicSpeed;
    float encMulti;
    
    private void calculate() {
        basicLift = (short)(((st + liftingST) * (st + liftingST)) / 5);
        // TODO should be allEquippedMeleeWeapons.weight + allEquippedRangedWeapons.weight 
        // + EquippedShield.weight + EquippedArmor.weight + allInventoryItems.weight
        combatLoad = 0; 
        if (combatLoad < basicLift + 1) encMulti = 1.0f;
            else if (combatLoad < (basicLift * 2) + 1) encMulti = 0.8f;
            else if (combatLoad < (basicLift * 3) + 1) encMulti = 0.6f;
            else if (combatLoad < (basicLift * 3) + 1) encMulti = 0.6f;
            else if (combatLoad < (basicLift * 4) + 1) encMulti = 0.4f;
            else if (combatLoad < (basicLift * 5) + 1) encMulti = 0.2f;
            else encMulti = 0.0f;
        basicSpeed = (float)(dx + ht) / 4.0f;
        basicMove = (short)(Math.round(basicSpeed));
        move = (short)(Math.round((float)(basicMove + enhancedMove) * encMulti));
        short n1;
        db = 0;                     // shield db? // need doublecheck
        dodge = (short)((Math.round(basicSpeed) + 3 
                + (n1 = (short)((combatRef) ? 1 : 0)) + enhancedDodge 
                +  db               // shield DB? // need doublecheck
                - (n1 = (short)((encMulti == 0.0f) ? 10 : Math.round(5.0f - (encMulti * 5.0f)))) // dodge
                ));
        hp = st;
        will = per = iq;
        fatigue = ht;
        block = (short)(Math.round(((shieldSkill / 2) + 3
                + db                // shield db? // need doublecheck
                + (n1 = (short)((combatRef) ? 1 : 0)))
                + enhancedBlock));
    }
    
    
    
    /**
     * REFERENCES
     */
    public enum CharacterType { PC, NPC };
    
    
    // TODO not final place
    private class MeleeAttack {
        /**
         * INPUTS
         */
//      String attackName;      // convert to weapon + attackType        
        String attackWeapon;    // convert to item(weapon)
        short skill, damageBonus_Die;
        String weaponQuality;   // replace into attackWeapon item(weapon)
        
        /**
         * CALCULATABLES
         */
        short parry;
        Roll damage; String damageType;
        
        
        /**
         * REFERENCES
         */

    }
    
    // TODO not final place
    private class RangedAttack {
        /**
         * INPUTS
         */
//      String attackName;      // convert to weapon + attackType        
        String attackWeapon;    // convert to item(weapon)
        short skill, damageBonus_Die;
        String weaponQuality;   // replace into attackWeapon item(weapon)
        
        /**
         * CALCULATABLES
         */
        com.ameron32.gurpsbattleflow.damage.Roll damage; String damageType;
        short accuracy;
        
        /**
         * REFERENCES
         */
        
    }
    
    public static final int MOVE_ENHANCED = 0;
    public static final int DODGE_ENHANCED = 1;
    public static final int PARRY_ENHANCED = 2;
    public static final int BLOCK_ENHANCED = 3;

    
    
    /**
     * RESPOND TO INCOMING EVENTS
     */
    
    
    @Override
    public void takeDamage(Damage d) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void reduceDamage(Damage d) {
        // TODO Auto-generated method stub
        
    }


    
    
}
