package play_game;

//creates Dagger object
public class Daggers extends Weapon {
	
	public Daggers(String name, int cost, int level, int dmg, int hands, String type) {
		//instantiates daggers
		nameWeapon = name;
		lvlWeapon = level;
		dmgWeapon = dmg;
		heroHands = hands;
		costWeapon = cost;
		typing = type;
	}
	
	private static void addDag(Daggers d) {
		weaponCollect.add(d);
	}

	public static void dagCreator() {
		//hardcoded daggers
		Daggers first = new Daggers("Dagger",200,1,250,1,"dag");
		Daggers second = new Daggers("Feather_Blow",100,1,10,1,"dag");
		Daggers third = new Daggers("X_Scissor",400,5,600,1,"dag");
		Daggers fourth = new Daggers("Blade_Breaker",1000,7,800,1,"dag");
		Daggers fifth = new Daggers("Monster_Bane",10000,20,8000,1,"dag");
		addDag(first);
		addDag(second);
		addDag(third);
		addDag(fourth);
		addDag(fifth);
	}
}
