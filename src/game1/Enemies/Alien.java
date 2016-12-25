package game1.Enemies;

import game1.Enemy;

public class Alien extends Enemy {
	public Alien() {
		setType("Alien");
		setHealth(10);
		setDamage(5);
		setGoldValue(30);
		setMaxActionTimer(100);
		setExpValue(50);
		setDamageResistance(0.8);
		setImage("Graphics/Characters/alien1.png");
	}
}
