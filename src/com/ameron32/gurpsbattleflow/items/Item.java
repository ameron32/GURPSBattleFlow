
package com.ameron32.gurpsbattleflow.items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ameron32.gurpsbattleflow.damage.Damage;

public class Item implements Serializable, DamageGenerator, DamageReducer {
    private static final long serialVersionUID = -4962512586546099923L;

    // TODO Notes: consider interfaces for Ownable, Personalizable/Customizable
    
    
    String name;
    int id, cost;
    short tl;
    float weight;
    ItemType itemType;

    String specialNotes, description;

    /**
     * Standard Constructor
     * 
     * @param name
     * @param id
     * @param cost
     * @param tl
     * @param weight
     * @param specialNotes
     * @param description
     */
    public Item(String name, int id, int cost, short tl, float weight, 
            String specialNotes, String description) {
        this.name = name;
        this.id = id;
        this.cost = cost;
        this.tl = tl;
        this.weight = weight;
        this.specialNotes = specialNotes;
        this.description = description;
        this.itemType = determineItemType(this);
    }

    /**
     * Duplicate an existing template item
     * 
     * @param source
     */
    public Item(Item source) {
        this.name = source.name;
        this.id = source.id;
        this.cost = source.cost;
        this.tl = source.tl;
        this.weight = source.weight;
        this.itemType = source.itemType;
        this.specialNotes = source.specialNotes;
        this.description = source.description;
    }

    
    /**
     * PERSONALIZE
     */
    String pName, pDescription; 
    boolean isEquipped;
    
    public void personalize(String pName, String pDescription) {
        this.pName = pName;
        this.pDescription = pDescription;
    }
    
    public void setIsEquipped(boolean isEquipped) {
        this.isEquipped = isEquipped; 
    }
    
    /**
     * REFERENCES
     */
    
    public enum ItemType {
        Item, 
        Weapon, MeleeWeapon, RangedWeapon, 
        Armor, Shield
    }
    
    private ItemType determineItemType(Object o) {
        if (o instanceof RangedWeapon)
            return ItemType.RangedWeapon;
        else if (o instanceof MeleeWeapon)
            return ItemType.MeleeWeapon;
        else if (o instanceof Weapon)
            return ItemType.Weapon;
        else if (o instanceof Shield)
            return ItemType.Shield;
        else if (o instanceof Armor)
            return ItemType.Armor;
        else if (o instanceof Item)
            return ItemType.Item;
        else return null;
    }

    /**
     * GENERATE EVENTS
     */
    
    @Override
    public void causeDamage(Damage d) {
        // TODO Auto-generated method stub
        
    }

    
    
    /**
     * RESPOND TO INCOMING EVENTS
     */
    
    private static final List<DamageReceiver> myTargets = new ArrayList<DamageReceiver>();
    @Override
    public void addReceiver(DamageReceiver dr) {
        // TODO Auto-generated method stub
        myTargets.add(dr);
    }

    @Override
    public void addReducer(DamageReducer dr) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void reduceDamage(Damage d) {
        // TODO Auto-generated method stub
        
    }
}
