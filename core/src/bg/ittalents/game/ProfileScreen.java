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

public class ProfileScreen implements Screen {

    public static final int WIDTH_SCREEN = Gdx.graphics.getWidth();
    public static final int HEIGHT_SCREEN = Gdx.graphics.getHeight();
    public static final float CONSTANT_HEIGHT_TITLE = 3;
    public static final float CONSTANT_WIDTH_TITLE = 2;
    public static final float CONSTANT_PAD_BOTTOM_AND_TOP = Gdx.graphics.getHeight() / 25;
    public static final int CONSTANT_HEIGHT_APPLY_BUTTON = 6;
    private static final float CONSTANT_WIDTH_APPLY_BUTTON = 3;
    private static final float CONSTANT_TEXT_WIDTH = 3;
    private static final float CONSTANT_TEXT_HEIGHT = 10;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,10}";
    public static final float CONSTANT_TABLE_MESSAGE_PAD_TOP = HEIGHT_SCREEN / 3.45f;

    //Trite promenlivi da se prehvurlqt
    private Label labelMessage;
    private Table tableMessage;


    private Stage stage;
    private Game game;
    private Table container;
    private SpriteBatch batch;
    private Sprite sprite;
    private Image imageTitle;
    private TextField passwordField;
    private TextField passwordCheckField;
    private TextField emailField;
    private Skin skin;
    private ImageButton applyButton;


    public ProfileScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        //Suzdavane na backgraunda
        batch = new SpriteBatch();
        sprite = new Sprite(bg.ittalents.game.Resource.Assets.backgroundMenu);
        sprite.setSize(WIDTH_SCREEN, HEIGHT_SCREEN);
        stage = new Stage(new ScreenViewport());

        Sprite spriteTitle = new Sprite(bg.ittalents.game.Resource.Assets.profileButton);
        SpriteDrawable spriteDrawableTitle = new SpriteDrawable(spriteTitle);
        imageTitle = new Image(spriteDrawableTitle);

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        passwordField = new TextField("NEW PASSWORD", skin);
        passwordCheckField = new TextField("RE-PASSWORD", skin);
        emailField = new TextField("EMAIL", skin);

        passwordField.setAlignment(Align.center);
        passwordCheckField.setAlignment(Align.center);
        emailField.setAlignment(Align.center);

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


        Sprite spriteApplyButton = new Sprite(bg.ittalents.game.Resource.Assets.applyButton);
        spriteApplyButton.setSize((WIDTH_SCREEN / CONSTANT_WIDTH_APPLY_BUTTON), (HEIGHT_SCREEN / CONSTANT_HEIGHT_APPLY_BUTTON));
        SpriteDrawable ApplySpriteDrawable = new SpriteDrawable(spriteApplyButton);
        applyButton = new ImageButton(ApplySpriteDrawable);


        stage = new Stage(new ScreenViewport());
        container = new Table();

        container.setWidth(stage.getWidth());
        container.align(Align.center | Align.top);
        container.setPosition(0, Gdx.graphics.getHeight());

        container.add(imageTitle).width(WIDTH_SCREEN / CONSTANT_WIDTH_TITLE)
                .height((HEIGHT_SCREEN / CONSTANT_HEIGHT_TITLE)).padBottom(CONSTANT_PAD_BOTTOM_AND_TOP);
        container.row();
        container.add(passwordField).width(WIDTH_SCREEN / CONSTANT_TEXT_WIDTH)
                .height(HEIGHT_SCREEN / CONSTANT_TEXT_HEIGHT).padBottom(CONSTANT_PAD_BOTTOM_AND_TOP);
        container.row();
        container.add(passwordCheckField).width(WIDTH_SCREEN / CONSTANT_TEXT_WIDTH)
                .height(HEIGHT_SCREEN / CONSTANT_TEXT_HEIGHT).padBottom(CONSTANT_PAD_BOTTOM_AND_TOP);
        container.row();
        container.add(emailField).width(WIDTH_SCREEN / CONSTANT_TEXT_WIDTH)
                .height(HEIGHT_SCREEN / CONSTANT_TEXT_HEIGHT).padBottom(CONSTANT_PAD_BOTTOM_AND_TOP);
        container.row();
        container.add(applyButton);

        stage.addActor(container);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);


        //        TOVA DA SE PREMESTI V DRUGIQ KOD
        tableMessage = new Table();
        tableMessage.setFillParent(true);
        tableMessage.top();

        labelMessage = new Label("", skin);
        labelMessage.setColor(Color.WHITE);
        labelMessage.setAlignment(Align.center);
        tableMessage.add(labelMessage).expandX().padTop(CONSTANT_TABLE_MESSAGE_PAD_TOP);
        stage.addActor(tableMessage);


        applyButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                bg.ittalents.game.Resource.Assets.clickButton.play();
                if ((cheakPasswordField(passwordField.getText().toString()))
                        && (checkPasswordAndRePassword(passwordField.getText().toString(), passwordCheckField.getText().toString()))
                        && (cheakEmailField(emailField.getText().toString()))) {
                    userInfoManagerJson();
                }
            }
        });
    }

    private boolean cheakPasswordField(String passwordText) {
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

    private boolean cheakEmailField(String emailField) {
        if (emailField.matches(EMAIL_PATTERN)) {
            return true;
        } else {
            labelMessage.setText("Invalid email");
            return false;
        }
    }


    private void userInfoManagerJson() {
        JsonObject json = new JsonObject();
//        Tuk sled kato napravim klasa User da vzimame ve4e ID koeto imame za konkretniq potrebitel
        json.add("userId", new JsonPrimitive(204));
        json.add("password", new JsonPrimitive(passwordField.getText()));
        json.add("email", new JsonPrimitive(emailField.getText()));
        json.add("allowNotification", new JsonPrimitive(true));

        final Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(bg.ittalents.game.Resource.Assets.HTTP_SERVER + "userInfoManager");
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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    DO TUK DA SE DOBAVI

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
        stage.dispose();
        game.dispose();
        skin.dispose();
        batch.dispose();
    }
}