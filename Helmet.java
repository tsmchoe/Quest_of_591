package play_game;

//Creates helmet object
public class Helmet extends Armor {
	
	public Helmet(String name, int cost, int level, int dmg, char pos) {
		//instantiates helmet
		nameArmor = name;
		lvlArmor = level;
		damageArmor= dmg;
		costArmor = cost;
		positionArmor = pos;
	}
	
	public static void addArm(Helmet h) {
		armCollect.add(h);
	}

	public static void createHelm() {
		//hardcoded helmets
		Helmet h = new Helmet("Iron_Helmet",350,3,600,'h');
		Helmet h2 = new Helmet("Hero's Helm",7000,8,1000,'h');
		Helmet h3 = new Helmet("Hat",100,1,10,'h');
		Helmet h4 = new Helmet("Yeet Cap",999,9,20000,'h');
		addArm(h);
		addArm(h2);
		addArm(h3);
		addArm(h4);
	}
	
}