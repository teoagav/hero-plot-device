package game1;


public class Items {
    String name;
    int value, effectiveness, manaCost;
    boolean isMagic, isWeapon, isPotion, isHelmet, isBodyArmour, isBoots = false;
    boolean isWindMagic, isWaterMagic, isFireMagic, isEarthMagic = false;
    
    public Items(int itemID){
        if (itemID == 1){
            name = "Health Potion";
            value = 25;
            effectiveness = 50;
            isPotion = true;
        }
        else if (itemID == 2){
            name = "Mana Potion";
            value = 25;
            effectiveness = 50;
            isPotion = true;
        }
        else if (itemID == 3){
            name = "Wooden Sword";
            value = 50;
            effectiveness = 5;
            isWeapon = true;
        }
        else if (itemID == 4){
            name = "Copper Sword";
            value = 250;
            effectiveness = 15;
            isWeapon = true;
        }
        else if (itemID == 5){
            name = "Steel Sword";
            value = 1000;
            effectiveness = 35;
            isWeapon = true;
        }
                
        else if (itemID == 6){
            name = "Leather Helmet";
            value = 50;
            effectiveness = 3;
            isHelmet = true;
        }
        else if (itemID == 7){
            name = "Leather Body Armour";
            value = 100;
            effectiveness = 5;
            isBodyArmour = true;
        }
        else if (itemID == 8){
            name = "Leather Boots";
            value = 50;
            effectiveness = 2;
            isBoots = true;
        }
        else if (itemID == 9){
            name = "Chain Helmet";
            value = 100;
            effectiveness = 5;
            isHelmet = true;
        }
        else if (itemID == 10){
            name = "Chain Body Armour";
            value = 250;
            effectiveness = 10;
            isBodyArmour = true;
        }
        else if (itemID == 11){
            name = "Chain Boots";
            value = 100;
            effectiveness = 5;
            isBoots = true;
        }
        
        else if (itemID == 12){
            name = "Dragon Scale Helmet";
            value = 250;
            effectiveness = 10;
            isHelmet = true;
        }
        else if (itemID == 13){
            name = "Dragon Scale Armour";
            value = 1000;
            effectiveness = 20;
            isBodyArmour = true;
        }
        else if (itemID == 14){
            name = "Dragon Scale Boots";
            value = 250;
            effectiveness = 10;
            isBoots = true;
        }
        else if (itemID == 15){
            name = "Pebble Pound";
            value = 50;
            manaCost = 1;
            effectiveness = 3;
            isMagic = true;
            isEarthMagic = true;
        }
        else if (itemID == 16){
            name = "Rock Roundhouse";
            value = 250;
            manaCost = 2;
            effectiveness = 7;
            isMagic = true;
            isEarthMagic = true;
        }
        else if (itemID == 17){
            name = "Boulder Bash";
            value = 1000;
            manaCost = 4;
            effectiveness = 14;
            isMagic = true;
            isEarthMagic = true;
        }
        
        else if (itemID == 18){
            name = "Bruising Breeze";
            value = 50;
            manaCost = 3;
            effectiveness = 3;
            isMagic = true;
            isWindMagic = true;
        }
        else if (itemID == 19){
            name = "Wind Whack";
            value = 250;
            manaCost = 9;
            effectiveness = 9;
            isMagic = true;
            isWindMagic = true;
        }
        else if (itemID == 20){
            name = "Tormenting Tempest";
            value = 1000;
            manaCost = 21;
            effectiveness = 21;
            isMagic = true;
            isWindMagic = true;
        }
        
        else if (itemID == 21){
            name = "Squirt Strike";
            value = 50;
            manaCost = 7;
            effectiveness = 5;
            isMagic = true;
            isWaterMagic = true;
        }
        else if (itemID == 22){
            name = "Wave Wallop";
            value = 250;
            manaCost = 23;
            effectiveness = 15;
            isMagic = true;
            isWaterMagic = true;
        }
        else if (itemID == 23){
            name = "Toppling Torrent";
            value = 1000;
            manaCost = 45;
            effectiveness = 30;
            isMagic = true;
            isWaterMagic = true;
        }
        else if (itemID == 24){
            name = "Sizzling Spark";
            value = 50;
            manaCost = 4;
            effectiveness = 8;
            isMagic = true;
            isFireMagic = true;
        }
        else if (itemID == 25){
            name = "Febrile Flame";
            value = 250;
            manaCost = 10;
            effectiveness = 20;
            isMagic = true;
            isFireMagic = true;
        }
        else if (itemID == 26){
            name = "Pyretic Plasma";
            value = 1000;
            manaCost = 25;
            effectiveness = 50;
            isMagic = true;
            isFireMagic = true;
        }        
    }
}
