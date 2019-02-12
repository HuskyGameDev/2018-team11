package lootquest.ui;

import lootquest.dungeon.TileType;
import lutebox.core.Lutebox;
import lutebox.graphics.Texture;

public class UI 
{
    public static final int size = 48;
	
	public int x;
	public int y;
	
	public Texture image;
	public boolean isSolid;
	
	//public UIType UIType;
	
	public UI( int x, int y, Texture image, boolean isSolid, TileType tileType ) {
		this.x = x;
		this.y = y;
		
		this.image = image;
		this.isSolid = isSolid;
		
		//this.tileType = tileType;
	}
	
	public String getType () {
	    //return tileType.toString();
		return null;
	}
	
	public void tick() {
		
	}
	
	public void render() {
		Lutebox.graphics.drawTexture(image, x, y, size, size);
	}
	
}