package lootquest.component;

import lutebox.ecs.Component;

public class Consumable extends Component{

	public int HPRestore = 0;
	public int SwordDmgUp = 0;
	public int BowDmgUp = 0;
	public float SpeedUp = 0;
	
	public Consumable SetHpRestore(int x) {
		this.HPRestore = x;
		return this;
	}
	public Consumable SetSwordDmgUp(int x) {
		this.SwordDmgUp = x;
		return this;
	}
	public Consumable SetBowDmgUp(int x) {
		this.BowDmgUp = x;
		return this;
	}
	public Consumable SetSpeedUp(float x) {
		this.SpeedUp = x;
		return this;
	}
}
