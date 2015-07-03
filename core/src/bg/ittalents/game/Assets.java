package bg.ittalents.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Ob1 on 6/30/2015.
 */
public class Assets {

    public static final AssetManager manager = new AssetManager();

    private Assets() {

    }
    public static final String ZOMBIE_BITE_PATH = "Sounds/ZombieBiteTest.wav";
    public static final String DYING_ZOMBIE_SOUND_PATH = "Sounds/DyingZombie1.wav";
    public static final String DYING_ZOMBIE_SOUND2_PATH = "Sounds/DyingZombie2.wav";
    public static final String SINGLE_SHOT_PATH = "Sounds/SingleShot.wav";
    public static final String DOUBLE_SHOT_PATH = "Sounds/DoubleShot.wav";
    public static final String TRIPLE_SHOT_PATH = "Sounds/TripleShot.ogg";
    public static final String GAME_OVER_SOUND_PATH = "Sounds/GameOverAlt.ogg";
    public static final String PUSH_BUTTON_SOUND_PATH = "Sounds/ClickButton.wav";

    public static final String GAME_PLAY_MUSIC_PATH = "Sounds/GamePlayMusic1.ogg";
    public static final String GAME_MENU_MUSIC_PATH = "Sounds/GameMenuMusic2.ogg";
//    public static final String INTRO_MUSIC_PATH = "Sounds/IntroMusic1.mp3";

    public static final String SCARY_ZOMBIE_IMAGE_PATH = "Images/ScaryZombie.jpg";
    public static final String BUY_BUTTON_PATH = "Images/BuyButton.png";
    public static final String IT_TALENTS_LOGO_PATH = "Images/ittalentslogo.png";
    public static final String ENEMY_SINGLE_IMAGE_PATH = "Images/EnemySingle.png";
    public static final String ENEMY_SINGLE_LEVEL2_IMAGE_PATH = "Images/EnemySingleLevel2.png";
    public static final String ENEMY_SINGLE_LEVEL3_IMAGE_PATH = "Images/EnemySingleLevel3.png";
    public static final String APPLY_BUTTON_PATH = "Images/ApplyButton.png";
    public static final String BACKGROUND_MENU_PATH = "Images/BackgroundMenu.png";
    public static final String BUTTON_FIVE_PATH = "Images/ButtonFive.png";
    public static final String BUTTON_FIVE_MARKED_PATH = "Images/ButtonFiveX.png";
    public static final String BUTTON_FOUR_PATH = "Images/ButtonFour.png";
    public static final String BUTTON_FOUR_MARKED_PATH = "Images/ButtonFourX.png";
    public static final String BUTTON_ONE_PATH = "Images/ButtonOne.png";
    public static final String BUTTON_SIX_PATH = "Images/ButtonSix.png";
    public static final String BUTTON_SIX_MARKED_PATH = "Images/ButtonSixX.png";
    public static final String BUTTON_THREE_PATH = "Images/ButtonThree.png";
    public static final String BUTTON_THREE_MARKED_PATH = "Images/ButtonThreeX.png";
    public static final String BUTTON_TWO_PATH = "Images/ButtonTwo.png";
    public static final String BUTTON_TWO_MARKED_PATH = "Images/ButtonTwoX.png";
    public static final String EASY_BUTTON_PATH = "Images/EasyButton.png";
    public static final String HARD_BUTTON_PATH = "Images/HardButton.png";
    public static final String HEAVY_MACHINE_GUN_AVAILABLE_PATH = "Images/HeavyMachineGun.png";
    public static final String HEAVY_MACHINE_GUN_ACTIVE_PATH = "Images/HeavyMachineGun2.png";
    public static final String HEAVY_MACHINE_GUN_LOCKED_PATH = "Images/HeavyMachineGun3.png";
    public static final String HIGH_SCORE_BUTTON_PATH = "Images/HighScore1.png";
    public static final String LOGIN_BUTTON_PATH = "Images/Login1.png";
    public static final String NORMAL_LEVEL_BUTTON_PATH = "Images/NormalButton.png";
    public static final String PARAGON_LEVEL_IMAGE_PATH = "Images/ParagonButton.png";
    public static final String PISTOL_AVAILABLE_PATH = "Images/Pistol.png";
    public static final String PISTOL_ACTIVE_PATH = "Images/Pistol2.png";
    public static final String PLAY_BUTTON_PATH = "Images/Play1.png";
    public static final String POLICE_BUILDING_BACKGROUND_PATH = "Images/policeBuildingBackground.png";
    public static final String PROFILE_BUTTON_PATH = "Images/Profile1.png";
    public static final String RAIL_RIFLE_AVAILABLE_PATH = "Images/RailRifle.png";
    public static final String RAIL_RIFLE_ACTIVE_PATH = "Images/RailRifle2.png";
    public static final String RAIL_RIFLE_LOCKED_PATH = "Images/RailRifle3.png";
    public static final String REGISTER_BUTTON_PATH = "Images/Register1.png";
    public static final String SHOP_BUTTON_PATH = "Images/Shop1.png";
    public static final String HIGH_SCORE_IMAGE_PATH = "Images/TransperantHighScore.png";
    public static final String PROFILE_IMAGE_PATH = "Images/TransperantProfileButton.png";
    public static final String SHOP_IMAGE_PATH = "Images/TransperantShopButton.png";
    public static final String ZOMBIE_SHOOTER_TITLE_PATH = "Images/ZombieShooter.png";




