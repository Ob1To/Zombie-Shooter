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

import bg.ittalents.game.Resource.Assets;
import bg.ittalents.game.Resource.Constant;

public class PlayScreen implements Screen {

    public static final float CONSTANT_PAD_BOTTOM_AND_TOP = Constant.HEIGHT_SCREEN / 20;
    public static final float WIDTH_BUTTONS = (float) (Constant.WIDTH_SCREEN / 3.5);
    public static final float HEIGHT_BUTTONS = Constant.HEIGHT_SCREEN / 6;
    public static final float WIDTH_PLAY_BUTTON = Constant.WIDTH_SCREEN / 2;
    public static final float HEIGHT_PLAY_BUTTON = Constant.HEIGHT_SCREEN / 3;

    private Game game;
    private SpriteBatch batch;
    private Sprite backgroundSprite;
    private Sprite titleSprite;
    private Sprite spritePlayButton;
    private Sprite spriteShopButton;
    private Sprite spriteHighScoreButton;
    private Sprite spriteProfileButton;
    private Image imageTitle;
    private Stage stage;
    private Table container;
    private Table buttonsContainer;
    private ImageButton playButton;
    private ImageButton shopButton;
    private ImageButton highScoreButton;
    private ImageButton profileButton;
    private SpriteDrawable spriteDrawableTitle;
    public static Label labelMessage;
    private Table tableMessage;
    private Skin skin;

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
        Assets.spriteDefaultColorSolid(titleSprite);
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

