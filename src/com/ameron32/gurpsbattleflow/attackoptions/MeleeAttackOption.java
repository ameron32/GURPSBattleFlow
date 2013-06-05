package com.ameron32.gurpsbattleflow.attackoptions;

import com.ameron32.gurpsbattleflow.damage.Damage.DamageType;
import com.ameron32.gurpsbattleflow.damage.Roll;


public class MeleeAttackOption extends AttackOption {

    short reach[];

    public MeleeAttackOption(DamageType damageType, Roll damage, short[] reach) {
        super(damageType, damage);
        this.reach = reach;
    }
}
