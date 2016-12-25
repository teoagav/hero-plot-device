package game1;

import game1.Enemies.Alien;
import game1.Enemies.Dogtopus;
import game1.Enemies.Frog;
import game1.Enemies.Ghost;
import game1.Enemies.Priest;
import game1.Enemies.Smiff;
import game1.Enemies.Warlock;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Main extends JApplet implements ActionListener, KeyListener, MouseListener{
    public Main() throws IOException{
    	
    }
    Timer timer = new Timer(20, this);
    static Random rand = new Random();
    static boolean right, left, up, down; //Key Presses
    
    static int frame, screenWidth, screenHeight;
    
    static Image heroImg;
    static Image floor, rock, jewel;
    Image offScreen;
    Graphics offg;
    boolean characterScreenWorld = false;
    boolean characterScreenDungeon = false;
    boolean shopScreen = false;
    boolean assignmentScreen = false;
    boolean turnBasedScreen = false;
    boolean postBattleScreen = false;
    boolean villageScreen = false;
    boolean innScreen = false;
    boolean dungeonScreen = false;
    boolean worldScreen = true;
    
    boolean overVillage = false;
    boolean overDungeon = false;
    boolean overTemple = false;
    
    Scanner fileScanner = new Scanner(new File("Maps/World.txt"));
    int[][] fileInts = new int [50][50];
    Image[][] tiles = new Image [7][7];
    int xPos = 23;
    int yPos = 13;
    int readInteger;
    int xRelative;
    int yRelative;
    
    BuildingMessages message;
    
    int tempStrength = 5;
    int tempAgility = 5;
    int tempEndurance = 5;
    int tempIntelligence = 5;
    
    Icons icons = new Icons(); 
    int previousStrength = tempStrength;
    int previousAgility = tempAgility;
    int previousEndurance = tempEndurance;
    int previousIntelligence = tempIntelligence;
    int exp = 0;
    int level = 1;
    int playerGold = 1000;
    int healthPots = 0;
    int manaPots = 0;
    TurnBasedHero hero = new TurnBasedHero(10, 10, previousStrength, previousAgility, previousEndurance, previousIntelligence);
    static RealTimeHero player = new RealTimeHero(100,100); ;
            
    Items[] spells = new Items[9];
    Items weapon;
    Items[] armour = new Items[3];    
    Items buyingItem;
    Items[] shopItems = new Items[6];
    
    int unassignedPoints = 10;
    int battleBounty = 0;
    int battleExp = 0;
    
    static int gems;
    static Gem gem;
    static boolean item = false, boss = false;
	static int roomX, roomY, numR;
	static int[][] map;
	static String[][] room = new String[28][28];
	static ArrayList<Block> blocks = new ArrayList<Block>();
	static ArrayList<Wall> walls = new ArrayList<Wall>();
	static ArrayList<Door> doors = new ArrayList<Door>();
	
	File room0 = new File("Maps/NoDoorRoom.txt");
	File room1 = new File("Maps/BlankRoom.txt");
	File room2 = new File("Maps/BlockRoom.txt");
	File room3 = new File("Maps/CornerRoom.txt");

	File dead1 = new File("Maps/SpecialRoomLeft.txt");	
	File dead2 = new File("Maps/SpecialRoomRight.txt");
	File dead3 = new File("Maps/SpecialRoomDown.txt");
	File dead4 = new File("Maps/SpecialRoomUp.txt");
	
	File room4 = new File("Maps/urRoom.txt");
	File room5 = new File("Maps/ulRoom.txt");
	File room6 = new File("Maps/drRoom.txt");
	File room7 = new File("Maps/dlRoom.txt");
	
	static ArrayList<File> roomList = new ArrayList<File>();
	static ArrayList<int[]> dataList = new ArrayList<int[]>();
     
    Enemy[] enemies = new Enemy[3];
	Enemy[] originalEnemies = new Enemy[3];
    
	static JPanel contentPane;
	
	JPanel characterScreenPanel;
	JPanel shopScreenPanel;
	JPanel villageScreenPanel;
	JPanel innScreenPanel;
	
	Color buttonBlue = new Color(36, 108, 242, 128);

	boolean basicAttack, staggeringAttack;
	Items currentSpellChoice = null;
	boolean lostBattle, wonBattle, inWorld, inDungeon;
	
	JButton btnBasicAttack, spell1, spell2,
	spell3, spell4, spell5,
	spell6;
	
	JPanel enemyMenu;
	JButton btnEnemy1 = new JButton("Enemy 1");
	JButton btnEnemy2 = new JButton("Enemy 2");
	JButton btnEnemy3 = new JButton("Enemy 3");
	
	JButton btnStaggeringAttack = new JButton("Staggering Blow");
	JButton btnSweepingAttack = new JButton("Sweeping Blow");
	
	JButton[] enemyButtons = { btnEnemy1, btnEnemy2, btnEnemy3 };
	
	int travelBattleCounter = randInt(20, 75);

    public void init() {
        frame = 0;
        screenWidth = 700;
        screenHeight = 700;
        this.setSize(screenWidth, screenHeight);
        this.addKeyListener(this);
        this.addMouseListener(this);
    	
        offScreen = createImage(this.getWidth(), this.getHeight());
        offg = offScreen.getGraphics();     

        heroImg = getImage(getDocumentBase(), "Graphics/Characters/hero1.png"); 
        floor = getImage(getDocumentBase(), "Graphics/Tiles/Dungeon Floor.png"); 
 		rock = getImage(getDocumentBase(), "Graphics/Tiles/Dungeon Wall.png"); 
 		jewel = getImage(getDocumentBase(), "Graphics/Tiles/Gem.png");
        
        for (int xInts = 0; xInts<50; xInts++){
            for (int yInts = 0; yInts<50; yInts++){
                fileInts[yInts][xInts] = fileScanner.nextInt();
            }
        }        
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        getContentPane().setLayout(null);
        
        roomList.add(room0);
		dataList.add(new int[] {0,0,0,0} );
		roomList.add(dead1);
		dataList.add(new int[] {1,0,0,0} );
		roomList.add(dead2);
		dataList.add(new int[] {0,1,0,0} );
		roomList.add(dead3);
		dataList.add(new int[] {0,0,0,1} );
		roomList.add(dead4);
		dataList.add(new int[] {0,0,1,0} );
		roomList.add(room1);
		dataList.add(new int[] {1,1,1,1} );
		roomList.add(room2);
		dataList.add(new int[] {1,1,1,1} );
		roomList.add(room3);
		dataList.add(new int[] {1,1,1,1} );
		roomList.add(room4);
		dataList.add(new int[] {0,1,1,0} );
		roomList.add(room5);
		dataList.add(new int[] {1,0,1,0} );
		roomList.add(room6);
		dataList.add(new int[] {0,1,0,1} );
		roomList.add(room7);
		dataList.add(new int[] {1,0,0,1} );
        
    }
    
    public void characterScreenActive() {   
        characterScreenPanel = new JPanel();
        characterScreenPanel.setSize(700, 700);
        contentPane.add(characterScreenPanel);
        characterScreenPanel.setLayout(null);
        
        JButton addStrength = new JButton("");
        JButton addAgility = new JButton("");
        JButton addEndurance = new JButton("");
        JButton addIntelligence = new JButton("");
        JButton subtractStrength = new JButton("");
        JButton subtractAgility = new JButton("");
        JButton subtractEndurance = new JButton("");
        JButton subtractIntelligence = new JButton("");
        
        addStrength.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if ((characterScreenDungeon || characterScreenWorld) && unassignedPoints > 0) {
                    tempStrength += 1;
                    unassignedPoints -= 1;
                }
            }
        });
        addStrength.setBounds(350, 100, 25, 25);
        characterScreenPanel.add(addStrength);
        
        addAgility.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if ((characterScreenDungeon || characterScreenWorld) && unassignedPoints > 0) {
                    tempAgility += 1;
                    unassignedPoints -= 1;
                }
            }
        });
        addAgility.setBounds(350, 200, 25, 25);
        characterScreenPanel.add(addAgility);

        addEndurance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if ((characterScreenDungeon || characterScreenWorld) && unassignedPoints > 0) {
                    tempEndurance += 1;
                    unassignedPoints -= 1;
                }
            }
        });
        addEndurance.setBounds(350, 300, 25, 25);
        characterScreenPanel.add(addEndurance);

        addIntelligence.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if ((characterScreenDungeon || characterScreenWorld) && unassignedPoints > 0) {
                    tempIntelligence += 1;
                    unassignedPoints -= 1;
                }
            }
        });
        addIntelligence.setBounds(350, 400, 25, 25);
        characterScreenPanel.add(addIntelligence);

        subtractStrength.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if ((characterScreenDungeon || characterScreenWorld) && tempStrength != previousStrength) {
                    tempStrength -= 1;
                    unassignedPoints += 1;
                }
            }
        });
        subtractStrength.setBounds(400, 100, 25, 25);
        characterScreenPanel.add(subtractStrength);

        subtractAgility.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if ((characterScreenDungeon || characterScreenWorld) && tempAgility != previousAgility) {
                    tempAgility -= 1;
                    unassignedPoints += 1;
                }
            }
        });
        subtractAgility.setBounds(400, 200, 25, 25);
        characterScreenPanel.add(subtractAgility);

        subtractEndurance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if ((characterScreenDungeon || characterScreenWorld) && tempEndurance != previousEndurance) {
                    tempEndurance -= 1;
                    unassignedPoints += 1;
                }
            }
        });
        subtractEndurance.setBounds(400, 300, 25, 25);
        characterScreenPanel.add(subtractEndurance);

        subtractIntelligence.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if ((characterScreenDungeon || characterScreenWorld) && tempIntelligence != previousIntelligence) {
                    tempIntelligence -= 1;
                    unassignedPoints += 1;
                }
            }
        });
        subtractIntelligence.setBounds(400, 400, 25, 25);
        characterScreenPanel.add(subtractIntelligence);
        
        JButton done = new JButton("Done");
        done.setBounds(300, 635, 100, 30);
        characterScreenPanel.add(done);
        done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if ((characterScreenDungeon || characterScreenWorld)) {
                    hero.strength = tempStrength;
                    hero.agility = tempAgility;
                    hero.endurance = tempEndurance;
                    hero.intelligence = tempIntelligence;
                    hero.setHealthAndMana(hero.endurance, hero.intelligence);
                    
                    previousStrength = tempStrength;
                    previousAgility = tempAgility;
                    previousEndurance = tempEndurance;
                    previousIntelligence = tempIntelligence;
                                        
                }
                if (characterScreenDungeon){
	                characterScreenDungeon = false;
	                dungeonScreen = true;
	                getContentPane().removeAll();
	                getContentPane().revalidate();
                }
                if (characterScreenWorld){
                	characterScreenWorld = false;
	                worldScreen = true;
	                getContentPane().removeAll();
	                getContentPane().revalidate();
                }
            }
        });
        characterScreenPanel.setBackground(Color.black);

    }
    
    public void shopScreenActive() {    
        shopScreenPanel = new JPanel();
        shopScreenPanel.setSize(700, 700);
        contentPane.add(shopScreenPanel);
        shopScreenPanel.setLayout(null);
        
        JButton buy1 = new JButton("");
        JButton buy2 = new JButton("");
        JButton buy3 = new JButton("");
        JButton buy4 = new JButton("");
        JButton buy5 = new JButton("");
        JButton buy6 = new JButton("");
        JButton back = new JButton("");
        	
        buy1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (shopScreen && !assignmentScreen && playerGold >= shopItems[0].value) {
                    playerGold -= shopItems[0].value;
                    buyingItem = shopItems[0];
                    if (buyingItem.isHelmet)
                    {
                    	if (armour[0] == null)
                    		armour[0] = buyingItem;
                    	else if(armour[0].name.equals(armour[0].name))
                    		playerGold += buyingItem.value;
                    	else
                    		armour[0] = buyingItem;
                    }
                    else if (buyingItem.isBodyArmour)
                    {
                    	if (armour[1] == null)
                    		armour[1] = buyingItem;
                    	else if(armour[1].name.equals(armour[1].name))
                    		playerGold += buyingItem.value;
                    	else
                    		armour[1] = buyingItem;
                    }
                    else if (buyingItem.isBoots)
                    {
                    	if (armour[2] == null)
                    		armour[2] = buyingItem;
                    	else if(armour[2].name.equals(armour[2].name))
                    		playerGold += buyingItem.value;
                    	else
                    		armour[2] = buyingItem;
                    }
                    else if (buyingItem.isWeapon)
                    	weapon = buyingItem;
                    else if (buyingItem.isMagic){
                        assignmentScreen = true;
                        requestFocus();
                    }
                }
            }
        });
        buy1.setBounds(350, 100, 50, 25);
        shopScreenPanel.add(buy1);
        
        buy2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (shopScreen && !assignmentScreen && playerGold >= shopItems[1].value) {
                    playerGold -= shopItems[1].value;
                    buyingItem = shopItems[1];
                    if (buyingItem.isHelmet)
                    {
                    	if (armour[0] == null)
                    		armour[0] = buyingItem;
                    	else if(armour[0].name.equals(armour[0].name))
                    		playerGold += buyingItem.value;
                    	else
                    		armour[0] = buyingItem;
                    }
                    else if (buyingItem.isBodyArmour)
                    {
                    	if (armour[1] == null)
                    		armour[1] = buyingItem;
                    	else if(armour[1].name.equals(armour[1].name))
                    		playerGold += buyingItem.value;
                    	else
                    		armour[1] = buyingItem;
                    }
                    else if (buyingItem.isBoots)
                    {
                    	if (armour[2] == null)
                    		armour[2] = buyingItem;
                    	else if(armour[2].name.equals(armour[2].name))
                    		playerGold += buyingItem.value;
                    	else
                    		armour[2] = buyingItem;
                    }
                    else if (buyingItem.isWeapon)
                    	weapon = buyingItem;
                    else
                        assignmentScreen = true;
                        requestFocus();
                }
            }
        });
        buy2.setBounds(350, 200, 50, 25);
        shopScreenPanel.add(buy2);
        
        buy3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (shopScreen && !assignmentScreen && playerGold >= shopItems[2].value) {
                    playerGold -= shopItems[2].value;
                    buyingItem = shopItems[2];
                    if (buyingItem.isHelmet)
                    {
                    	if (armour[0] == null)
                    		armour[0] = buyingItem;
                    	else if(armour[0].name.equals(armour[0].name))
                    		playerGold += buyingItem.value;
                    	else
                    		armour[0] = buyingItem;
                    }
                    else if (buyingItem.isBodyArmour)
                    {
                    	if (armour[1] == null)
                    		armour[1] = buyingItem;
                    	else if(armour[1].name.equals(armour[1].name))
                    		playerGold += buyingItem.value;
                    	else
                    		armour[1] = buyingItem;
                    }
                    else if (buyingItem.isBoots)
                    {
                    	if (armour[2] == null)
                    		armour[2] = buyingItem;
                    	else if(armour[2].name.equals(armour[2].name))
                    		playerGold += buyingItem.value;
                    	else
                    		armour[2] = buyingItem;
                    }
                    else if (buyingItem.isWeapon)
                    	weapon = buyingItem;
                    else
                        assignmentScreen = true;
                        requestFocus();
                }
            }
        });
        buy3.setBounds(350, 300, 50, 25);
        shopScreenPanel.add(buy3);
        
        buy4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (shopScreen && !assignmentScreen && playerGold >= shopItems[3].value) {
                    playerGold -= shopItems[3].value;
                    buyingItem = shopItems[3];
                    if (buyingItem.isHelmet)
                    {
                    	if (armour[0] == null)
                    		armour[0] = buyingItem;
                    	else if(armour[0].name.equals(armour[0].name))
                    		playerGold += buyingItem.value;
                    	else
                    		armour[0] = buyingItem;
                    }
                    else if (buyingItem.isBodyArmour)
                    {
                    	if (armour[1] == null)
                    		armour[1] = buyingItem;
                    	else if(armour[1].name.equals(armour[1].name))
                    		playerGold += buyingItem.value;
                    	else
                    		armour[1] = buyingItem;
                    }
                    else if (buyingItem.isBoots)
                    {
                    	if (armour[2] == null)
                    		armour[2] = buyingItem;
                    	else if(armour[2].name.equals(armour[2].name))
                    		playerGold += buyingItem.value;
                    	else
                    		armour[2] = buyingItem;
                    }
                    else if (buyingItem.isWeapon)
                    	weapon = buyingItem;
                    else
                        assignmentScreen = true;
                        requestFocus();

                }
            }
        });
        buy4.setBounds(350, 400, 50, 25);
        shopScreenPanel.add(buy4);
        
        buy5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (shopScreen && !assignmentScreen && playerGold >= shopItems[4].value) {
                    playerGold -= shopItems[4].value;
                    buyingItem = shopItems[4];
                    healthPots += 1;
                }
            }
        });
        buy5.setBounds(350, 500, 50, 25);
        shopScreenPanel.add(buy5);

        buy6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (shopScreen && !assignmentScreen && playerGold >= shopItems[5].value) {
                    playerGold -= shopItems[5].value;
                    buyingItem = shopItems[5];
                    manaPots += 1;
                }
            }
        });
        buy6.setBounds(350, 600, 50, 25);
        shopScreenPanel.add(buy6);
        
        back.setBounds(500, 550, 100, 30);
        shopScreenPanel.add(back);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (shopScreen && !assignmentScreen) {
                    shopScreen = false;
                    villageScreen = true;
                    getContentPane().removeAll();
                    getContentPane().revalidate();
                    villageScreenActive();
                }
            }
        });
                
        shopScreenPanel.setBackground(Color.black);
    }
    
    public void villageScreenActive(){
    	villageScreenPanel = new JPanel();
    	villageScreenPanel.setSize(700, 700);
        contentPane.add(villageScreenPanel);
        villageScreenPanel.setLayout(null);
        message = new BuildingMessages(1);
        if (xPos == 4 && yPos == 5)
        	message = new BuildingMessages(1);
        if (xPos == 4 && yPos == 27)
        	message = new BuildingMessages(2);
        if (xPos == 10 && yPos == 43)
        	message = new BuildingMessages(3);
        if (xPos == 23 && yPos == 13)
        	message = new BuildingMessages(4);
        if (xPos == 25 && yPos == 13)
        	message = new BuildingMessages(5);
        if (xPos == 26 && yPos == 24)
        	message = new BuildingMessages(6);
        if (xPos == 27 && yPos == 35)
        	message = new BuildingMessages(7);
        if (xPos == 34 && yPos == 14)
        	message = new BuildingMessages(8);
        if (xPos == 44 && yPos == 10)
        	message = new BuildingMessages(9);
        if (xPos == 40 && yPos == 33)
        	message = new BuildingMessages(10);
        
        
        JButton turnBattle = new JButton("Hunt");
        JButton shop = new JButton("Shop");
        JButton inn = new JButton("Inn");
        JButton leave = new JButton("Leave");
        
        turnBattle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	turnBasedScreen = true;
            	villageScreen = false;
            	inWorld = false;
				getContentPane().removeAll();
				getContentPane().revalidate();
				turnBased(false);
            }
        });
        turnBattle.setBounds(300, 450, 100, 50);
        villageScreenPanel.add(turnBattle);   
        
        shop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                villageScreen = false; 
            	shopScreen = true;    
            	shopItems[0] = new Items(randInt(3, 26));
            	shopItems[1] = new Items(randInt(3, 26));
            	shopItems[2] = new Items(randInt(3, 26));
            	shopItems[3] = new Items(randInt(3, 26));
            	shopItems[4] = new Items(1);
            	shopItems[5] = new Items(2);
            	getContentPane().removeAll();
                getContentPane().revalidate();
                shopScreenActive();
            }
        });
        
        shop.setBounds(300, 500, 100, 50);
        villageScreenPanel.add(shop);
        
        inn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                villageScreen = false; 
            	innScreen = true;    
            	playerGold -= 10;
            	hero.health = hero.maxHealth;
            	hero.mana = hero.maxMana;            	
            	getContentPane().removeAll();
                getContentPane().revalidate();
                innScreenActive();
            }
        });
        
        inn.setBounds(300, 550, 100, 50);
        villageScreenPanel.add(inn);
        
        leave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                villageScreen = false; 
            	worldScreen = true;    
            	getContentPane().removeAll();
                getContentPane().revalidate();
            }
        });
        leave.setBounds(300, 600, 100, 50);
        villageScreenPanel.add(leave);        
        villageScreenPanel.setBackground(Color.black);
    }
    
    public void innScreenActive(){
    	innScreenPanel = new JPanel();
    	innScreenPanel.setSize(700, 700);
        contentPane.add(innScreenPanel);
        innScreenPanel.setLayout(null);
        
        message = new BuildingMessages(randInt(11, 15));
        
        JButton leave = new JButton("Continue");
        
        leave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                innScreen = false; 
            	villageScreen = true;    
            	getContentPane().removeAll();
                getContentPane().revalidate();
                villageScreenActive();
            }
        });
        
        leave.setBounds(300, 500, 100, 50);
        innScreenPanel.add(leave);
        
        innScreenPanel.setBackground(Color.black);
    }
    
    public void turnBased(boolean isBossBattle) {
    	hero.setDamage(weapon);
		hero.setDamageResistance(armour);
		
		generateEnemies(isBossBattle);
		
		originalEnemies[0] = enemies[0];
		originalEnemies[1] = enemies[1];
		originalEnemies[2] = enemies[2];
				
		if (enemies[0] != null) { 
			btnEnemy1.setText(enemies[0].type);
		} if (enemies[1] != null) {
			btnEnemy2.setText(enemies[1].type);
		} if (enemies[2] != null) {
			btnEnemy3.setText(enemies[2].type);
		}

		ImageIcon attack = new ImageIcon("Graphics/Icons/attack1.png");
		ImageIcon magic = new ImageIcon("Graphics/Icons/magic1.png");
		ImageIcon items = new ImageIcon("Graphics/Icons/items1.png");
		ImageIcon runAway = new ImageIcon("Graphics/Icons/runAway1.png");
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.LEFT);
		tabbedPane.setBounds(40, 500, 442, 140);
		contentPane.add(tabbedPane);

		enemyMenu = new JPanel();
		enemyMenu.setForeground(Color.BLACK);
		FlowLayout fl_enemyMenu = (FlowLayout) enemyMenu.getLayout();
		fl_enemyMenu.setAlignOnBaseline(true);
		enemyMenu.setBounds(482, 518, 150, 163);
		enemyMenu.setVisible(false);
		contentPane.add(enemyMenu);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Attack", attack, panel, "Hit him with your weapon!");
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		btnBasicAttack = new JButton("Basic Attack");
		btnBasicAttack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (hero.actionTimer >= 60) {
					enemyMenu.setVisible(true);
					basicAttack = true;
				}
			}
		});
		panel.add(btnBasicAttack);
		
		if (weapon != null) {
			panel.add(btnStaggeringAttack);
			panel.add(btnSweepingAttack);
			
			btnStaggeringAttack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (hero.actionTimer >= 100) {
						enemyMenu.setVisible(true);
						staggeringAttack = true;
					}
				}
			});
			
			btnSweepingAttack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (hero.actionTimer >= 120) {
						hero.sweepingAttack(enemies);
					}
				}
			});
		}

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Magic", magic, panel_1, "Use a spell!");
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		if (spells[0] != null){
			spell1 = new JButton(spells[0].name);
			spell1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (hero.mana >= spells[0].manaCost) {
						if (hero.mana >= spells[0].manaCost) {
							if (spells[0].isWaterMagic || spells[0].isWindMagic) {
								hero.castMultiTargetSpell(enemies, spells[0]);
							} else {
								enemyMenu.setVisible(true);
								currentSpellChoice = spells[0];
							}
						}
					}
				}
			});
			panel_1.add(spell1);
		}
		
		if (spells[1] != null){
			spell2 = new JButton(spells[1].name);
			spell2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (hero.mana >= spells[1].manaCost) {
						if (spells[1].isWaterMagic || spells[1].isWindMagic) {
							hero.castMultiTargetSpell(enemies, spells[1]);
						} else {
							enemyMenu.setVisible(true);
							currentSpellChoice = spells[1];
						}
					}
				}
			});
			panel_1.add(spell2);
		}
		
		if (spells[2] != null){
			spell3 = new JButton(spells[2].name);
			spell3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (hero.mana >= spells[2].manaCost) {
						if (spells[2].isWaterMagic || spells[2].isWindMagic) {
							hero.castMultiTargetSpell(enemies, spells[2]);
						} else {
							enemyMenu.setVisible(true);
							currentSpellChoice = spells[2];
						}
					}
				}
			});
			panel_1.add(spell3);
		}
		
		if (spells[3] != null){
			spell4 = new JButton(spells[3].name);
			spell4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (hero.mana >= spells[3].manaCost) {
						if (spells[3].isWaterMagic || spells[3].isWindMagic) {
							hero.castMultiTargetSpell(enemies, spells[3]);
						} else {
							enemyMenu.setVisible(true);
							currentSpellChoice = spells[3];
						}
					}
				}
			});
			panel_1.add(spell4);
		}
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Items", items, panel_2,
				"Use an item from your backpack!");
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnHealthPotion = new JButton("Health Potion: \n" + healthPots);
		btnHealthPotion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (healthPots > 0) {
					if ((hero.health + 20) < hero.maxHealth) {
						hero.health += 20;
					} else {
						hero.health += (hero.maxHealth - hero.health);
					}
					healthPots -= 1;
				}
			}
		});
		panel_2.add(btnHealthPotion);

		JButton btnManaPotion = new JButton("Mana Potion");
		btnManaPotion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (manaPots > 0) {
					if ((hero.mana + 20) < hero.maxMana) {
						hero.mana += 20;
					} else {
						hero.mana += (hero.maxMana - hero.mana);
					}
					manaPots -= 1;
				}
			}
		});
		panel_2.add(btnManaPotion);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Flee!", runAway, panel_3, "Run away like a coward!");
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnFlee = new JButton("Go on...");
		btnFlee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hero.actionTimer = 0;
				getContentPane().removeAll();
				getContentPane().revalidate();
				if (inWorld) {
					turnBasedScreen = false;
					worldScreen = true;
				}
				else {
					turnBasedScreen = false;
					villageScreen = true;
					villageScreenActive();
				}

			}
		});
		panel_3.add(btnFlee);
		
		enemyButtons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (basicAttack || staggeringAttack) {
					attackTarget(enemies[0], enemyMenu);
				} else if (currentSpellChoice != null) {
					hero.castSpell(enemies[0], currentSpellChoice);
					currentSpellChoice = null;
					enemyMenu.setVisible(false);
				}
			}
		});

		enemyButtons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (basicAttack || staggeringAttack) {
					attackTarget(enemies[1], enemyMenu);
				} else if (currentSpellChoice != null) {
					hero.castSpell(enemies[1], currentSpellChoice);
					currentSpellChoice = null;
					enemyMenu.setVisible(false);
				}
			}
		});

		enemyButtons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (basicAttack || staggeringAttack) {
					attackTarget(enemies[2], enemyMenu);
				} else if (currentSpellChoice != null) {
					hero.castSpell(enemies[2], currentSpellChoice);
					currentSpellChoice = null;
					enemyMenu.setVisible(false);
				}
			}
		});

		for (int i = 0; i < enemies.length; i++) {
			if (enemies[i] != null) {
				enemyMenu.add(enemyButtons[i]);
			}
		}
	}
    
    public void turnBasedFin(String result) {
		contentPane.setLayout(null);
		
		JButton next = new JButton("Next");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				battleBounty = 0;
				battleExp = 0;
				getContentPane().removeAll();
				getContentPane().revalidate();
				
				if (inWorld) {
					postBattleScreen = false;
					worldScreen = true;
				} else if(inDungeon) {
					postBattleScreen = false;
					dungeonScreen = true;
				} else {
					postBattleScreen = false;
					villageScreen = true;
					villageScreenActive();	
				}

			}
		});
		
		next.setBounds(300, 500, 100, 100);
		contentPane.add(next);
		
		for (Enemy enemy : originalEnemies) {
			if (enemy != null) {
				battleBounty += enemy.goldValue;
				battleExp += enemy.expValue;
			}
		}
		
		if (result.equals("won")) {
			exp += battleExp;
			playerGold += battleBounty;
			if (exp > 1000) {			
				exp -= 1000;
				level++;
				unassignedPoints += 3;
			}
		}
		else if (result.equals("lost")) {
			hero.health = 1;
			playerGold -= 0.5 * battleBounty;
			exp += 0.2 * battleExp;
			
			if (exp > 1000) {			
				exp -= 1000;
				unassignedPoints += 3;
			}

			if (playerGold < 0)
				playerGold = 0;
		}
	}
   
    public void paint(Graphics g){
    	super.paint(offg);
    	
    	if (worldScreen){
	    	xRelative = -3;
	        yRelative = -3;
	        for (int xTile = 0; xTile<7; xTile++){
	            for (int yTile = 0; yTile<7; yTile++){
	                if (fileInts[xPos+xRelative][yPos+yRelative] == 0)
	                    tiles[xTile][yTile] = getImage(getDocumentBase(), "Graphics/Tiles/Water.png");
	                else if (fileInts[xPos+xRelative][yPos+yRelative] == 1)
	                    tiles[xTile][yTile] = getImage(getDocumentBase(), "Graphics/Tiles/Road.png");
	                else if (fileInts[xPos+xRelative][yPos+yRelative] == 2)
	                    tiles[xTile][yTile] = getImage(getDocumentBase(), "Graphics/Tiles/Bridge.png");
	                else if (fileInts[xPos+xRelative][yPos+yRelative] == 3)
	                    tiles[xTile][yTile] = getImage(getDocumentBase(), "Graphics/Tiles/Village.png");
	                else if (fileInts[xPos+xRelative][yPos+yRelative] == 4)
	                    tiles[xTile][yTile] = getImage(getDocumentBase(), "Graphics/Tiles/Grass.png");
	                else if (fileInts[xPos+xRelative][yPos+yRelative] == 5)
	                    tiles[xTile][yTile] = getImage(getDocumentBase(), "Graphics/Tiles/Hills.png");
	                else if (fileInts[xPos+xRelative][yPos+yRelative] == 6)
	                    tiles[xTile][yTile] = getImage(getDocumentBase(), "Graphics/Tiles/Forest.png");
	                else if (fileInts[xPos+xRelative][yPos+yRelative] == 7)
	                    tiles[xTile][yTile] = getImage(getDocumentBase(), "Graphics/Tiles/Desert.png");
	                else if (fileInts[xPos+xRelative][yPos+yRelative] == 8)
	                    tiles[xTile][yTile] = getImage(getDocumentBase(), "Graphics/Tiles/Snow.png");
	                else if (fileInts[xPos+xRelative][yPos+yRelative] == 9)
	                    tiles[xTile][yTile] = getImage(getDocumentBase(), "Graphics/Tiles/Mountain.png");
	                else if (fileInts[xPos+xRelative][yPos+yRelative] == 10)
	                    tiles[xTile][yTile] = getImage(getDocumentBase(), "Graphics/Tiles/Dungeon.png");
	                else if (fileInts[xPos+xRelative][yPos+yRelative] == 11)
	                	tiles[xTile][yTile] = getImage(getDocumentBase(), "Graphics/Tiles/Temple Tile.png");
	                
	                yRelative += 1;
	            }
	            xRelative += 1;
	            yRelative = -3;
	        }
	        if (fileInts[xPos][yPos] == 3)
	        	overVillage = true;
	        else
	        	overVillage = false;
	        
	        if (fileInts[xPos][yPos] == 10)
	        	overDungeon = true;
	        else
	        	overDungeon = false;
	        
	        if (fileInts[xPos][yPos] == 11) {
	        	overTemple = true;
	        } else {
	        	overTemple = false;
	        }
	        
	        for (int xTile = 0; xTile<7; xTile++){
	            for (int yTile = 0; yTile<7; yTile++){
	                offg.drawImage(tiles[xTile][yTile], xTile*100, yTile*100, 100, 100, this);
	            }
	        }
	        offg.drawImage(heroImg, 300,300, 100, 100, this);
    	}   	
    	
    	
        if (characterScreenDungeon || characterScreenWorld){
        	offg.drawImage(icons.strengthImage, 15, 85, 60, 60, null);
        	offg.drawImage(icons.agilityImage, 15, 185, 60, 60, null);
        	offg.drawImage(icons.enduranceImage, 15, 285, 60, 60, null);
        	offg.drawImage(icons.intelligenceImage, 15, 385, 60, 60, null);
            offg.setFont(new Font("Ms Sherif", Font.PLAIN, 40));
            offg.setColor(Color.WHITE);
            offg.drawString("Strength " + tempStrength, 100, 130);
            offg.drawString("Agility " + tempAgility, 100, 230);
            offg.drawString("Endurance " + tempEndurance, 100, 330);
            offg.drawString("Intelligence " + tempIntelligence, 100, 430);
            offg.drawString("Unassigned Attribute Points " + unassignedPoints, 50, 600);
            
            offg.setFont(new Font("Ms Sherif", Font.PLAIN, 20));
            offg.drawString("Level: " + level, 100, 480);
            offg.drawString("Exp: " + exp + " /1000", 100, 530);
            
            offg.setColor(Color.RED);
            offg.drawString("+", 357, 120);
            offg.drawString("+", 357, 220);
            offg.drawString("+", 357, 320);
            offg.drawString("+", 357, 420);
            
            offg.drawString("-", 410, 119);
            offg.drawString("-", 410, 219);
            offg.drawString("-", 410, 319);
            offg.drawString("-", 410, 419);
        }
        
        if (characterScreenDungeon || characterScreenWorld || shopScreen){
            offg.setColor(Color.WHITE);
            offg.setFont(new Font("Ms Sherif", Font.PLAIN, 18));
            offg.drawString("Magic", 435, 125);
            offg.setFont(new Font("Ms Sherif", Font.PLAIN, 15));
            for (int spell = 0; spell < 4; spell++){
                if (spells[spell] != null){
                    offg.drawString((spell + 1)+ ". " + spells[spell].name, 435, 150 + (spell*50));
                    offg.drawString("Strength: " + spells[spell].effectiveness + ". Cost: " + spells[spell].manaCost, 435, 175 + (spell*50));
                }
            }
            
                        
            offg.setFont(new Font("Ms Sherif", Font.PLAIN, 18));
            offg.drawString("Armour", 435, 400);
            offg.drawString("Weapon", 435, 350);            
            offg.setFont(new Font("Ms Sherif", Font.PLAIN, 15));
            for (int item = 0; item < 3; item++){
                if (armour[item] != null)
                    offg.drawString(armour[item].name + ". Strength: " + armour[item].effectiveness, 435, 425 + (item*25));
            }
            if (weapon != null)
            	offg.drawString(weapon.name + ". Strength: " + weapon.effectiveness, 435, 375);   
            
            offg.setFont(new Font("Ms Sherif", Font.PLAIN, 18));
            offg.drawString("Gold " + playerGold, 435, 500);
            
            offg.setFont(new Font("Ms Sherif", Font.PLAIN, 15));
            offg.drawString("Health Potions " + healthPots, 435, 520);
            offg.drawString("Mana Potions " + manaPots, 435, 540);
        }
        
        if (shopScreen){
            offg.setFont(new Font("Ms Sherif", Font.PLAIN, 40));
            offg.setColor(Color.WHITE);
            offg.drawString("Shop", 300, 60);
            offg.setFont(new Font("Ms Sherif", Font.PLAIN, 20));
            for (int item = 0; item < 6; item++){
                offg.drawString(shopItems[item].name + " " + shopItems[item].value, 25, 120 + (100*item));
            }    
            
            offg.setColor(Color.RED);
            offg.drawString("Buy", 359, 120);
            offg.drawString("Buy", 359, 220);
            offg.drawString("Buy", 359, 320);
            offg.drawString("Buy", 359, 420);
            offg.drawString("Buy", 359, 520);
            offg.drawString("Buy", 359, 620);
            offg.drawString("Back", 527, 572);          
        }
        
        if (assignmentScreen){
            offg.setColor(Color.CYAN);
            offg.fillRect(50,275,300,150);
            offg.setFont(new Font("Ms Sherif", Font.PLAIN, 20));
            offg.setColor(Color.RED);
            offg.drawString("Assign your item to your magic &", 50, 300);
            offg.drawString("weapons inventory using numbers", 50, 330);
            offg.drawString("1-4 (note this will replace current", 50, 360);
            offg.drawString("item in that slot)", 50 ,390);
        }
        
        if (turnBasedScreen) {
			super.paint(offg);
			offg.drawImage(hero.image, 100, 170, 100, 100, null);
			hero.showBars(offg);

			for (int i = 0; i < enemies.length; i++) {
				if (enemies[i] != null) {
					if (enemies[i].health <= 0) {
						enemies[i] = null;
						enemyMenu.remove(enemyButtons[i]);
					}
				}

				if (enemies[i] != null) {
					offg.drawImage(enemies[i].image, 500, enemies[i].posY, 100,
							90, null);
					enemies[i].drawInfo(offg);
				}
			}
		}
		
		if (postBattleScreen) {
			offg.setColor(Color.black);
			if (wonBattle) {
				offg.drawString("You have won!", 50, 75);
				offg.drawImage(icons.goldImage, 25, 150, 100, 100, null);
				offg.drawImage(icons.expImage, 25, 350, 100, 100, null);
				offg.drawString("And you gained " + battleBounty + " gold!", 150, 200);
				offg.drawString("And you gained " + battleExp + " experience points!", 150, 400);

			} else if (lostBattle) {
				offg.drawString("You have lost!", 25, 75);
				offg.drawImage(icons.goldImage, 25, 150, 100, 100, null);
				offg.drawImage(icons.expImage, 25, 350, 100, 100, null);
				offg.drawString("And you lost " + battleBounty / 2 + " gold!", 150, 200);
				offg.drawString("And you gained " + battleExp / 2 + " experience points!", 150, 400);
			}
		}
        
        if (innScreen || villageScreen){
        	offg.setFont(new Font("Ms Sherif", Font.PLAIN, 25));
            offg.setColor(Color.WHITE);
            offg.drawString(message.line1, 25, 75);
            offg.drawString(message.line2, 25, 125);
            offg.drawString(message.line3, 25, 175);
            offg.drawString(message.line4, 25, 225);
            offg.drawString(message.line5, 25, 275);
            offg.drawString(message.line6, 25, 325);
            offg.drawString(message.line7, 25, 375);
            offg.drawString(message.line8, 25, 425);
        }
        
        if (dungeonScreen){ //Dungeon Paint
			
			for (int i = 0; i < blocks.size(); i++){
				blocks.get(i).paint(offg, this);
			}
		
			for (int i = 0; i < walls.size(); i++){
				walls.get(i).paint(offg, this);
			}
		
			for (int i = 0; i < doors.size(); i++){
				doors.get(i).paint(offg, this);
			}
		
			if (gem.active)
				gem.paint(offg, this);
			
			player.paint(offg);
			
		} //Dungeon Paint End
        
        if (dungeonScreen || worldScreen){
        	ResourceBar health = new ResourceBar(hero.health, hero.maxHealth, Color.RED, 40, 40);
        	ResourceBar mana = new ResourceBar(hero.mana, hero.maxMana, Color.BLUE, 40, 75);
        	ResourceBar gemCount = new ResourceBar(gems, 10, Color.MAGENTA, 40, 110);
        	health.drawBig(offg);
        	mana.drawBig(offg);
        	gemCount.drawBig(offg);
        	
    		offg.setColor(Color.WHITE);
    		offg.setFont(new Font("Ms Sherif", Font.PLAIN, 20));
    		offg.drawString("Health: " + hero.health + "/" + hero.maxHealth, 50, 60);
    		offg.drawString("Mana: " + hero.mana + "/" + hero.maxMana, 50,95);
    		offg.drawString("Gems: " + gems + "/ 10", 50,130);
    		
    	}
		
		g.drawImage(offScreen, 0, 0, this);
	}
    
    public void start(){
        timer.start();
    }
    
    public void stop(){
        timer.stop();
    }
  
    public void actionPerformed(ActionEvent e) {
    	if (frame < 1000 / timer.getDelay())
			frame++;
		else
			frame = 1;

		if (dungeonScreen) {
			keyCheck();
			collisionCheck();
		}
		
		if (turnBasedScreen) {
			if (hero.actionTimer >= hero.maxActionTimer) {
			    hero.actionBarColor = Color.green;
			   } else {
			    hero.actionTimer += 1 + 0.1 * hero.agility;
			    hero.actionBarColor = hero.actionBarLoadingColor;
			   }
			
			lostBattle = true;
			wonBattle = true;
			for (Enemy enemy : enemies) {
				if (enemy != null) {
					enemyAction(enemy);
					wonBattle = false;
				} 
			}
			if (hero.health > 0) {
				lostBattle = false;
			}
			
			if (wonBattle || lostBattle) {
				turnBasedScreen = false;
				hero.actionTimer = 0;
				postBattleScreen = true;
				getContentPane().removeAll();
				getContentPane().revalidate();
				if (wonBattle) {
					turnBasedFin("won");
				} else if (lostBattle) {
					turnBasedFin("lost");
				}
			}
		}
		repaint();
	}

    public void generateEnemies(boolean isBossBattle) {
    	if (!isBossBattle) {
        	int[] spawnList = {1, 2, 3, 4, 5, 6, 0, 0, 0};
        	
        	for (int i = 0; i < 3; i++) {
        		int selection = spawnList[randInt(0, spawnList.length - 1)];
        		Enemy spawnEnemy = null;
        		
        		if (selection == 0)
        			spawnEnemy = null;
        		if (selection == 1)
        			spawnEnemy = new Ghost();
        		if (selection == 2)
        			spawnEnemy = new Alien();
        		if (selection == 3)
        			spawnEnemy = new Dogtopus();
        		if (selection == 4)
        			spawnEnemy = new Frog();
        		if (selection == 5)
        			spawnEnemy = new Priest();
        		if (selection == 6)
        			spawnEnemy = new Warlock();
        		
        		enemies[i] = spawnEnemy;
        		if (enemies[i] != null) {
        			enemies[i].setPosY(30 + 140*i);
        		}
        	}
        	
        	if (enemies[0] == null && enemies[1] == null && enemies[2] == null) {
        		generateEnemies(false);
        	}	
        	
    	} else if (isBossBattle) {
    		enemies[0] = null;
    		enemies[2] = null;
    		enemies[1] = new Smiff();
    		enemies[1].setPosY(175);
    	}
    }
    
    public void attackTarget(Enemy enemyTarget, JPanel enemyMenu) {
    	enemyMenu.setVisible(true);
		if (basicAttack) {
			hero.basicAttack(enemyTarget);
			basicAttack = false;
		} else if (staggeringAttack) {
			hero.staggeringAttack(enemyTarget);
			staggeringAttack = false;
		}
		enemyMenu.setVisible(false);
	}
    
    public void enemyAction(Enemy enemy) {
    	if (enemy.actionTimer < enemy.maxActionTimer) {
			enemy.actionTimer += 1;
		} else if (enemy.actionTimer == enemy.maxActionTimer) {
			enemy.attack(hero);
			enemy.actionTimer = 0;
		}
    	
    	if (enemy.type.equals("Warlock") && enemy.health <= 0.5 * enemy.maxHealth) {
    		enemy.actionTimer = 50;
    		enemy.setMaxActionTimer(90);
    	}
    	if (enemy.type.equals("Priest") && enemy.health <= 0.5 * enemy.maxHealth) {
    		enemy.setDamageResistance(0.5);
    	}
	}
    
    public static int randInt(int min, int max) {
        int randNumber = rand.nextInt(max - min + 1) + min;
        return randNumber;
    }

    @Override
    public void keyPressed(KeyEvent e) {    
        if (worldScreen){
        	if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
    			worldScreen = false;
    			characterScreenWorld = true;
    			characterScreenActive();
    		}	
        	if (e.getKeyCode() == KeyEvent.VK_ENTER && overVillage)
            {
            	worldScreen = false;
            	villageScreen = true;
            	villageScreenActive();
            }
        	if (e.getKeyCode() == KeyEvent.VK_ENTER && overDungeon)
            {
        			worldScreen = false;
        			numR = 0;
        			roomX = 10;
        			roomY = 10;
        			item = false;
        			map = new int[21][21];
        			map[roomX][roomY] = 5;	
        			changeRoom(5);
        			gem = new Gem(0,0,50,50);
        			dungeonScreen = true;
        			inDungeon = true;
        			inWorld = false;
            }
        	if (e.getKeyCode() == KeyEvent.VK_ENTER && overTemple && gems == 10)
            {
            	worldScreen = false;
            	turnBasedScreen = true;
            	inWorld = true;
            	turnBased(true);
            }
        	if ((e.getKeyCode() == KeyEvent.VK_RIGHT) && (fileInts[xPos+1][yPos] != 0) && (fileInts[xPos+1][yPos] != 9)) {
        		if (fileInts[xPos][yPos] != 1) {
        			travelBattleCounter -= 1;
        		}
        		xPos += 1;
        	}
            if ((e.getKeyCode() == KeyEvent.VK_LEFT) && (fileInts[xPos-1][yPos] != 0) && (fileInts[xPos-1][yPos] != 9)) {
	    		if (fileInts[xPos][yPos] != 1) {
	    			travelBattleCounter -= 1;
	    		}
            	xPos -= 1;
            }
            if ((e.getKeyCode() == KeyEvent.VK_UP) && (fileInts[xPos][yPos-1] != 0) && (fileInts[xPos][yPos-1] != 9)) {
	    		if (fileInts[xPos][yPos] != 1) {
	    			travelBattleCounter -= 1;
	    		}
            	yPos -= 1;
            }
            if ((e.getKeyCode() == KeyEvent.VK_DOWN) && (fileInts[xPos][yPos+1] != 0)&&(fileInts[xPos][yPos+1] != 9)) {
	    		if (fileInts[xPos][yPos] != 1) {
	    			travelBattleCounter -= 1;
	    		}
            	yPos += 1;
            }	
	    	if (travelBattleCounter <= 0) {
	    		inWorld = true;
	    		worldScreen = false;
	    		turnBasedScreen = true;
	    		getContentPane().removeAll();
	    		getContentPane().revalidate();
	    		travelBattleCounter = randInt(20, 75);
	    		turnBased(false);
	    	}
        }
    	if (assignmentScreen){
            if (e.getKeyCode() == KeyEvent.VK_1){
                spells[0] = buyingItem;
                assignmentScreen = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_2){
            	spells[1] = buyingItem;
                assignmentScreen = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_3){
            	spells[2] = buyingItem;
                assignmentScreen = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_4){
            	spells[3] = buyingItem;
                assignmentScreen = false;
            }
        }
        if (dungeonScreen){
    		if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
    			dungeonScreen = false;
    			characterScreenDungeon = true;
    			characterScreenActive();
    		}	    		
    		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    			right = true;
    		if (e.getKeyCode() == KeyEvent.VK_LEFT)
    			left = true;
    		if (e.getKeyCode() == KeyEvent.VK_UP)
    			up = true;
    		if (e.getKeyCode() == KeyEvent.VK_DOWN)
    			down = true;
    		
    		if (e.getKeyCode() == KeyEvent.VK_D)
    			right = true;
    		if (e.getKeyCode() == KeyEvent.VK_A)
    			left = true;
    		if (e.getKeyCode() == KeyEvent.VK_W)
    			up = true;
    		if (e.getKeyCode() == KeyEvent.VK_S)
    			down = true;
    		
    		/*if (e.getKeyCode() == KeyEvent.VK_CONTROL){
    			for (int x = 0; x < map.length; x++){
    				for(int y = 0; y < map[0].length; y++){
    					System.out.print("[" + map[x][y] + "]");
    				}
    				System.out.println();
    			}
    		}*/
        }
    }
    
	@Override
	public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
				right = false;
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
				left = false;
			if (e.getKeyCode() == KeyEvent.VK_UP)
				up = false;
			if (e.getKeyCode() == KeyEvent.VK_DOWN)
				down = false;
			
			if (e.getKeyCode() == KeyEvent.VK_D)
				right = false;
			if (e.getKeyCode() == KeyEvent.VK_A)
				left = false;
			if (e.getKeyCode() == KeyEvent.VK_W)
				up = false;
			if (e.getKeyCode() == KeyEvent.VK_S)
				down = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
    public void keyCheck() {
    	if (dungeonScreen){
	    	if (right == true){
				player.moveR();
			}
			else if (left == true) {
				player.moveL();
			}
			if (up == true)
				player.moveU();
			else if (down == true)
				player.moveD();
			if (right || left || up || down){
				if (rand.nextInt(1000) <= 3){
					dungeonScreen = false;
					turnBasedScreen = true;
					getContentPane().removeAll();
					getContentPane().revalidate();
					turnBased(false);
				}	
			}
    	}
    }
    
	public void collisionCheck(){
		for (int  i = 0; i < doors.size(); i++) {
			doors.get(i).collsionCheck(player);
		}
		
		for (int  i = 0; i < walls.size(); i++) {
			walls.get(i).collsionCheck(player);
		}
		
		for (int i = 0; i < walls.size(); i++) {
			if ( player.hitbox.contains( walls.get(i).hitbox.getMaxX() - 2.5, walls.get(i).hitbox.getMaxY() - 2.5, 2.5, 2.5))
				player.hitbox.x = (int) (walls.get(i).hitbox.getMaxX() + 1);
		}
		
		if (gem.active)
			if (gem.checkCollision(player.hitbox)) {
				dungeonScreen = false;
				inDungeon = false;
				inWorld = true;
				worldScreen = true;
				fileInts[xPos][yPos] = 9;
				xPos += 1;
			}
	}
	
	public static void readRoom( File data) {
		
		try {
			@SuppressWarnings("resource")
			Scanner fileget = new Scanner(data); 
			String rawtext = "";
			while (fileget.hasNextLine()) {
				rawtext = rawtext + fileget.nextLine();
			}
			for (int x = 0; x < room.length; x++) {
				for (int y = 0; y < room[0].length; y++) {
					String s = rawtext.substring(x+28*y, x+1+28*y);
				room[x][y] = s;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (int x = 0; x < room.length; x++) { //Create Objects
			for (int y = 0; y < room[0].length; y++) {
				if (room[x][y].equals("X")){
					walls.add(new Wall( x*25, y*25, 25, 25));
				} else if (room[x][y].equals("o")){
					blocks.add(new Block( x*25, y*25, 25, 25));
				} else if (room[x][y].equals("R")){
					doors.add(new Door( x*25, y*25, 25, 25, 1));
				} else if (room[x][y].equals("L")){
					doors.add(new Door( x*25, y*25, 25, 25, 2));
				} else if (room[x][y].equals("U")){
					doors.add(new Door( x*25, y*25, 25, 25, 3));
				} else if (room[x][y].equals("D")){
					doors.add(new Door( x*25, y*25, 25, 25, 4));
				}
			}
		}
	}
	
    @Override
    public void mouseClicked(MouseEvent e) {
        
        
    }
    
    public static void changeRoom(int room){
		
		blocks.clear();
		walls.clear();
		doors.clear();
		
		if (map[roomX][roomY] == 0)
			map[roomX][roomY] = room;
		
		readRoom(roomList.get(room));
		
	}
    
    public static void pickRoom(int direction) {
		
		int dist, bossChance, itemChance;
		int tempI, tempRoom;
		
		numR++;
		
		dist = Math.abs( 20 - (roomX + roomY) );
		
		itemChance = (dist * 10) - 10 + (numR * 5) - 10;
		bossChance = (dist * 10) - 20 + (numR * 5) - 20;
		
		if (item || itemChance < 0)
			itemChance = 0;
		if (boss || bossChance < 0)
			bossChance = 0;
		
		
		if (map[roomX][roomY] != 0) {
			changeRoom(map[roomX][roomY]);
		
		} else {
			
			tempI = rand.nextInt(itemChance + bossChance + 20); //Choose a random number
			
			while (true) {
				tempRoom = rand.nextInt(dataList.size());
				if (map[roomX + 1][roomY] != 0) {
					if ( !((dataList.get(map[roomX + 1][roomY])[0] == 1 && dataList.get(tempRoom)[1] == 1) || (dataList.get(map[roomX + 1][roomY])[0] == 0 && dataList.get(tempRoom)[1] == 0)))
						continue;
				}
				if (map[roomX - 1][roomY] != 0) {
					if ( !((dataList.get(map[roomX - 1][roomY])[1] == 1 && dataList.get(tempRoom)[0] == 1) || (dataList.get(map[roomX - 1][roomY])[1] == 0 && dataList.get(tempRoom)[0] == 0)))
						continue;
				}
				if (map[roomX][roomY + 1] != 0) {
					if ( !((dataList.get(map[roomX][roomY + 1])[2] == 1 && dataList.get(tempRoom)[3] == 1) || (dataList.get(map[roomX][roomY + 1])[2] == 0 && dataList.get(tempRoom)[3] == 0)))
						continue;
				}
				if (map[roomX][roomY - 1] != 0) {
					if ( !((dataList.get(map[roomX][roomY - 1])[3] == 1 && dataList.get(tempRoom)[2] == 1) || (dataList.get(map[roomX][roomY - 1])[3] == 0 && dataList.get(tempRoom)[2] == 0)))
						continue;
				}
				if (roomX == 20) { 
					if ( !(dataList.get(tempRoom)[1] == 0)) 
						continue; 
				} 
				if (roomX == 1) { 
					if ( !(dataList.get(tempRoom)[0] == 0)) 
						continue; 
				} 
				if (roomY == 20) { 
					if ( !(dataList.get(tempRoom)[3] == 0)) 
						continue; 
				} 
				if (roomY == 1) { 
					if ( !(dataList.get(tempRoom)[2] == 0)) 
						continue; 
				} 
				changeRoom(tempRoom);
				if (tempRoom > 0 && tempRoom < 5) {
					if (tempI < itemChance) {
						item = true;
						gem.hitbox.x = 13*25;
						gem.hitbox.y = 12*25;
						gem.active = true;
						if (tempRoom == 1) {
							walls.add(new Wall((25),(11*25),25,25));
							walls.add(new Wall((25),(12*25),25,25));
							walls.add(new Wall((25),(13*25),25,25));
							walls.add(new Wall((25),(14*25),25,25));
						} else if (tempRoom == 2) {
							walls.add(new Wall((26*25),(11*25),25,25));
							walls.add(new Wall((26*25),(12*25),25,25));
							walls.add(new Wall((26*25),(13*25),25,25));
							walls.add(new Wall((26*25),(14*25),25,25));
						} else if (tempRoom == 3) {
							walls.add(new Wall((12*25),(26*25),25,25));
							walls.add(new Wall((13*25),(26*25),25,25));
							walls.add(new Wall((14*25),(26*25),25,25));
							walls.add(new Wall((15*25),(26*25),25,25));
						} else if (tempRoom == 4) {
							walls.add(new Wall((12*25),(25),25,25));
							walls.add(new Wall((13*25),(25),25,25));
							walls.add(new Wall((14*25),(25),25,25));
							walls.add(new Wall((15*25),(25),25,25));
						}
					} else if (tempI >= itemChance && tempI < itemChance + bossChance){
						boss = true;
					}
				}
				break;
			}
		} 	
	}
    
    @Override
    public void mouseEntered(MouseEvent e) {
        
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        requestFocus();
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        
    }    
}

