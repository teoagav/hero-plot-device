package game1;

public class Door extends Block {

	int access;
	
	public Door(int x, int y, int width, int height, int direction) {
		super(x, y, width, height);
		access = direction;
	}
	
	public void collsionCheck(RealTimeHero player) {
		
		if (Main.right == true) {
			if (access == 1) {
				if (player.hitbox.contains(left, top) || player.hitbox.contains(left, bot)) {
					player.hitbox.x = Main.screenWidth - hitbox.x + 30;
					Main.roomX++;
					Main.pickRoom(1);
				}
			}
		}

		if (Main.up == true) {
			if (access == 3) {
				if (player.hitbox.contains(left, bot) || player.hitbox.contains(right, bot)) {
					player.hitbox.y = (int) (Main.screenHeight - hitbox.getMinY() - player.hitbox.height - 30);
					Main.roomY--;
					Main.pickRoom(3);
				}
			}
		}

		if (Main.down == true) {
			if (access == 4) {
				if (player.hitbox.contains(left, top) || player.hitbox.contains(right, top)) {
					player.hitbox.y = Main.screenHeight - hitbox.y + 30;
					Main.roomY++;
					Main.pickRoom(4);
				}
			}
		}

		if (Main.left == true) {
			if (access == 2) {
				if (player.hitbox.contains(right, top) || player.hitbox.contains(right, bot)) {
					player.hitbox.x = (int) (Main.screenWidth - hitbox.getMaxX() - player.hitbox.width - 30);
					Main.roomX--;
					Main.pickRoom(2);
				}
			}
		}
		
	}
	
}