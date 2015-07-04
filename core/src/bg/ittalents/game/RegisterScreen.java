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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;


public class RegisterScreen extends LoginScreen implements Screen {

    public static final int WIDTH_SCREEN = Gdx.graphics.getWidth();
    public static final int HEIGHT_SCREEN = Gdx.graphics.getHeight();
    private static final float CONSTANT_WIDTH = 3;
    private static final float CONSTANT_HEIGHT = 10;
    public static final float CONSTANT_PAD_BOTTOM = Gdx.graphics.getHeight() / 12;
    public static final float CONSTANT_PAD_LEFT_AND_RIGHT = Gdx.graphics.getWidth() / 30;
    public static final float CONSTANT_HEIGHT_TITLE = 3;
    public static final int CONSTANT_HEIGHT_REGISTER_BUTTON = 5;
    public static final String USER_PATTERN = "(?=.*[a-z]).{3,10}";
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,10}";
    public static final float CONSTANT_TABLE_MESSAGE_PAD_TOP = HEIGHT_SCREEN / 3.2f;
    public static final String USER_NAME = "USER NAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String RE_PASSWORD = "RE-PASSWORD";
    public static final String EMAIL = "EMAIL";

    private Game game;
    private Skin skin;
    private Stage stage;
    private SpriteBatch batch;
    private Sprite backGroundSprite;
    private Table container;
    private Table containerFirstRow;
    private Table containerSecondRow;
    private TextField userField;
    private TextField passwordField;
    private TextField passwordCheckField;
    private TextField emailField;
    private ImageButton registerButton;
    private Image imageTitle;
    private Label labelMessage;
    private Table tableMessage;

    public RegisterScreen(Game game) {
        super(game);
        this.game = game;
    }


    @Override
    public void show() {

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();

        Gdx.input.setInputProcessor(stage);

        // Adding the game title to the screen
        Sprite spriteTitle = new Sprite(Assets.zombieShooterTitle);
        Assets.spriteDefaultColorSolid(spriteTitle);
        SpriteDrawable spriteDrawableTitle = new SpriteDrawable(spriteTitle);
        imageTitle = new Image(spriteDrawableTitle);

        // Creating all the containers for the positioning and arranging of our stuff
        container = new Table();
        container.setWidth(stage.getWidth());
        container.align(Align.center | Align.top);
        container.setPosition(0, HEIGHT_SCREEN);

        containerFirstRow = new Table();
        containerFirstRow.setWidth(stage.getWidth());
        containerFirstRow.align(Align.center | Align.top);
        containerFirstRow.setPosition(0, HEIGHT_SCREEN);

        containerSecondRow = new Table();
        containerSecondRow.setWidth(stage.getWidth());
        containerSecondRow.align(Align.center | Align.top);
        containerSecondRow.setPosition(0, HEIGHT_SCREEN);

        creatingRegisterButton();

        creatingTheTextFields();

        // Setting the text field text to be on the center
        settingTextFieldsTextToCenter();

        // Remove any text in hte text fields on click so that user can write his information
        cleaningTextFieldsToBlank();


        //Adding all the object into the stage. First of all we add to the container
        // for the row then to the main container , finally into the main stage
        arrangingTheScreen();

        // Creating the background
        backGroundSprite = new Sprite(Assets.backgroundMenu);
        backGroundSprite.setSize(WIDTH_SCREEN, HEIGHT_SCREEN);

        // Setting the back key on android to true, so it can accept interaction
        Gdx.input.setCatchBackKey(true);

        tableMessage = new Table();
        tableMessage.setFillParent(true);
        tableMessage.top();

        labelMessage = new Label("", skin);
        labelMessage.setColor(Color.WHITE);
        labelMessage.setAlignment(Align.center);
        tableMessage.add(labelMessage).expandX().padTop(CONSTANT_TABLE_MESSAGE_PAD_TOP);
        stage.addActor(tableMessage);

        // When clicking on the register button we start the validation process.
        // It calls all the methods which are checking the user input
        registerButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                Assets.clickButton.play();
                if ((checkUserField(userField.getText().toString())) && (checkPasswordField(passwordField.getText().toString()))
                        && (checkPasswordAndRePassword(passwordField.getText().toString(), passwordCheckField.getText().toString()))
                        && (checkEmailField(emailField.getText().toString()))) {
                    registerJson();
                }
            }
        });
    }

    private void creatingRegisterButton() {
        Sprite spriteRegisterButton = new Sprite(Assets.registerButton);
        Assets.spriteDefaultColor(spriteRegisterButton);
        spriteRegisterButton.setSize((WIDTH_SCREEN / CONSTANT_WIDTH), (HEIGHT_SCREEN / CONSTANT_HEIGHT_REGISTER_BUTTON));
        SpriteDrawable registerSpriteDrawable = new SpriteDrawable(spriteRegisterButton);
        registerButton = new ImageButton(registerSpriteDrawable);
    }

    private void creatingTheTextFields() {
        userField = new TextField(USER_NAME, skin);
        userField.setColor(1,0,0,0.5f);
        passwordField = new TextField(PASSWORD, skin);
        passwordField.setColor(1,0,0,0.5f);
        passwordCheckField = new TextField(RE_PASSWORD, skin);
        passwordCheckField.setColor(1,0,0,0.5f);
        emailField = new TextField(EMAIL, skin);
        emailField.setColor(1,0,0,0.5f);
    }

    private void settingTextFieldsTextToCenter() {
        userField.setAlignment(Align.center);
        passwordField.setAlignment(Align.center);
        passwordCheckField.setAlignment(Align.center);
        emailField.setAlignment(Align.center);
    }

    private void cleaningTextFieldsToBlank() {
        userField.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                userField.setText("");
            }
        });

        passwordField.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                passwordField.setText("");
                passwordField.setPasswordMode(true);
                passwordField.setPasswordCharacter('*');
            }
        });

        passwordCheckField.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                passwordCheckField.setText("");
                passwordCheckField.setPasswordMode(true);
                passwordCheckField.setPasswordCharacter('*');
            }
        });

        emailField.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                emailField.setText("");
            }
        });
    }

    private void registerJson() {
        JsonObject json = new JsonObject();
        json.add("username", new JsonPrimitive(userField.getText()));
        json.add("password", new JsonPrimitive(passwordField.getText()));
        json.add("email", new JsonPrimitive(emailField.getText()));

        final Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Assets.HTTP_SERVER + "registration");
        httpRequest.setHeader("Content-Type", "application/json");
        httpRequest.setContent(json.toString());
        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(final Net.HttpResponse httpResponse) {
                System.out.print(httpResponse.getResultAsString());
                if (httpResponse.getStatus().getStatusCode() == 200) {
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                                @Override
                                public void run() {
                                    login(userField.getText().toString(),passwordField.getText().toString(),false);
                                    game.setScreen(new PlayScreen(game));
                                }
                            })));
                        }
                    });
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

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        backGroundSprite.draw(batch);
        batch.draw(backGroundSprite, backGroundSprite.getX(), backGroundSprite.getY(), backGroundSprite.getWidth(), backGroundSprite.getHeight());
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.setScreen(new LoginScreen(game));
        }
    }

    private void arrangingTheScreen() {
        container.add(imageTitle).width(WIDTH_SCREEN)
                .height((HEIGHT_SCREEN / CONSTANT_HEIGHT_TITLE)).padBottom(CONSTANT_PAD_BOTTOM);
        container.row();
        containerFirstRow.add(userField).width(WIDTH_SCREEN / CONSTANT_WIDTH)
                .height(HEIGHT_SCREEN / CONSTANT_HEIGHT).padBottom(CONSTANT_PAD_BOTTOM)
                .padRight(CONSTANT_PAD_LEFT_AND_RIGHT);
        containerFirstRow.add(passwordField).width(WIDTH_SCREEN / CONSTANT_WIDTH)
                .height(HEIGHT_SCREEN / CONSTANT_HEIGHT).padBottom(CONSTANT_PAD_BOTTOM)
                .padLeft(CONSTANT_PAD_LEFT_AND_RIGHT);
        container.add(containerFirstRow);
        container.row();
        containerSecondRow.add(emailField).width(WIDTH_SCREEN / CONSTANT_WIDTH)
                .height(HEIGHT_SCREEN / CONSTANT_HEIGHT).padBottom(CONSTANT_PAD_BOTTOM)
                .padRight(CONSTANT_PAD_LEFT_AND_RIGHT);
        containerSecondRow.add(passwordCheckField).width(WIDTH_SCREEN / CONSTANT_WIDTH)
                .height(HEIGHT_SCREEN / CONSTANT_HEIGHT).padBottom(CONSTANT_PAD_BOTTOM)
                .padLeft(CONSTANT_PAD_LEFT_AND_RIGHT);
        container.add(containerSecondRow);
        container.row();
        container.add(registerButton);

        stage.addActor(container);
    }

    private boolean checkUserField(String logField) {
        if (logField.matches(USER_PATTERN)) {
            return true;
        }
        labelMessage.setText("Username must be at least 3 characters long and one letter.");
        return false;
    }

    private boolean checkPasswordField(String passwordText) {
        if (passwordText.matches(PASSWORD_PATTERN)) {
            return true;
        } else {
            labelMessage.setText("Password must contain a capital letter, \n one lowercase letter, one number and be 5-10 characters");
            return false;
        }
    }

    private boolean checkPasswordAndRePassword(String password, String rePassword) {
        if (password.equals(rePassword)) {
            return true;
        } else {
            labelMessage.setText("Password and Re-Password must be the same");
            return false;
        }
    }

    private boolean checkEmailField(String emailField) {
        if (emailField.matches(EMAIL_PATTERN)) {
            return true;
        } else {
            labelMessage.setText("Invalid email");
            return false;
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
        skin.dispose();
        stage.dispose();
        batch.dispose();
    }

}