
package com.ameron32.gurpsbattleflow.items.design;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ameron32.gurpsbattleflow.damage.Damage;
import com.ameron32.gurpsbattleflow.items.frmwk.Attachable;
import com.ameron32.gurpsbattleflow.items.frmwk.AttachmentCarrier;
import com.ameron32.gurpsbattleflow.items.frmwk.DamageGenerator;
import com.ameron32.gurpsbattleflow.items.frmwk.DamageReceiver;
import com.ameron32.gurpsbattleflow.items.frmwk.DamageReducer;
import com.ameron32.gurpsbattleflow.items.frmwk.Equippable;
import com.ameron32.gurpsbattleflow.items.frmwk.EquipmentUser;

public class Item implements Serializable, DamageGenerator, DamageReducer, Equippable, AttachmentCarrier {
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
        this.itemType = determineItemType(this);
        this.specialNotes = specialNotes;
        this.description = description;
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
        
        this.user = source.user;      // TODO need to reEquip?
    }

    
    /**
     * PERSONALIZE
     */
    String pName, pDescription; 
    
    public void personalize(String pName, String pDescription) {
        this.pName = pName;
        this.pDescription = pDescription;
    }
    
    /**
     * REFERENCES
     */
    
    public enum ItemType {
        Item, 
        Weapon, MeleeWeapon, RangedWeapon, 
        Armor, Shield,
        ProjectileBucket
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
        else if (o instanceof ProjectileBucket)
            return ItemType.ProjectileBucket;
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

    // EQUIP
    EquipmentUser user;
    @Override
    public boolean equipTo(EquipmentUser eu) {
        user = eu;
        return true;
        // TODO add slot item replacement and restrictions
    }
    
    @Override
    public boolean unequip() {
        user = null;
        return true;
    }
    
    @Override
    public boolean isEquipped() {
        return !(user == null);
    }

    @Override
    public EquipmentUser getEquippedBy() {
        return user;
    }
    

    
    // ATTACHMENTCARRIER
    List<Attachable> attachments;
    @Override
    public void attach(Attachable a) {
        // TODO Auto-generated method stub
        if (a == null) attachments = new ArrayList<Attachable>();
        if (a != this) attachments.add(a);
    }
    
    @Override
    public void remove(Attachable a) {
        // TODO Auto-generated method stub
        if (attachments.contains(a)) attachments.remove(a);
    }

    
    
    
    /*
     * GETTERS AND SETTERS
     */
        
    
    public String getSpecialNotes() {
        return specialNotes;
    }

    public void setSpecialNotes(String specialNotes) {
        this.specialNotes = specialNotes;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public EquipmentUser getUser() {
        return user;
    }

    public void setUser(EquipmentUser user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public short getTl() {
        return tl;
    }

    public float getWeight() {
        return weight;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public String getDescription() {
        return description;
    }

    public List<Attachable> getAttachments() {
        return attachments;
    }
    
    


}
