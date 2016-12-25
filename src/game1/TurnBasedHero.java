package game1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TurnBasedHero {
	int health, mana, strength, endurance, intelligence, agility, damage;
	int maxHealth, maxMana;
	final int maxActionTimer;
	double actionTimer = 0.0;
	double damageResistance;
	Color actionBarColor;
	Color actionBarLoadingColor = new Color(174, 250, 132);
	Image image = null;

	public TurnBasedHero(int health, int mana, int strength, int endurance,
			int intelligence, int agility) {
		this.health = 10 * endurance;
		this.maxHealth = 10 * endurance;
		this.mana = 10 * intelligence;
		this.maxMana = 10 * intelligence;
		this.strength = strength;
		this.intelligence = intelligence;
		this.agility = agility;

		this.maxActionTimer = 150;

		try {
			image = ImageIO.read(new File("Graphics/Characters/hero1.png"));
		} catch (IOException e) {
		}
	}

	public void setDamage(Items weapon) {
		if (weapon != null) {
			this.damage = strength + weapon.effectiveness / 2;
		} else {
			this.damage = strength;
		}
	}

	public void setDamageResistance(Items[] armour) {
		int totalArmourEffectiveness = 0;
		for (Items armourPiece : armour) {
			if (armourPiece != null) {
				totalArmourEffectiveness += armourPiece.effectiveness;
			}
		}
		
		this.damageResistance = 1 - (5*endurance + totalArmourEffectiveness) / 100.0;
	}

	public void showBars(Graphics g) {
		ResourceBar heroHealthBar = new ResourceBar(health, maxHealth,
				Color.RED, 100, 280);
		ResourceBar heroManaBar = new ResourceBar(mana, maxMana, Color.BLUE,
				100, 300);
		ResourceBar heroActionBar = new ResourceBar((int) actionTimer,
				maxActionTimer, actionBarColor, 100, 320);
		heroHealthBar.draw(g);
		heroManaBar.draw(g);
		heroActionBar.draw(g);
	}

	public void basicAttack(Enemy enemy) {
		enemy.health -= damage * enemy.damageResistance; 
		enemy.actionTimer -= 3*strength;
		actionTimer -= 60;
	}
	
	public void sweepingAttack(Enemy[] enemies) {
		for (Enemy enemy : enemies) {
			if (enemy != null) {
				enemy.health -= 0.8 * damage * enemy.damageResistance;
			}
		}
		actionTimer -= 120;
	}
	
	public void staggeringAttack(Enemy enemy) {
		 enemy.health -= 1.2*damage*enemy.damageResistance;
		  if (enemy.actionTimer > 8 * strength) {
		   enemy.actionTimer -= 8*strength;
		  } else if (enemy.actionTimer - 8*strength <= 0) {
		   enemy.actionTimer = 0;
		  }
		  actionTimer -= 100;
	}

	public void castSpell(Enemy enemy, Items spell) {
		enemy.health -= 0.2 * intelligence * spell.effectiveness;
		mana -= spell.manaCost; 
	}
	
	public void castMultiTargetSpell(Enemy[] enemies, Items spell) {
		for (Enemy enemy : enemies) {
			if (enemy != null) {
				enemy.health -= 0.2 * intelligence * spell.effectiveness;
			}
		}
		mana -= spell.manaCost;
	}
	
	public void setHealthAndMana(int endurance, int intelligence) {  
	  this.maxMana = 10 * intelligence;
	  this.mana = maxMana;
	  this.maxHealth = 10 * endurance;
	  this.health = maxHealth;
	}
}
