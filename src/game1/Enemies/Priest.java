package game1.Enemies;

import game1.Enemy;

public class Priest extends Enemy {
	public Priest() {
		setType("Priest");
		setHealth(10);
		setDamage(5);
		setMaxActionTimer(120);
		setGoldValue(30);
		setExpValue(50);
		setDamageResistance(1);
		setImage("Graphics/Characters/priest1.png");
	}
}
