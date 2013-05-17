package com.ameron32.gurpsbattleflow.items.design;

import com.ameron32.gurpsbattleflow.damage.Damage.DamageType;

public class Projectile extends Item {
    private static final long serialVersionUID = 5179889091910045221L;

    DamageType damageType;
    short damageBoost;
    
    public Projectile(Projectile source) {
        super(source);
        // TODO Auto-generated constructor stub
    }

}
