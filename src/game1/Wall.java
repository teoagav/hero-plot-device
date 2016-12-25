package game1;

public class Wall extends Block {
	
	public Wall(int x, int y, int width, int height) {
		super(x, y, width, height);
		img = Main.rock;
				
	}

	public void collsionCheck(RealTimeHero player) {
		/* If Character's hitbox contains x-side of wall move character
		 * just to the x of that wall */
		
		if (Main.right == true) {
			if (player.hitbox.contains(left, top) || player.hitbox.contains(left, bot)) {
				if (hitbox.getCenterX() > player.hitbox.getMaxX())
					player.hitbox.x = (int) (left - player.hitbox.width - 1);
			}
		}

		if (Main.up == true) {
			if (player.hitbox.contains(left, bot) || player.hitbox.contains(right, bot)) {
				if (hitbox.getCenterY() < player.hitbox.getMinY())
					if (Main.left == true) {
						if (!(hitbox.getCenterX() < player.hitbox.getMinX()))
							player.hitbox.y = (int) (bot + 1);
					} else
						player.hitbox.y = (int) (bot + 1);
			}
		}

		if (Main.down == true) {
			if (player.hitbox.contains(left, top) || player.hitbox.contains(right, top)) {
				if (hitbox.getCenterY() > player.hitbox.getMaxY())
					player.hitbox.y = (int) (top - player.hitbox.height - 1);
			}
		}

		if (Main.left == true) {
			if (player.hitbox.contains(right, top) || player.hitbox.contains(right, bot)) {
				if (hitbox.getCenterX() < player.hitbox.getMinX()) {
					if (Main.up == true) {
						if (!(hitbox.getCenterY() < player.hitbox.getMinY()))
							player.hitbox.x = (int) (right + 1);
					} else
						player.hitbox.x = (int) (right + 1);
				}
			}
		}
	}
}
