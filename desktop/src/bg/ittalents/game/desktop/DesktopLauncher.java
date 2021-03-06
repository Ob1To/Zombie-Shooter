package bg.ittalents.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import bg.ittalents.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Zombie Shooter";
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
