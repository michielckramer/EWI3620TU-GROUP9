/**
 * Player represents the actual player in MazeRunner.
 * <p>
 * This class extends GameObject to take advantage of the already implemented
 * location functionality. Furthermore, it also contains the orientation of the
 * Player, ie. where it is looking at and the player's speed.
 * <p>
 * For the player to move, a reference to a Control object can be set, which can
 * then be polled directly for the most recent input.
 * <p>
 * All these variables can be adjusted freely by MazeRunner. They could be
 * accessed by other classes if you pass a reference to them, but this should be
 * done with caution.
 * 
 * @author Bruno Scheele
 * 
 */
public class Player extends GameObject {
	private double horAngle, verAngle;
	private static double speed;

	private Control control = null;

	/**
	 * The Player constructor.
	 * <p>
	 * This is the constructor that should be used when creating a Player. It
	 * sets the starting location and orientation.
	 * <p>
	 * Note that the starting location should be somewhere within the maze of
	 * MazeRunner, though this is not enforced by any means.
	 * 
	 * @param x
	 *            the x-coordinate of the location
	 * @param y
	 *            the y-coordinate of the location
	 * @param z
	 *            the z-coordinate of the location
	 * @param h
	 *            the horizontal angle of the orientation in degrees
	 * @param v
	 *            the vertical angle of the orientation in degrees
	 */
	public Player(double x, double y, double z, double h, double v) {
		// Set the initial position and viewing direction of the player.
		super(x, y, z);
		horAngle = h;
		verAngle = v;
		speed = 0.01;
	}

	/**
	 * Sets the Control object that will control the player's motion
	 * <p>
	 * The control must be set if the object should be moved.
	 * 
	 * @param input
	 */
	public void setControl(Control control) {
		this.control = control;
	}

	/**
	 * Gets the Control object currently controlling the player
	 * 
	 * @return
	 */
	public Control getControl() {
		return control;
	}

	/**
	 * Returns the horizontal angle of the orientation.
	 * 
	 * @return the horAngle
	 */
	public double getHorAngle() {
		return horAngle;
	}

	/**
	 * Sets the horizontal angle of the orientation.
	 * 
	 * @param horAngle
	 *            the horAngle to set
	 */
	public void setHorAngle(double horAngle) {
		this.horAngle = horAngle;
	}

	/**
	 * Returns the vertical angle of the orientation.
	 * 
	 * @return the verAngle
	 */
	public double getVerAngle() {
		return verAngle;
	}

	/**
	 * Sets the vertical angle of the orientation.
	 * 
	 * @param verAngle
	 *            the verAngle to set
	 */
	public void setVerAngle(double verAngle) {
		this.verAngle = verAngle;
	}

	/**
	 * Returns the speed.
	 * 
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed.
	 * 
	 * @param speed
	 *            the speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getLocationX() {
		return super.getLocationX();
	}

	public double getLocationZ() {
		return super.getLocationZ();
	}

	/**
	 * Updates the physical location and orientation of the player
	 * 
	 * @param deltaTime
	 *            The time in milliseconds since the last update.
	 */
	public void update(int deltaTime) {
		if (control != null) {
			control.update();
			setHorAngle((double) control.getdX());
			setVerAngle((double) control.getdY());
			if (control.getForward()) {
				this.setLocationX(locationX
						- Math.sin(Math.toRadians(getHorAngle())) * deltaTime
						* speed);
				this.setLocationZ(locationZ
						- Math.cos(Math.toRadians(getHorAngle())) * deltaTime
						* speed);
			}
			if (control.getBack()) {
				this.setLocationX(locationX
						- Math.sin(Math.toRadians(getHorAngle() + 180))
						* deltaTime * speed);
				this.setLocationZ(locationZ
						- Math.cos(Math.toRadians(getHorAngle() + 180))
						* deltaTime * speed);
			}
			if (control.getLeft()) {
				this.setLocationX(locationX
						- Math.sin(Math.toRadians(getHorAngle() + 90))
						* deltaTime * speed);
				this.setLocationZ(locationZ
						- Math.cos(Math.toRadians(getHorAngle() + 90))
						* deltaTime * speed);
			}
			if (control.getRight()) {
				this.setLocationX(locationX
						- Math.sin(Math.toRadians(getHorAngle() - 90))
						* deltaTime * speed);
				this.setLocationZ(locationZ
						- Math.cos(Math.toRadians(getHorAngle() - 90))
						* deltaTime * speed);
			}
			if (control.getUp()) {
				this.setLocationY(locationY + deltaTime * speed);
			}
			if (control.getDown()) {
				this.setLocationY(locationY - deltaTime * speed);
			}
		}
	}
}