    public static Sound zombieBite;
    public static Sound dyingZombie1;
    public static Sound dyingZombie2;
    public static Sound singleShot;
    public static Sound doubleShot;
    public static Sound tripleShot;
    public static Sound gameOver;
    public static Sound clickButton;

    //    public static Music introMusic;
    public static Music gamePlayMusic;
    public static Music gameMenuMusic;

    public static Texture scaryZombieImage;
    public static Texture buyButton;
    public static Texture itTalentsLogo;
    public static Texture enemySingleImage;
    public static Texture enemySingleImageLevel2;
    public static Texture enemySingleImageLevel3;
    public static Texture applyButton;
    public static Texture backgroundMenu;
    public static Texture buttonFive;
    public static Texture buttonFiveMarked;
    public static Texture buttonFour;
    public static Texture buttonFourMarked;
    public static Texture buttonOne;
    public static Texture buttonTwo;
    public static Texture buttonTwo_marked;
    public static Texture buttonSix;
    public static Texture buttonSix_marked;
    public static Texture buttonThree;
    public static Texture buttonThreeMarked;
    public static Texture heavyMachineGunAvailable;
    public static Texture heavyMachineGunActive;
    public static Texture heavyMachineGunLocked;
    public static Texture highScoreButton;
    public static Texture loginButton;
    public static Texture normalLevelButton;
    public static Texture easyLevelButton;
    public static Texture hardLevelButton;
    public static Texture paragonLevelImage;
    public static Texture pistolAvailable;
    public static Texture pistolActive;
    public static Texture playButton;
    public static Texture policeBuildingBackground;
    public static Texture profileButton;
    public static Texture railRifleAvailable;
    public static Texture railRifleActive;
    public static Texture railRifleLocked;
    public static Texture registerButton;
    public static Texture shopButton;
    public static Texture highScoreImage;
    public static Texture profileImage;
    public static Texture shopImage;
    public static Texture zombieShooterTitle;

