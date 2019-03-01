package lootquest.system;

import lootquest.component.Collider;
import lootquest.component.Direction;
import lootquest.component.Enemy;
import lootquest.component.EquipedCrossbow;
import lootquest.component.EquipedSword;
import lootquest.component.Movement;
import lootquest.component.Player;
import lootquest.component.Position;
import lutebox.core.Lutebox;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;
import lutebox.input.Input;

public class PlayerInputSystem extends IteratingEntitySystem {

	public PlayerInputSystem() {
		super(Filter.include(Player.class, Direction.class).create()); 
	}
	
	private boolean mute = false;
	
	public void updateEntity(Entity e) {
		Direction d = e.get(Direction.class); 
		Movement v = e.get(Movement.class);
		EquipedSword sword = e.get(EquipedSword.class);
		Collider col = e.get(Collider.class);
		Position playerPos = e.get(Position.class);
		EquipedCrossbow bow = e.get(EquipedCrossbow.class);
		
		if (!col.collidingEntities.isEmpty() && col.collidingEntities.get(0).get(Enemy.class) != null) {
			float colX = playerPos.x - col.collidingEntities.get(0).get(Position.class).x;
			float colY = playerPos.y - col.collidingEntities.get(0).get(Position.class).y;
			d.updateFromMovement = false;
			v.set(colX*5, colY*5);
			return;
		}
		
		d.moving = false;
		
		float dx = 0; 
		float dy = 0; 
		float s = v.maxSpeed; 
		if (sword != null && Lutebox.input.getKey(Input.KEY_SPACE)) sword.use();
		if (bow != null && Lutebox.input.getKey(Input.KEY_G)) bow.use();
		d.updateFromMovement = true;
		
		if (Lutebox.input.getKey(Input.KEY_A) || Lutebox.input.getKey(Input.KEY_J) || Lutebox.input.getKey(Input.KEY_LEFT)) {
			d.direction = Direction.LEFT;
			dx -= s; 
			d.moving = true;
		}
		if (Lutebox.input.getKey(Input.KEY_D) || Lutebox.input.getKey(Input.KEY_L) || Lutebox.input.getKey(Input.KEY_RIGHT)) {
			d.direction = Direction.RIGHT;
			dx += s; 
			d.moving = true;
		}
		if (Lutebox.input.getKey(Input.KEY_W) || Lutebox.input.getKey(Input.KEY_I) || Lutebox.input.getKey(Input.KEY_UP)) {
			d.direction = Direction.UP;
			dy -= s; 
			d.moving = true;
		}
		if (Lutebox.input.getKey(Input.KEY_S) || Lutebox.input.getKey(Input.KEY_K) || Lutebox.input.getKey(Input.KEY_DOWN)) {
			d.direction = Direction.DOWN;
			dy += s; 
			d.moving = true;
		}
		if (Lutebox.input.getKey(Input.KEY_M)) {
		    if ( mute == false ) {
		        mute = true;
		        Lutebox.audio.muteAll();
		    } else if ( mute == true ) {
		        mute = false;
		        Lutebox.audio.unmuteALL();
		    }
		}
		if (Lutebox.input.getKey(Input.KEY_Z)) {
		    Lutebox.audio.lowerVol();
		}
		if (Lutebox.input.getKey(Input.KEY_X)) {
		    Lutebox.audio.increaseVol();
		}
		
		v.set(dx, dy); 
	}
	
}