        tableMessage = new Table();
        tableMessage.setFillParent(true);
        tableMessage.top();

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        labelMessage = new Label("", skin);
        labelMessage.setColor(Color.WHITE);
        labelMessage.setAlignment(Align.center);
        tableMessage.add(labelMessage).expandX().padTop(Constant.CONSTANT_TABLE_MESSAGE_PAD_TOP);
        stage.addActor(tableMessage);
        if (!LoginScreen.offlineModeSelect) {
            loadUserInformation();
            weaponsStoreJson();
        }

    }

    private void creatingAllTheButtons() {
        spritePlayButton = new Sprite(Assets.playButton);
        spritePlayButton.setSize(WIDTH_PLAY_BUTTON, HEIGHT_PLAY_BUTTON);
        SpriteDrawable spriteDrawablePlayButton = new SpriteDrawable(spritePlayButton);
        playButton = new ImageButton(spriteDrawablePlayButton);

        spriteShopButton = new Sprite(Assets.shopButton);
        spriteShopButton.setSize(WIDTH_BUTTONS, HEIGHT_BUTTONS);
        SpriteDrawable spriteDrawableShopButton = new SpriteDrawable(spriteShopButton);
        shopButton = new ImageButton(spriteDrawableShopButton);

        spriteHighScoreButton = new Sprite(Assets.highScoreButton);
        spriteHighScoreButton.setSize(WIDTH_BUTTONS, HEIGHT_BUTTONS);
        SpriteDrawable spriteDrawableHighScoreButton = new SpriteDrawable(spriteHighScoreButton);
        highScoreButton = new ImageButton(spriteDrawableHighScoreButton);

        spriteProfileButton = new Sprite(Assets.profileButton);
        spriteProfileButton.setSize(WIDTH_BUTTONS, HEIGHT_BUTTONS);
        SpriteDrawable spriteDrawableProfileButton = new SpriteDrawable(spriteProfileButton);
        profileButton = new ImageButton(spriteDrawableProfileButton);

        Assets.spriteDefaultColor(spritePlayButton, spriteShopButton, spriteHighScoreButton, spriteProfileButton); // Setting the transparency of the buttons
    }

    private void addingListenersToAllButtons() {
        playButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                spritePlayButton.setColor(1, 0.271f, 0, 0.8f);
                return true;
            }
        });
        playButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Assets.clickButton.play();
                spritePlayButton.setColor(0.545f, 0, 0, 0.7f);
                stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new LevelMapScreen(game));
                    }
                })));
            }
        });

        shopButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                spriteShopButton.setColor(1, 0.271f, 0, 0.8f);
                return true;
            }
        });
        shopButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Assets.clickButton.play();
                spriteShopButton.setColor(0.545f, 0, 0, 0.7f);
                stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new ShopScreen(game));
                    }
                })));
            }
        });
        highScoreButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                spriteHighScoreButton.setColor(1, 0.271f, 0, 0.8f);
                return true;
            }
        });
        highScoreButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Assets.clickButton.play();
                spriteHighScoreButton.setColor(0.545f, 0, 0, 0.7f);
                if (!LoginScreen.offlineModeSelect) {
                    stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            game.setScreen(new HighScoreScreen(game));
                        }
                    })));
                }
            }
        });
        profileButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                spriteProfileButton.setColor(1, 0.271f, 0, 0.8f);
                return true;
            }
        });
        profileButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Assets.clickButton.play();
                spriteProfileButton.setColor(0.545f, 0, 0, 0.7f);
                if (!LoginScreen.offlineModeSelect) {
                    stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            game.setScreen(new ProfileScreen(game));
                        }
                    })));
                }
            }
        });
    }

    private void addingButtonsToContainer() {
        container.add(imageTitle).width(Constant.WIDTH_SCREEN)
                .height(Constant.CONSTANT_HEIGHT_TITLE).padBottom(CONSTANT_PAD_BOTTOM_AND_TOP);
        container.row();
        container.add(playButton).padBottom(CONSTANT_PAD_BOTTOM_AND_TOP);
        container.row();
        buttonsContainer.add(shopButton).padRight(Constant.CONSTANT_PAD_LEFT_AND_RIGHT);
        buttonsContainer.add(highScoreButton).padLeft(Constant.CONSTANT_PAD_LEFT_AND_RIGHT).padRight(Constant.CONSTANT_PAD_LEFT_AND_RIGHT);
        buttonsContainer.add(profileButton).padLeft(Constant.CONSTANT_PAD_LEFT_AND_RIGHT);
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
        skin.dispose();
        game.dispose();
    }

    private void loadUserInformation() {
        final Net.HttpRequest httpGet = new Net.HttpRequest(Net.HttpMethods.GET);
        httpGet.setUrl(Assets.HTTP_SERVER + "userInfoManager?userId=" + User.getSingletonUser().getUserId());
        Gdx.net.sendHttpRequest(httpGet, new Net.HttpResponseListener() {
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                Gson gson = new Gson();
                JsonElement element = gson.fromJson(httpResponse.getResultAsString(), JsonElement.class);
                JsonObject jsonObj = element.getAsJsonObject();
                User.getSingletonUser().setWeapon(jsonObj.get("weapon").getAsJsonObject().get("type").getAsInt());
                User.getSingletonUser().setLevel(jsonObj.get("level").getAsInt());
                User.getSingletonUser().setScore(jsonObj.get("score").getAsInt());
            }

            @Override
            public void failed(Throwable t) {
                System.out.println(t + "purvata");
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

    private void weaponsStoreJson() {
        final Net.HttpRequest httpGet = new Net.HttpRequest(Net.HttpMethods.GET);
        httpGet.setUrl(Assets.HTTP_SERVER + "weaponsStore?userId=" + User.getSingletonUser().getUserId());
        Gdx.net.sendHttpRequest(httpGet, new Net.HttpResponseListener() {
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                Gson gson = new Gson();
                JsonElement element = gson.fromJson(httpResponse.getResultAsString(), JsonElement.class);
                JsonObject jsonObj = element.getAsJsonObject();
                User.getSingletonUser().setWeaponOneUnlock(jsonObj.get("unlockedWeapons").getAsJsonArray().get(0).getAsJsonObject().get("type").getAsInt());
                User.getSingletonUser().setWeaponTwoUnlock(jsonObj.get("unlockedWeapons").getAsJsonArray().get(1).getAsJsonObject().get("type").getAsInt());
                User.getSingletonUser().setWeaponTreeUnlock(jsonObj.get("unlockedWeapons").getAsJsonArray().get(2).getAsJsonObject().get("type").getAsInt());
            }

            @Override
            public void failed(Throwable t) {
                if (t instanceof IndexOutOfBoundsException) {
                    System.out.print("This is ok!");
                } else {
                    labelMessage.setText("Please check your Internet connection.");
                }
            }

            @Override
            public void cancelled() {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
    }

}
