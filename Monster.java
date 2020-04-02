package play_game;

import java.util.*;

//Creates a general monster class and allows other monsters to be build from here

public abstract class Monster implements Comparable<Monster>{
	//monster attributes
	public String nameMon;
	public int hpMon;
	public int levelMon;
	public int defMon;
	public int dodgeMon;
	public int dmgMon;
	public int maxHp;
	public int maxDef;
	public int maxDodge;
	public int maxDamage;
	public String typing;
	public static List<Monster> monCollect = new ArrayList<Monster>();
	public static List<Monster> monLineup = new ArrayList<Monster>();
	//monster attribute formatting
	private static String MonFormat = "| %-6d | %-20s | %-5d | %-6d | %-8d | %-8d | %-9s |%n";
	
	public static void Mattack(Hero h, Monster m) {
		//General monster attack since they all attack the same way so far.
		double dmg = 0;
		System.out.println(m.nameMon + " attacks!");
		int dodge = (int) h.dodgeHero();
		if (dodge >= m.dmgMon) {
			System.out.println("Dodged! Ha!");
		}
		else {
			dmg = (m.dmgMon - h.defHero);
			if (dmg < 0) {
				dmg = 0;
			}
			h.hpHero -= dmg;
			System.out.println(m.nameMon + " did " + dmg + " damage to " + h.nameHero);
		}
	}
	
	public static Monster pickRandom() {
		//pick random monsters
		Spirit.createSpirit();
		Dragon.createDragon();
		Exoskeleton.createExo();
		Random r = new Random();
		int x = r.nextInt(monCollect.size());
		return monCollect.get(x);
	}
	
	public static void printMon() {
		//gives info for mon
		System.out.format("+--------+----------------------+-------+--------+----------+----------+-----------+%n");
		System.out.format("| Number |      Name            | Level |   HP   | Strength | Defence  |    Type   |%n");
		System.out.format("+--------+----------------------+-------+--------+----------+----------+-----------+%n");
		for (int i = 0; i < monLineup.size(); i++) {
			Monster m = monLineup.get(i);
		    System.out.format(MonFormat, i+1, m.nameMon,m.levelMon, m.hpMon,m.dmgMon,m.defMon, m.typing);
		}
		System.out.format("+--------+----------------------+-------+--------+----------+----------+-----------+%n");
	}
	
	public static void repairMon(Monster m) {
		//repairs mon object
		m.hpMon = m.maxHp;
		m.defMon = m.maxDef;
		m.dmgMon = m.maxDamage;
		m.dodgeMon = m.maxDodge;
	}
	
	  @Override     
	  public int compareTo(Monster m) {   
		  //overrides to compare
	    if (this.levelMon < m.levelMon) {
	    	return -1;
	    }
	    else if (this.levelMon > m.levelMon) {
	    	return 1;
	    }
	    return 0;
	  }
	  
	  

	  
	  
	  
}
