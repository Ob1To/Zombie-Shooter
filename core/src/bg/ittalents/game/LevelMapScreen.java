package bg.ittalents.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class LevelMapScreen implements Screen {

    public static final int WIDTH_SCREEN = Gdx.graphics.getWidth();
    public static final int HEIGHT_SCREEN = Gdx.graphics.getHeight();
    public static final float CONSTANT_PAD_BOTTOM_AND_TOP = Gdx.graphics.getHeight() / 17;
    public static final float CONSTANT_PAD_LEFT_AND_RIGHT = Gdx.graphics.getWidth() / 15;
    public static final float CONSTANT_HEIGHT_TITLE = 3;
    public static final double WIDTH_BUTTONS = 3.5;
    public static final int HEIGHT_BUTTONS = 4;
    public static final float CONSTANT_TABLE_MESSAGE_PAD_TOP = HEIGHT_SCREEN / 3.2f;

    private Game game;
    private SpriteBatch batch;
    private Sprite backgroundSprite;
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

    private Sprite spriteTwoButton;
    private Sprite spriteThreeButton;
    private Sprite spriteFourButton;
    private Sprite spriteFiveButton;
    private Sprite spriteSixButton;
    private Sprite spriteButtonOne;
    private Label labelMessage;
    private Table tableMessage;
    private Skin skin;


    public LevelMapScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {


        batch = new SpriteBatch();
        backgroundSprite = new Sprite(Assets.backgroundMenu);
        backgroundSprite.setSize(WIDTH_SCREEN, HEIGHT_SCREEN);
        stage = new Stage(new ScreenViewport());

        Sprite spriteTitle = new Sprite(Assets.paragonLevelImage);
        Assets.spriteDefaultColorSolid(spriteTitle);
        SpriteDrawable spriteDrawableTitle = new SpriteDrawable(spriteTitle);
        imageTitle = new Image(spriteDrawableTitle);
        stage = new Stage(new ScreenViewport());
        container = new Table();
        firstRowContainer = new Table();
        secondRowContainer = new Table();

        initializeButton();

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

        addListenerForButton();

        Assets.spriteDefaultColor(spriteButtonOne, spriteTwoButton, spriteThreeButton, spriteFourButton, spriteFiveButton, spriteSixButton);

        Gdx.input.setCatchBackKey(true);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        tableMessage = new Table();
        tableMessage.setFillParent(true);
        tableMessage.top();

        labelMessage = new Label("", skin);
        labelMessage.setColor(Color.WHITE);
        labelMessage.setAlignment(Align.center);
        tableMessage.add(labelMessage).expandX().padTop(CONSTANT_TABLE_MESSAGE_PAD_TOP); // KAKVO E TOVA VLADO ? EXPANDX() ? KAKVO E TOVA VLADO ? EXPANDX() ? KAKVO E TOVA VLADO ? EXPANDX() ?
        stage.addActor(tableMessage);
    }

    private void addListenerForButton() {
        oneButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                Assets.clickButton.play();
                User.getSingletonUser().setGameLevel(1);
                changeTheScreenMethod(1);
            }
        });

        twoButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (User.getSingletonUser().getLevel() >= 2) {
                    Assets.clickButton.play();
                    User.getSingletonUser().setGameLevel(2);
                    changeTheScreenMethod(2);
                }
            }
        });

        threeButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (User.getSingletonUser().getLevel() >= 3) {
                    Assets.clickButton.play();
                    User.getSingletonUser().setGameLevel(3);
                        changeTheScreenMethod(3);
                }
            }
        });

        fourButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (User.getSingletonUser().getLevel() >= 4) {
                    Assets.clickButton.play();
                    User.getSingletonUser().setGameLevel(4);
                    changeTheScreenMethod(4);
                }
            }
        });

        fiveButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (User.getSingletonUser().getLevel() >= 5) {
                    Assets.clickButton.play();
                    User.getSingletonUser().setGameLevel(5);
                    changeTheScreenMethod(5);
                }
            }
        });

            sixButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (User.getSingletonUser().getLevel() >= 6) {
                    Assets.clickButton.play();
                    User.getSingletonUser().setGameLevel(6);
                    changeTheScreenMethod(6);
                }
            }
        });
    }

        private void initializeButton () {

            spriteButtonOne = new Sprite(Assets.buttonOne);
            spriteButtonOne.setSize((float) (WIDTH_SCREEN / WIDTH_BUTTONS), (float) (HEIGHT_SCREEN / HEIGHT_BUTTONS));
            SpriteDrawable spriteDrawableBuyItButton = new SpriteDrawable(spriteButtonOne);
            oneButton = new ImageButton(spriteDrawableBuyItButton);

            int checkForLevel = User.getSingletonUser().getLevel();

            if (checkForLevel >= 2) {
                spriteTwoButton = new Sprite(Assets.buttonTwo);
                Assets.spriteDefaultColor(spriteTwoButton);
            } else {
                spriteTwoButton = new Sprite(Assets.buttonTwo_marked);
                Assets.spriteDefaultColor(spriteTwoButton);
            }
            spriteTwoButton.setSize((float) (WIDTH_SCREEN / WIDTH_BUTTONS), (float) (HEIGHT_SCREEN / HEIGHT_BUTTONS));
            SpriteDrawable spriteDrawableTwoButton = new SpriteDrawable(spriteTwoButton);
            twoButton = new ImageButton(spriteDrawableTwoButton);


            if (checkForLevel >= 3) {
                spriteThreeButton = new Sprite(Assets.buttonThree);
                Assets.spriteDefaultColor(spriteThreeButton);
            } else {
                spriteThreeButton = new Sprite(Assets.buttonThreeMarked);
                Assets.spriteDefaultColor(spriteThreeButton);
            }
            spriteThreeButton.setSize((float) (WIDTH_SCREEN / WIDTH_BUTTONS), (float) (HEIGHT_SCREEN / HEIGHT_BUTTONS));
            SpriteDrawable spriteDrawableThreeButton = new SpriteDrawable(spriteThreeButton);
            threeButton = new ImageButton(spriteDrawableThreeButton);

            if (checkForLevel >= 4) {
                spriteFourButton = new Sprite(Assets.buttonFour);
                Assets.spriteDefaultColor(spriteFourButton);
            } else {
                spriteFourButton = new Sprite(Assets.buttonFourMarked);
                Assets.spriteDefaultColor(spriteFourButton);
            }
            spriteFourButton.setSize((float) (WIDTH_SCREEN / WIDTH_BUTTONS), (float) (HEIGHT_SCREEN / HEIGHT_BUTTONS));
            SpriteDrawable spriteDrawableFourButton = new SpriteDrawable(spriteFourButton);
            fourButton = new ImageButton(spriteDrawableFourButton);


            if (checkForLevel >= 5) {
                spriteFiveButton = new Sprite(Assets.buttonFive);
                Assets.spriteDefaultColor(spriteFiveButton);
            } else {
                spriteFiveButton = new Sprite(Assets.buttonFiveMarked);
                Assets.spriteDefaultColor(spriteFiveButton);
            }
            spriteFiveButton.setSize((float) (WIDTH_SCREEN / WIDTH_BUTTONS), (float) (HEIGHT_SCREEN / HEIGHT_BUTTONS));
            SpriteDrawable spriteDrawableFiveButton = new SpriteDrawable(spriteFiveButton);
            fiveButton = new ImageButton(spriteDrawableFiveButton);

            if (checkForLevel >= 6) {
                spriteSixButton = new Sprite(Assets.buttonSix);
                Assets.spriteDefaultColor(spriteSixButton);
            } else {
                spriteSixButton = new Sprite(Assets.buttonSix_marked);
                Assets.spriteDefaultColor(spriteSixButton);
            }
            spriteSixButton.setSize((float) (WIDTH_SCREEN / WIDTH_BUTTONS), (float) (HEIGHT_SCREEN / HEIGHT_BUTTONS));
            SpriteDrawable spriteDrawableSixButton = new SpriteDrawable(spriteSixButton);
            sixButton = new ImageButton(spriteDrawableSixButton);
        }

        @Override
        public void render ( float delta){
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            backgroundSprite.draw(batch);
            batch.draw(backgroundSprite, backgroundSprite.getX(), backgroundSprite.getY(), backgroundSprite.getWidth(), backgroundSprite.getHeight());
            batch.end();
            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
            if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
                game.setScreen(new PlayScreen(game));
            }

        }

        private void levelInfoJson () {

            final Net.HttpRequest httpGet = new Net.HttpRequest(Net.HttpMethods.GET);
            httpGet.setUrl(Assets.HTTP_SERVER + "levelManager?userId=" + User.getSingletonUser().getUserId() + "&level=" + User.getSingletonUser().getGameLevel());
            Gdx.net.sendHttpRequest(httpGet, new Net.HttpResponseListener() {
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    Gson gson = new Gson();
                    JsonElement element = gson.fromJson(httpResponse.getResultAsString(), JsonElement.class);
                    JsonObject jsonObj = element.getAsJsonObject();

                    User.getSingletonUser().setUserHealth(jsonObj.get("userHealth").getAsInt());
                    User.getSingletonUser().setGameAppearingZombieAll(jsonObj.get("count").getAsInt());
                    User.getSingletonUser().setGameAppearingZombieTime(jsonObj.get("durationOn").getAsFloat());
                    User.getSingletonUser().setGameHidingZombie(jsonObj.get("durationOff").getAsFloat());
                    User.getSingletonUser().setGameBulletsForLevel(jsonObj.get("bullets").getAsInt() * User.getSingletonUser().getWeapon());
                    stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            game.setScreen(new DifficultyScreen(game));
                        }
                    })));


                }

                @Override
                public void failed(Throwable t) {
                    labelMessage.setText("Please check your Internet connection.");
                }

                @Override
                public void cancelled() {
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
//                     ?????
                        }
                    });
                }
            });
        }

        @Override
        public void resize ( int width, int height){

        }

        @Override
        public void pause () {

        }

        @Override
        public void resume () {

        }

        @Override
        public void hide () {

        }

        @Override
        public void dispose () {
            game.dispose();
            stage.dispose();
            batch.dispose();
        }

    private void changeTheScreenMethod(final int z){
        if (!LoginScreen.offlineModeSelect) {
            levelInfoJson();
        } else {
            stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                @Override
                public void run() {
                    ResourcesForOffline.levelMapResources(z);
                    game.setScreen(new DifficultyScreen(game));
                }
            })));
        }
    }
    }