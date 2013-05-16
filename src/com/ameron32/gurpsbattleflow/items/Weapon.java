
package com.ameron32.gurpsbattleflow.items;

public abstract class Weapon extends Item {
    private static final long serialVersionUID = 4585651326671906498L;

    String weaponGroup;
    short minST;
    short quality;
    // Attack[] attacks;
    // EquipLocation[] equipLocation;
    // IdleLocation[] idleLocation;

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
     * @param quality
     */
    public Weapon(String name, int id, int cost, short tl, float weight, String specialNotes,
            String description, String weaponGroup, short minST, short quality) {
        super(name, id, cost, tl, weight, specialNotes, description);
        this.weaponGroup = weaponGroup;
        this.minST = minST;
        this.quality = quality;
    }

    /**
     * Duplicate an existing weapon
     * 
     * @param source
     */
    public Weapon(Weapon source) {
        super(source);
        this.weaponGroup = source.weaponGroup;
        this.minST = source.minST;
        this.quality = source.quality;
    }
    
}
