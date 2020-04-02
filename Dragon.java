package play_game;

//Creates Dragon enemy
public class Dragon extends Monster{
	
	private Dragon(String name, int hp, int level, int dmg, int def, int dodge, String type) {
		//instantiates Dragon
		nameMon = name;
		hpMon = hp;
		levelMon = level;
		dmgMon = dmg;
		defMon = def;
		dodgeMon = dodge;
		typing = type;
		maxHp = hp;
		maxDef = def;
		maxDamage = dmg;
		maxDodge = dodge;
	}
	
	
	public static void createDragon() {
		//Hardcoded dragon
		Dragon d = new Dragon("Desghidorrah", 100, 2, 600, 500, 40,"Dragon");
		Dragon d2 = new Dragon("Chrysophylax", 100, 1, 600, 500, 40,"Dragon");
		Dragon d3 = new Dragon("TheScaleless", 700, 3, 550, 450, 25,"Dragon");
		Dragon d4 = new Dragon("Phaarthurnax", 400, 4, 700, 600, 40,"Dragon");
		Dragon d5 = new Dragon("D-Maleficent", 1000, 5, 800, 700, 50,"Dragon");
		Dragon d6 = new Dragon("Kas-Ethelinh", 100, 7, 350, 150, 75,"Dragon");
		addDrg(d);
		addDrg(d2);
		addDrg(d3);
		addDrg(d4);
		addDrg(d5);
		addDrg(d6);
	}
	private static void addDrg(Dragon d) {
		monCollect.add(d);
	}
	
}