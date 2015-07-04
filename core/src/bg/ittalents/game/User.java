package bg.ittalents.game;

public class User {
    public static User singletonUser = new User();

    private User(){
    }

    public static User singletonUser(){
        return singletonUser;
    }


    private int userId;
    private int weapon;
    private int score;
    private int level;
    private int weaponOneUnlock;
    private int weaponTwoUnlock;
    private int weaponTreeUnlock;

    private int gameLevel;
    private int gameAppearingZombieAll;
    private float gameAppearingZombieTime;
    private float gameHidingZombie;
    private int gameDamageZombie;
    private int userHealth;
    private int gameBulletsForLevel;


    public int getWeaponOneUnlock() {
        return weaponOneUnlock;
    }

    public void setWeaponOneUnlock(int weaponOneUnlock) {
        this.weaponOneUnlock = weaponOneUnlock;
    }

    public int getWeaponTwoUnlock() {
        return weaponTwoUnlock;
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

    public int getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(int gameLevel) {
        this.gameLevel = gameLevel;
    }

    public int getGameAppearingZombieAll() {
        return gameAppearingZombieAll;
    }

    public void setGameAppearingZombieAll(int gameAppearingZombieAll) {
        this.gameAppearingZombieAll = gameAppearingZombieAll;
    }

    public float getGameAppearingZombieTime() {
        return gameAppearingZombieTime;
    }

    public void setGameAppearingZombieTime(float gameAppearingZombieTime) {
        this.gameAppearingZombieTime = gameAppearingZombieTime;
    }

    public float getGameHidingZombie() {
        return gameHidingZombie;
    }

    public void setGameHidingZombie(float gameHidingZombie) {
        this.gameHidingZombie = gameHidingZombie;
    }

    public int getGameDamageZombie() {
        return gameDamageZombie;
    }

    public void setGameDamageZombie(int gameDamageZombie) {
        this.gameDamageZombie = gameDamageZombie;
    }

    public int getUserHealth() {
        return userHealth;
    }

    public void setUserHealth(int userHealth) {
        this.userHealth = userHealth;
    }

    public int getGameBulletsForLevel() {
        return this.gameBulletsForLevel;
    }

    public void setGameBulletsForLevel(int gameBulletsForLevel) {
        this.gameBulletsForLevel = gameBulletsForLevel;
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
                ", gameLevel=" + gameLevel +
                ", gameAppearingZombieAll=" + gameAppearingZombieAll +
                ", gameAppearingZombieTime=" + gameAppearingZombieTime +
                ", gameHidingZombie=" + gameHidingZombie +
                ", gameDamageZombie=" + gameDamageZombie +
                ", userHealth=" + userHealth +
                ", gameBulletsForLevel=" + gameBulletsForLevel +
                '}';
    }
}