    public static void init() {
        manager.load(ZOMBIE_BITE_PATH, Sound.class);
        manager.load(DYING_ZOMBIE_SOUND_PATH, Sound.class);
        manager.load(DYING_ZOMBIE_SOUND2_PATH, Sound.class);
        manager.load(SINGLE_SHOT_PATH, Sound.class);
        manager.load(DOUBLE_SHOT_PATH, Sound.class);
        manager.load(TRIPLE_SHOT_PATH, Sound.class);
        manager.load(GAME_OVER_SOUND_PATH, Sound.class);
        manager.load(PUSH_BUTTON_SOUND_PATH, Sound.class);

        manager.load(GAME_PLAY_MUSIC_PATH, Music.class);
        manager.load(GAME_MENU_MUSIC_PATH, Music.class);
//        manager.load(INTRO_MUSIC_PATH, Music.class);

        manager.load(SCARY_ZOMBIE_IMAGE_PATH, Texture.class);
        manager.load(BUY_BUTTON_PATH, Texture.class);
        manager.load(IT_TALENTS_LOGO_PATH, Texture.class);
        manager.load(ENEMY_SINGLE_IMAGE_PATH, Texture.class);
        manager.load(ENEMY_SINGLE_LEVEL2_IMAGE_PATH, Texture.class);
        manager.load(ENEMY_SINGLE_LEVEL3_IMAGE_PATH, Texture.class);
        manager.load(APPLY_BUTTON_PATH, Texture.class);
        manager.load(BACKGROUND_MENU_PATH, Texture.class);
        manager.load(BUTTON_FIVE_PATH, Texture.class);
        manager.load(BUTTON_FIVE_MARKED_PATH, Texture.class);
        manager.load(BUTTON_FOUR_PATH, Texture.class);
        manager.load(BUTTON_FOUR_MARKED_PATH, Texture.class);
        manager.load(BUTTON_ONE_PATH, Texture.class);
        manager.load(BUTTON_SIX_PATH, Texture.class);
        manager.load(BUTTON_SIX_MARKED_PATH, Texture.class);
        manager.load(BUTTON_THREE_PATH, Texture.class);
        manager.load(BUTTON_THREE_MARKED_PATH, Texture.class);
        manager.load(BUTTON_TWO_PATH, Texture.class);
        manager.load(BUTTON_TWO_MARKED_PATH, Texture.class);
        manager.load(EASY_BUTTON_PATH, Texture.class);
        manager.load(HARD_BUTTON_PATH, Texture.class);
        manager.load(HEAVY_MACHINE_GUN_AVAILABLE_PATH, Texture.class);
        manager.load(HEAVY_MACHINE_GUN_ACTIVE_PATH, Texture.class);
        manager.load(HEAVY_MACHINE_GUN_LOCKED_PATH, Texture.class);
        manager.load(HIGH_SCORE_BUTTON_PATH, Texture.class);
        manager.load(LOGIN_BUTTON_PATH, Texture.class);
        manager.load(NORMAL_LEVEL_BUTTON_PATH, Texture.class);
        manager.load(PARAGON_LEVEL_IMAGE_PATH, Texture.class);
        manager.load(PISTOL_AVAILABLE_PATH, Texture.class);
        manager.load(PISTOL_ACTIVE_PATH, Texture.class);
        manager.load(PLAY_BUTTON_PATH, Texture.class);
        manager.load(POLICE_BUILDING_BACKGROUND_PATH, Texture.class);
        manager.load(PROFILE_BUTTON_PATH, Texture.class);
        manager.load(RAIL_RIFLE_AVAILABLE_PATH, Texture.class);
        manager.load(RAIL_RIFLE_ACTIVE_PATH, Texture.class);
        manager.load(RAIL_RIFLE_LOCKED_PATH, Texture.class);
        manager.load(REGISTER_BUTTON_PATH, Texture.class);
        manager.load(SHOP_BUTTON_PATH, Texture.class);
        manager.load(HIGH_SCORE_IMAGE_PATH, Texture.class);
        manager.load(PROFILE_IMAGE_PATH, Texture.class);
        manager.load(SHOP_IMAGE_PATH, Texture.class);
        manager.load(ZOMBIE_SHOOTER_TITLE_PATH, Texture.class);

//        Assets.manager.finishLoading();


    }

