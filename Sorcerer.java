package play_game;

import java.util.ArrayList;

//Creates Sorcerer
public class Sorcerer extends Hero{
	
	public Sorcerer(String name, int mana, int strength, int agility, int dexterity, int def, int money, int lvl, String type) {
		//instantiates sorcerer
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
		//Sorcerer level up
		hpHero = 100*lvlHero;
		manaHero += manaHero * 0.1;
		strHero += strHero * 0.05;
		dxtHero += dxtHero * 0.1;
		aglHero += aglHero * 0.1;
		expHero = 0;
	}
	
	private static void addHero(Sorcerer s) {
		heroPool.add(s);
	}

	public static void createSor() {
		//Hardcoded sorcerers
		Sorcerer s = new Sorcerer("Garl_Glittergold",700,550,600,500,100,2500,7,"Sorcerer");
		Sorcerer s2 = new Sorcerer("Rillifane_Rallathil",1300,750,450,500,100,2500,9,"Sorcerer");
		Sorcerer s3 = new Sorcerer("Segojan_Earthcaller",900,800,500,650,100,2500,5,"Sorcerer");
		Sorcerer s4 = new Sorcerer("Skoraeus_Stonebones",800,850,600,450,100,2500,6,"Sorcerer");
		addHero(s);
		addHero(s2);
		addHero(s3);
		addHero(s4);
	}
}
