package game1;

import java.awt.Rectangle;

public class Gem extends Block{

	boolean active = false;
	
	public Gem(int x, int y, int width, int height) {
		super(x, y, width, height);
		img = Main.jewel;
	}

	public boolean checkCollision(Rectangle player){
		if (player.intersects(hitbox)){
			Main.gems++;
			Main.walls.remove(Main.walls.size() - 1);
			Main.walls.remove(Main.walls.size() - 1);
			Main.walls.remove(Main.walls.size() - 1);
			Main.walls.remove(Main.walls.size() - 1);
			active = false;
			return true;
		} else
			return false;
		
	}
	
}
