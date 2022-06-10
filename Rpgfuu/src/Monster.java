public class Monster implements Interface{
	public String name;
	public int hp;
	public int atk;
	public int def;
	public int exp;
	public int money;
	
	Monster(int id) {
		switch(id) {
			case 1:
				this.setData("�X���C��", 50,60,40,500,200);
				break;
			case 2:
				this.setData("�S�u����", 100, 85, 70, 1000, 400);
				break;
			case 3:
				this.setData("�I�[�K", 150, 130, 95, 2000, 800);
				break;
			default:
				this.setData("���΂�", 1000, 1000, 1000, 5, 5);
		}
	}
	
	public void setData(String name, int hp, int atk, int def, int exp, int money) {
		this.name = name;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.exp = exp;
		this.money = money;
	}
	
	public void attack(Pc character) {
		if (this.hp > 0 && character.nowHp > 0) {
			System.out.println(this.name + "�̂�������!");
			Interface.wait(0.5);
			int dmg = this.atk - character.finalStatus[defs];
			if (dmg <= 0) {
				System.out.println(character.name + "�̓_���[�W���󂯂Ȃ������B");
			} else {
				character.nowHp -= dmg;
				System.out.println(character.name + "��" + dmg + "�̃_���[�W!");
			}
			if (character.nowHp <= 0) {
				System.out.println(character.name + "�͗͐s�����B");
				Party.aliveMember--;
			}
			System.out.println();
			Interface.wait(1.0);
		}
	}
	
	public void showStatus() {
		System.out.println(this.name);
		System.out.println("�̗́@�F" + this.hp);
		System.out.println("�U���́F" + this.atk);
		System.out.println("�h��́F" + this.def);
		System.out.println("----------");
	}
}