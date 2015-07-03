package bg.ittalents.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class ShopScreen extends Actor implements Screen {

    public static final int WIDTH_SCREEN = Gdx.graphics.getWidth();
    public static final int CONSTANT_COORDINATES_SCORE_X = WIDTH_SCREEN / 40;
    public static final int HEIGHT_SCREEN = Gdx.graphics.getHeight();
    public static final float CONSTANT_HEIGHT_TITLE = HEIGHT_SCREEN / 3;
    public static final float CONSTANT_WIDTH_TITLE = WIDTH_SCREEN / 3;
    public static final int WIDTH_WEAPON_BUTTON = WIDTH_SCREEN / 4;
    public static final int HEIGHT_WEAPON_BUTTON = HEIGHT_SCREEN / 3;
    public static final int CONSTANT_COORDINATES_TEXT_ROW_FIRST = HEIGHT_SCREEN - HEIGHT_SCREEN / 21;
    public static final int CONSTANT_COORDINATES_TEXT_ROW_SECOND = HEIGHT_SCREEN - HEIGHT_SCREEN / 8;
    public static final int CONSTANT_FOR_POSITION = 2;
    public static final int CONSTANT_FOR_POSITION_HEIGHT_WEAPON = HEIGHT_WEAPON_BUTTON / 2;
    public static final int CONSTANT_FOR_POSITION_HEIGHT_TITLE = 15;
    public static final int CONSTANT_FOR_POSITION_WEAPON_3 = WIDTH_SCREEN / 5;
    public static final double CONSTANT_FOR_POSITION_WEAPON_2 = 2.7;
    public static final int CONSTANT_FOR_POSITION_WEAPON_1 = 25;

    private Game game;
    private SpriteBatch batch;
    private Sprite backgroundSprite;
    private Sprite spriteShop;
    private Image imageTitle;
    private Stage stage;
    private BitmapFont textBitmapFont;


    private Sprite spriteWeaponButton1;
    private Sprite spriteWeaponButton2;
    private Sprite spriteWeaponButton3;

    private Image weapon1;
    private Image weapon2;
    private Image weapon3;

    public ShopScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {

        batch = new SpriteBatch();
        backgroundSprite = new Sprite(Assets.backgroundMenu);
        backgroundSprite.setSize(WIDTH_SCREEN, HEIGHT_SCREEN);
        stage = new Stage(new ScreenViewport());
        spriteShop = new Sprite(Assets.shopImage);
        SpriteDrawable spriteDrawableTitle = new SpriteDrawable(spriteShop);
        imageTitle = new Image(spriteDrawableTitle);

        stage = new Stage(new ScreenViewport());

        imageTitle.setPosition(CONSTANT_WIDTH_TITLE, CONSTANT_HEIGHT_TITLE);
        imageTitle.setPosition(WIDTH_SCREEN / CONSTANT_FOR_POSITION - imageTitle.getWidth() / CONSTANT_FOR_POSITION, HEIGHT_SCREEN / CONSTANT_FOR_POSITION + HEIGHT_SCREEN / CONSTANT_FOR_POSITION_HEIGHT_TITLE);
        stage.addActor(imageTitle);
        textBitmapFont = loadFont();

        checkWeapons();
        setButtonPositionAndAddInStage();


        addListenerWeapons();
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(stage);

    }

    private void setButtonPositionAndAddInStage() {
        spriteWeaponButton1.setSize(WIDTH_WEAPON_BUTTON, HEIGHT_WEAPON_BUTTON);
        SpriteDrawable spriteDrawableWeaponButton1 = new SpriteDrawable(spriteWeaponButton1);

        spriteWeaponButton2.setSize(WIDTH_WEAPON_BUTTON, HEIGHT_WEAPON_BUTTON);
        SpriteDrawable spriteDrawableWeaponButton2 = new SpriteDrawable(spriteWeaponButton2);

        spriteWeaponButton3.setSize(WIDTH_WEAPON_BUTTON, HEIGHT_WEAPON_BUTTON);
        SpriteDrawable spriteDrawableWeaponButton3 = new SpriteDrawable(spriteWeaponButton3);

        weapon1 = new Image(spriteDrawableWeaponButton1);
        weapon1.setPosition(WIDTH_SCREEN / CONSTANT_FOR_POSITION_WEAPON_1, CONSTANT_FOR_POSITION_HEIGHT_WEAPON);
        weapon2 = new Image(spriteDrawableWeaponButton2);
        weapon2.setPosition((float) (WIDTH_SCREEN / CONSTANT_FOR_POSITION_WEAPON_2), CONSTANT_FOR_POSITION_HEIGHT_WEAPON);
        weapon3 = new Image(spriteDrawableWeaponButton3);
        weapon3.setPosition((float) (WIDTH_SCREEN / CONSTANT_FOR_POSITION + CONSTANT_FOR_POSITION_WEAPON_3), CONSTANT_FOR_POSITION_HEIGHT_WEAPON);

        stage.addActor(weapon1);
        stage.addActor(weapon2);
        stage.addActor(weapon3);
        addListenerWeapons();

    }

    private void checkWeapons() {
        if (LoginScreen.myUser.getWeapon() == LoginScreen.myUser.getWeaponOneUnlock()) {
            spriteWeaponButton1 = new Sprite(Assets.pistolActive);
        } else {
            spriteWeaponButton1 = new Sprite(Assets.pistolAvailable);
        }


        if (LoginScreen.myUser.getWeapon() == LoginScreen.myUser.getWeaponTwoUnlock()) {
            spriteWeaponButton2 = new Sprite(Assets.railRifleActive);

        } else {
            if (LoginScreen.myUser.getWeaponTwoUnlock() != 0) {
                spriteWeaponButton2 = new Sprite(Assets.railRifleAvailable);
            } else {
                spriteWeaponButton2 = new Sprite(Assets.railRifleLocked);
            }
        }

        if (LoginScreen.myUser.getWeapon() == LoginScreen.myUser.getWeaponTreeUnlock()) {
            spriteWeaponButton3 = new Sprite(Assets.heavyMachineGunActive);
        } else {
            if (LoginScreen.myUser.getWeaponTreeUnlock() != 0) {
                spriteWeaponButton3 = new Sprite(Assets.heavyMachineGunAvailable);
            } else {
                spriteWeaponButton3 = new Sprite(Assets.heavyMachineGunLocked);
            }
        }

        System.out.println(LoginScreen.myUser.toString());
    }


    public void draw(Batch batch) {
        batch.draw(spriteWeaponButton1, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                getScaleX(), getScaleY(), getRotation());
        batch.draw(spriteWeaponButton2, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                getScaleX(), getScaleY(), getRotation());
        batch.draw(spriteWeaponButton3, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                getScaleX(), getScaleY(), getRotation());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        backgroundSprite.draw(batch);
        batch.draw(backgroundSprite, backgroundSprite.getX(), backgroundSprite.getY(), backgroundSprite.getWidth(), backgroundSprite.getHeight());
        textBitmapFontDraw();
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.setScreen(new PlayScreen(game));
        }

    }


    private void textBitmapFontDraw() {
        textBitmapFont.draw(batch, "SCORE", CONSTANT_COORDINATES_SCORE_X, CONSTANT_COORDINATES_TEXT_ROW_FIRST);
        textBitmapFont.draw(batch, "" + LoginScreen.myUser.getScore(), CONSTANT_COORDINATES_SCORE_X, CONSTANT_COORDINATES_TEXT_ROW_SECOND);
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
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public BitmapFont loadFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("game-font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = Gdx.graphics.getHeight() / 12;

        BitmapFont defaultFont = generator.generateFont(fontParameter);

        defaultFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        return defaultFont;
    }

    private void addListenerWeapons() {

        weapon1.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                LoginScreen.myUser.setWeapon(1);
                checkWeapons();
                setButtonPositionAndAddInStage();
            }
        });

        weapon2.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (LoginScreen.myUser.getWeaponTwoUnlock() != 0) {
                    LoginScreen.myUser.setWeapon(2);
                    checkWeapons();
                    setButtonPositionAndAddInStage();
                }
            }
        });

        weapon3.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (LoginScreen.myUser.getWeaponTreeUnlock() != 0) {
                    LoginScreen.myUser.setWeapon(3);
                    checkWeapons();
                    setButtonPositionAndAddInStage();
                }
            }
        });
    }
}