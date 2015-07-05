package bg.ittalents.game.Resource;

import com.badlogic.gdx.Gdx;

/**
 * Created by vlado on 05-Jul-15.
 */
public abstract class Constant {
    public static final int WIDTH_SCREEN = Gdx.graphics.getWidth();
    public static final int HEIGHT_SCREEN = Gdx.graphics.getHeight();
    public static final float CONSTANT_TABLE_MESSAGE_PAD_TOP = HEIGHT_SCREEN / 3.2f;
    public static final String USER_NAME = "USER NAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,10}";
    public static final String USER_PATTERN = "(?=.*[a-z]).{3,10}";
    public static final float CONSTANT_WIDTH_FIELD_AND_BUTTON = Constant.WIDTH_SCREEN / 3;
    public static final float CONSTANT_HEIGHT_FIELD_AND_BUTTON = Constant.HEIGHT_SCREEN / 10;
    public static final float CONSTANT_HEIGHT_TITLE = Constant.HEIGHT_SCREEN / 3;
    public static final int CONSTANCE_HEIGHT_BUTTONS = Constant.HEIGHT_SCREEN / 5;
    public static final float CONSTANT_PAD_LEFT_AND_RIGHT = Gdx.graphics.getWidth() / 30;
    public static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
}
