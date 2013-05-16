package com.ameron32.gurpsbattleflow.items;

import java.util.Hashtable;

public class Inventory {

    // the INTEGER should store the unique identifier of "my item" generated
    // at the point of item purchase or generation (cloning from the library
    // item template). the BOOLEAN should store if this item is considered
    // "equipped" on the character, rather than a separate list.
    private Hashtable<Integer, Item> myBackpackItems;
    // TODO will I need to store the new instances of the cloned items here somewhere?
    public Inventory () {
        if (myBackpackItems == null) myBackpackItems = new Hashtable<Integer, Item>();
    }
    
}
