package game1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy {
	int health, damage, actionTimer, maxHealth, maxActionTimer, goldValue, expValue;
	String type;
	double damageResistance = 1.0;

	BufferedImage image = null;

	int posY;
	
	public void attack(TurnBasedHero hero) {
		hero.health -= damage * hero.damageResistance;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public void setHealth(int health) {
		this.health = health;
		this.maxHealth = health;
	}
	
	public void setGoldValue(int goldValue) {
		this.goldValue = goldValue;
	}
	
	public void setExpValue(int expValue) {
		this.expValue = expValue;
	}

	public void setMaxActionTimer(int maxActionTimer) {
		this.maxActionTimer = maxActionTimer;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public void setDamageResistance(double damageResistance) {
		this.damageResistance = damageResistance;
	}

	public void setImage(String filePath) {
        try {
            BufferedImage image = ImageIO.read(new File(filePath));
            this.image = image;
        } catch (IOException e) {
        }   
    }

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public void drawInfo(Graphics g) {
		ResourceBar enemyHealthBar = new ResourceBar(health, maxHealth,
				Color.RED, 500, posY + 100);
		ResourceBar enemyActionBar = new ResourceBar(actionTimer,
				maxActionTimer, Color.GREEN, 500, posY + 120);
		enemyHealthBar.draw(g);
		enemyActionBar.draw(g);
	}
}