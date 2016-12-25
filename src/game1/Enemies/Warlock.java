package game1.Enemies;

import game1.Enemy;

public class Warlock extends Enemy {
	public Warlock() {
		setType("Warlock");
		setHealth(10);
		setDamage(5);
		setGoldValue(30);
		setMaxActionTimer(180);
		setExpValue(50);
		setDamageResistance(1);
		setImage("Graphics/Characters/warlock1.png");
	}
}
