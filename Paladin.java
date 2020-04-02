package play_game;

import java.util.ArrayList;

//Creates Paladin
public class Paladin extends Hero{
	
	public Paladin(String name, int mana, int strength, int agility, int dexterity, int def, int money, int lvl, String type) {
		//Instantiates Paladin
		nameHero = name;
		hpHero = 100*lvl;
		lvlHero = lvl;
		manaHero = mana;
		strHero = strength;
		dxtHero = dexterity;
		aglHero = agility;
		defHero = def;
		moneyHero = money;
		maxMana = mana;
		maxAGL = agility;
		maxSTR = strength;
		maxDXT = dexterity;
		typing = type;
		expHero = 0;
		rHand = null;
		lHand = null;
		head = null;
		chest = null;
		feet = null;
		Hbag = new ArrayList<Potion>();
		HArmor = new ArrayList<Armor>();
		HSpell = new ArrayList<Spell>();
		HWeapon =new ArrayList<Weapon>();

	}
	
	public void levelUp() {
		//Paladin level up
		hpHero = 100*lvlHero;
		manaHero += manaHero * 0.1;
		strHero += strHero * 0.1;
		dxtHero += dxtHero * 0.1;
		aglHero += aglHero * 0.05;
		expHero = 0;
	}
	
	private static void addHero(Paladin p) {
		heroPool.add(p);
	}

	public static void createPal() {
		//Hardcoded paladins
		Paladin p = new Paladin("Solonor_Thelandira", 300, 750, 650, 700, 400, 2500, 7,"Paladin");
		Paladin p2 = new Paladin("Sehanine_Moonbow",300,750,700,700,400,2500,7,"Paladin");
		Paladin p3 = new Paladin("Skoraeus_Stonebones",250,650,600,350,400,2500,4,"Paladin");
		Paladin p4 = new Paladin("Garl_Glittergold",100,600,500,400,400,2500,5,"Paladin");
		addHero(p);
		addHero(p2);
		addHero(p3);
		addHero(p4);
	}

}
