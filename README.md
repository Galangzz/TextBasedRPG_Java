# Text Based RPG
**_Tugas OOP TID23 kelompok 1_**

Anggota :
| Nama                          | NIM           | 
| :-----------------------------| :------------ | 
| Galang Krisna Pramudya        | `23051204134` | 
| Alif Rasyid Febriansyah       | `23051204131` | 
| Mirza Faris Al Arifin         | `23051204142` | 
| Alvin Aulia Rahman Sh         | `23051204117` | 
| Mohammad Eksa Hardian         | `23051204115` | 
| Neng Jihan                    | `23051204114` | 


## Penjelasan Kode
babababab

## Cara Menjalankan
1. Clone repository ini
```bash
git clone https://github.com/Galangzz/TextBasedRPG_Java.git
```

2. Start MySQL dan buat table pada mysql

```sql
CREATE TABLE akun (
    ID_AKUN INT AUTO_INCREMENT,
    EMAIL_AKUN VARCHAR(255) UNIQUE NOT NULL,
    PASSWORD_AKUN VARCHAR(255) NOT NULL,
    ROLE_AKUN VARCHAR(20) NOT NULL DEFAULT 'player',
    PRIMARY KEY (ID_AKUN)
);


CREATE TABLE weapon(
    ID_WEAPON INT AUTO_INCREMENT,
    NAMA_WEAPON VARCHAR(255) NOT NULL,
    DAMAGE_WEAPON INT NOT NULL DEFAULT 10,
    KEGUNAAN_WEAPON ENUM('fighter', 'mage', 'tank') DEFAULT 'fighter',  
    PRIMARY KEY (ID_WEAPON)
);

CREATE TABLE armor(
    ID_ARMOR INT AUTO_INCREMENT,
    NAMA_ARMOR VARCHAR(255) NOT NULL,
    DEFENSE_ARMOR INT NOT NULL DEFAULT 10,
    KEGUNAAN_ARMOR ENUM('fighter', 'mage', 'tank') DEFAULT 'fighter',
    PRIMARY KEY (ID_ARMOR)
);

CREATE TABLE monster(
    ID_MONSTER INT AUTO_INCREMENT,
    NAMA_MONSTER VARCHAR(255) NOT NULL,
    HP_MONSTER INT NOT NULL DEFAULT 100,
    DAMAGE_MONSTER INT NOT NULL DEFAULT 20,
    LEVEL_MONSTER INT NOT NULL DEFAULT 1,
    PRIMARY KEY (ID_MONSTER)
);

CREATE TABLE chara(
    ID_CHARA INT AUTO_INCREMENT,
    ID_AKUN INT NOT NULL,
    ID_WEAPON INT NOT NULL,
    ID_ARMOR INT NOT NULL,
    ID_MONSTER INT,
    NAMA_CHARA VARCHAR(255) NOT NULL,
    LEVEL_CHARA INT NOT NULL DEFAULT 1,
    HP_CHARA INT NOT NULL DEFAULT 100,
    DAMAGE_CHARA INT NOT NULL DEFAULT 5,
    DEFENSE_CHARA INT NOT NULL DEFAULT 0,
    HEAL_AMOUNT INT NOT NULL DEFAULT 0,
    ROLE_CHARA ENUM('fighter', 'mage', 'tank'),
    PRIMARY KEY (ID_CHARA),
    FOREIGN KEY (ID_AKUN) REFERENCES akun(ID_AKUN),
    FOREIGN KEY (ID_WEAPON) REFERENCES weapon(ID_WEAPON),
    FOREIGN KEY (ID_ARMOR) REFERENCES armor(ID_ARMOR),
    FOREIGN KEY (ID_MONSTER) REFERENCES monster(ID_MONSTER)
);

//tambahkan admin game
INSERT INTO akun(EMAIL_AKUN, PASSWORD_AKUN, ROLE_AKUN) VALUES ("admingame@gmail.com", "admin123321nimda", "admin")

INSERT INTO armor(NAMA_ARMOR, DEFENSE_ARMOR, KEGUNAAN_ARMOR) VALUES 
("Battleworn Vest",15,"fighter"),
("Crimson Plate",25,"fighter"),
("Arcane Shroud",10,"mage"),
("Astral Robe",15,"mage"),
("Obsidian Carapace",35,"tank"),
("Fortress Shield",50,"tank");

INSERT INTO weapon(NAMA_WEAPON, DAMAGE_WEAPON, KEGUNAAN_WEAPON) VALUES 
("Bloodfang Blade", 25, "fighter"),
("Thunderstrike Axe", 30, "fighter"),
("Emberstaff", 20, "mage"),
("Frostshard Wand", 15, "mage"),
("Ironbreaker Mace", 10, "tank"),
("Sentinels Halberd", 15, "tank");

INSERT INTO monster(NAMA_MONSTER, HP_MONSTER, DAMAGE_MONSTER, LEVEL_MONSTER) VALUES 
("Shadow Imp", 80, 30, 1),
("Forest Wolf", 100, 32, 1),
("Cave Bat", 60, 20, 1),
("Slime Blob", 90, 20, 1),
("Goblin Scout", 110, 22, 1),
("Stone Golem", 300, 40, 2),
("Venom Drake", 180, 38, 2),
("Skeleton Warrior", 150, 42, 2),
("Fire Salamander", 170, 38, 2),
("Dark Elf Archer", 180, 43, 2),
("Abyssal Tyrant", 800, 60, 3),
("Infernal Warlord", 900, 65, 3),
("Nightmare Beast", 700, 75, 3),
("Lich King", 750, 60, 3),
("Hydra", 1000, 100, 3);

```
3. Compile java
```bash
javac -cp "lib/mysql-connector-j-9.1.0.jar" -d bin -Xlint:deprecation $(find src -name "*.java")
```
4. Run Java(Pastika SQL tetap active)
```bash
java -cp "bin;lib/mysql-connector-j-9.1.0.jar" App
```


## Sabar ya ini programnya belum selesai
![SS](https://github.com/user-attachments/assets/b6f110a4-a815-4ae0-b2a8-802254656046)

## kurang dikit. Sabar Yahh
