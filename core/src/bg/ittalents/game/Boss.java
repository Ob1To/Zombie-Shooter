package bg.ittalents.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * Created by Ob1 on 7/3/2015.
 */
public class Boss extends Actor {
    private Game game;
    private Texture[] arrayTextureBoss;

    public Boss(){
        for (int i = 1; i < 10 ; i++) {
            for (int j = 0; j < 9; j++) {
                arrayTextureBoss = new Texture[9];
                arrayTextureBoss[j]=Assets.i;

            }

        }
    }









}
