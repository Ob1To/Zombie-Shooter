package bg.ittalents.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import bg.ittalents.game.Resource.Assets;


public class IntroScreen extends ApplicationAdapter implements Screen {
    public static final float DEGREES_PER_SECOND = 10f;
    final float SHAKE_AMPLITUDE_IN_DEGREES = 5.0f;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Sprite itTalentsSprite;
    private TextureRegion itTalentsLogoRegion;
    private Game zombieShooterGame;
    private float rotationIndex;
    private float scale = 1f;
    private float animationLengthSec;
    private Music introMusic;
    private Texture itTaletntsLogo;

    public IntroScreen(Game game) {
        zombieShooterGame = game;
    }



    @Override
    public void show() {
        introMusic = Gdx.audio.newMusic(Gdx.files.internal("Sounds/IntroMusic1.mp3"));
        introMusic.setVolume(0.4f);
        introMusic.play();

        camera = new OrthographicCamera(2, 1.5f);
        batch = new SpriteBatch();
        itTaletntsLogo = new Texture(Gdx.files.internal("Images/ittalentslogo.png"));
        itTaletntsLogo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        itTalentsLogoRegion = new TextureRegion(itTaletntsLogo, 0, 0, itTaletntsLogo.getWidth(), itTaletntsLogo.getHeight());
        itTalentsSprite = new Sprite(itTalentsLogoRegion);
        itTalentsSprite.setSize(0.9f, 0.9f * itTalentsSprite.getHeight() / itTalentsSprite.getWidth());
        itTalentsSprite.setOrigin(itTalentsSprite.getWidth() / 2, itTalentsSprite.getHeight() / 2);
        itTalentsSprite.setPosition(-itTalentsSprite.getWidth() / 2, -itTalentsSprite.getHeight() / 2);
        this.animationLengthSec = 1f;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        animationLengthSec -= delta;
        batch.begin();
        rotationIndex = (rotationIndex + Gdx.graphics.getDeltaTime() * DEGREES_PER_SECOND) % 360; // This take cares of the logo rotation in the beginning
        float shake = MathUtils.sin(rotationIndex) * SHAKE_AMPLITUDE_IN_DEGREES;
        itTalentsSprite.setRotation(shake);
        itTalentsSprite.setScale(scale);
        scale = scale + (Gdx.graphics.getDeltaTime() / 6);
        itTalentsSprite.draw(batch);
        batch.end();

        if (Assets.manager.update()){
            if (animationLengthSec <= 0){
                Assets.createAssets();
                introMusic.stop();
                zombieShooterGame.setScreen(new LoginScreen(zombieShooterGame));
            }
        }
    }

    @Override
    public void dispose() { // AssetManager takes care of the dispose of all resources
        batch.dispose();
        zombieShooterGame.dispose();
        introMusic.dispose();
    }

    @Override
    public void hide() {
    }
}
