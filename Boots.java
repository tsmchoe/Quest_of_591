package play_game;

//Creates boots object
public class Boots extends Armor {
	
	private Boots(String name, int cost, int level, int dmg, char pos) {
		//instantiates boots
		nameArmor = name;
		lvlArmor = level;
		damageArmor= dmg;
		costArmor = cost;
		positionArmor = pos;
	}
	
	private static void addArm(Boots b) {
		armCollect.add(b);
	}
	
	public static void createBoots() {
		//hardcoded boots
		Boots b = new Boots("Speed_Boots",550,4,600,'f');
		Boots b2 = new Boots("Quick_Feet",7000,8,1000,'f');
		Boots b3 = new Boots("Shoes",100,1,10,'f');
		Boots b4 = new Boots("Pumped_up_kicks",999,9,20000,'f');
		addArm(b);
		addArm(b2);
		addArm(b3);
		addArm(b4);
	}
	
}