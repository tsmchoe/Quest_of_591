package play_game;

//Creates Axes objects
public class Axes extends Weapon {
	
	private Axes(String name, int cost, int level, int dmg, int hands, String type) {
		//instantiates axes
		nameWeapon = name;
		lvlWeapon = level;
		dmgWeapon = dmg;
		heroHands = hands;
		costWeapon = cost;
		typing = type;
	}
	
	private static void addAxe(Axes a) {
		weaponCollect.add(a);
	}
	
	
	public static void axCreator() {
		//hardcoded axes
		Axes first = new Axes("Axe",500,1,300,1,"axe");
		Axes second = new Axes("Guilotine",1000,5,6000,2,"axe");
		Axes third = new Axes("War_Smasher",11000,9,20000,2,"axe");
		Axes fourth = new Axes("Soul_Sucker",5000,8,8000,2,"axe");
		Axes fifth = new Axes("Throwing_Ax",200,2,250,1,"axe");
		addAxe(first);
		addAxe(second);
		addAxe(third);
		addAxe(fourth);
		addAxe(fifth);
	}
}
