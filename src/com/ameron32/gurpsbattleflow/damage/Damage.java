
package com.ameron32.gurpsbattleflow.damage;

public class Damage {

    Roll damage;
    DamageType damageType;
    
    public Damage (Roll damage, DamageType damageType) {
        this.damage = damage;
        this.damageType = damageType;
    }
    
    
    /**
     * Duplicate an existing damage effect
     * 
     * @param source
     */
    public Damage (Damage source) {
        this.damage = source.damage;
        this.damageType = source.damageType;
    }
    
    
    
    /**
     * REFERENCES
     */
    public enum DamageType {
        cr, imp, cut, burn, tox, pi, pi0, pi10, pi100
    }

    public static final String[] damageTypes = {
        "cr", "imp", "cut", "burn", "tox", "pi-", "pi", "pi+", "pi++"
    };

    public static DamageType getDamageTypeByString(String damageType) {
        if (damageType.equals(damageTypes[0]))
            return DamageType.cr;
        else if (damageType.equals(damageTypes[0]))
            return DamageType.imp;
        else if (damageType.equals(damageTypes[1]))
            return DamageType.cut;
        else if (damageType.equals(damageTypes[2]))
            return DamageType.burn;
        else if (damageType.equals(damageTypes[3]))
            return DamageType.tox;
        else if (damageType.equals(damageTypes[4]))
            return DamageType.pi0;
        else if (damageType.equals(damageTypes[5]))
            return DamageType.pi;
        else if (damageType.equals(damageTypes[6]))
            return DamageType.pi10;
        else if (damageType.equals(damageTypes[7]))
            return DamageType.pi100;
        return null;
    }
    
}
