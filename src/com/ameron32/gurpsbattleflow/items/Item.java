
package com.ameron32.gurpsbattleflow.items;

import java.io.Serializable;

public class Item implements Serializable {
    private static final long serialVersionUID = -4962512586546099923L;

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
     * Duplicate an existing item
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
//      else if (o instanceof Shield)
//          ;
        else if (o instanceof Armor)
            return ItemType.Armor;
        else if (o instanceof Item)
            return ItemType.Item;
        else return null;
    }
}
