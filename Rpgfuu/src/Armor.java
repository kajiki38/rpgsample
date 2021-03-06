import java.util.ArrayList;

public class Armor extends Equipment {
	
	Armor(int id, String name, int hp, int atk, int def, int value) {
		super(id, name, hp, atk, def, value);
	}
	
	public static void createData() {
		armors.add(new Armor(0, "かわのふく", 0, 0, 10, 100));
		armors.add(new Armor(1, "てつのよろい", 0, 0, 20, 500));
		armors.add(new Armor(2, "くさりかたびら", 0, 10, 25, 1000));
	}
	
	public void showFromStatus() {
		super.showFromStatus();
		Pc S = this.beEquipped;
		int r = sc.nextInt();
		if (r == 1) {
			S.removeArmor(true);
		} else if (r == 2) {
			if (Party.havingArmor.isEmpty()) {
				System.out.println("防具を持っていません");
			} else {
				int i = 1;
				int k;
				ArrayList<Armor> armlist = new ArrayList<>();
				for (Armor armor : Party.havingArmor) {
					if (armor.beEquipped != S) {
						System.out.print(i + "．" + armor.name);
						if (armor.beEquipped != null) {
							System.out.print(" (" + armor.beEquipped.name + "が装備中)");
						}
						System.out.println();
						i++;
						armlist.add(armor);
					}
					
				}
				k = sc.nextInt();
				if (k <= armlist.size() && k > 0) {
					S.equip(armlist.get(k-1));
				}
			}
		} 
		S.showStatus();
	}

}
