package bg.ittalents.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.Random;
import bg.ittalents.game.Resource.Assets;
import bg.ittalents.game.Resource.Constant;

/**
 * Created by Ob1 on 6/28/2015.
 */
public class GameScreen implements Screen, ITextFont {
    private static final int WIDTH_SCREEN = Constant.WIDTH_SCREEN;
    private static final int HEIGHT_SCREEN = Constant.HEIGHT_SCREEN;
    private static final float CONSTANT_X_FOR_LIVES_AND_MONEY = (float) (WIDTH_SCREEN / 2 + WIDTH_SCREEN / 3.7);
    private static final int CONSTANT_X_FOR_SCORE = WIDTH_SCREEN / 50;
    private static final float CONSTANT_Y_FOR_MONEY = (float) (HEIGHT_SCREEN / 2 + HEIGHT_SCREEN / 2.2);
    private static final int CONSTANT_Y_FOR_LIVES = HEIGHT_SCREEN;
    private static final float CONSTANT_Y_FOR_SCORE = (float) (HEIGHT_SCREEN / 2 + HEIGHT_SCREEN / 2.1);
    private static final int CONSTANT_TEXT_SIZE = HEIGHT_SCREEN / 20;
    private static final float WIDTH_ZOMBIE = (float) (WIDTH_SCREEN / 8.3);
    private static final float HEIGHT_ZOMBIE = (float) (HEIGHT_SCREEN / 3.5);
    private static final float POSITION_ONE_ZOMBIE_X = WIDTH_SCREEN / 13;
    private static final float POSITION_SECOND_ZOMBIE_X = (float) (WIDTH_SCREEN / 4.4);
    private static final float POSITION_THREE_ZOMBIE_X = (float) (WIDTH_SCREEN / 2.2);
    private static final float POSITION_FOUR_ZOMBIE_X = (float) ((WIDTH_SCREEN / 2) + (WIDTH_SCREEN / 5.5));
    private static final float POSITION_FIVE_ZOMBIE_X = (float) ((WIDTH_SCREEN / 2) + (WIDTH_SCREEN / 3.1));
    private static final float POSITION_WINDOWS_ZOMBIE_Y = (float) (HEIGHT_SCREEN / 4.8);
    private static final float POSITION_DOOR_ZOMBIE_Y = (float) (HEIGHT_SCREEN / 8);
    protected static Zombie[] zombieArray;
    protected static int points;
    protected static int lives;
    protected static Stage mainStage;
    protected Game game;
    protected static Sprite backGroundSprite;
    protected static Sprite scaryZombieBackground;
    private SpriteBatch spriteBatch;
    private float[] zombiePosition;
    private float lastSpawnZombieTimer;
    private BitmapFont textBitmapFont;
    private Texture bossTexture;
    private Sprite spriteBoss;
    private float timerBoss;
    private Image imageBoss;
    protected static Image backGroundImage;
    protected static Image scaryZombieBackgroundImage;
    private SpriteDrawable spriteDrawableBackGround;
    private SpriteDrawable spriteDrawableScaryZombieBackground;
    private Boolean alreadyPlayed = true;
    private Zombie newZombie;
    private boolean checkForAddTextureBoss;
    protected static boolean outOfAmmo;
    private Sprite preBossSprite;
    private SpriteDrawable preBossSpriteDrawable;
    private Image preBossImage;
    private boolean alreadyStartedBackgroundMusic;


    public GameScreen(Game game) {
        this.game = game;
        this.points = 0;
        this.lives = User.getSingletonUser().getUserHealth();

    }

