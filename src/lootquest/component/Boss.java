package lootquest.component;

import lutebox.ecs.Component;

public class Boss extends Component{
	public int counterCur = 0;
	public int counterMax = (int) Math.random()*120 + 30;
	public float distance = 8;
	public int delay = 0;
}
