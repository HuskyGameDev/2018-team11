package lootquest.system;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;
	
	//Constructor for sprite sheet allowing use of grabImage.
	public SpriteSheet(BufferedImage image){
		this.image = image;
	}
	
	//returns a specified section of a buffered image
	//Made for use within sprite sheets
	public BufferedImage grabImage(int col, int row, int width, int height){
		BufferedImage img = image.getSubimage((col * width) - width, (row * height) - height, width, height);
		return img;
	}
}
