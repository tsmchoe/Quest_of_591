package play_game;

import java.util.ArrayList;

//Creates Warrior
public class Warrior extends Hero{
	
	public Warrior(String name, int mana, int strength, int agility, int dexterity, int def, int money, int lvl, String type) {
		//instantiates Warrior
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
		//Warrior level up 
		hpHero = 100*lvlHero;
		manaHero += manaHero * 0.1;
		strHero += strHero * 0.1;
		dxtHero += dxtHero * 0.05;
		aglHero += aglHero * 0.1;
		expHero = 0;
	}
	
	
	private static void addHero(Warrior w) {
		heroPool.add(w);
	}

	public static void createWar() {
		//hardcoded warriors
		Warrior w = new Warrior("Gaerdal_Ironhand",100,700,500,600,200,1354,7,"Warrior");
		Warrior w2 = new Warrior("Sehanine_Monnbow",600,700,800,500,200,2500,8,"Warrior");
		Warrior w3 = new Warrior("Muamman_Duathall",300,900,500,750,200,2546,6,"Warrior");
		Warrior w4 = new Warrior("Flandal_Steelskin",200,750,650,700,200,2500,7,"Warrior");
		addHero(w);
		addHero(w2);
		addHero(w3);
		addHero(w4);
	}
}
