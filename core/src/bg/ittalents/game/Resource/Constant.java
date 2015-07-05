package bg.ittalents.game.Resource;

import com.badlogic.gdx.Gdx;

/**
 * Created by vlado on 05-Jul-15.
 */
public abstract class Constant {

//    Login Screen constant
    public static final int WIDTH_SCREEN = Gdx.graphics.getWidth();
    public static final int HEIGHT_SCREEN = Gdx.graphics.getHeight();
    public static final float CONSTANT_TABLE_MESSAGE_PAD_TOP = HEIGHT_SCREEN / 3.2f;
    public static final String USER_NAME = "USER NAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,10}";
    public static final String USER_PATTERN = "(?=.*[a-z]).{3,10}";
}
