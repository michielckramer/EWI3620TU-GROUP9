import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;
import com.sun.opengl.util.texture.Texture;

/**
 * Maze represents the maze used by MazeRunner.
 * <p>
 * The Maze is defined as an array of integers, where 0 equals nothing and 1
 * equals a wall. Note that the array is square and that MAZE_SIZE contains the
 * exact length of one side. This is to perform various checks to ensure that
 * there will be no ArrayOutOfBounds exceptions and to perform the calculations
 * needed by not only the display(GL) function, but also by functions in the
 * MazeRunner class itself.<br />
 * Therefore it is of the utmost importance that MAZE_SIZE is correct.
 * <p>
 * SQUARE_SIZE is used by both MazeRunner and Maze itself for calculations of
 * the display(GL) method and other functions. The larger this value, the larger
 * the world of MazeRunner will be.
 * <p>
 * This class implements VisibleObject to force the developer to implement the
 * display(GL) method, so the Maze can be displayed.
 * 
 * @author Bruno Scheele, revised by Mattijs Driel
 * 
 */
public class Maze implements VisibleObject {

	public static final double MAZE_SIZE = 10;
	public static final double SQUARE_SIZE = 10;
	private static Texture texture;

	public static int[][] maze = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1, 0, 1, 1, 0, 1 },
			{ 1, 0, 1, 1, 0, 0, 1, 0, 0, 1 }, { 1, 0, 0, 0, 0, 1, 1, 0, 1, 1 },
			{ 1, 1, 1, 0, 1, 0, 1, 0, 0, 1 }, { 1, 0, 0, 0, 1, 0, 1, 0, 1, 1 },
			{ 1, 0, 1, 1, 1, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 0, 1, 1, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	public static double getSquare() {
		return SQUARE_SIZE;
	}

	/**
	 * isWall(int x, int z) checks for a wall.
	 * <p>
	 * It returns whether maze[x][z] contains a 1.
	 * 
	 * @param x
	 *            the x-coordinate of the location to check
	 * @param z
	 *            the z-coordinate of the location to check
	 * @return whether there is a wall at maze[x][z]
	 */
	public static boolean isWall(int x, int z) {
		if (x >= 0 && x < MAZE_SIZE && z >= 0 && z < MAZE_SIZE)
			return maze[x][z] == 1;
		else
			return false;
	}

	/**
	 * isWall(double x, double z) checks for a wall by converting the double
	 * values to integer coordinates.
	 * <p>
	 * This method first converts the x and z to values that correspond with the
	 * grid defined by maze[][]. Then it calls upon isWall(int, int) to check
	 * for a wall.
	 * 
	 * @param x
	 *            the x-coordinate of the location to check
	 * @param z
	 *            the z-coordinate of the location to check
	 * @return whether there is a wall at maze[x][z]
	 */
	public static boolean isWall(double x, double z) {
		int gX = convertToGridX(x);
		int gZ = convertToGridZ(z);
		return isWall(gX, gZ);
	}

	/**
	 * Converts the double x-coordinate to its correspondent integer coordinate.
	 * 
	 * @param x
	 *            the double x-coordinate
	 * @return the integer x-coordinate
	 */
	private static int convertToGridX(double x) {
		return (int) Math.floor(x / SQUARE_SIZE);
	}

	/**
	 * Converts the double z-coordinate to its correspondent integer coordinate.
	 * 
	 * @param z
	 *            the double z-coordinate
	 * @return the integer z-coordinate
	 */
	private static int convertToGridZ(double z) {
		return (int) Math.floor(z / SQUARE_SIZE);
	}

	public void display(GL gl) {
		GLUT glut = new GLUT();

		// Setting the wall colour and material.
		float wallColour[] = { 0.5f, 0.0f, 0.7f, 1.0f }; // The walls are
															// purple.

		gl.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, wallColour, 0); // Set the
																	// materials
																	// used by
																	// the wall.

		// draw the grid with the current material
		for (int i = 0; i < MAZE_SIZE; i++) {
			for (int j = 0; j < MAZE_SIZE; j++) {
				gl.glPushMatrix();
				gl.glTranslated(i * SQUARE_SIZE + SQUARE_SIZE / 2,
						SQUARE_SIZE / 2, j * SQUARE_SIZE + SQUARE_SIZE / 2);
				if (isWall(i, j))
					glut.glutSolidCube((float) SQUARE_SIZE);
				gl.glPopMatrix();
			}
		}
		paintSingleFloorTile(gl, MAZE_SIZE * SQUARE_SIZE); // Paint the floor.
		paintSingleCeilingTile(gl, MAZE_SIZE * SQUARE_SIZE); // Paint the //==
																// ceiling.
	}

	/**
	 * paintSingleFloorTile(GL, double) paints a single floor tile, to represent
	 * the floor of the entire maze.
	 * 
	 * @param gl
	 *            the GL context in which should be drawn
	 * @param size
	 *            the size of the tile
	 */

	private void paintSingleCeilingTile(GL gl, double size) {
		float wallColour[] = { 0.0f, 0.0f, 1.0f, 1.0f };
		gl.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, wallColour, 0);
		gl.glNormal3d(0, 1, 0);
		gl.glBegin(GL.GL_QUADS);
		gl.glVertex3d(0, SQUARE_SIZE, 0);
		gl.glVertex3d(size, SQUARE_SIZE, 0);
		gl.glVertex3d(size, SQUARE_SIZE, size);
		gl.glVertex3d(0, SQUARE_SIZE, size);
		gl.glEnd();
	}

	private void paintSingleFloorTile(GL gl, double size) {
		float wallColour[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		gl.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, wallColour, 0);
		gl.glEnable(GL.GL_TEXTURE_2D);

		if (texture == null) {
			texture = MazeRunner
					.loadTexture("C://Users/Michiel/Documents/GitHub/EWI3620TU-GROUP9/Textures/muur2.jpg");
		}
		texture.bind();

		gl.glNormal3d(0, 1, 0);
		gl.glBegin(GL.GL_QUADS);
		gl.glVertex3d(0, 0, 0);
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3d(0, 0, size);
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3d(size, 0, size);
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3d(size, 0, 0);
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glEnd();

	}
}
