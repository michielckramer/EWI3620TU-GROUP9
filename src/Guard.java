import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

public class Guard extends GameObject implements VisibleObject {
	private double speed;
	public static final double SQUARE_SIZE = 5;

	public Guard(double x, double y, double z) {
		// Set the starting position and speed of the guard
		super(x, y, z);
		speed = 0.01;
	}

	public void display(GL gl) {
		GLUT glut = new GLUT();

		// Setting the guard colour and material
		float guardColour[] = { 0.0f, 1.0f, 0.0f }; // Guard is green

		gl.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, guardColour, 0);

		gl.glPushMatrix();
		gl.glTranslated(locationX, locationY, locationZ);
		glut.glutSolidCube((float) SQUARE_SIZE / 2);
		gl.glPopMatrix();

	}
}