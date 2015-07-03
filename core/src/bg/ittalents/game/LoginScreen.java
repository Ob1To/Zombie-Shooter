package bg.ittalents.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;


public class LoginScreen implements Screen {
    public static final int WIDTH_SCREEN = Gdx.graphics.getWidth();
    public static final int HEIGHT_SCREEN = Gdx.graphics.getHeight();
    public static final float CONSTANT_PAD_BOTTOM = Gdx.graphics.getHeight() / 12;
    public static final float CONSTANT_PAD_LEFT_AND_RIGHT = Gdx.graphics.getWidth() / 30;
    public static final float CONSTANT_HEIGHT_TITLE = 3;
    public static final int CONSTANCE_HEIGHT_BUTTONS = 5;
    private static final float CONSTANT_WIDTH = 3;
    private static final float CONSTANT_HEIGHT = 10;
    public static final String HTTP_SERVER = "http://192.168.6.99:8080/ShootThemAll";
    public static final int CONSTANT_LENGTH_USERNAME_CHECK = 3;
    public static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,10}";
    public static final float CONSTANT_TABLE_MESSAGE_PAD_TOP = HEIGHT_SCREEN / 3.2f;
    public static final String USER_NAME = "USER NAME";
    public static final String PASSWORD = "PASSWORD";
    private static boolean counterForStartMusicOneTime;
    private Skin skin;
    private Stage stage;
    private Game zombieShooterGame;
    private Table container;
    private Table buttonsContainer;
    private ImageButton loginButton;
    private ImageButton registerButton;
    private TextField loginField;
    private TextField passwordField;
    private SpriteBatch batch;
    private Sprite backgroundSprite;
    private Sprite zombieShooterSpriteTitle;
    private Sprite spriteRegisterButton;
    private Sprite spriteLoginButton;
    private SpriteDrawable zombieShooterSpriteDrawable;
    private SpriteDrawable spriteDrawableRegisterButton;
    private SpriteDrawable spriteDrawableLoginButton;
    private Image imageTitle;
    private Label lblStatus;
    private Label labelMessage;
    private Table tableMessage;
    public static User myUser;

    public LoginScreen(Game game) {
        zombieShooterGame = game;
    }

    @Override
    public void show() {
        myUser = new User();
        if (!counterForStartMusicOneTime) { // KO PRAVESHE TOVA VLADO ... SHTO E TAKA KO PRAVESHE TOVA VLADO ... SHTO E TAKAKO PRAVESHE TOVA VLADO ... SHTO E TAKA
            gameMenuMusic();
            counterForStartMusicOneTime = true;
        }
        spriteDrawableCreator(); // Creating the image title, register and login buttons

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());
        container = new Table();
        buttonsContainer = new Table();


        loginField = new TextField(USER_NAME, skin);
        passwordField = new TextField(PASSWORD, skin);
        loginField.setAlignment(Align.center);
        passwordField.setAlignment(Align.center);


        loginButton = new ImageButton(spriteDrawableRegisterButton);
        registerButton = new ImageButton(spriteDrawableLoginButton);

        clickListenerHandler();

        initializationContainer();

        stage.addActor(container);

        Gdx.input.setInputProcessor(stage); // This is needed to set up the stage so it can receive inputs from our users.

        batch = new SpriteBatch();
        backgroundSprite = new Sprite(Assets.backgroundMenu);
        backgroundSprite.setSize(WIDTH_SCREEN, HEIGHT_SCREEN);
        lblStatus = new Label("", skin); // KAKVO E TOVA VLADO ??? KAKVO E TOVA VLADO ???KAKVO E TOVA VLADO ???KAKVO E TOVA VLADO ???
//        lblStatus.se


        tableMessage = new Table();
        tableMessage.setFillParent(true);
        tableMessage.top();

        labelMessage = new Label("", skin);
        labelMessage.setColor(Color.WHITE);
        labelMessage.setAlignment(Align.center);
        tableMessage.add(labelMessage).expandX().padTop(CONSTANT_TABLE_MESSAGE_PAD_TOP); // KAKVO E TOVA VLADO ? EXPANDX() ? KAKVO E TOVA VLADO ? EXPANDX() ? KAKVO E TOVA VLADO ? EXPANDX() ?
        stage.addActor(tableMessage);
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
    }

    private void spriteDrawableCreator() { // Creating the image title, register and login buttons
        zombieShooterSpriteTitle = new Sprite(Assets.zombieShooterTitle);
        zombieShooterSpriteDrawable = new SpriteDrawable(zombieShooterSpriteTitle);
        imageTitle = new Image(zombieShooterSpriteDrawable);

        spriteRegisterButton = new Sprite(Assets.loginButton);
        spriteRegisterButton.setSize((WIDTH_SCREEN / CONSTANT_WIDTH), (HEIGHT_SCREEN / CONSTANCE_HEIGHT_BUTTONS));
        spriteDrawableRegisterButton = new SpriteDrawable(spriteRegisterButton);

        spriteLoginButton = new Sprite(Assets.registerButton);
        spriteLoginButton.setSize((WIDTH_SCREEN / CONSTANT_WIDTH), (HEIGHT_SCREEN / CONSTANCE_HEIGHT_BUTTONS));
        spriteDrawableLoginButton = new SpriteDrawable(spriteLoginButton);
    }

    private void initializationContainer() { // Arranging the screen and how all the things are sorted in it.
        container.setWidth(stage.getWidth());
        container.align(Align.center | Align.top);
        container.setPosition(0, HEIGHT_SCREEN);

        container.add(imageTitle).width(WIDTH_SCREEN).height((HEIGHT_SCREEN / CONSTANT_HEIGHT_TITLE)).padBottom(CONSTANT_PAD_BOTTOM);
        container.row();
        container.add(loginField).width(WIDTH_SCREEN / CONSTANT_WIDTH).height(HEIGHT_SCREEN / CONSTANT_HEIGHT).padBottom(CONSTANT_PAD_BOTTOM);
        container.row();
        container.add(passwordField).width(WIDTH_SCREEN / CONSTANT_WIDTH).height(HEIGHT_SCREEN / CONSTANT_HEIGHT).padBottom(CONSTANT_PAD_BOTTOM);
        container.row();
        buttonsContainer.add(loginButton).padRight(CONSTANT_PAD_LEFT_AND_RIGHT);
        buttonsContainer.add(registerButton).padLeft(CONSTANT_PAD_LEFT_AND_RIGHT);
        container.add(buttonsContainer);

    }

    private void clickListenerHandler() {
        loginField.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                loginField.setText("");
            }
        });

        passwordField.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                passwordField.setText("");
                passwordField.setPasswordMode(true);
                passwordField.setPasswordCharacter('*');
            }
        });

        registerButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                Assets.clickButton.play();
                stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        zombieShooterGame.setScreen(new RegisterScreen(zombieShooterGame));
                    }
                })));
            }
        });

        loginButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                Assets.clickButton.play();
                if ((loginField.getText().toString().length() > CONSTANT_LENGTH_USERNAME_CHECK)
                        && (passwordField.getText().toString().matches(PASSWORD_PATTERN))) {
                    login();
                } else {
                    labelMessage.setText("Invalid username or password.");
                }
