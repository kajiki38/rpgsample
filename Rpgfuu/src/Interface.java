import java.util.ArrayList;
import java.util.Scanner;

public interface Interface {
	Scanner sc = new Scanner(System.in);
	
	public static ArrayList<Equipment> equipments = new ArrayList<>();
	
	//buki
	public static ArrayList<Weapon> weapons = new ArrayList<>();
	public static final int COPPER_SWORD = 0;
	public static final int IRON_SWORD = 1;
	public static final int SILVER_SWORD = 2;
	
	//bougu
	public static ArrayList<Armor> armors = new ArrayList<>();
	public static final int KAWA_FUKU = 0;
	public static final int IRON_ARMOR = 1;
	public static final int KUSARI_KATABIRA = 2;
	
	//akuse
	public static ArrayList<Accessory> accessorys = new ArrayList<>();
	public static final int SILVER_RING = 0;
	public static final int GOLD_RING = 1;
	public static final int ATTACK_PIERCE = 2;
	
	public static final int hps = 0;
	public static final int atks = 1;
	public static final int defs = 2;
	
	
	public ArrayList<Shop> shops = new ArrayList<>();
	
	public static void wait(double second) {
		try {
			Thread.sleep((int)Math.round(second * 1000)); // 10•b(1–œƒ~ƒŠ•b)ŠÔ‚¾‚¯ˆ—‚ğ~‚ß‚é
		} catch (InterruptedException e) {
		}
	}
	
	
}
