package lootquest;

import lootquest.component.AI;
import lootquest.component.Collider;
import lootquest.component.Consumable;
import lootquest.component.Direction;
import lootquest.component.Enemy;
import lootquest.component.EquipedCrossbow;
import lootquest.component.EquipedSword;
import lootquest.component.Health;
import lootquest.component.Player;
import lootquest.component.Position;
import lootquest.component.Projectile;
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
    	e.attach(Movement.class).setMaxSpeed(3); 
    	e.attach(Direction.class); 
        e.attach(Size.class).set(0.8f, 0.8f); 
        e.attach(EquipedSword.class);
        e.attach(Health.class);
        e.attach(Collider.class);
		e.attach(Enemy.class);
		e.attach(AI.class).setRangedRandom();
		e.attach(EquipedCrossbow.class);
        
        return e; 
    }
    
    public static Entity createArrow(Entity from, float x,float y,float xVel,float yVel) {
    	Entity e = Lutebox.scene.createEntity();
    	EquipedCrossbow bow = from.get(EquipedCrossbow.class);
    	
    	e.attach(Position.class).set(x, y);
    	e.attach(Movement.class).setMaxSpeed(6).set(xVel*5, yVel*5);
    	e.attach(Size.class).set(0.3f, 0.3f);
    	e.attach(Collider.class);
    	e.attach(Projectile.class).setShooter(from).setDamage(bow.damage);
    	
    	
    	return e;
    }
    
    public static Entity createConsumable(float x,float y,int HP, int SDmg, float Speed) {
    	Entity e = Lutebox.scene.createEntity();
    	
    	e.attach(Size.class).set(0.6f, 0.6f);
    	e.attach(Position.class).set(x, y);
    	e.attach(Collider.class);
    	e.attach(Consumable.class).SetHpRestore(HP).SetSwordDmgUp(SDmg).SetSpeedUp(Speed);
    	e.attach(Movement.class);
    	
    	return e;
    }
}
