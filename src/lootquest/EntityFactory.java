package lootquest;

import lootquest.component.AI;
import lootquest.component.Boss;
import lootquest.component.Collider;
import lootquest.component.Consumable;
import lootquest.component.Direction;
import lootquest.component.Enemy;
import lootquest.component.EquipedCrossbow;
import lootquest.component.EquipedSword;
import lootquest.component.Gold;
import lootquest.component.Health;
import lootquest.component.Player;
import lootquest.component.HealthBar;
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
        e.attach(Movement.class).setMaxSpeed(4); 
        e.attach(Size.class).set(0.8f, 0.8f); 
        e.attach(Direction.class);
        e.attach(Health.class).set(10, 10);
        e.attach(EquipedSword.class).setCoolDown(0.5f);
        e.attach(Player.class);
        e.attach(Collider.class); 
        e.attach(Gold.class);
        createHealthBar(e, x, y);
        
        return e; 
    }
    
    public static Entity createEnemy1(float x, float y, int type) {
    	Entity e = Lutebox.scene.createEntity(); 
    	
    	e.attach(Position.class).set(x, y);
    	if(type == 2) {
    	  e.attach(Movement.class).setMaxSpeed(2);
    	  e.attach(EquipedCrossbow.class);
    	} else if(type == 1) {
    	  e.attach(Movement.class).setMaxSpeed(3);
    	} else {
    	  e.attach(Movement.class).setMaxSpeed(1);
    	}
    	e.attach(Direction.class); 
        e.attach(Size.class).set(0.8f, 0.8f); 
        e.attach(EquipedSword.class).setCoolDown(1f);
        e.attach(Health.class);
        e.attach(Collider.class);
		e.attach(Enemy.class);
		e.attach(AI.class).setTypeEnemy(type).setRanged(type == 2);
		//e.attach(EquipedCrossbow.class);
        
        return e; 
    }
    
    //createArrow(<entity firing arrow>, <x pos>, <y pos>, <x velocity>, <y velocity>)
    public static Entity createArrow(Entity from, float x,float y,float xVel,float yVel) {
    	Entity e = Lutebox.scene.createEntity();
    	EquipedCrossbow bow = from.get(EquipedCrossbow.class);
    	
    	e.attach(Position.class).set(x, y);
    	if (from.get(Player.class) != null) {
    		e.attach(Movement.class).setMaxSpeed(10).set(xVel*10, yVel*10);
    	} else {
    		e.attach(Movement.class).setMaxSpeed(6).set(xVel*5, yVel*5);
    	}
    	e.attach(Size.class).set(0.3f, 0.3f);
    	e.attach(Collider.class);
    	e.attach(Direction.class);
    	e.attach(Projectile.class).setShooter(from).setDamage(bow.damage).setStartPosition(x, y);
    	
    	
    	return e;
    }
    
    //arguments are x and y position followed by stat values that can be increased.
    public static Entity createConsumable(float x,float y,int HP, int SDmg, float Speed) {
    	Entity e = Lutebox.scene.createEntity();
    	
    	e.attach(Size.class).set(0.6f, 0.6f);
    	e.attach(Position.class).set(x, y);
    	e.attach(Collider.class);
    	e.attach(Consumable.class).SetHpRestore(HP).SetSwordDmgUp(SDmg).SetSpeedUp(Speed);
    	e.attach(Movement.class);
    	
    	return e;
    }

	public static Entity createEnemyBoss(float x, float y) {
		Entity e = Lutebox.scene.createEntity();

		e.attach(Position.class).set(x, y);
		e.attach(Size.class).set(1.8f, 1.8f);
		e.attach(Collider.class);
		e.attach(Movement.class).setMaxSpeed(3);
		e.attach(Boss.class);
		e.attach(EquipedSword.class).setCoolDown(1.5f);
		e.attach(Enemy.class);
		e.attach(Health.class).set(5, 5);
		e.attach(Direction.class);
		
		createHealthBar(e, x, y);

		return e;
	}
	
	public static Entity createHealthBar(Entity above, float x, float y) {
		Entity e = Lutebox.scene.createEntity();
		
		e.attach(Position.class).set(x, y);
		e.attach(Size.class).set(.2f, .8f);
		e.attach(HealthBar.class).setAbove(above);
		
		return e;
	}
	
	 public static Entity createGold(float x,float y, int value) {
	    	Entity e = Lutebox.scene.createEntity();
	    	
	    	e.attach(Size.class).set(0.6f, 0.6f);
	    	e.attach(Position.class).set(x, y);
	    	e.attach(Collider.class);
	    	e.attach(Gold.class).SetGoldValue((int)Math.random() * 100);
	    	e.attach(Movement.class);
	    	
	    	return e;
	    }
}
