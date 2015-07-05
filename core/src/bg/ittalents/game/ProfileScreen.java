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

import bg.ittalents.game.Resource.Assets;
import bg.ittalents.game.Resource.Constant;


public class ProfileScreen implements Screen {
    private static final float CONSTANT_HEIGHT_TITLE = Constant.HEIGHT_SCREEN / 3;
    private static final float CONSTANT_WIDTH_TITLE = Constant.WIDTH_SCREEN / 2;
    private static final float CONSTANT_PAD_BOTTOM_AND_TOP = Gdx.graphics.getHeight() / 25;
    private static final int CONSTANT_HEIGHT_APPLY_BUTTON = Constant.HEIGHT_SCREEN / 6;
    private static final float CONSTANT_WIDTH_APPLY_BUTTON = Constant.WIDTH_SCREEN / 3;
    private static final float CONSTANT_TEXT_WIDTH = Constant.WIDTH_SCREEN / 3;
    private static final float CONSTANT_TEXT_HEIGHT = Constant.HEIGHT_SCREEN / 10;
    private static final float CONSTANT_TABLE_MESSAGE_PAD_TOP = Constant.HEIGHT_SCREEN / 3.45f;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

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
    private Label labelMessage;
    private Table tableMessage;


    protected ProfileScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        sprite = new Sprite(Assets.backgroundMenu);
        sprite.setSize(Constant.WIDTH_SCREEN, Constant.HEIGHT_SCREEN);
        stage = new Stage(new ScreenViewport());

        Sprite spriteTitle = new Sprite(Assets.profileButton);
        SpriteDrawable spriteDrawableTitle = new SpriteDrawable(spriteTitle);
        imageTitle = new Image(spriteDrawableTitle);

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        passwordField = new TextField("NEW PASSWORD", skin);
        passwordCheckField = new TextField("RE-PASSWORD", skin);
        emailField = new TextField("EMAIL", skin);

        passwordField.setAlignment(Align.center);
        passwordCheckField.setAlignment(Align.center);
        emailField.setAlignment(Align.center);
        addListenerField();

        Sprite spriteApplyButton = new Sprite(Assets.applyButton);
        spriteApplyButton.setSize(CONSTANT_WIDTH_APPLY_BUTTON, CONSTANT_HEIGHT_APPLY_BUTTON);
        SpriteDrawable ApplySpriteDrawable = new SpriteDrawable(spriteApplyButton);
        applyButton = new ImageButton(ApplySpriteDrawable);

        stage = new Stage(new ScreenViewport());
        container = new Table();
        initializingContainer();

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

        warningInitializingTable();
        addListenerButton();
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

    private void addListenerButton() {
        applyButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                Assets.clickButton.play();
                if ((cheakPasswordField(passwordField.getText().toString()))
                        && (checkPasswordAndRePassword(passwordField.getText().toString(), passwordCheckField.getText().toString()))
                        && (cheakEmailField(emailField.getText().toString()))) {
                    userInfoManagerJson();
                }
            }
        });
    }

    private void warningInitializingTable() {
        tableMessage = new Table();
        tableMessage.setFillParent(true);
        tableMessage.top();

        labelMessage = new Label("", skin);
        labelMessage.setColor(Color.WHITE);
        labelMessage.setAlignment(Align.center);
        tableMessage.add(labelMessage).expandX().padTop(CONSTANT_TABLE_MESSAGE_PAD_TOP);
        stage.addActor(tableMessage);
    }

    private void initializingContainer() {
        container.setWidth(stage.getWidth());
        container.align(Align.center | Align.top);
        container.setPosition(0, Gdx.graphics.getHeight());

        container.add(imageTitle).width(CONSTANT_WIDTH_TITLE).height(CONSTANT_HEIGHT_TITLE)
                .padBottom(CONSTANT_PAD_BOTTOM_AND_TOP);
        container.row();
        container.add(passwordField).width(CONSTANT_TEXT_WIDTH).height(CONSTANT_TEXT_HEIGHT)
                .padBottom(CONSTANT_PAD_BOTTOM_AND_TOP);
        container.row();
        container.add(passwordCheckField).width(CONSTANT_TEXT_WIDTH).height(CONSTANT_TEXT_HEIGHT)
                .padBottom(CONSTANT_PAD_BOTTOM_AND_TOP);
        container.row();
        container.add(emailField).width(CONSTANT_TEXT_WIDTH).height(CONSTANT_TEXT_HEIGHT)
                .padBottom(CONSTANT_PAD_BOTTOM_AND_TOP);
        container.row();
        container.add(applyButton);

        stage.addActor(container);
    }

    private void addListenerField() {
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

    private boolean cheakPasswordField(String passwordText) {
        if (passwordText.matches(Constant.PASSWORD_PATTERN)) {
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
        json.add("userId", new JsonPrimitive(User.getSingletonUser().getUserId()));
        json.add("password", new JsonPrimitive(passwordField.getText()));
        json.add("email", new JsonPrimitive(emailField.getText()));
        json.add("allowNotification", new JsonPrimitive(true));

        final Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Assets.HTTP_SERVER + "userInfoManager");
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