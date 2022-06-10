
public class Pc implements Interface {
	
	public String name;
	public String job;
	public int level = 1;
	public int[] hp = new int[50];
	public int nowHp;
	public int[] atk = new int[50];
	public int[] def = new int[50];
	public int exp = 0;
	public int[] status = new int[10];
	public int[] finalStatus = new int[10];
	public int hprr = 10;
	public int atkr = 10;
	public int defr = 10;
	public Weapon equippingWeapon;
	public Armor equippingArmor;
	public Accessory equippingAccessory;
	public boolean alive = true;
	public boolean defence = false;
	public int[] eqPlusStatus = new int[10];
	
	
	Pc(String name) {
		this.name = name;
		this.exp = 0;
	}
	
	
	Pc(int level) {
		this.level = level;
		if (this.level == 1) {
			this.exp = 0;
		}
	}
	
	
	public void showStatus() {
		Interface.wait(0.5);
		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println(this.name);
		System.out.println("�E�ƁF" + this.job);
		System.out.println("���׃��F" + this.level);
		System.out.println("�̗́@�F" + this.nowHp + "/" + this.finalStatus[hps] + "(+" + this.eqPlusStatus[hps] + ")");
		System.out.println("�U���́F" + this.finalStatus[atks] + "(+" + this.eqPlusStatus[atks] + ")");
		System.out.println("�h��́F" + this.finalStatus[defs] + "(+" + this.eqPlusStatus[defs] + ")");
		System.out.println("*******");
		System.out.println("����");
		System.out.print("����F");
		if (this.equippingWeapon == null) {
			System.out.print("�Ȃ� ");
		} else {
			System.out.print(this.equippingWeapon.name + " ");
		}
		System.out.print("�h��F");
		if (this.equippingArmor == null) {
			System.out.print("�Ȃ� ");
		} else {
			System.out.print(this.equippingArmor.name + " ");
		}
		System.out.print("�A�N�Z�F");
		if (this.equippingAccessory == null) {
			System.out.println("�Ȃ� ");
		} else {
			System.out.println(this.equippingAccessory.name + " ");
		}
		System.out.println("-----------------------------------------------");
		System.out.println("�ǂ̏������܂����H");
		System.out.println("1�F����  2�F�h��  3�F�A�N�Z  0�F�߂�  ���̑��F����");
		int r = sc.nextInt();
		Interface.wait(0.5);
		if (r == 0) {
			Party.show();
			System.exit(0);
		} else if (r == 1) {
			if (this.equippingWeapon == null) {
				System.out.println(this.name + "�͕���𑕔����Ă��Ȃ�");
				System.out.println("1�F�������� 0�F�߂� ���̑�:����");
				int z = sc.nextInt();
				int i = 1;
				Interface.wait(0.5);
				if (z == 1) {
					if (Party.havingWeapon.isEmpty()) {
						System.out.println("����������Ă��Ȃ�");
						this.showStatus();
						System.exit(0);
					} else {
						for (Weapon weapon : Party.havingWeapon) {
							System.out.print(i + "�D" + weapon.name);
							if (weapon.beEquipped != null) {
								System.out.print(" (" + weapon.beEquipped.name + "��������)");
							}
							System.out.println();
							i++;
						}
						int k = sc.nextInt();
						if (k <= Party.havingWeapon.size() && k > 0) {
							this.equip(Party.havingWeapon.get(k-1));
							this.showStatus();
							System.exit(0);
						} else {
							this.showStatus();
							System.exit(0);
						}
					}
				} else if (z == 0) {
					this.showStatus();
					System.exit(0);
				} else {
					int l = Party.location;
					if (l == 0 || l == 15 || l == 35) {
						Party.arrive();
						System.exit(0);
					} else {
						Party.what();
						System.exit(0);
					}
				}
			} else {
				this.equippingWeapon.showFromStatus();
				System.exit(0);
			}
		} else if (r == 2) {
			if (this.equippingArmor == null) {
				System.out.println(this.name + "�͖h��𑕔����Ă��Ȃ�");
				System.out.println("1�F�������� 0�F�߂� ���̑�:����");
				int z = sc.nextInt();
				int i = 1;
				if (z == 1) {
					if (Party.havingArmor.isEmpty()) {
						System.out.println("�h��������Ă��Ȃ�");
						this.showStatus();
						System.exit(0);
					} else {
						for (Armor armor : Party.havingArmor) {
							System.out.print(i + "�D" + armor.name);
							if (armor.beEquipped != null) {
								System.out.print(" (" + armor.beEquipped.name + "��������)");
							}
							System.out.println();
							i++;
						}
						int k = sc.nextInt();
						if (k <= Party.havingArmor.size() && k > 0) {
							this.equip(Party.havingArmor.get(k-1));
							this.showStatus();
							System.exit(0);
						} else {
							this.showStatus();
							System.exit(0);
						}
					}
				} else if (z == 0) {
					this.showStatus();
					System.exit(0);
					System.exit(0);
				} else {
					int l = Party.location;
					if (l == 0 || l == 15 || l == 35) {
						Party.arrive();
						System.exit(0);
					} else {
						Party.what();
						System.exit(0);
					}
				}
			} else {
				this.equippingArmor.showFromStatus();
				System.exit(0);
			}
			
		} else if (r == 3) {
			if (this.equippingAccessory == null) {
				System.out.println(this.name + "�̓A�N�Z�𑕔����Ă��Ȃ�");
				System.out.println("1�F�������� 0�F�߂� ���̑�:����");
				int z = sc.nextInt();
				int i = 1;
				if (z == 1) {
					if (Party.havingAccessory.isEmpty()) {
						System.out.println("�A�N�Z�������Ă��Ȃ�");
						this.showStatus();
						System.exit(0);
					} else {
						for (Accessory accessory : Party.havingAccessory) {
							System.out.print(i + "�D" + accessory.name);
							if (accessory.beEquipped != null) {
								System.out.print(" (" + accessory.beEquipped.name + "��������)");
							}
							System.out.println();
							i++;
						}
						int k = sc.nextInt();
						if (k <= Party.havingAccessory.size() && k > 0) {
							this.equip(Party.havingAccessory.get(k-1));
							this.showStatus();
							System.exit(0);
						} else {
							this.showStatus();
							System.exit(0);
						}
					}
				} else if (z == 0) {
					this.showStatus();
					System.exit(0);
				} else {
					int l = Party.location;
					if (l == 0 || l == 15 || l == 35) {
						Party.arrive();
						System.exit(0);
					} else {
						Party.what();
						System.exit(0);
					}
				}
			} else {
				this.equippingAccessory.showFromStatus();
				System.exit(0);
			}
		} else {
			int l = Party.location;
			if (l == 0 || l == 15 || l == 35) {
				Party.arrive();
				System.exit(0);
			} else {
				Party.what();
				System.exit(0);
			}
		}
	}
	
	
	public void attack(Monster monster) {
		Interface.wait(0.5);
		System.out.println();
		if (this.nowHp > 0) {
			if (monster.hp > 0) {
				System.out.println(this.name + "�̂�������!");
				Interface.wait(0.5);
				int dmg = this.finalStatus[atks] - monster.def;
				if (dmg <= 0) {
					System.out.println(monster.name + "�̓_���[�W���󂯂Ȃ������B");
				} else {
					monster.hp -= dmg;
					System.out.println(monster.name + "��" + dmg + "�̃_���[�W!");
				}
				if (monster.hp <= 0) {
				}
			} else {
				System.out.println(monster.name + "�͊��ɓ|��Ă���");
			}
			System.out.println();
			Interface.wait(1.0);
		}
	}
	
	
	public void gainExp(int exp) {
		System.out.println(this.name + "��" + exp + "�̌o���l�𓾂��B");
		int dummy = this.level;
		this.exp += exp;
		if (this.exp < 100) {
			this.level = 1;
		} else if (this.exp < 250) {
			this.level = 2;
		} else if (this.exp < 600) {
			this.level = 3;
		} else if (this.exp < 1000) {
			this.level = 4;
		} else if (this.exp < 1500) {
			this.level = 5;
		} else if (this.exp < 2100) {
			this.level = 6;
		} else if (this.exp < 2800) {
			this.level = 7;
		} else if (this.exp < 3700) {
			this.level = 8;
		} else if (this.exp < 5000) {
			this.level = 9;
		} else if (this.exp < 6500) {
			this.level = 10;
		} else if (this.exp < 8500) {
			this.level = 11;
		} else if (this.exp < 12000) {
			this.level = 12;
		} else if (this.exp < 16500) {
			this.level = 13;
		} else if (this.exp < 22000) {
			this.level = 14;
		} else {
			this.level = 15;
		}
		if (dummy != this.level) {
			Interface.wait(0.5);
			System.out.println(this.name + "�� ���x��" + this.level + " �ɏオ����!");
			Interface.wait(0.5);
			int h = this.finalStatus[hps];
			int a = this.finalStatus[atks];
			int b = this.finalStatus[defs];
			this.status[hps] = this.hp[this.level - 1];
			this.status[atks] = this.atk[this.level - 1];
			this.status[defs] = this.def[this.level - 1];
			this.finalStatus[hps] = this.status[hps] + this.eqPlusStatus[hps];
			this.finalStatus[atks] = this.status[atks] + this.eqPlusStatus[atks];
			this.finalStatus[defs] = this.status[defs] + this.eqPlusStatus[defs];
			System.out.println("�̗́@ +" + (this.finalStatus[hps] - h) + " (" + this.finalStatus[hps] + ")" );
			Interface.wait(1.0);
			System.out.println("�U���� +" + (this.finalStatus[atks] - a) + " (" + this.finalStatus[atks] + ")" );
			Interface.wait(1.0);
			System.out.println("�h��� +" + (this.finalStatus[defs] - b) + " (" + this.finalStatus[defs] + ")" );
			Interface.wait(1.0);
			Interface.wait(1.0);
			System.out.println();
		}
	}
	
	
	
