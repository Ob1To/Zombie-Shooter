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


public class LevelMapScreen implements Screen {

    public static final int WIDTH_SCREEN = Gdx.graphics.getWidth();
    public static final int HEIGHT_SCREEN = Gdx.graphics.getHeight();
    public static final float CONSTANT_PAD_BOTTOM_AND_TOP = Gdx.graphics.getHeight() / 17;
    public static final float CONSTANT_PAD_LEFT_AND_RIGHT = Gdx.graphics.getWidth() / 15;
    public static final float CONSTANT_HEIGHT_TITLE = 3;
    public static final double WIDTH_BUTTONS = 3.5;
    public static final int HEIGHT_BUTTONS = 4;

    private Game game;
    private SpriteBatch batch;
    private Sprite sprite;
    private Image imageTitle;
    private Stage stage;
    private Table container;
    private Table firstRowContainer;
    private Table secondRowContainer;
    private ImageButton oneButton;
    private ImageButton twoButton;
    private ImageButton threeButton;
    private ImageButton fourButton;
    private ImageButton fiveButton;
    private ImageButton sixButton;

    public LevelMapScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        //Suzdavane na backgraunda
        batch = new SpriteBatch();
        sprite = new Sprite(Assets.backgroundMenu);
        sprite.setSize(WIDTH_SCREEN, HEIGHT_SCREEN);
        stage = new Stage(new ScreenViewport());

        Sprite spriteTitle = new Sprite(Assets.paragonLevelImage);
        SpriteDrawable spriteDrawableTitle = new SpriteDrawable(spriteTitle);
        imageTitle = new Image(spriteDrawableTitle);

        stage = new Stage(new ScreenViewport());
        container = new Table();
        firstRowContainer = new Table();
        secondRowContainer = new Table();


        Sprite spriteBuyItButton = new Sprite(Assets.buttonOne);
        spriteBuyItButton.setSize((float) (WIDTH_SCREEN / WIDTH_BUTTONS), (float) (HEIGHT_SCREEN / HEIGHT_BUTTONS));
        SpriteDrawable spriteDrawableBuyItButton = new SpriteDrawable(spriteBuyItButton);
        oneButton = new ImageButton(spriteDrawableBuyItButton);

        Sprite spriteTwoButton = new Sprite(Assets.buttonThreeMarked);
        spriteTwoButton.setSize((float) (WIDTH_SCREEN / WIDTH_BUTTONS), (float) (HEIGHT_SCREEN / HEIGHT_BUTTONS));
        SpriteDrawable spriteDrawableTwoButton = new SpriteDrawable(spriteTwoButton);
        twoButton = new ImageButton(spriteDrawableTwoButton);

        Sprite spriteThreeBottom = new Sprite(Assets.buttonThreeMarked);
        spriteThreeBottom.setSize((float) (WIDTH_SCREEN / WIDTH_BUTTONS), (float) (HEIGHT_SCREEN / HEIGHT_BUTTONS));
        SpriteDrawable spriteDrawableThreeButton = new SpriteDrawable(spriteThreeBottom);
        threeButton = new ImageButton(spriteDrawableThreeButton);

        Sprite spriteFourButton = new Sprite(Assets.buttonFourMarked);
        spriteFourButton.setSize((float) (WIDTH_SCREEN / WIDTH_BUTTONS), (float) (HEIGHT_SCREEN / HEIGHT_BUTTONS));
        SpriteDrawable spriteDrawableFourButton = new SpriteDrawable(spriteFourButton);
        fourButton = new ImageButton(spriteDrawableFourButton);

        Sprite spriteFiveButton = new Sprite(Assets.buttonFiveMarked);
        spriteFiveButton.setSize((float) (WIDTH_SCREEN / WIDTH_BUTTONS), (float) (HEIGHT_SCREEN / HEIGHT_BUTTONS));
        SpriteDrawable spriteDrawableFiveButton = new SpriteDrawable(spriteFiveButton);
        fiveButton = new ImageButton(spriteDrawableFiveButton);

        Sprite spriteSixButton = new Sprite(Assets.buttonSix_marked);
        spriteSixButton.setSize((float) (WIDTH_SCREEN / WIDTH_BUTTONS), (float) (HEIGHT_SCREEN / HEIGHT_BUTTONS));
        SpriteDrawable spriteDrawableSixButton = new SpriteDrawable(spriteSixButton);
        sixButton = new ImageButton(spriteDrawableSixButton);


        container.setWidth(stage.getWidth());
        container.align(Align.center | Align.top);
        container.setPosition(0, Gdx.graphics.getHeight());

        container.add(imageTitle).width(WIDTH_SCREEN)
                .height(HEIGHT_SCREEN / CONSTANT_HEIGHT_TITLE).padBottom(CONSTANT_PAD_BOTTOM_AND_TOP);
        container.row();
        firstRowContainer.add(oneButton).padLeft(CONSTANT_PAD_LEFT_AND_RIGHT);
        firstRowContainer.add(twoButton).padLeft(CONSTANT_PAD_LEFT_AND_RIGHT).padRight(CONSTANT_PAD_LEFT_AND_RIGHT);
        firstRowContainer.add(threeButton).padRight(CONSTANT_PAD_LEFT_AND_RIGHT);
        container.add(firstRowContainer).padBottom(CONSTANT_PAD_BOTTOM_AND_TOP);
        container.row();
        secondRowContainer.add(fourButton).padLeft(CONSTANT_PAD_LEFT_AND_RIGHT);

        secondRowContainer.add(fiveButton).padLeft(CONSTANT_PAD_LEFT_AND_RIGHT).padRight(CONSTANT_PAD_LEFT_AND_RIGHT);
        secondRowContainer.add(sixButton).padRight(CONSTANT_PAD_LEFT_AND_RIGHT);
        container.add(secondRowContainer);

        stage.addActor(container);

        Gdx.input.setInputProcessor(stage);

        oneButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                Assets.clickButton.play();
                stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new DifficultyScreen(game));
                    }
                })));
            }
        });

        twoButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                Assets.clickButton.play();
                stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new DifficultyScreen(game));
                    }
                })));
            }
        });

        threeButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                Assets.clickButton.play();
                stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new DifficultyScreen(game));
                    }
                })));
            }
        });

        fourButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                Assets.clickButton.play();
                stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new DifficultyScreen(game));
                    }
                })));
            }
        });

        fiveButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                Assets.clickButton.play();
                stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new DifficultyScreen(game));
                    }
                })));
            }
        });

        sixButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                Assets.clickButton.play();
                stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new DifficultyScreen(game));
                    }
                })));
            }
        });


        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        sprite.draw(batch);
        batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.setScreen(new PlayScreen(game));
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