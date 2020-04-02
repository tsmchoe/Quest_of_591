package play_game;

//Creates sword object
public class Swords extends Weapon {	
	public Swords(String name, int cost, int level, int dmg, int hands, String type) {
		//instantiates swords
		nameWeapon = name;
		lvlWeapon = level;
		dmgWeapon = dmg;
		heroHands = hands;
		costWeapon = cost;
		typing = type;
	}
	
	private static void addSword(Swords s) {
		weaponCollect.add(s);
	}
	
	public static void swordCreator() {
		//Hardcoded swords
		Swords first = new Swords("Sword",500,1,300,1,"sword");
		Swords second = new Swords("Destiny_Blade",100,8,999999,2,"sword");
		Swords third = new Swords("Katana",1000,3,2000,1,"sword");
		Swords fourth = new Swords("Slasher",5000,5,8000,1,"sword");
		Swords fifth = new Swords("Excaliber",50000,7,90990,1,"sword");
		addSword(first);
		addSword(second);
		addSword(third);
		addSword(fourth);
		addSword(fifth);
	}
}
