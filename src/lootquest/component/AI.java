package lootquest.component;

import lutebox.ecs.Component;

public class AI extends Component{
	public int counterCur = 0;
	public int counterMax = (int) Math.random()*120 + 30;
	public float distance = 3;
	public boolean ranged = false;
	
	public AI setDistance(float d) {
		this.distance = d;
		return this;
	}
	
	public AI setRanged(boolean b) {
		this.ranged = b;
		return this;
	}
	public AI setRangedRandom() {
		if(Math.random() < 0.5) {
			this.ranged = true;
		} else {
			this.ranged = false;
		}
		return this;
	}
}
