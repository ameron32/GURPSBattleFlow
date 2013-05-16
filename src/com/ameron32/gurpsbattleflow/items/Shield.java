package com.ameron32.gurpsbattleflow.items;

import com.ameron32.gurpsbattleflow.damage.Damage;

public class Shield extends Armor implements DamageGenerator, DamageReducer, DamageReceiver {
    private static final long serialVersionUID = 8287792029935856404L;

    short db;
    short hp;
     
    
    /**
     * Duplicate an existing shield
     * 
     * @param source
     */
    public Shield(Shield source) {
        super(source);
        // TODO Auto-generated constructor stub
    }
    
    
    
    
    /**
     * RESPOND TO INCOMING EVENTS
     */

    @Override
    public void takeDamage(Damage d) {
        // TODO Auto-generated method stub
        
    }

}
