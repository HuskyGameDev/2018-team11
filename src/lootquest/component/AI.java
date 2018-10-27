package lootquest.component;

import lutebox.ecs.Component;

public class AI extends Component{
	public int counterCur = 0;
	public int counterMax = (int) Math.random()*45 + 45;
}
