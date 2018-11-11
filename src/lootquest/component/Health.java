package lootquest.component;

import lutebox.ecs.Component;

public class Health extends Component{
	public int max = 1;
	public int current = 1;
	
	public int getMax()
	{
		return max;
	}
	
	public int getCurrent()
	{
		return current;
	}
}
