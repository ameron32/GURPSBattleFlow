
package com.ameron32.gurpsbattleflow.items;

public class Armor extends Item {
    private static final long serialVersionUID = 3592351610507935292L;
    
    String material;
    String[] covers;
    short dr, don, holdout;
    // Addon[] addons;
    
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
     * @param material
     * @param covers
     * @param dr
     * @param don
     * @param holdout
     */
    public Armor(String name, int id, int cost, short tl, float weight, String specialNotes,
            String description, String material, String[] covers, short dr, short don, short holdout) {
        super(name, id, cost, tl, weight, specialNotes, description);
        this.material = material;
        this.covers = covers;
        this.dr = dr;
        this.don = don;
        this.holdout = holdout;
    }


    /**
     * Duplicate an existing armor
     * 
     * @param name
     */
    public Armor(Armor source) {
        super(source);
        this.material = source.material;
        this.covers = source.covers;
        this.dr = source.dr;
        this.don = source.don;
        this.holdout = source.holdout;
    }
}
