package lootquest.system;

import java.awt.image.BufferedImage;

public class Texture {

	SpriteSheet bs, ps;
	private BufferedImage blockSheet = null;
	private BufferedImage playerSheet = null;
	private BufferedImage attackSheet = null;
	
	private BufferedImage[] playerSlash = new BufferedImage[7];
	BufferedImage[] playerMove = new BufferedImage[12];
	private BufferedImage[] batMove = new BufferedImage[7];
	private BufferedImage[] skeletonMove = new BufferedImage[7];
	
	
	public Texture(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			//blockSheet = loader.loadImage("/");
			playerSheet = loader.loadImage("assets/textures/Player/player-move-sheet.png");
			//attackSheet = loader.loadImage("/");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(blockSheet);
		ps = new SpriteSheet(playerSheet);
		
		getTextures();
	}
	
	//grab image in (col, row, width, height)
	private void getTextures(){
		playerMove[0] = ps.grabImage(1, 1, 27, 50);
		playerMove[1] = ps.grabImage(2, 1, 27, 50);
		playerMove[2] = ps.grabImage(3, 1, 27, 50);
		playerMove[3] = ps.grabImage(4, 1, 27, 50); 
		playerMove[4] = ps.grabImage(1, 2, 27, 50); 
		playerMove[5] = ps.grabImage(2, 2, 27, 50); 
		playerMove[6] = ps.grabImage(3, 2, 27, 50); 
		playerMove[7] = ps.grabImage(4, 2, 27, 50); 
		playerMove[8] = ps.grabImage(1, 3, 27, 50); 
		playerMove[9] = ps.grabImage(2, 3, 27, 50); 
		playerMove[10] = ps.grabImage(3, 3, 27, 50); 
		playerMove[11] = ps.grabImage(4, 3, 27, 50); 
	}
}