    @Override
    public void show() {
        scaryZombieBackground = new Sprite(Assets.scaryZombieImage);
        scaryZombieBackground.setSize(WIDTH_SCREEN, HEIGHT_SCREEN);
        spriteDrawableScaryZombieBackground = new SpriteDrawable(scaryZombieBackground);
        scaryZombieBackgroundImage = new Image(spriteDrawableScaryZombieBackground);

        preBossSprite = new Sprite(Assets.preBossBackground);
        preBossSprite.setSize(WIDTH_SCREEN, HEIGHT_SCREEN);
        preBossSpriteDrawable = new SpriteDrawable(preBossSprite);
        preBossImage = new Image(preBossSpriteDrawable);


        Assets.gamePlayMusic.play();
        LoginScreen.stopMenuMusic();
        Assets.gamePlayMusic.setLooping(true);

        mainStage = new Stage(new ScreenViewport());
        backGroundSprite = new Sprite(Assets.policeBuildingBackground);
        backGroundSprite.setSize(WIDTH_SCREEN, HEIGHT_SCREEN);
        spriteDrawableBackGround = new SpriteDrawable(backGroundSprite);
        backGroundImage = new Image(spriteDrawableBackGround);

        mainStage.addActor(backGroundImage);
        spriteBatch = new SpriteBatch();

        zombieArray = new Zombie[5];
        zombiePosition = new float[7];
        zombiePosition[0] = POSITION_ONE_ZOMBIE_X;
        zombiePosition[1] = POSITION_SECOND_ZOMBIE_X;
        zombiePosition[2] = POSITION_THREE_ZOMBIE_X;
        zombiePosition[3] = POSITION_FOUR_ZOMBIE_X;
        zombiePosition[4] = POSITION_FIVE_ZOMBIE_X;
        zombiePosition[5] = POSITION_WINDOWS_ZOMBIE_Y;
        zombiePosition[6] = POSITION_DOOR_ZOMBIE_Y;

        textBitmapFont = loadFont();

        Gdx.input.setInputProcessor(mainStage);

        mainStage.addListener(new ClickListener() { // Adding Shooting sounds to the stage.
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (User.getSingletonUser().getGameBulletsForLevel() <= 0) {
                    Assets.emptyClip.play();
                } else {
                    User.getSingletonUser().setGameBulletsForLevel(User.getSingletonUser().getGameBulletsForLevel() - User.getSingletonUser().getWeapon());
                    switch (User.getSingletonUser().getWeapon()) {
                        case 1:
                            long id = Assets.singleShot.play(0.3f);
                            break;
                        case 2:
                            long idDouble = Assets.doubleShot.play(0.3f);
                            break;
                        case 3:
                            long idTriple = Assets.tripleShot.play(0.3f);
                            break;
                    }
                }
                return true;
            }
        });

