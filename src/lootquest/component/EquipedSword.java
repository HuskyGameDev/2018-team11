package lootquest.component;

import lutebox.ecs.Component;

public class EquipedSword extends Component {

	public float cooldownTime = 1f; 
	public float animationTime = 0.2f; 
	
	public int damage = 1; 
	
	private float curTime = 0; 
	private boolean inUse = false; 
	
	public boolean use() {
		if (isUsing()) return false; 
		
		inUse = true; 
		return true; 
	}
	
	public EquipedSword setCoolDown( float cd ) {
	    cooldownTime = cd;
	    return this;
	}
	
	public boolean isUsing() {
		return inUse; 
	}
	
    public boolean shouldShowAnimation() {
    	return inUse && curTime < animationTime; 
    }
    
    public boolean shouldApplyDamage() {
    	return inUse && curTime == 0; 
    }
    
    public void addTime(float time) {
    	if (inUse) {
    		curTime += time; 
    		
    		if (curTime >= cooldownTime) {
    			inUse = false; 
    			curTime = 0; 
    		}
    	}
    }
}
