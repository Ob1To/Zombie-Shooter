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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Random;

/**
 * Created by Ob1 on 6/28/2015.
 */
public class GameScreen implements Screen {
    public static final int WIDTH_SCREEN = Gdx.graphics.getWidth();
    public static final float CONSTANT_X_FOR_LIVES_AND_MONEY = (float) (WIDTH_SCREEN / 2 + WIDTH_SCREEN / 3.7);
    public static final int CONSTANT_X_FOR_SCORE = WIDTH_SCREEN / 50;
    public static final int HEIGHT_SCREEN = Gdx.graphics.getHeight();
    public static final float CONSTANT_Y_FOR_MONEY = (float) (HEIGHT_SCREEN / 2 + HEIGHT_SCREEN / 2.2);
    public static final int CONSTANT_Y_FOR_LIVES = HEIGHT_SCREEN;
    public static final float CONSTANT_Y_FOR_SCORE = (float) (HEIGHT_SCREEN / 2 + HEIGHT_SCREEN / 2.1);
    public static final int CONSTANT_TEXT_SIZE = HEIGHT_SCREEN / 20;
    public static final float WIDTH_ZOMBIE = (float) (Gdx.graphics.getWidth() / 8.3);
    public static final float HEIGHT_ZOMBIE = (float) (Gdx.graphics.getHeight() / 3.5);
    public static final float POSITION_ONE_ZOMBIE_X = WIDTH_SCREEN / 13;
    public static final float POSITION_SECOND_ZOMBIE_X = (float) (WIDTH_SCREEN / 4.4);
    public static final float POSITION_THREE_ZOMBIE_X = (float) (WIDTH_SCREEN / 2.2);
    public static final float POSITION_FOUR_ZOMBIE_X = (float) ((WIDTH_SCREEN / 2) + (WIDTH_SCREEN / 5.5));
    public static final float POSITION_FIVE_ZOMBIE_X = (float) ((WIDTH_SCREEN / 2) + (WIDTH_SCREEN / 3.1));
    public static final float POSITION_WINDOWS_ZOMBIE_Y = (float) (HEIGHT_SCREEN / 4.8);
    public static final float POSITION_DOOR_ZOMBIE_Y = (float) (HEIGHT_SCREEN / 8);
    protected static Zombie[] zombieArray;
    protected static int points;
    protected static int lives;
    protected static int bullets;
    public static Stage mainStage;
    protected Game game;
    protected static Sprite backGroundSprite;
    private SpriteBatch spriteBatch;
    private float[] zombiePosition;
    private float lastSpawnZombieTimer;
    private BitmapFont textBitmapFont;

    public GameScreen(Game game) {
        this.game = game;
        this.points = 0;
        this.lives = LoginScreen.myUser.getGameLevel();
        this.bullets = LoginScreen.myUser.getGameBulletsForLevel();
    }

    @Override
    public void show() {
        System.out.println(LoginScreen.myUser.toString());
        Assets.gamePlayMusic.play();
        LoginScreen.stopMenuMusic();
        Assets.gamePlayMusic.setLooping(true);

        mainStage = new Stage(new ScreenViewport());
        backGroundSprite = new Sprite(Assets.policeBuildingBackground);
        backGroundSprite.setSize(WIDTH_SCREEN, HEIGHT_SCREEN);
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
                LoginScreen.myUser.setGameBulletsForLevel(LoginScreen.myUser.getGameBulletsForLevel() - LoginScreen.myUser.getWeapon());
                Assets.singleShot.play();
                return true;
            }
        });

        for (int y = 0; y < 5; y++) {
            Zombie newZombie = new Zombie(LoginScreen.myUser.getGameDamageZombie(), LoginScreen.myUser.getGameHidingZombie());
            newZombie.setSize(WIDTH_ZOMBIE, HEIGHT_ZOMBIE);
            newZombie.setPosition(zombiePosition[y], ((y != 2) ? zombiePosition[5] : zombiePosition[6]));
            zombieArray[y] = newZombie;
            zombieArray[y].setVisible(false);
            mainStage.addActor(newZombie);

        }
    }

    public void addZombie() {
        Random random = new Random();
        int x = random.nextInt(5);
        for (int y = 0; y < 7; y++) {
            if (!zombieArray[x].isVisible()) {
                zombieArray[x].setVisible(true);
                return;
            } else {
                if (x < zombieArray.length-1) {
                    x++;
                } else {
                    x = 0;
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
        if (this.lastSpawnZombieTimer > LoginScreen.myUser.getGameAppearingZombieTime()) {
            this.lastSpawnZombieTimer = 0.0f;
            this.addZombie();
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
        backGroundSprite.draw(spriteBatch);
        textBitmapFontDraw();
        spriteBatch.end();
    }

    private void textBitmapFontDraw() {
        textBitmapFont.draw(spriteBatch, "SCORE  " + points, CONSTANT_X_FOR_SCORE, CONSTANT_Y_FOR_SCORE);
        textBitmapFont.draw(spriteBatch, "LIVES  " + lives, CONSTANT_X_FOR_LIVES_AND_MONEY, CONSTANT_Y_FOR_LIVES);
        textBitmapFont.draw(spriteBatch, "BULLETS  " + bullets, CONSTANT_X_FOR_LIVES_AND_MONEY, CONSTANT_Y_FOR_MONEY);
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

    @Override
    public void dispose() {

    }
}