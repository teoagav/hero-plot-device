package game1;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class RealTimeHero{
	
	int speed;

	Rectangle hitbox;
	//Image img;
	BufferedImage image;
	
	public RealTimeHero(int x, int y) {		
		speed = 8;
		
		hitbox = new Rectangle();
		hitbox.x = x;
		hitbox.y = y;
		hitbox.width = 73;
		hitbox.height = 73;
		
		  try {
			   this.image = ImageIO.read(new File("Graphics/Characters/hero1.png"));
			  } catch (IOException e) {
			  }
		
		//img = Main.heroImg;
		
	}
	
	public void moveR() {
		hitbox.x += speed;
		if (hitbox.x > Main.screenWidth - hitbox.width)
			hitbox.x = Main.screenWidth - hitbox.width;		
	}
	
	public void moveL() {
		hitbox.x -= speed;
		if (hitbox.x < 0)
			hitbox.x = 0;
	}
	
	public void moveU() {
		hitbox.y -= speed;
		if (hitbox.y < 0)
			hitbox.y = 0;
	}
	
	public void moveD() {
		hitbox.y += speed;
		if (hitbox.y > Main.screenHeight - hitbox.height)
			hitbox.y = Main.screenHeight - hitbox.height;
	}
	
	public void paint(Graphics g) {
		g.drawImage(image, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);
	}
	
}