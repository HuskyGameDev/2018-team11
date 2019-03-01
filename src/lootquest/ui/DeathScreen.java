package lootquest.ui;

import lootquest.dungeon.TileType;
import lutebox.graphics.Texture;

public class DeathScreen extends UI
{
		int xValue = 0;
		int yValue = 0;
		
		public static final int size = 48;
		
		public DeathScreen( int x, int y, Texture image, boolean isSolid, TileType tileType )
		{
			super(x, y, image, isSolid, tileType);
		}
		
		//button to continue the game
		public void continueButton()
		{
			 super.x = 100;
			 super.y = 100;
			 //super.image = "image";
		     super.render();
		}
		  
		//button to quit the game
		public void quitTab()
		{
			 super.x = 100;
			 super.y = 200;
			 //super.image = "image";
		     super.render();
		}
}
