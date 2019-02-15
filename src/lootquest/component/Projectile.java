package lootquest.component;

import lutebox.ecs.Component;
import lutebox.ecs.Entity;

public class Projectile extends Component{
	
	public int damage = 1;
	public Entity shooter = null; 
	public float startingX = 0;
	public float startingY = 0;
	
	public Projectile setShooter(Entity e) {
		shooter = e; 
		return this; 
	}
	
	public Projectile setDamage(int x) {
		damage = x;
		return this;
	}
	
	public Projectile setStartPosition(float x, float y) {
		startingX = x;
		startingY = y;
		return this;
	}
	
	
}
