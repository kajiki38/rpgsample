import java.util.ArrayList;

public class Accessory extends Equipment {
	
	Accessory(int id, String name, int hp, int atk, int def, int value) {
		super(id, name, hp, atk, def, value);
	}
	
	public static void createData() {
		accessorys.add(new Accessory(0, "ぎんのゆびわ", 25, 0, 5, 50));
		accessorys.add(new Accessory(1, "きんのゆびわ", 50, 0, 10, 300));
		accessorys.add(new Accessory(2, "こうげきのピアス",10, 20, 0, 700));
	}
	
	public void showFromStatus() {
		super.showFromStatus();
		Pc S = this.beEquipped;
		int r = sc.nextInt();
		if (r == 1) {
			S.removeAccessory(true);
		} else if (r == 2) {
			if (Party.havingAccessory.isEmpty()) {
				System.out.println("アクセを持っていません");
			} else {
				int i = 1;
				int k;
				ArrayList<Accessory> accelist = new ArrayList<>();
				for (Accessory accessory : Party.havingAccessory) {
					if (accessory.beEquipped != S) {
						System.out.print(i + "．" + accessory.name);
						if (accessory.beEquipped != null) {
							System.out.print(" (" + accessory.beEquipped.name + "が装備中)");
						}
						System.out.println();
						i++;
						accelist.add(accessory);
					}
				}
				k = sc.nextInt();
				if (k <= accelist.size() && k > 0) {
					S.equip(accelist.get(k-1));
				}
			}
		} 
		S.showStatus();
	}

}