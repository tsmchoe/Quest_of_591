package play_game;

//Creates Exoskeleton enemy
public class Exoskeleton extends Monster{
	
	private Exoskeleton(String name, int hp, int level, int dmg, int def, int dodge, String type) {
		//instantiates exoskeleton
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
	
	
	public static void createExo() {
		//Hardcoded exoskel
		Exoskeleton e = new Exoskeleton("Cyrrollalee", 100, 2, 600, 500, 40,"ExoSkel");
		Exoskeleton e2 = new Exoskeleton("Brandobaris", 100, 1, 600, 500, 40,"ExoSkel");
		Exoskeleton e3 = new Exoskeleton("BigBad-Wolf", 700, 3, 550, 450, 25,"ExoSkel");
		Exoskeleton e4 = new Exoskeleton("Kiaransalee", 400, 4, 700, 600, 40,"ExoSkel");
		Exoskeleton e5 = new Exoskeleton("Merrshaullk", 1000, 10, 800, 700, 50,"ExoSkel");
		Exoskeleton e6 = new Exoskeleton("St-Shargaas", 100, 7, 350, 150, 75,"ExoSkel");
		addEx(e);
		addEx(e2);
		addEx(e3);
		addEx(e4);
		addEx(e5);
		addEx(e6);
	}
	private static void addEx(Exoskeleton e) {
		monCollect.add(e);
	}
	
}