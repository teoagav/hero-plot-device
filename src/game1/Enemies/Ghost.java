package game1.Enemies;

import game1.Enemy;

public class Ghost extends Enemy {
	public Ghost() {
		setType("Ghost");
		setHealth(15);
		setDamage(5);
		setGoldValue(30);
		setMaxActionTimer(180);
		setExpValue(50);
		setDamageResistance(0.8);
		setImage("Graphics/Characters/ghost1.png");
	}
	
	public void specialMove() {
		System.out.println("I am a spoopy, spoopy, hypercharged ghost!");
	}
}
