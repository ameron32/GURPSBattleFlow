
package com.ameron32.gurpsbattleflow.character;

import java.io.Serializable;

import com.ameron32.gurpsbattleflow.damage.*;
import com.ameron32.gurpsbattleflow.items.design.Inventory;
import com.ameron32.gurpsbattleflow.items.design.Item;
import com.ameron32.gurpsbattleflow.items.frmwk.DamageReceiver;
import com.ameron32.gurpsbattleflow.items.frmwk.DamageReducer;
import com.ameron32.gurpsbattleflow.items.frmwk.Equippable;
import com.ameron32.gurpsbattleflow.items.frmwk.EquipmentUser;

/**
 * How to use CharacterRecord | Creating a CharacterRecord: So, you create a
 * character in GURPS using a pretty standard order of operations. Let's
 * design a CharacterRecord in the same order.
 * @author klemeilleur
 */
public class CharacterRecord implements Serializable, DamageReducer, DamageReceiver, EquipmentUser {
    private static final long serialVersionUID = 3258519085442584964L;

    // TODO going down the inputs and making simple data loaders
    // TODO don't forget to use a "clone" constructor
    // TODO DB isn't right, double check it
    // TODO generate getters and setters AFTER item implementation



    /*
     * INPUTS
     */
    CharacterType mCharacterType;
    short mST, mDX, mIQ, mHT,
            mEnhancedMove, mEnhancedDodge, mEnhancedParry, mEnhancedBlock,
            mStrikingST, mLiftingST,
            mShieldSkill, // convert to Skill[shield]
            mSM;
    boolean mCombatReflexes;
    // MeleeAttack[] mAttackOptions; // stored within MeleeWeapon
    String mShield; // covert to item(shield)
    // RangedAttack[] rAttackOptions; // stored within RangedWeapon
    // Skill[] skills;
    // Trait[] traits; // aka Advantages
    Inventory mInventory;


    /**
     * Create a new CharacterRecord. Use setEnhanced(), setStrikingST(),
     * setLiftingST(), and setSizeModifier() if needed. Use finalize() to
     * process the auto-calculate fields.
     * @param st Strength
     * @param dx Dexterity
     * @param iq Intelligence
     * @param ht Health
     */
    public CharacterRecord(int st, int dx, int iq, int ht) {
        this.mST = (short) st;
        this.mDX = (short) dx;
        this.mIQ = (short) iq;
        this.mHT = (short) ht;
        mEnhancedMove = mEnhancedDodge = mEnhancedParry = mEnhancedBlock = 0;
        mStrikingST = mLiftingST = 0;
        mSM = 0;
    }

    public void setEnhanced(int... enhancement) {
        for (int e : enhancement) {
            switch (e) {
                case MOVE_ENHANCED:
                    mEnhancedMove = 1;
                    break;
                case DODGE_ENHANCED:
                    mEnhancedDodge = 1;
                    break;
                case PARRY_ENHANCED:
                    mEnhancedParry = 1;
                    break;
                case BLOCK_ENHANCED:
                    mEnhancedBlock = 1;
                    break;
            }
        }
    }

    public void setStrikingST(int strikingST) {
        this.mStrikingST = (short) strikingST;
    }

    public void setLiftingST(int liftingST) {
        this.mLiftingST = (short) liftingST;
    }

    public void setSizeModifier(int sm) {
        this.mSM = (short) sm;
    }

    public void finalize() {
        calculate();
    }

