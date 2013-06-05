package com.ameron32.gurpsbattleflow.attackoptions;

import com.ameron32.gurpsbattleflow.damage.Damage.DamageType;
import com.ameron32.gurpsbattleflow.damage.Roll;
import com.ameron32.gurpsbattleflow.items.design.ProjectileBucket;

public class RangedAttackOption extends AttackOption {

    ProjectileBucket projectileBucket;
    
    public RangedAttackOption(DamageType damageType, Roll damage, ProjectileBucket projectileBucket) {
        super(damageType, damage);
        this.projectileBucket = projectileBucket;
    }
}
