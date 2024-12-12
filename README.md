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

CREATE TABLE chara(
    ID_CHARA INT AUTO_INCREMENT,
    ID_AKUN INT NOT NULL,
    ID_WEAPON INT NOT NULL,
    ID_ARMOR INT NOT NULL,
    NAMA_CHARA VARCHAR(255) NOT NULL,
    LEVEL_CHARA INT NOT NULL DEFAULT 1,
    HP_CHARA INT NOT NULL DEFAULT 100,
    DAMAGE_CHARA INT NOT NULL DEFAULT 20,
    ROLE_CHARA ENUM('fighter', 'mage', 'tank'),
    PRIMARY KEY (ID_CHARA),
    FOREIGN KEY (ID_AKUN) REFERENCES akun(ID_AKUN),
    FOREIGN KEY (ID_WEAPON) REFERENCES weapon(ID_WEAPON),
    FOREIGN KEY (ID_ARMOR) REFERENCES armor(ID_ARMOR)
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

//tambahkan admin game
INSERT INTO akun(EMAIL_AKUN, PASSWORD_AKUN, ROLE_AKUN) VALUES ("admingame@gmail.com", "admin123321nimda", "admin")

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
