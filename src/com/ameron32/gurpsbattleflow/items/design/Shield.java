package com.ameron32.gurpsbattleflow.items.design;

import com.ameron32.gurpsbattleflow.damage.Damage;
import com.ameron32.gurpsbattleflow.items.frmwk.DamageGenerator;
import com.ameron32.gurpsbattleflow.items.frmwk.DamageReceiver;
import com.ameron32.gurpsbattleflow.items.frmwk.DamageReducer;

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
    
    
    
    
    /*
     * RESPOND TO INCOMING EVENTS
     */

    @Override
    public void takeDamage(Damage d) {
        // TODO Auto-generated method stub
        
    }
    
    
    
    
    
    
    /*
     * GETTERS AND SETTERS
     */

    public short getDb() {
        return db;
    }
    
    
}
