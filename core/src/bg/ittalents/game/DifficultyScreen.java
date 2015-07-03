package bg.ittalents.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class DifficultyScreen implements Screen {

    public static final int WIDTH_SCREEN = Gdx.graphics.getWidth();
    public static final int HEIGHT_SCREEN = Gdx.graphics.getHeight();
    public static final float CONSTANT_PAD_BOTTOM = Gdx.graphics.getHeight() / 12;
    public static final float CONSTANT_HEIGHT_TITLE = 3;
    public static final int CONSTANT_HEIGHT_REGISTER_BUTTON = 8;
    private static final float CONSTANT_WIDTH = 3.5f;
    private Game game;
    private Stage stage;
    private SpriteBatch batch;
    private Sprite backgroundSprite;
    private Table container;
    private Image imageTitle;
    private ImageButton easyButton;
    private ImageButton normalButton;
    private ImageButton hardButton;

    public DifficultyScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {

        //Suzdavane na backgraunda
        batch = new SpriteBatch();
        backgroundSprite = new Sprite(Assets.backgroundMenu);
        backgroundSprite.setSize(WIDTH_SCREEN, HEIGHT_SCREEN);
        stage = new Stage(new ScreenViewport());

        //Dobavqne na zaglavieto na igrata
        Sprite spriteTitle = new Sprite(Assets.zombieShooterTitle);
        SpriteDrawable spriteDrawableTitle = new SpriteDrawable(spriteTitle);
        imageTitle = new Image(spriteDrawableTitle);


        Sprite spriteEasyButton = new Sprite(Assets.easyLevelButton);
        spriteEasyButton.setSize((WIDTH_SCREEN / CONSTANT_WIDTH), (HEIGHT_SCREEN / CONSTANT_HEIGHT_REGISTER_BUTTON));
        SpriteDrawable easySpriteDrawable = new SpriteDrawable(spriteEasyButton);

        Sprite spriteNormalButton = new Sprite(Assets.normalLevelButton);
        spriteNormalButton.setSize((WIDTH_SCREEN / CONSTANT_WIDTH), (HEIGHT_SCREEN / CONSTANT_HEIGHT_REGISTER_BUTTON));
        SpriteDrawable normalSpriteDrawable = new SpriteDrawable(spriteNormalButton);

        Sprite spriteHardButton = new Sprite(Assets.hardLevelButton);
        spriteHardButton.setSize((WIDTH_SCREEN / CONSTANT_WIDTH), (HEIGHT_SCREEN / CONSTANT_HEIGHT_REGISTER_BUTTON));
        SpriteDrawable hardSpriteDrawable = new SpriteDrawable(spriteHardButton);

        easyButton = new ImageButton(easySpriteDrawable);
        normalButton = new ImageButton(normalSpriteDrawable);
        hardButton = new ImageButton(hardSpriteDrawable);


        easyButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                Assets.clickButton.play();
                stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new GameScreen(game));
                    }
                })));
            }
        });

        normalButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                Assets.clickButton.play();
                stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new GameScreen(game));
                    }
                })));
            }
        });

        hardButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                Assets.clickButton.play();
                stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new GameScreen(game));
                    }
                })));
            }
        });

        container = new Table();
        container.setWidth(stage.getWidth());
        container.align(Align.center | Align.top);
        container.setPosition(0, HEIGHT_SCREEN);

        container.add(imageTitle).width(WIDTH_SCREEN)
                .height((HEIGHT_SCREEN / CONSTANT_HEIGHT_TITLE)).padBottom(CONSTANT_PAD_BOTTOM);
        container.row();
        container.add(easyButton).padBottom(CONSTANT_PAD_BOTTOM);
        container.row();
        container.add(normalButton).padBottom(CONSTANT_PAD_BOTTOM);
        container.row();
        container.add(hardButton).padBottom(CONSTANT_PAD_BOTTOM);
        container.row();

        stage.addActor(container);

        Gdx.input.setInputProcessor(stage);

        Gdx.input.setCatchBackKey(true);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        backgroundSprite.draw(batch);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.setScreen(new LevelMapScreen(game));
        }
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
        game.dispose();
        stage.dispose();
        batch.dispose();
    }
}