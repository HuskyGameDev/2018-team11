package lootquest.component;

import lutebox.ecs.Component;

public class Gold extends Component {
	public int goldValue = 100;
	public int currentGold = 0;
	public Gold SetGoldValue(int amt){
		this.goldValue = amt;
		return this;
	}
	
	public int getCurrentGold(){
		return currentGold;
	}
	
}
