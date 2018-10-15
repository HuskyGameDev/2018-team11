package lootquest.component;

import lutebox.ecs.Component;

public class EquipedSword extends Component {

    public boolean isUsing; 
    public int damage = 1;
    public int cooldownMax= 60;
    public int cooldownCur = 0;
}