    public static void createAssets() {
        zombieBite = manager.get(ZOMBIE_BITE_PATH, Sound.class);
        dyingZombie1 = manager.get(DYING_ZOMBIE_SOUND_PATH, Sound.class);
        dyingZombie2 = manager.get(DYING_ZOMBIE_SOUND2_PATH, Sound.class);
        singleShot = manager.get(SINGLE_SHOT_PATH, Sound.class);
        doubleShot = manager.get(DOUBLE_SHOT_PATH, Sound.class);
        tripleShot = manager.get(TRIPLE_SHOT_PATH, Sound.class);
        gameOver = manager.get(GAME_OVER_SOUND_PATH, Sound.class);
        clickButton = manager.get(PUSH_BUTTON_SOUND_PATH, Sound.class);

//        introMusic = manager.get(INTRO_MUSIC_PATH, Music.class);
        gamePlayMusic = manager.get(GAME_PLAY_MUSIC_PATH, Music.class);
        gameMenuMusic = manager.get(GAME_MENU_MUSIC_PATH, Music.class);


        scaryZombieImage = manager.get(SCARY_ZOMBIE_IMAGE_PATH, Texture.class);
        easyLevelButton = manager.get(EASY_BUTTON_PATH, Texture.class);
        hardLevelButton = manager.get(HARD_BUTTON_PATH, Texture.class);
        buyButton = manager.get(BUY_BUTTON_PATH, Texture.class);
        itTalentsLogo = manager.get(IT_TALENTS_LOGO_PATH, Texture.class);
        enemySingleImage = manager.get(ENEMY_SINGLE_IMAGE_PATH, Texture.class);
        enemySingleImageLevel2 = manager.get(ENEMY_SINGLE_LEVEL2_IMAGE_PATH, Texture.class);
        enemySingleImageLevel3 = manager.get(ENEMY_SINGLE_LEVEL3_IMAGE_PATH, Texture.class);
        applyButton = manager.get(APPLY_BUTTON_PATH, Texture.class);
        backgroundMenu = manager.get(BACKGROUND_MENU_PATH, Texture.class);
        buttonFive = manager.get(BUTTON_FIVE_PATH, Texture.class);
        buttonFiveMarked = manager.get(BUTTON_FIVE_MARKED_PATH, Texture.class);
        buttonFour = manager.get(BUTTON_FOUR_PATH, Texture.class);
        buttonFourMarked = manager.get(BUTTON_FOUR_MARKED_PATH, Texture.class);
        buttonOne = manager.get(BUTTON_ONE_PATH, Texture.class);
        buttonTwo = manager.get(BUTTON_TWO_PATH, Texture.class);
        buttonTwo_marked = manager.get(BUTTON_TWO_MARKED_PATH, Texture.class);
        buttonSix = manager.get(BUTTON_SIX_PATH, Texture.class);
        buttonSix_marked = manager.get(BUTTON_SIX_MARKED_PATH, Texture.class);
        buttonThree = manager.get(BUTTON_THREE_PATH, Texture.class);
        buttonThreeMarked = manager.get(BUTTON_THREE_MARKED_PATH, Texture.class);
        heavyMachineGunAvailable = manager.get(HEAVY_MACHINE_GUN_AVAILABLE_PATH, Texture.class);
        heavyMachineGunActive = manager.get(HEAVY_MACHINE_GUN_ACTIVE_PATH, Texture.class);
        heavyMachineGunLocked = manager.get(HEAVY_MACHINE_GUN_LOCKED_PATH, Texture.class);
        highScoreButton = manager.get(HIGH_SCORE_BUTTON_PATH, Texture.class);
        loginButton = manager.get(LOGIN_BUTTON_PATH, Texture.class);
        normalLevelButton = manager.get(NORMAL_LEVEL_BUTTON_PATH, Texture.class);
        paragonLevelImage = manager.get(PARAGON_LEVEL_IMAGE_PATH, Texture.class);
        pistolAvailable = manager.get(PISTOL_AVAILABLE_PATH, Texture.class);
        pistolActive = manager.get(PISTOL_ACTIVE_PATH, Texture.class);
        playButton = manager.get(PLAY_BUTTON_PATH, Texture.class);
        policeBuildingBackground = manager.get(POLICE_BUILDING_BACKGROUND_PATH, Texture.class);
        profileButton = manager.get(PROFILE_BUTTON_PATH, Texture.class);
        railRifleAvailable = manager.get(RAIL_RIFLE_AVAILABLE_PATH, Texture.class);
        railRifleActive = manager.get(RAIL_RIFLE_ACTIVE_PATH, Texture.class);
        railRifleLocked = manager.get(RAIL_RIFLE_LOCKED_PATH, Texture.class);
        registerButton = manager.get(REGISTER_BUTTON_PATH, Texture.class);
        shopButton = manager.get(SHOP_BUTTON_PATH, Texture.class);
        highScoreImage = manager.get(HIGH_SCORE_IMAGE_PATH, Texture.class);
        profileImage = manager.get(PROFILE_IMAGE_PATH, Texture.class);
        shopImage = manager.get(SHOP_IMAGE_PATH, Texture.class);
        zombieShooterTitle = manager.get(ZOMBIE_SHOOTER_TITLE_PATH, Texture.class);
    }

    public static void dispose() {
        manager.dispose();
    }
}