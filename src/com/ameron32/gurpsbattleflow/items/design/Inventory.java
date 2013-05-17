package com.ameron32.gurpsbattleflow.items.design;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    // the INTEGER should store the unique identifier of "my item" generated
    // at the point of item purchase or generation (cloning from the library
    // item template). the BOOLEAN should store if this item is considered
    // "equipped" on the character, rather than a separate list.
    private List<Item> myBackpackItems;
    // TODO will I need to store the new instances of the cloned items here somewhere?
    public Inventory () {
        if (myBackpackItems == null) myBackpackItems = new ArrayList<Item>();
    }

    public List<Armor> getEquippedArmor() {
        List<Armor> equipped = new ArrayList<Armor>();
        for (Item item : getEquippedItems()) {
            if (item instanceof Armor) 
                equipped.add((Armor) item);
        }
        return equipped;
    }
    
    public List<Weapon> getEquippedWeapons() {
        List<Weapon> equipped = new ArrayList<Weapon>();
        for (Item item : getEquippedItems()) {
            if (item instanceof Weapon) 
                equipped.add((Weapon) item);
        }        
        return equipped;
    }
    
    public List<Item> getEquippedItems() {
        List<Item> equipped = new ArrayList<Item>();
        for (Item item : myBackpackItems) {
            if (item.isEquipped())
                equipped.add(item);
        }
        return equipped;
    }
    
    public List<Item> getInventory() {
        return myBackpackItems;
    }
}
