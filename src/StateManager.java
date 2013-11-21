import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

public class StateManager {
	private static int screenHeight;
	private static int screenWidth;
	private float buttonSize;
	private static boolean game = false;

	/*
	 * States: 1 = Startmenu, 2 = Running, 3 = Pause
	 */
	private static int gamestate;

	public static int getGameState() {
		return gamestate;
	}

	public static void setGameState(int s) {
		gamestate = s;
	}

	public static boolean getPause() {
		return gamestate == 3;
	}

	public static void setPause() {
		gamestate = 3;
	}

	public static void resume() {
		setGameState(2);
	}

	public static void display(GLAutoDrawable drawable) {

		GL gl = drawable.getGL();
		int state = StateManager.getGameState();
		switch (state) {
		case 1:
			StartMenu.display(gl);
			initUpdater(drawable);
			break;
		case 3:
			break;
		}
	}

	public static void initUpdater(GLAutoDrawable drawable) {
		if (!game) {
			MainClass.runner.init(drawable);
			// MainClass.runner.reshape(0, 0, screenWidth, screenHeight);
			game = true;
		}
	}

	public static int getScreenHeight() {
		return screenHeight;
	}

	public static void setScreenHeight(int Height) {
		screenHeight = Height;
	}

	public static int getScreenWidth() {
		return screenWidth;
	}

	public static void setScreenWidth(int Width) {
		screenWidth = Width;
	}
}
