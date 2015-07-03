package bg.ittalents.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class ShopScreen implements Screen {

    public static final int WIDTH_SCREEN = Gdx.graphics.getWidth();
    public static final int CONSTANT_COORDINATES_SCORE_X = WIDTH_SCREEN / 40;
    public static final int HEIGHT_SCREEN = Gdx.graphics.getHeight();
    public static final float CONSTANT_PAD_BOTTOM_AND_TOP = Gdx.graphics.getHeight() / 17;
    public static final float CONSTANT_PAD_LEFT_AND_RIGHT = Gdx.graphics.getWidth() / 10;
    public static final float CONSTANT_HEIGHT_TITLE = 3;
    public static final float CONSTANT_WIDTH_TITLE = 3;
    public static final int WIDTH_WEAPON_BUTTON = WIDTH_SCREEN / 4;
    public static final int HEIGHT_WEAPON_BUTTON = HEIGHT_SCREEN / 3;
    public static final int CONSTANT_COORDINATES_TEXT_ROW_FIRST = Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 21;
    public static final int CONSTANT_COORDINATES_TEXT_ROW_SECOND = Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 8;

    private Game game;
    private SpriteBatch batch;
    private Sprite backgroundSprite;
    private Sprite spriteShop;
    private Image imageTitle;
    private Stage stage;
    private Table container;
    private Table weaponContainer;

    private ImageButton weaponButton1;
    private ImageButton weaponButton2;
    private ImageButton weaponButton3;
    private BitmapFont textBitmapFont;
    private Sprite spriteWeaponButton1;
    private Sprite spriteWeaponButton2;
    private Sprite spriteWeaponButton3;

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
        container = new Table();
        weaponContainer = new Table();

        checkWeapons();

        xxxxxxxxxxxxxxxxxxxxxxxx();

        container.setWidth(stage.getWidth());
        container.align(Align.center | Align.top);
        container.setPosition(0, Gdx.graphics.getHeight());

        container.add(imageTitle).width(WIDTH_SCREEN / CONSTANT_WIDTH_TITLE)
                .height((HEIGHT_SCREEN / CONSTANT_HEIGHT_TITLE)).padBottom(CONSTANT_PAD_BOTTOM_AND_TOP);
        container.row();
        weaponContainer.add(weaponButton1).padLeft(CONSTANT_PAD_LEFT_AND_RIGHT);
        weaponContainer.add(weaponButton2).padLeft(CONSTANT_PAD_LEFT_AND_RIGHT).padRight(CONSTANT_PAD_LEFT_AND_RIGHT);
        weaponContainer.add(weaponButton3).padRight(CONSTANT_PAD_LEFT_AND_RIGHT);
        container.add(weaponContainer).padBottom(CONSTANT_PAD_BOTTOM_AND_TOP);
        container.row();

        stage.addActor(container);

        textBitmapFont = loadFont();

        addListenerWeapons();
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(stage);

    }

    private void xxxxxxxxxxxxxxxxxxxxxxxx() {
        spriteWeaponButton1.setSize(WIDTH_WEAPON_BUTTON, HEIGHT_WEAPON_BUTTON);
        SpriteDrawable spriteDrawableWeaponButton1 = new SpriteDrawable(spriteWeaponButton1);

        spriteWeaponButton2.setSize(WIDTH_WEAPON_BUTTON, HEIGHT_WEAPON_BUTTON);
        SpriteDrawable spriteDrawableWeaponButton2 = new SpriteDrawable(spriteWeaponButton2);

        spriteWeaponButton3.setSize(WIDTH_WEAPON_BUTTON, HEIGHT_WEAPON_BUTTON);
        SpriteDrawable spriteDrawableWeaponButton3 = new SpriteDrawable(spriteWeaponButton3);


        weaponButton1 = new ImageButton(spriteDrawableWeaponButton1);
        weaponButton2 = new ImageButton(spriteDrawableWeaponButton2);
        weaponButton3 = new ImageButton(spriteDrawableWeaponButton3);
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
            if (LoginScreen.myUser.getScore() > 1000) {
                spriteWeaponButton2 = new Sprite(Assets.railRifleAvailable);
            } else {
                spriteWeaponButton2 = new Sprite(Assets.railRifleLocked);
            }
        }

        if (LoginScreen.myUser.getWeapon() == LoginScreen.myUser.getWeaponTreeUnlock()) {
            spriteWeaponButton3 = new Sprite(Assets.heavyMachineGunActive);
        } else {
            if (LoginScreen.myUser.getScore() > 2000) {
                spriteWeaponButton3 = new Sprite(Assets.heavyMachineGunAvailable);
            } else {
                spriteWeaponButton3 = new Sprite(Assets.heavyMachineGunLocked);
            }
        }

        System.out.println(LoginScreen.myUser.toString());
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

    }

    @Override
    public void dispose() {

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

        weaponButton1.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                LoginScreen.myUser.setWeapon(1);
                checkWeapons();
                xxxxxxxxxxxxxxxxxxxxxxxx();
            }
        });

        weaponButton2.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (LoginScreen.myUser.getWeaponTwoUnlock() != 0) {
                    LoginScreen.myUser.setWeapon(2);
                    checkWeapons();
                    xxxxxxxxxxxxxxxxxxxxxxxx();
                }
            }
        });

        weaponButton3.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (LoginScreen.myUser.getWeaponTreeUnlock() != 0) {
                    LoginScreen.myUser.setWeapon(3);
                    checkWeapons();
                    xxxxxxxxxxxxxxxxxxxxxxxx();
                }
            }
        });
    }
}