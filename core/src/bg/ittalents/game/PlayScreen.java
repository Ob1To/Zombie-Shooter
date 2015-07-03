package bg.ittalents.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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

public class PlayScreen implements Screen {

    public static final int WIDTH_SCREEN = Gdx.graphics.getWidth();
    public static final int HEIGHT_SCREEN = Gdx.graphics.getHeight();
    public static final float CONSTANT_PAD_BOTTOM_AND_TOP = Gdx.graphics.getHeight() / 20;
    public static final float CONSTANT_PAD_LEFT_AND_RIGHT = Gdx.graphics.getWidth() / 30;
    public static final float CONSTANT_HEIGHT_TITLE = 3;
    public static final double WIDTH_BUTTONS = 3.5;
    public static final int HEIGHT_BUTTONS = 6;
    public static final int WIDTH_PLAY_BUTTON = 2;
    public static final int HEIGHT_PLAY_BUTTON = 3;

    private Game game;
    private SpriteBatch batch;
    private Sprite backgroundSprite;
    private Sprite titleSprite;
    private Image imageTitle;
    private Stage stage;
    private Table container;
    private Table buttonsContainer;
    private ImageButton playButton;
    private ImageButton shopButton;
    private ImageButton highScoreButton;
    private ImageButton profileButton;
    private SpriteDrawable spriteDrawableTitle;

    public PlayScreen(Game game) {
        this.game = game;
    }


    @Override
    public void show() {
        batch = new SpriteBatch();

        Gdx.input.setCatchBackKey(true);

        // Creating and setting the background and game title
        backgroundSprite = new Sprite(Assets.backgroundMenu);
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        titleSprite = new Sprite(Assets.zombieShooterTitle);
        spriteDrawableTitle = new SpriteDrawable(titleSprite);
        imageTitle = new Image(spriteDrawableTitle);


        stage = new Stage(new ScreenViewport());
        container = new Table();
        buttonsContainer = new Table();
        container.setWidth(stage.getWidth());
        container.align(Align.center | Align.top);
        container.setPosition(0, Gdx.graphics.getHeight());

        creatingAllTheButtons();

        addingButtonsToContainer();

        stage.addActor(container);

        addingListenersToAllButtons();

        Gdx.input.setInputProcessor(stage);
    }

    private void creatingAllTheButtons() {
        Sprite spritePlayButton = new Sprite(Assets.playButton);
        spritePlayButton.setSize((float) (WIDTH_SCREEN / WIDTH_PLAY_BUTTON), (float) (HEIGHT_SCREEN / HEIGHT_PLAY_BUTTON));
        SpriteDrawable spriteDrawablePlayButton = new SpriteDrawable(spritePlayButton);
        playButton = new ImageButton(spriteDrawablePlayButton);

        Sprite spriteShopButton = new Sprite(Assets.shopButton);
        spriteShopButton.setSize((float) (WIDTH_SCREEN / WIDTH_BUTTONS), (float) (HEIGHT_SCREEN / HEIGHT_BUTTONS));
        SpriteDrawable spriteDrawableShopButton = new SpriteDrawable(spriteShopButton);
        shopButton = new ImageButton(spriteDrawableShopButton);

        Sprite spriteHighScoreButton = new Sprite(Assets.highScoreButton);
        spriteHighScoreButton.setSize((float) (WIDTH_SCREEN / WIDTH_BUTTONS), (float) (HEIGHT_SCREEN / HEIGHT_BUTTONS));
        SpriteDrawable spriteDrawableHighScoreButton = new SpriteDrawable(spriteHighScoreButton);
        highScoreButton = new ImageButton(spriteDrawableHighScoreButton);

        Sprite spriteProfileButton = new Sprite(Assets.profileButton);
        spriteProfileButton.setSize((float) (WIDTH_SCREEN / WIDTH_BUTTONS), (float) (HEIGHT_SCREEN / HEIGHT_BUTTONS));
        SpriteDrawable spriteDrawableProfileButton = new SpriteDrawable(spriteProfileButton);
        profileButton = new ImageButton(spriteDrawableProfileButton);
    }

    private void addingListenersToAllButtons() {
        playButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                Assets.clickButton.play();
                stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new LevelMapScreen(game));
                    }
                })));
            }
        });
        shopButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                Assets.clickButton.play();
                stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new ShopScreen(game));
                    }
                })));
            }
        });
        highScoreButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                Assets.clickButton.play();
                stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new HighScoreScreen(game));
                    }
                })));
            }
        });
        profileButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                Assets.clickButton.play();
                stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new ProfileScreen(game));
                    }
                })));
            }
        });
    }

    private void addingButtonsToContainer() {
        container.add(imageTitle).width(WIDTH_SCREEN)
                .height((HEIGHT_SCREEN / CONSTANT_HEIGHT_TITLE)).padBottom(CONSTANT_PAD_BOTTOM_AND_TOP);
        container.row();
        container.add(playButton).padBottom(CONSTANT_PAD_BOTTOM_AND_TOP);
        container.row();
        buttonsContainer.add(shopButton).padRight(CONSTANT_PAD_LEFT_AND_RIGHT);
        buttonsContainer.add(highScoreButton).padLeft(CONSTANT_PAD_LEFT_AND_RIGHT).padRight(CONSTANT_PAD_LEFT_AND_RIGHT);
        buttonsContainer.add(profileButton).padLeft(CONSTANT_PAD_LEFT_AND_RIGHT);
        container.add(buttonsContainer).padTop(CONSTANT_PAD_BOTTOM_AND_TOP);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        backgroundSprite.draw(batch);
        batch.draw(backgroundSprite, backgroundSprite.getX(), backgroundSprite.getY(), backgroundSprite.getWidth(), backgroundSprite.getHeight());
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.setScreen(new LoginScreen(game));
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
        batch.dispose();
        stage.dispose();
        game.dispose();
    }

}