    /**
     * Duplicate an existing character record.
     * @param record Record to copy
     */
    public CharacterRecord(CharacterRecord source) {
        this.mCharacterType = source.mCharacterType;
        this.mST = source.mST;
        this.mDX = source.mDX;
        this.mIQ = source.mIQ;
        this.mHT = source.mHT;
        this.mEnhancedMove = source.mEnhancedMove;
        this.mEnhancedDodge = source.mEnhancedDodge;
        this.mEnhancedParry = source.mEnhancedParry;
        this.mEnhancedBlock = source.mEnhancedBlock;
        this.mStrikingST = source.mStrikingST;
        this.mLiftingST = source.mLiftingST;
        this.mShieldSkill = source.mShieldSkill;
        this.mSM = source.mSM;
        this.mCombatReflexes = source.mCombatReflexes;
        this.mShield = source.mShield;
        this.mThrust = source.mThrust;
        this.mSwing = source.mSwing;
        this.mBasicLift = source.mBasicLift;
        this.mCombatLoad = source.mCombatLoad;
        this.mBasicMove = source.mBasicMove;
        this.mMove = source.mMove;
        this.mDodge = source.mDodge;
        this.mHP = source.mHP;
        this.mWill = source.mWill;
        this.mPer = source.mPer;
        this.mFatigue = source.mFatigue;
        this.mBlock = source.mBlock;
        this.mDB = source.mDB;
        this.mBasicSpeed = source.mBasicSpeed;
        this.mEncMulti = source.mEncMulti;
        this.mInventory = source.mInventory;
    }

    /*
     * CALCULATABLES
     */
    Roll mThrust, mSwing;
    short mBasicLift,
            mBasicMove, mMove, mDodge,
            mHP, mWill, mPer, mFatigue,
            mBlock, mDB;
    float mBasicSpeed;
    float mEncMulti, mCombatLoad;

    private void calculate() {
        mBasicLift = (short) (((mST + mLiftingST) * (mST + mLiftingST)) / 5);
        mCombatLoad = mInventory.getInventoryTotalWeight();
        if (mCombatLoad < mBasicLift + 1)
            mEncMulti = 1.0f;
        else if (mCombatLoad < (mBasicLift * 2) + 1)
            mEncMulti = 0.8f;
        else if (mCombatLoad < (mBasicLift * 3) + 1)
            mEncMulti = 0.6f;
        else if (mCombatLoad < (mBasicLift * 4) + 1)
            mEncMulti = 0.4f;
        else if (mCombatLoad < (mBasicLift * 5) + 1)
            mEncMulti = 0.2f;
        else
            mEncMulti = 0.0f;
        mBasicSpeed = (float) (mDX + mHT) / 4.0f;
        mBasicMove = (short) (Math.round(mBasicSpeed));
        mMove = (short) (Math.round((float) (mBasicMove + mEnhancedMove) * mEncMulti));
        mDB = mInventory.getEquippedDB();
        mDodge = (short) ((Math.round(mBasicSpeed) + 3
                + ((short) ((mCombatReflexes) ? 1 : 0)) + mEnhancedDodge
                + mDB
                - ((short) ((mEncMulti == 0.0f) ? 10 : Math.round(5.0f - (mEncMulti * 5.0f))))));
        mHP = mST;
        mWill = mPer = mIQ;
        mFatigue = mHT;
        mBlock = (short) (Math.round(((mShieldSkill / 2) + 3
                + mDB
                + ((short) ((mCombatReflexes) ? 1 : 0)))
                + mEnhancedBlock));
    }

    /*
     * REFERENCES
     */
    public enum CharacterType {
        PC, NPC
    };

    // TODO not final place
    private class MeleeAttack {
        /*
         * INPUTS
         */
        // String attackName; // convert to weapon + attackType
        String attackWeapon; // convert to item(weapon)
        short skill, damageBonus_Die;
        String weaponQuality; // replace into attackWeapon item(weapon)

        /*
         * CALCULATABLES
         */
        short parry;
        Roll damage;
        String damageType;

    }

    // TODO not final place
    private class RangedAttack {
        /*
         * INPUTS
         */
        // String attackName; // convert to weapon + attackType
        String attackWeapon; // convert to item(weapon)
        short skill, damageBonus_Die;
        String weaponQuality; // replace into attackWeapon item(weapon)

        /*
         * CALCULATABLES
         */
        Roll damage;
        String damageType;
        short accuracy;

    }

    public static final int MOVE_ENHANCED = 0;
    public static final int DODGE_ENHANCED = 1;
    public static final int PARRY_ENHANCED = 2;
    public static final int BLOCK_ENHANCED = 3;

    /*
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

    @Override
    public boolean equip(Equippable e) {
        if (e instanceof Item)
            return ((Item) e).equipTo(this);
        else 
            return false;
    }
}
