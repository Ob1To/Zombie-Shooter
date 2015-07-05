package bg.ittalents.game;

/**
 * Created by vlado on 04-Jul-15.
 */
abstract class ResourcesForOffline {
    public static final int USER_HEALTH = 3;
    private static final float BULLETS_COEFFICIENT = 1.2f;
    private static int count;
    private static float durationOn;
    private static float durationOff;

    public static void levelMapResources(int index) {
        switch (index) {
            case 1:
                count = 10;
                durationOn = 1;
                durationOff = 2;
                break;
            case 2:
                count = 60;
                durationOn = 0.9f;
                durationOff = 1.8f;
                break;
            case 3:
                count = 80;
                durationOn = 0.8f;
                durationOff = 1.6f;
                break;
            case 4:
                count = 100;
                durationOn = 0.7f;
                durationOff = 1.4f;
                break;
            case 5:
                count = 120;
                durationOn = 0.6f;
                durationOff = 1.2f;
                break;
            case 6:
                count = 140;
                durationOn = 0.5f;
                durationOff = 1;
                break;
            default:
                count = 40;
                durationOn = 1;
                durationOff = 2;
                break;
        }

        User.getSingletonUser().setUserHealth(USER_HEALTH);
        User.getSingletonUser().setGameAppearingZombieAll(count);
        User.getSingletonUser().setGameAppearingZombieTime(durationOn);
        User.getSingletonUser().setGameHidingZombie(durationOff);
        User.getSingletonUser().setGameBulletsForLevel((int) ((count * BULLETS_COEFFICIENT) * (User.getSingletonUser().getWeapon())));
    }
}
