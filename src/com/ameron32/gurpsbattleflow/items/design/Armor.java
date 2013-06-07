
package com.ameron32.gurpsbattleflow.items.design;

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
     * @param i
     * @param weight
     * @param specialNotes
     * @param description
     * @param material
     * @param covers
     * @param dr
     * @param don
     * @param holdout
     */
    public Armor(String name, int id, int cost, int i, double weight, String specialNotes,
            String description, String material, String[] covers, int dr, int don, int holdout) {
        super(name, id, cost, i, weight, specialNotes, description);
        this.material = material;
        this.covers = covers;
        this.dr = (short)dr;
        this.don = (short)don;
        this.holdout = (short)holdout;
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
