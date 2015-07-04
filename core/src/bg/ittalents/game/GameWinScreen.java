package bg.ittalents.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by Ob1 on 7/4/2015.
 */
public class GameWinScreen implements Screen{
    public static final int WIDTH_SCREEN = Gdx.graphics.getWidth();
    public static final int HEIGHT_SCREEN = Gdx.graphics.getHeight();
    public static final int CONSTANT_SEE_SCREEN = 3;

    private Game game;
    private SpriteBatch batch;
    private Sprite backgroundSprite;
    private Stage stage;
    private int minSeeScreen;


    public GameWinScreen(Game game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        backgroundSprite = new Sprite(Assets.gameWinScreen);
        backgroundSprite.setSize(WIDTH_SCREEN, HEIGHT_SCREEN);
        stage = new Stage(new ScreenViewport());

        Assets.gamePlayMusic.stop();
        Assets.gameWinMusic.play();

        Gdx.input.setInputProcessor(stage);

        stage.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (minSeeScreen > CONSTANT_SEE_SCREEN) {
                    Assets.gameWinMusic.stop();
                    Assets.gameMenuMusic.play();
                    Assets.gameMenuMusic.setLooping(true);
                    game.setScreen(new PlayScreen(game));
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        backgroundSprite.draw(batch);
        batch.draw(backgroundSprite, backgroundSprite.getX(), backgroundSprite.getY(), backgroundSprite.getWidth(), backgroundSprite.getHeight());

        batch.end();
        setTimeSeeScreen(Gdx.graphics.getDeltaTime());
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    private void setTimeSeeScreen(float delta){
        minSeeScreen += delta;
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

    }
}
