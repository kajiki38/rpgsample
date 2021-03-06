import java.util.ArrayList;

public class Weapon extends Equipment {
	
	Weapon(int id, String name, int hp, int atk, int def, int value) {
		super(id, name, hp, atk, def, value);
	}
	
	public static void createData() {
		weapons.add(new Weapon(0, "どうのつるぎ", 0, 10, 0, 120));
		weapons.add(new Weapon(1, "てつのつるぎ", 0, 20, 0, 550));
		weapons.add(new Weapon(2, "ぎんのつるぎ", 0, 25, 10, 1200));
	}
	
	public void showFromStatus() {
		super.showFromStatus();
		Pc S = this.beEquipped;
		int r = sc.nextInt();
		if (r == 1) {
			S.removeWeapon(true);
		} else if (r == 2) {
			if (Party.havingWeapon.isEmpty()) {
				System.out.println("武器を持っていません");
			} else {
				int i = 1;
				int k;
				ArrayList<Weapon> weaponlist = new ArrayList<>();
				for (Weapon weapon : Party.havingWeapon) {
					if (weapon.beEquipped != S) {
						System.out.print(i + "．" + weapon.name);
						if (weapon.beEquipped != null) {
							System.out.print(" (" + weapon.beEquipped.name + "が装備中)");
						}
						System.out.println();
						i++;
						weaponlist.add(weapon);
					}
				}
				k = sc.nextInt();
				if (k <= weaponlist.size() && k > 0) {
					S.equip(weaponlist.get(k-1));
				}
			}
		} 
		S.showStatus();
	}
}