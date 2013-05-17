package com.ameron32.gurpsbattleflow.items.frmwk;

import com.ameron32.gurpsbattleflow.damage.Damage;

public interface DamageGenerator {
    
    public void addReducer(DamageReducer dr);
    
    public void addReceiver(DamageReceiver dr);
    
    public void causeDamage(Damage d);
    
}
