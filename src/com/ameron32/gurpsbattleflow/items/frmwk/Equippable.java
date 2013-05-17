package com.ameron32.gurpsbattleflow.items.frmwk;

public interface Equippable {

    public boolean equipTo(EquipmentUser eu);
    
    public boolean unequip();
    
    public EquipmentUser getEquippedBy();
    
    public boolean isEquipped();
    
}
