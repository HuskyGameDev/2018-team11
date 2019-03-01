package lootquest.ui;

import lutebox.graphics.Texture;
import lutebox.input.Input;
import lootquest.dungeon.TileType;
import lutebox.core.Lutebox;
import lutebox.ecs.Component;

public class PauseMenu extends UI
{		
		public static final int size = 48;
		
		public PauseMenu( int x, int y, Texture image, boolean isSolid, TileType tileType )
		{
			super(x, y, image, isSolid, tileType);
		}
		
		//button to continue the game
		public void continueButton()
		{
			if (Lutebox.input.getKey(Input.BUTTON_LEFT))
			{
			 super.x = 100;
			 super.y = 100;
			 //super.image = "image";
		     super.render();
			}
		}
		  
		//button to quit the game
		public void quitTab()
		{
			if (Lutebox.input.getKey(Input.BUTTON_MIDDLE))
			{
			 super.x = 100;
			 super.y = 200;
			 //super.image = "image";
		     super.render();
			}
		}
		
		public void action()
		{
			if (Lutebox.input.getKey(Input.BUTTON_LEFT))
			{
			   
			}
		}
}

