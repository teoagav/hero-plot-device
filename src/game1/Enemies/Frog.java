package game1.Enemies;

import game1.Enemy;

public class Frog extends Enemy {
	public Frog() {
		setType("Deathfrog");
		setHealth(10);
		setDamage(5);
		setGoldValue(30);
		setMaxActionTimer(120);
		setExpValue(50);
		setDamageResistance(0.9);
		setImage("Graphics/Characters/frog1.png");
	}
}
