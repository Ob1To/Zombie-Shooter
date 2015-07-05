package bg.ittalents.game;


import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {


    @Override
    public void create() {
        bg.ittalents.game.Resource.Assets.init();
        this.setScreen(new IntroScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.ittalents.game.Resource.Assets.dispose();
    }
}
