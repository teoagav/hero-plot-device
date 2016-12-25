package game1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;


public class Block {

	int width, height;
	double top, bot, left, right;
	Rectangle hitbox;
	Image img;
	
	public Block( int x, int y, int width, int height) {		
		hitbox = new Rectangle();
		hitbox.x = x;
		hitbox.y = y;
		hitbox.width = width;
		hitbox.height = height;
		
		top = hitbox.getMinY();
		bot = hitbox.getMaxY();
		left = hitbox.getMinX();
		right = hitbox.getMaxX();
		
		img = Main.floor;
		
	}
	
	public void paint (Graphics g, ImageObserver imgO) {
		g.drawImage(img, hitbox.x, hitbox.y, hitbox.width, hitbox.height, imgO);
	}
	
}
