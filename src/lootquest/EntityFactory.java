package lootquest;

import lootquest.component.AI;
import lootquest.component.Collider;
import lootquest.component.Direction;
import lootquest.component.Enemy;
import lootquest.component.EquipedSword;
import lootquest.component.Health;
import lootquest.component.Player;
import lootquest.component.Position;
import lootquest.component.Size;
import lootquest.component.Movement;
import lutebox.core.Lutebox;
import lutebox.ecs.Entity;

public final class EntityFactory {

    // don't instantiate this class 
    private EntityFactory() {} 
    
    public static Entity createPlayer(float x, float y) {
        Entity e = Lutebox.scene.createEntity(); 
        
        e.attach(Position.class).set(x, y); 
        e.attach(Movement.class).setMaxSpeed(3); 
        e.attach(Size.class).set(0.8f, 0.8f); 
        e.attach(Direction.class);
        e.attach(Health.class);
        e.attach(EquipedSword.class);
        e.attach(Player.class);
        e.attach(Collider.class); 
        
        return e; 
    }
    
    public static Entity createEnemy1(float x, float y) {
    	Entity e = Lutebox.scene.createEntity(); 
    	
    	e.attach(Position.class).set(x, y); 
    	e.attach(Movement.class).setMaxSpeed(2); 
    	e.attach(Direction.class); 
        e.attach(Size.class).set(0.8f, 0.8f); 
        e.attach(EquipedSword.class);
        e.attach(Health.class);
        e.attach(Collider.class);
		e.attach(Enemy.class);
		e.attach(AI.class);
        
        return e; 
    }
    
}
