package game1.Enemies;

import game1.Enemy;

public class Dogtopus extends Enemy {
	public Dogtopus() {
		setType("Dogtopus");
		setHealth(10);
		setDamage(2);
		setGoldValue(30);
		setMaxActionTimer(120);
		setExpValue(50);
		setDamageResistance(0.9);
		setImage("Graphics/Characters/dogtopus1.png");
	}
}
