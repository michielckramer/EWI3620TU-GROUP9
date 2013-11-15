import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;
import java.util.ArrayList;
import java.awt.Point;

public class Guard extends GameObject implements VisibleObject {
	private double speed;
	double SQUARE_SIZE = Maze.getSquare();
	public ArrayList<Point> points = new ArrayList<Point>();
	public int counter = 0;

	public Guard(double x, double y, double z) {
		// Set the starting position and speed of the guard
		super(x, y, z);
		speed = 0.3;
	}

	// setLocation
	public void setLocationX(double locationX) {
		this.locationX = locationX;
	}

	public void setLocationY(double locationY) {
		this.locationY = locationY;
	}

	public void setLocationZ(double locationZ) {
		this.locationZ = locationZ;
	}

	public void setRoute(ArrayList<Point> points) {
		this.points = points;
	}

	public void display(GL gl) {
		GLUT glut = new GLUT();

		// Setting the guard colour and material
		float guardColour[] = { 0.0f, 1.0f, 0.0f }; // Guard is green


		gl.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, guardColour, 0); // Set the
																		// materials

		// Movement of the Guard
		if (counter < (points.size() - 1)) {
			if ((points.get(counter).getX() - points.get(counter + 1).getX()) == 0) {
				if ((points.get(counter).getY() - points.get(counter + 1).getY()) > 0) {
					this.setLocationZ(locationZ - speed);
				}
				if ((points.get(counter).getY() - points.get(counter + 1).getY()) < 0) {
					this.setLocationZ(locationZ + speed);
				}
			}
		
			if ((points.get(counter).getY() - points.get(counter + 1).getY()) == 0) {
				if ((points.get(counter).getX() - points.get(counter + 1).getX()) > 0) {
					this.setLocationX(locationX - speed);
				}
				if ((points.get(counter).getX() - points.get(counter + 1).getX()) < 0) {
					this.setLocationX(locationX + speed);
				}
			}
			
			//Afstand p1 -- p2
			double d1 = Math.sqrt(Math.pow(points.get(counter).getX() - points.get(counter + 1).getX(),2) + Math.pow(points.get(counter).getY() - points.get(counter + 1).getY(), 2));
			
			
			//Afstand p1 -- huidige positie
			double d2 = Math.sqrt(Math.pow(points.get(counter).getX() - locationX,2) + Math.pow(points.get(counter).getY() - locationZ, 2));
			
			
			
			if(d2 > d1){
				counter++;
				System.out.println(counter + "counter test");
			} 
			
			if(counter == (points.size() - 1) ){
				counter = 0;
			}
		}
		
		// Als ik dit deed gebruiken liep die de route en zou die terug moeten lopen
		// maar aangezien je counter dan -1 verlaagt en daarna wer +1 verhoogt
		// blijft die heen en weer lopen tussen de laatste 2 punten
		// je moet dan wel de if hierboven weghalen dat die hem op 0 zet.
		/*else{
			if ((points.get(counter).getX() - points.get(counter - 1).getX()) == 0) {
				if ((points.get(counter).getY() - points.get(counter - 1).getY()) > 0) {
					this.setLocationZ(locationZ - speed);
				}
				if ((points.get(counter).getY() - points.get(counter - 1).getY()) < 0) {
					this.setLocationZ(locationZ + speed);
				}
			}
	
			if ((points.get(counter).getY() - points.get(counter - 1).getY()) == 0) {
				if ((points.get(counter).getX() - points.get(counter - 1).getX()) > 0) {
					this.setLocationX(locationX - speed);
				}
				if ((points.get(counter).getX() - points.get(counter - 1).getX()) < 0) {
					this.setLocationX(locationX + speed);
				}
				
			}	
		
			//Afstand p1 -- p2
			double d1 = Math.sqrt(Math.pow(points.get(counter).getX() - points.get(counter - 1).getX(),2) + Math.pow(points.get(counter).getY() - points.get(counter - 1).getY(), 2));
			
			//Afstand p1 -- huidige positie
			double d2 = Math.sqrt(Math.pow(points.get(counter).getX() - locationX,2) + Math.pow(points.get(counter).getY() - locationZ, 2));
			

			if(d2 > d1){
				counter = 0;
				System.out.println(counter + " second counter test");
			}
			
		}*/
		
	

		gl.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, guardColour, 0);


		gl.glPushMatrix();
		gl.glTranslated(locationX, locationY, locationZ);
		glut.glutSolidCube((float) SQUARE_SIZE / 2);
		gl.glPopMatrix();

	}
}