	public void equip(Equipment equipment) {
		Equipment r;
		if (equipment.beEquipped == null) {
			if (equipment.getClass() == Weapon.class) {
				r = this.equippingWeapon;
				this.removeWeapon(false);
				this.equippingWeapon = (Weapon)equipment;
			} else if (equipment.getClass() == Armor.class) {
				r = this.equippingArmor;
				this.removeArmor(false);
				this.equippingArmor = (Armor)equipment;
			} else {
				r = this.equippingAccessory;
				this.removeAccessory(false);
				this.equippingAccessory = (Accessory)equipment;
			}
			if (r == null) {
				System.out.println(this.name + "��" + equipment.name + "�𑕔�����");
			} else {
				System.out.println(this.name  + "��" + r.name + "���O����" + equipment.name + "�𑕔�����");
			}
			this.eqPlusStatus[hps] += equipment.hp;
			this.finalStatus[hps] =this.status[hps] + this.eqPlusStatus[hps];
			this.eqPlusStatus[atks] += equipment.atk;
			this.finalStatus[atks] =this.status[atks] + this.eqPlusStatus[atks];
			this.eqPlusStatus[defs] += equipment.def;
			this.finalStatus[defs] =this.status[defs] + this.eqPlusStatus[defs];
			equipment.beEquipped = this;
		} else {
			System.out.println(equipment.name + "��" + equipment.beEquipped.name + "���������Ă���");
			System.out.println(equipment.beEquipped.name + "����" + this.name + "�ɕt���ւ��܂����H");
			System.out.println("1 : �͂�  ���̑� : ������");
			int n = sc.nextInt();
			Interface.wait(0.5);
			if (n == 1) {
				if (equipment.getClass() == Weapon.class) {
					r = this.equippingWeapon;
					this.removeWeapon(false);
					equipment.beEquipped.removeWeapon(false);
					this.equippingWeapon = (Weapon)equipment;
					
				} else if (equipment.getClass() == Armor.class) {
					r = this.equippingArmor;
					this.removeArmor(false);
					equipment.beEquipped.removeArmor(false);
					this.equippingArmor = (Armor)equipment;
				} else {
					r = this.equippingAccessory;
					this.removeAccessory(false);
					equipment.beEquipped.removeAccessory(false);
					this.equippingAccessory = (Accessory)equipment;
				}
				if(r == null) {
					System.out.println(this.name + "��" + equipment.name + "�𑕔�����");
				} else {
					System.out.println(this.name  + "��" + r.name + "���O����" + equipment.name + "�𑕔�����");
				}
				this.eqPlusStatus[hps] += equipment.hp;
				this.finalStatus[hps] =this.status[hps] + this.eqPlusStatus[hps];
				this.eqPlusStatus[atks] += equipment.atk;
				this.finalStatus[atks] =this.status[atks] + this.eqPlusStatus[atks];
				this.eqPlusStatus[defs] += equipment.def;
				this.finalStatus[defs] =this.status[defs] + this.eqPlusStatus[defs];
				equipment.beEquipped = this;
			} else {
			}
		}
	}
	
	
	public void removeWeapon(boolean line) {
		Weapon r = this.equippingWeapon;
		if (this.equippingWeapon == null) {
			if (line == true) {
				System.out.println(this.name + "�͕���𑕔����Ă��Ȃ�");
			}
		} else {
			this.eqPlusStatus[hps] -= this.equippingWeapon.hp;
			this.finalStatus[hps] = this.status[hps] + this.eqPlusStatus[hps];
			this.eqPlusStatus[atks] -= this.equippingWeapon.atk;
			this.finalStatus[atks] = this.status[atks] + this.eqPlusStatus[atks];
			this.eqPlusStatus[defs] -= this.equippingWeapon.def;
			this.finalStatus[defs] = this.status[defs] + this.eqPlusStatus[defs];
			this.equippingWeapon.beEquipped = null;
			this.equippingWeapon = null;
		}
		if (line == true) {
			System.out.println(this.name + "��" + r.name + "���O����");
		}
	}
	
	
	public void removeArmor(boolean line) {
		Armor r = this.equippingArmor;
		if (this.equippingArmor == null) {
			if (line == true) {
				System.out.println(this.name + "�͖h��𑕔����Ă��Ȃ�");
			}
		} else {
			this.eqPlusStatus[hps] -= this.equippingArmor.hp;
			this.finalStatus[hps] = this.status[hps] + this.eqPlusStatus[hps];
			this.eqPlusStatus[atks] -= this.equippingArmor.atk;
			this.finalStatus[atks] = this.status[atks] + this.eqPlusStatus[atks];
			this.eqPlusStatus[defs] -= this.equippingArmor.def;
			this.finalStatus[defs] = this.status[defs] + this.eqPlusStatus[defs];
			this.equippingArmor.beEquipped = null;
			this.equippingArmor = null;
		}
		if (line == true) {
			System.out.println(this.name + "��" + r.name + "���O����");
		}
	}
	
	
	public void removeAccessory(boolean line) {
		Accessory r = this.equippingAccessory;
		if (this.equippingAccessory == null) {
			if (line == true) {
				System.out.println(this.name + "�̓A�N�Z�𑕔����Ă��Ȃ�");
			}
		} else {
			this.eqPlusStatus[hps] -= this.equippingAccessory.hp;
			this.finalStatus[hps] = this.status[hps] + this.eqPlusStatus[hps];
			this.eqPlusStatus[atks] -= this.equippingAccessory.atk;
			this.finalStatus[atks] = this.status[atks] + this.eqPlusStatus[atks];
			this.eqPlusStatus[defs] -= this.equippingAccessory.def;
			this.finalStatus[defs] = this.status[defs] + this.eqPlusStatus[defs];
			this.equippingAccessory.beEquipped = null;
			this.equippingAccessory = null;
		}
		if (line == true) {
			System.out.println(this.name + "��" + r.name + "���O����");
		}
	}
	
	public void defend() {

		this.finalStatus[defs] = (int)Math.round(this.finalStatus[defs] * 1.1);
		this.defence = true;
		Party.defence = true;
	}
	
	public void newEq(Equipment equipment) {
		this.eqPlusStatus[hps] += equipment.hp;
		this.finalStatus[hps] =this.status[hps] + this.eqPlusStatus[hps];
		this.eqPlusStatus[atks] += equipment.atk;
		this.finalStatus[atks] =this.status[atks] + this.eqPlusStatus[atks];
		this.eqPlusStatus[defs] += equipment.def;
		this.finalStatus[defs] =this.status[defs] + this.eqPlusStatus[defs];
		equipment.beEquipped = this;
		if (equipment.getClass() == Weapon.class) {
			this.equippingWeapon = (Weapon)equipment;
		} else if (equipment.getClass() == Armor.class) {
			this.equippingArmor = (Armor)equipment;
		} else {
			this.equippingAccessory = (Accessory)equipment;
		}
	}
}