        for (int y = 0; y < 5; y++) {
            if (y == 2) {
                newZombie = new Zombie(User.getSingletonUser().getGameDamageZombie(), User.getSingletonUser().getGameHidingZombie(), true);
            } else {
                newZombie = new Zombie(User.getSingletonUser().getGameDamageZombie(), User.getSingletonUser().getGameHidingZombie(), false);
            }
            newZombie.setSize(WIDTH_ZOMBIE, HEIGHT_ZOMBIE);
            newZombie.setPosition(zombiePosition[y], ((y != 2) ? zombiePosition[5] : zombiePosition[6]));
            zombieArray[y] = newZombie;
            zombieArray[y].setVisible(false);
            mainStage.addActor(newZombie);

        }
        mainStage.addActor(scaryZombieBackgroundImage);
        scaryZombieBackgroundImage.setVisible(false);
        mainStage.addActor(preBossImage);
        preBossImage.setVisible(false);
    }

    public void addZombie() {
        Random zombiePositionGenerator = new Random();
        int tempPosition = zombiePositionGenerator.nextInt(5);
        for (int y = 0; y < 7; y++) {
            if (!zombieArray[tempPosition].isVisible()) {
                zombieArray[tempPosition].setVisible(true);
                return;
            } else {
                if (tempPosition < zombieArray.length - 1) {
                    tempPosition++;
                } else {
                    tempPosition = 0;
                }
            }
        }
    }

    public void updateZombieTimeLiving(float timeSinceLast) {
        for (int i = 0; i < zombieArray.length; i++) {
            if ((zombieArray[i] != null)) {
                zombieArray[i].timeLiving(timeSinceLast);
                zombieArray[i].checkTimeLiving();
            }
        }
    }

    public void zombieSpawner(float timeSinceLast) {

        this.lastSpawnZombieTimer += timeSinceLast;
        updateZombieTimeLiving(timeSinceLast);
        if (this.lastSpawnZombieTimer > User.getSingletonUser().getGameAppearingZombieTime()) {
            this.lastSpawnZombieTimer = 0.0f;
            if (User.getSingletonUser().getGameAppearingZombieAll() > 0) {
                User.getSingletonUser().setGameAppearingZombieAll(User.getSingletonUser().getGameAppearingZombieAll() - 1);
                this.addZombie();
            }
        }
    }

    private void settingOutOfAmmo() {
        if (User.getSingletonUser().getGameBulletsForLevel() <= 0) {
            outOfAmmo = true;
        }else{
            outOfAmmo = false;
        }
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        zombieSpawner(Gdx.graphics.getDeltaTime());

        mainStage.act(Gdx.graphics.getDeltaTime());
        mainStage.draw();

        spriteBatch.begin();
        textBitmapFontDraw();
        spriteBatch.end();

        startTimerBoss(Gdx.graphics.getDeltaTime());
        addBossTexture();
        playingBossSounds();
        checkForGameOver();
        checkWinScreen();
        settingOutOfAmmo();
        preBossImageManager();

    }

    private void textBitmapFontDraw() {
        textBitmapFont.draw(spriteBatch, "SCORE  " + points, CONSTANT_X_FOR_SCORE, CONSTANT_Y_FOR_LIVES);
        textBitmapFont.draw(spriteBatch, "Zombie  " + User.getSingletonUser().getGameAppearingZombieAll(), CONSTANT_X_FOR_SCORE, CONSTANT_Y_FOR_MONEY);
        textBitmapFont.draw(spriteBatch, "LIVES  " + lives, CONSTANT_X_FOR_LIVES_AND_MONEY, CONSTANT_Y_FOR_LIVES);
        textBitmapFont.draw(spriteBatch, "BULLETS  " + User.getSingletonUser().getGameBulletsForLevel(), CONSTANT_X_FOR_LIVES_AND_MONEY, CONSTANT_Y_FOR_MONEY);
    }

    public BitmapFont loadFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("game-font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = CONSTANT_TEXT_SIZE;

        BitmapFont defaultFont = generator.generateFont(fontParameter);

        defaultFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        return defaultFont;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
    }


    private void addBossTexture() {
        if ((timerBoss <= 12.22) && (checkForAddTextureBoss == false)) {
            checkForAddTextureBoss = true;
            bossTexture = Assets.bossTexture1;
        } else if ((timerBoss > 15.22) && (timerBoss <= 18.22) && (checkForAddTextureBoss == true)) {
            checkForAddTextureBoss = false;
            Assets.takingDamage.play();
            bossTexture = Assets.bossTexture2;
        } else if ((timerBoss > 18.22) && (timerBoss <= 21.22) && (checkForAddTextureBoss == false)) {
            checkForAddTextureBoss = true;
            Assets.takingDamage.play();
            bossTexture = Assets.bossTexture3;
        } else if ((timerBoss > 21.22) && (timerBoss <= 24.22) && (checkForAddTextureBoss == true)) {
            checkForAddTextureBoss = false;
            Assets.takingDamage.play();
            bossTexture = Assets.bossTexture4;
        } else if ((timerBoss > 24.22) && (timerBoss <= 27.22) && (checkForAddTextureBoss == false)) {
            checkForAddTextureBoss = true;
            Assets.takingDamage.play();
            bossTexture = Assets.bossTexture5;
        } else if ((timerBoss > 27.22) && (timerBoss <= 30.22) && (checkForAddTextureBoss == true)) {
            checkForAddTextureBoss = false;
            Assets.takingDamage.play();
            bossTexture = Assets.bossTexture6;
        } else if ((timerBoss > 30.22) && (timerBoss <= 33.22) && (checkForAddTextureBoss == false)) {
            checkForAddTextureBoss = true;
            Assets.takingDamage.play();
            bossTexture = Assets.bossTexture7;
        } else if ((timerBoss > 33.22) && (timerBoss <= 36.22) && (checkForAddTextureBoss == true)) {
            checkForAddTextureBoss = false;
            Assets.takingDamage.play();
            bossTexture = Assets.bossTexture8;
        } else if ((timerBoss > 36.22) && (timerBoss <= 39.22) && (checkForAddTextureBoss == false)) {
            checkForAddTextureBoss = true;
            Assets.takingDamage.play();
            bossTexture = Assets.bossTexture9;
        }


        spriteBoss = new Sprite(bossTexture);
        spriteBoss.setSize((float) (WIDTH_SCREEN / 2.2), HEIGHT_SCREEN);
        SpriteDrawable spriteDrawableBoss = new SpriteDrawable(spriteBoss);
        imageBoss = new Image(spriteDrawableBoss);
        imageBoss.setPosition(WIDTH_SCREEN / 2 - imageBoss.getWidth() / 2, HEIGHT_SCREEN / 2 - imageBoss.getHeight() / 2);
        imageBoss.addListener(new ClickListener() { // Adding Shooting sounds to the stage.
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                points += 2 * User.getSingletonUser().getWeapon();
                User.getSingletonUser().setGameBulletsForLevel(User.getSingletonUser().getGameBulletsForLevel() + User.getSingletonUser().getWeapon());
                return true;
            }
        });
        if ((User.getSingletonUser().getGameAppearingZombieAll() <= 0) && (timerBoss >=  12.22)) { // ASASSASASSSSSAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
            imageBoss.remove();
            mainStage.addActor(imageBoss);
        }
    }

    private void playingBossSounds() {
//        Assets.gamePlayMusic.stop();
        if (timerBoss >= 12.22 && timerBoss <= 16.22 && alreadyPlayed == true) {
            preBossImage.setVisible(false);
            alreadyPlayed = false;
            Assets.bossSound1.play();
//            long id = Assets.bossSound1.play();
//            Assets.bossSound1.setPitch(id, 0.8f);
        } else if (timerBoss > 16.22 && timerBoss <= 20.22 && alreadyPlayed == false) {
            alreadyPlayed = true;
            long id = Assets.bossSound2.play();
//            Assets.bossSound2.setPitch(id, 0.8f);
        } else if (timerBoss > 20.22 && timerBoss <= 24.22 && alreadyPlayed == true) {
            alreadyPlayed = false;
            long id = Assets.bossSound3.play();
//            Assets.bossSound3.setPitch(id, 0.8f);
        } else if (timerBoss > 24.22 && timerBoss <= 28.22 && alreadyPlayed == false) {
            alreadyPlayed = true;
            long id = Assets.bossSound4.play();
//            Assets.bossSound4.setPitch(id, 0.8f);
        } else if (timerBoss > 28.22 && timerBoss <= 32.22 && alreadyPlayed == true) {
            long id = Assets.bossSound1.play();
//            Assets.bossSound1.setPitch(id, 0.8f);
            alreadyPlayed = false;
        } else if (timerBoss > 32.22 && timerBoss <= 36.22 && alreadyPlayed == false) {
            long id = Assets.bossSound2.play();
//            Assets.bossSound2.setPitch(id, 0.8f);
            alreadyPlayed = true;
        } else if (timerBoss > 36.22 && timerBoss <= 40.22 && alreadyPlayed == true) {
            long id = Assets.bossSound3.play();
//            Assets.bossSound3.setPitch(id, 0.8f);
            alreadyPlayed = false;
        } else if (timerBoss > 40.22 && timerBoss <= 44.22 && alreadyPlayed == false) {
            long id = Assets.bossSound4.play();
//            Assets.bossSound4.setPitch(id, 0.8f);
            alreadyPlayed = true;
        }
    }

    private void preBossImageManager(){

        if (timerBoss > 3 && timerBoss <= 12.22f && alreadyStartedBackgroundMusic == false){
            alreadyStartedBackgroundMusic = true;
            preBossImage.setVisible(true);
            Assets.gamePlayMusic.stop();
            Assets.bossIntro.play();
        }
        else if (timerBoss > 12.22f){
            preBossImage.setVisible(false);
            Assets.bossIntro.stop();
            Assets.gamePlayMusic.play();
        }
    }

    private void startTimerBoss(float delta) {
        if (User.getSingletonUser().getGameAppearingZombieAll() <= 0) {
//            Assets.gamePlayMusic.stop();
//            Assets.bossIntro.play();
            timerBoss += delta;
        }
    }

    private void checkForGameOver() {
        if (lives <= 0) {
            game.setScreen(new GameOverScreen(game));
        }
    }

    private void checkWinScreen() {
        if ((lives > 0) && (timerBoss > 39.22)) {
            game.setScreen(new GameWinScreen(game));
        }

    }
    @Override
    public void dispose() {
        textBitmapFont.dispose();
        spriteBatch.dispose();
        game.dispose();
        mainStage.dispose();
    }
}