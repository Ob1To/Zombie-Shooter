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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;


public class HighScoreScreen implements Screen {
    public static final int WIDTH_SCREEN = Gdx.graphics.getWidth();
    public static final int HEIGHT_SCREEN = Gdx.graphics.getHeight();
    public static final float CONSTANT_TABLE_MESSAGE_PAD_TOP = HEIGHT_SCREEN / 3.2f;
    public static final float CONSTANT_HEIGHT_TITLE = 3;
    public static final float CONSTANT_WIDTH_TITLE = 2;

    private Stage stage;
    private Game game;
    private SpriteBatch batch;
    private Sprite sprite;
    private Image imageTitle;
    private Label labelMessage;
    private Table tableMessage;
    private Skin skin;
    private Table scrollTable;

    public HighScoreScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        //Suzdavane na backgraunda
        batch = new SpriteBatch();
        sprite = new Sprite(Assets.backgroundMenu);
        sprite.setSize(WIDTH_SCREEN, HEIGHT_SCREEN);
        stage = new Stage(new ScreenViewport());

        Sprite spriteTitle = new Sprite(Assets.highScoreImage);
        SpriteDrawable spriteDrawableTitle = new SpriteDrawable(spriteTitle);
        imageTitle = new Image(spriteDrawableTitle);

        stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        tableMessage = new Table();
        tableMessage.setFillParent(true);
        tableMessage.top();

        labelMessage = new Label("", skin);
        labelMessage.setColor(Color.WHITE);
        labelMessage.setAlignment(Align.center);
        tableMessage.add(labelMessage).expandX().padTop(CONSTANT_TABLE_MESSAGE_PAD_TOP);
        stage.addActor(tableMessage);


        scrollTable = new Table();
        scrollTable.setWidth(stage.getWidth());
        scrollTable.align(Align.left | Align.top);
        scrollTable.setPosition(0, Gdx.graphics.getHeight());

        scrollTable.add(imageTitle).width(WIDTH_SCREEN / CONSTANT_WIDTH_TITLE)
                .height((HEIGHT_SCREEN / CONSTANT_HEIGHT_TITLE));
        scrollTable.row();

        highScoreJson();

        ScrollPane scroller = new ScrollPane(scrollTable);

        Table table = new Table();
        table.setFillParent(true);
        table.add(scroller).fill().expand();

        this.stage.addActor(table);

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

    private void highScoreJson() {
        final Net.HttpRequest httpGet = new Net.HttpRequest(Net.HttpMethods.GET);
        //        Tuk sled kato napravim klasa User da vzimame ve4e ID koeto imame za konkretniq potrebitel
        httpGet.setUrl(Assets.HTTP_SERVER + "leaderBoard?userId=" + User.singletonUser().getUserId());
        Gdx.net.sendHttpRequest(httpGet, new Net.HttpResponseListener() {
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                Gson gson = new Gson();
                JsonElement element = gson.fromJson(httpResponse.getResultAsString(), JsonElement.class);
                JsonArray jsonObj = element.getAsJsonArray();
                for (int x = 0; x < jsonObj.size(); x++) {
                    addUserInTable(jsonObj.get(x).getAsJsonObject().get("position").getAsString(),
                            jsonObj.get(x).getAsJsonObject().get("username").getAsString(),
                            jsonObj.get(x).getAsJsonObject().get("score").getAsString());
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
        game.dispose();
        stage.dispose();
        batch.dispose();
        skin.dispose();
    }


    private void addUserInTable(String position, String name, String score) {
        Label rowLabel = new Label("", skin);
        rowLabel.setColor(Color.WHITE);
        rowLabel.setAlignment(Align.left);
        rowLabel.setText(position + "  " + name + " - " + score);
        rowLabel.setFontScale(5, 2);
        scrollTable.add(rowLabel).left();
        scrollTable.row();

    }
}