//                zombieShooterGame.setScreen(new PlayScreen(zombieShooterGame));
            }
        });
    }

    private void login() {
        JsonObject json = new JsonObject();
        json.add("username", new JsonPrimitive(loginField.getText()));
        json.add("password", new JsonPrimitive(passwordField.getText()));

        final Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(HTTP_SERVER + "/login");
        httpRequest.setHeader("Content-Type", "application/json");
        httpRequest.setContent(json.toString());
        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(final Net.HttpResponse httpResponse) {
                if (httpResponse.getStatus().getStatusCode() == 200) {
                    Gson gson = new Gson();
                    JsonElement element = gson.fromJson(httpResponse.getResultAsString(), JsonElement.class);
                    JsonObject jsonObj = element.getAsJsonObject();
                    myUser.setUserId(jsonObj.get("userId").getAsInt()); // Initialization of User Id via POST request
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                                @Override
                                public void run() {
                                    loadUserInformation();
                                    weaponsStoreJson();
                                    zombieShooterGame.setScreen(new PlayScreen(zombieShooterGame));
                                }
                            })));
                        }
                    });
                } else {
                    labelMessage.setText("Check username and password or register.");
                }
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

    private void loadUserInformation() {
        final Net.HttpRequest httpGet = new Net.HttpRequest(Net.HttpMethods.GET);
        httpGet.setUrl(HTTP_SERVER + "/userInfoManager?userId=" + LoginScreen.myUser.getUserId());
        Gdx.net.sendHttpRequest(httpGet, new Net.HttpResponseListener() {
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                Gson gson = new Gson();
                JsonElement element = gson.fromJson(httpResponse.getResultAsString(), JsonElement.class);
                JsonObject jsonObj = element.getAsJsonObject();
                LoginScreen.myUser.setWeapon(jsonObj.get("weapon").getAsJsonObject().get("type").getAsInt());
                LoginScreen.myUser.setLevel(jsonObj.get("level").getAsInt());
                LoginScreen.myUser.setScore(jsonObj.get("score").getAsInt());
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

    private void weaponsStoreJson() {
        final Net.HttpRequest httpGet = new Net.HttpRequest(Net.HttpMethods.GET);
        httpGet.setUrl(HTTP_SERVER + "/weaponsStore?userId=" + LoginScreen.myUser.getUserId());
        Gdx.net.sendHttpRequest(httpGet, new Net.HttpResponseListener() {
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                Gson gson = new Gson();
                JsonElement element = gson.fromJson(httpResponse.getResultAsString(), JsonElement.class);
                JsonObject jsonObj = element.getAsJsonObject();

                LoginScreen.myUser.setWeaponOneUnlock(jsonObj.get("unlockedWeapons").getAsJsonArray().get(0).getAsJsonObject().get("type").getAsInt());
                LoginScreen.myUser.setWeaponTwoUnlock(jsonObj.get("unlockedWeapons").getAsJsonArray().get(1).getAsJsonObject().get("type").getAsInt());
                LoginScreen.myUser.setWeaponTreeUnlock(jsonObj.get("unlockedWeapons").getAsJsonArray().get(2).getAsJsonObject().get("type").getAsInt());
                System.out.println(LoginScreen.myUser.toString());
            }

            @Override
            public void failed(Throwable t) {
                if (t.equals(NullPointerException.class)) {
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
//                     ?????
                    }
                });
            }
        });
    }

    public static void stopMenuMusic() {
        Assets.gameMenuMusic.stop();
    }

    private static void gameMenuMusic() {
        Assets.gameMenuMusic.play();
        Assets.gameMenuMusic.setLooping(true);
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
        skin.dispose();
        stage.dispose();
        zombieShooterGame.dispose();
    }

}