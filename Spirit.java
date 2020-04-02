package play_game;

//Creates Spirit enemy
public class Spirit extends Monster{	
	
	private Spirit(String name, int hp, int level, int dmg, int def, int dodge, String type) {
		//instantiates Spirit
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
	
	public static void createSpirit() {
		//hardcoded spirit
		Spirit s = new Spirit("Andrealphus", 100, 2, 600, 500, 40,"Spirit");
		Spirit s2 = new Spirit("Aim-Haborym ", 100, 1, 600, 500, 40,"Spirit");
		Spirit s3 = new Spirit("Andromalius", 700, 3, 550, 450, 25,"Spirit");
		Spirit s4 = new Spirit("Chiang-shih", 400, 4, 700, 600, 40,"Spirit");
		Spirit s5 = new Spirit("FallenAngel", 1000, 9, 800, 700, 50,"Spirit");
		Spirit s6 = new Spirit("Melchiresas", 100, 7, 350, 150, 75,"Spirit");
		addSp(s);
		addSp(s2);
		addSp(s3);
		addSp(s4);
		addSp(s5);
		addSp(s6);
	}
	private static void addSp(Spirit s) {
		monCollect.add(s);
	}
	

}
