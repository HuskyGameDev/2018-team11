package lootquest.component;

import lutebox.ecs.Component;

public class Consumable extends Component{

	public int HPRestore = 0;
	public int SwordDmgUp = 0;
	public float SpeedUp = 0;
	public int type = 0;
	
	public Consumable SetHpRestore(int x) {
		this.HPRestore = x;
		return this;
	}
	public Consumable SetSwordDmgUp(int x) {
		this.SwordDmgUp = x;
		return this;
	}
	public Consumable SetSpeedUp(float x) {
		this.SpeedUp = x;
		return this;
	}
	public Consumable SetType( int x ) {
	    this.type = x;
	    return this;
	}
}
