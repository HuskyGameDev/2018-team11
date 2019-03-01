package lootquest.system;

import java.awt.image.BufferedImage;

import lootquest.util.TextureCache;
import lutebox.core.Lutebox;

public class Texture {

	SpriteSheet bs, ps;
	
	//Create place holders for different sprite sheets
	private BufferedImage blockSheet = null;
	private BufferedImage playerSheet = null;
	private BufferedImage attackSheet = null;
	
	//create empty arrays that will hold each section of the sprite sheet for each animation
	private BufferedImage[] playerSlash = new BufferedImage[7];
	private BufferedImage[] playerMove = new BufferedImage[7];
	private BufferedImage[] batMove = new BufferedImage[7];
	private BufferedImage[] skeletonMove = new BufferedImage[7];
	
	
	public Texture(){
		
		//Create an instance of the bufferedImageLoader
		BufferedImageLoader loader = new BufferedImageLoader();
		
		//Try loading the sprite sheets as buffered images. and if they don't load correctly throw and exception
		try{
			//blockSheet = loader.loadImage("/");
		    //Lutebox.graphics.drawTexture(TextureCache.get("assets/textures/Player/player-back0.png"), p.x, p.y - s.h, s.w, s.h * 2);
			playerSheet = loader.loadImage("assets/textures/Player/player-move-sheet.png");
			//attackSheet = loader.loadImage("/");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(blockSheet);
		ps = new SpriteSheet(playerSheet);
		
		getTextures();
	}
	
	//grab image in (col, row, width, height) of the sprite sheet
	private void getTextures(){
		playerMove[0] = ps.grabImage(1, 1, 27, 50);
		playerMove[1] = ps.grabImage(2, 1, 27, 50);
		playerMove[2] = ps.grabImage(3, 1, 27, 50);
		playerMove[3] = ps.grabImage(4, 1, 27, 50);
	}
}
