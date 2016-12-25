package game1.Enemies;

import game1.Enemy;

public class Smiff extends Enemy {
	public Smiff() {
	  setType("Smiff, Accountant");
	  setHealth(1000);
	  setDamage(30);
	  setDamageResistance(0.2);
	  setMaxActionTimer(50);
	  setGoldValue(1000);
	  setExpValue(1000);
	  setImage("Graphics/Characters/smiff1.jpg");
	}
}