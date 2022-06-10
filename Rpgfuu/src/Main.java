import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Main implements Interface {
	private static long startTime;
	private static long endTime;
	private static long cumulativeTime = 0;
	
	public static void main(String[] args) {
		int tt = sc.nextInt();
		System.out.println(tt);
		gameStart();
	}
	
	//save
	public static void save() {
		Interface.wait(0.5);
		System.out.println("前のデータに上書きしてもよろしいですか？");
		System.out.println("1：はい　その他：いいえ");
		endTime = System.currentTimeMillis();
		int q = sc.nextInt();
		Interface.wait(0.5);
		if (q == 1) {
			try {
				FileWriter file = new FileWriter("C:\\Users\\branch\\Documents\\miyazaki\\saveData.txt");
				PrintWriter pw = new PrintWriter(new BufferedWriter(file));
				
				pw.println(Party.location);
				pw.println(Party.money);
				for (Weapon weapon :Party.havingWeapon) {
					pw.println(weapon.id);
				}
				if (Party.havingWeapon.size() < 3) {
					for (int i = 0; i < 3 - Party.havingWeapon.size(); i++) {
						pw.println(99);
					}
				}
				for (Armor armor :Party.havingArmor) {
					pw.println(armor.id);
				}
				if (Party.havingArmor.size() < 3) {
					for (int i = 0; i < 3 - Party.havingArmor.size(); i++) {
						pw.println(99);
					}
				}
				for (Accessory accessory :Party.havingAccessory) {
					pw.println(accessory.id);
				}
				if (Party.havingAccessory.size() < 3) {
					for (int i = 0; i < 3 - Party.havingAccessory.size(); i++) {
						pw.println(99);
					}
				}
				for (Pc member : Party.partyMember) {
					pw.println(member.job);
					pw.println(member.name);
					pw.println(member.level);
					pw.println(member.exp);
					pw.println(member.nowHp);
					pw.println(member.status[hps]);
					pw.println(member.status[atks]);
					pw.println(member.status[defs]);
					if (member.equippingWeapon != null) {
						pw.println(member.equippingWeapon.id);
					} else {
						pw.println("false");
					}
					if (member.equippingArmor != null) {
						pw.println(member.equippingArmor.id);
					} else {
						pw.println("false");
					}
					if (member.equippingAccessory != null) {
						pw.println(member.equippingAccessory.id);
					} else {
						pw.println("false");
					}
				}
				pw.println(cumulativeTime = cumulativeTime + endTime - startTime);
				pw.close();
				System.out.println("セーブが完了しました");
				Interface.wait(0.5);
				System.out.println("ゲームを終了しますか？");
				System.out.println("1：終了する　その他：続ける");
				int qq = sc.nextInt();
				Interface.wait(0.5);
				if (qq == 1) {
					System.out.println("今回のプレイ時間は" + playTime() + "です");
					System.out.println("累計のプレイ時間は" + cumulativeTime() + "です");
					int n = sc.nextInt();
					System.exit(0);
				} else {
					Party.arrive();
					System.exit(0);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("セーブせずに終了しますか？");
			System.out.println("1：はい　その他：いいえ");
			int qqq = sc.nextInt();
			Interface.wait(0.5);
			if (qqq == 1) {
				System.out.println("今回のプレイ時間は" + playTime() + "です");
				System.out.println("累計のプレイ時間は" + cumulativeTime() + "です");
				int n = sc.nextInt();
				System.exit(0);
			} else {
				Party.arrive();
				System.exit(0);
			}
		}
		
	}
	
	//load
	public static void gameStart() {
		System.out.println(">>>GAME START!>>>");
		Weapon.createData();
		Armor.createData();
		Accessory.createData();
		Shop.createData();
		for (Weapon equipment : weapons) {
			equipments.add(equipment);
		}
		for (Armor equipment : armors) {
			equipments.add(equipment);
		}
		for (Accessory equipment : accessorys) {
			equipments.add(equipment);
		}
		Warrior raian = new Warrior("ライアン");
		Assassin zax = new Assassin("アサシン");
		Knight grage = new Knight("グレイグ");
		Party.add(raian);
		Party.add(zax);
		Party.add(grage);
		
		System.out.println("1：つづきからはじめる　0：さいしょからはじめる");
		int r = sc.nextInt();
		if (r == 1) {
			String fname = "C:\\Users\\branch\\Documents\\miyazaki\\saveData.txt";
			LinkedList<String> list = new LinkedList<>();
			try {
				FileInputStream is = new FileInputStream(fname);
				InputStreamReader in = new InputStreamReader(is,"UTF-8");
				BufferedReader inb = new BufferedReader(in);
				String line;
				while ((line = inb.readLine()) != null) {
					list.add(line);
				}
				inb.close();
				in.close();
				is.close();
			}
			catch (IOException e) {
				System.err.println(e);
			}
			Interface.wait(0.5);
			System.out.println("ロードが完了しました。");
			Interface.wait(0.5);
			Party.location = Integer.valueOf(list.get(0));
			Party.money = Integer.valueOf(list.get(1));
			for (int i = 0; i < 3; i++) {
				if (Integer.valueOf(list.get(2 + i)) != 99) {
					Party.get(weapons.get(Integer.valueOf(list.get(2 + i))));
				}
			}
			for (int i = 0; i < 3; i++) {
				if (Integer.valueOf(list.get(5 + i)) != 99) {
					Party.get(armors.get(Integer.valueOf(list.get(5 + i))));
				}
			}
			for (int i = 0; i < 3; i++) {
				if (Integer.valueOf(list.get(8 + i)) != 99) { 
					Party.get(accessorys.get(Integer.valueOf(list.get(8 + i))));
				}
			}
			raian.level = Integer.valueOf(list.get(13));
			raian.exp = Integer.valueOf(list.get(14));
			raian.status[hps] = Integer.valueOf(list.get(16));
			raian.status[atks] = Integer.valueOf(list.get(17));
			raian.status[defs] = Integer.valueOf(list.get(18));
			if (! list.get(19).equals("false")) {
				raian.newEq(weapons.get(Integer.valueOf(list.get(19))));
			}
			if (! list.get(20).equals("false")) {
				raian.newEq(armors.get(Integer.valueOf(list.get(20))));
			}
			if (! list.get(21).equals("false")) {
				raian.newEq(accessorys.get(Integer.valueOf(list.get(21))));
			}
			raian.nowHp = Integer.valueOf(list.get(15));
			zax.level = Integer.valueOf(list.get(24));
			zax.exp =  Integer.valueOf(list.get(25));
			zax.status[hps] = Integer.valueOf(list.get(27));
			zax.status[atks] = Integer.valueOf(list.get(28));
			zax.status[defs] = Integer.valueOf(list.get(29));
			if (! list.get(30).equals("false")) {
				zax.newEq(weapons.get(Integer.valueOf(list.get(30))));
			}
			if (! list.get(31).equals("false")) {
				zax.newEq(armors.get(Integer.valueOf(list.get(31))));
			}
			if (! list.get(32).equals("false")) {
				zax.newEq(accessorys.get(Integer.valueOf(list.get(32))));
			}
			zax.nowHp =  Integer.valueOf(list.get(26));
			grage.level = Integer.valueOf(list.get(35));
			grage.exp =  Integer.valueOf(list.get(36));
			grage.status[hps] = Integer.valueOf(list.get(38));
			grage.status[atks] = Integer.valueOf(list.get(39));
			grage.status[defs] = Integer.valueOf(list.get(40));
			if (! list.get(41).equals("false")) {
				grage.newEq(weapons.get(Integer.valueOf(list.get(41))));
			}
			if (! list.get(42).equals("false")) {
				grage.newEq(armors.get(Integer.valueOf(list.get(42))));
			}
			if (! list.get(43).equals("false")) {
				grage.newEq(accessorys.get(Integer.valueOf(list.get(43))));
			}
			grage.nowHp =  Integer.valueOf(list.get(37));
			cumulativeTime = Integer.valueOf(list.get(44));
		}
		startTime = System.currentTimeMillis();
		Party.arrive();
	}
	
	public static void gameEnd() {
		Interface.wait(0.5);
		System.out.println("<<<GAME END!<<<");
	}
	
	public static String playTime() {
		long sum = endTime - startTime;
		long h = sum / 3600000;
		long m = (sum % 360000) / 60000;
		long s = (sum % 60000) / 1000;
		return (h + "時間" + m + "分" + s + "秒");
	}
	
	public static String cumulativeTime() {
		long h = cumulativeTime / 3600000;
		long m = (cumulativeTime % 360000) / 60000;
		long s = (cumulativeTime % 60000) / 1000;
		return (h + "時間" + m + "分" + s + "秒");
	}
	
	

}
