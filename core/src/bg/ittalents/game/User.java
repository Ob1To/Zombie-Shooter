package bg.ittalents.game;

public class User {


    private int userId;
    private int weapon;
    private int score;
    private int level;
    private int weaponOneUnlock;
    private int weaponTwoUnlock;
    private int weaponTreeUnlock;


    public int getWeaponOneUnlock() {
        return weaponOneUnlock;
    }

    public void setWeaponOneUnlock(int weaponOneUnlock) {
        this.weaponOneUnlock = weaponOneUnlock;
    }

    public int getWeaponTwoUnlock() {
        return weaponTwoUnlock;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", weapon=" + weapon +
                ", score=" + score +
                ", level=" + level +
                ", weaponOneUnlock=" + weaponOneUnlock +
                ", weaponTwoUnlock=" + weaponTwoUnlock +
                ", weaponTreeUnlock=" + weaponTreeUnlock +
                '}';
    }

    public void setWeaponTwoUnlock(int weaponTwoUnlock) {
        this.weaponTwoUnlock = weaponTwoUnlock;
    }
    public int getWeaponTreeUnlock() {
        return weaponTreeUnlock;
    }

    public void setWeaponTreeUnlock(int weaponTreeUnlock) {
        this.weaponTreeUnlock = weaponTreeUnlock;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWeapon() {
        return weapon;
    }

    public void setWeapon(int weapon) {
        this.weapon = weapon;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}