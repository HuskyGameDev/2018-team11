package lootquest.ui;

import lootquest.dungeon.TileType;
import lootquest.util.TextureCache;
import lutebox.graphics.Texture;


public class HealthUI extends UI
{
	public HealthUI( int x, int y, Texture image, boolean isSolid, TileType tileType )
	{
		super(x, y, image, isSolid, tileType);
	}
	
	
	public void healthBar(int numHearts)
	{
	  for(int i = 0; i < numHearts; i++)
	  {
	     //super(/*health x*/, /*health y*/, TextureCache.get(/*heart image*/), false, /*heart*/);
	  }
	}
	
	public void loseHealth()
	{
		//destroy heart image
		image = null; //garbage cleaner will destroy the image
	    System.out.println("1 heart lost");
	}
	
	public void gainHealth()
	{
		//super(/*health x*/, /*health y*/, TextureCache.get(/*heart image*/), false, /*heart*/);
		System.out.println("1 heart gained");
	}
		
}