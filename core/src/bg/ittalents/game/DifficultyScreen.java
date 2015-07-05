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

import bg.ittalents.game.Resource.Assets;
import bg.ittalents.game.Resource.Constant;
import bg.ittalents.game.Resource.ResourcesForOffline;

public class DifficultyScreen implements Screen {
    public static final float CONSTANT_PAD_BOTTOM = Constant.HEIGHT_SCREEN / 12;
    private static final int CONSTANT_HEIGHT_REGISTER_BUTTON =  Constant.HEIGHT_SCREEN /  8;
    private static final float CONSTANT_WIDTH = Constant.WIDTH_SCREEN / 3.5f;
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
        backgroundSprite.setSize(Constant.WIDTH_SCREEN, Constant.HEIGHT_SCREEN);
        stage = new Stage(new ScreenViewport());

        //Dobavqne na zaglavieto na igrata
        Sprite spriteTitle = new Sprite(Assets.zombieShooterTitle);
        Assets.spriteDefaultColorSolid(spriteTitle);
        SpriteDrawable spriteDrawableTitle = new SpriteDrawable(spriteTitle);
        imageTitle = new Image(spriteDrawableTitle);

        generateButton();

        addListenerButton();

        generateAndAddInContainer();

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

    private void generateAndAddInContainer() {
        container = new Table();
        container.setWidth(stage.getWidth());
        container.align(Align.center | Align.top);
        container.setPosition(0, Constant.HEIGHT_SCREEN);

        container.add(imageTitle).width(Constant.WIDTH_SCREEN)
                .height(Constant.CONSTANT_HEIGHT_TITLE).padBottom(CONSTANT_PAD_BOTTOM);
        container.row();
        container.add(easyButton).padBottom(CONSTANT_PAD_BOTTOM);
        container.row();
        container.add(normalButton).padBottom(CONSTANT_PAD_BOTTOM);
        container.row();
        container.add(hardButton).padBottom(CONSTANT_PAD_BOTTOM);
        container.row();

        stage.addActor(container);
    }

    private void addListenerButton() {
        easyButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                Assets.clickButton.play();
                stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        User.getSingletonUser().setGameDamageZombie(1);
                        ResourcesForOffline.setBullets();
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
                        User.getSingletonUser().setGameDamageZombie(2);
                        ResourcesForOffline.setBullets();
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
                        User.getSingletonUser().setGameDamageZombie(3);
                        ResourcesForOffline.setBullets();
                        game.setScreen(new GameScreen(game));
                    }
                })));
            }
        });
    }

    private void generateButton() {
        Sprite spriteEasyButton = new Sprite(Assets.easyLevelButton);
        spriteEasyButton.setSize(CONSTANT_WIDTH,CONSTANT_HEIGHT_REGISTER_BUTTON);
        SpriteDrawable easySpriteDrawable = new SpriteDrawable(spriteEasyButton);

        Sprite spriteNormalButton = new Sprite(Assets.normalLevelButton);
        spriteNormalButton.setSize(CONSTANT_WIDTH, CONSTANT_HEIGHT_REGISTER_BUTTON);
        SpriteDrawable normalSpriteDrawable = new SpriteDrawable(spriteNormalButton);

        Sprite spriteHardButton = new Sprite(Assets.hardLevelButton);
        spriteHardButton.setSize(CONSTANT_WIDTH, CONSTANT_HEIGHT_REGISTER_BUTTON);
        SpriteDrawable hardSpriteDrawable = new SpriteDrawable(spriteHardButton);

        Assets.spriteDefaultColor(spriteEasyButton, spriteNormalButton, spriteHardButton);

        easyButton = new ImageButton(easySpriteDrawable);
        normalButton = new ImageButton(normalSpriteDrawable);
        hardButton = new ImageButton(hardSpriteDrawable);
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