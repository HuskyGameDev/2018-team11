package lootquest.component;

import lutebox.ecs.Component;

public class Health extends Component{
	public int max;
	public int current;
	
	public int getMax()
	{
		return max;
	}
	
	public int getCurrent()
	{
		return current;
	}
}
