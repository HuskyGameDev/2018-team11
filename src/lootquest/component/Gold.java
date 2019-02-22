package lootquest.component;

import lutebox.ecs.Component;

public class Gold extends Component {
	public int goldValue = 100;
	
	public Gold SetGoldValue(int amt){
		this.goldValue = amt;
		return this;
	}
}
