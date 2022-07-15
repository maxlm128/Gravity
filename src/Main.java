
//Controller
public class Main {

	private final GUI gui;
	private final Listener l;
	private final EntityManager eM;
	private long lastTimestamp;
	private long lastTimestamp2;
	private boolean running;
	static final float SPEED = 3600; // Ingame Seconds per real Second (One ISS Orbit per 4 Seconds)
//	static final float SPEED = 86400 * 29; // Ingame Seconds per real Second (1 Day per Second)
	static final int STEPS = 10;
	static final int MSPF = 1000 / 144;// Milliseconds per frame

	public static void main(String args[]) {
		new Main();
	}

	public Main() {
		eM = new EntityManager();
		l = new Listener(this);
		gui = new GUI(l);
		lastTimestamp = System.nanoTime();
		lastTimestamp2 = (long) (System.nanoTime() * 1E-6);
		running = true;
		mainLoop();
	}

	/**
	 * Main-Loop of the Game
	 */
	private void mainLoop() {
		float dt;
		while (true) {
			sleep();
			dt = getDeltaTime();
			reactToInput(l.getLastMouseButton(), l.getLastKeyInput(), l.getScrollAmount());
			gui.executeRender(eM.getParticles(), dt, eM.getNumberP());
			if (running) {
				eM.moveParticles((dt * SPEED));
			}
		}
	}

	/**
	 * Reacts to Inputs given from Keyboard and Mouse
	 * 
	 * @param lastMouseInput ,a Integer of the Id of the last Mouse Input
	 * @param lastKeyInput   ,a Integer of the Id of the last Keyboard Input
	 * @param scroll         ,a Integer for the distance scrolled
	 */
	protected void reactToInput(int lastMouseInput, char lastKeyInput, int scroll) {
		// Update of Mouse-Position
		if (eM.mouseGravity) {
			eM.updateMousePos(screenToGamePos(l.getMousePosition()));
		}
		// Reaction to scrolling
		if (scroll != 0) {
			gui.getCamera().zoomCamera(scroll);
		}
		// Reaction to Mouse-Inputs
		switch (lastMouseInput) {
		case Listener.MOUSE_LEFT_KLICK:
			gui.getCamera().moveCamera(l.getMouseMovement());
			break;
		case Listener.MOUSE_MIDDLE_KLICK:
			eM.deleteAllEntities();
			break;
		case Listener.MOUSE_RIGHT_KLICK:
			Vector pos = l.getMousePosition();
			if (pos != null) {
				if (!eM.mouseGravity) {
					pos = screenToGamePos(pos);
					if (!eM.newParticle(pos.x, pos.y, 5.972E24f, 6.371E6f, 0, 0)) {
						gui.drawErr("Max Number of Entities reached!");
					}
				} else {
					gui.drawErr("You cannot spawn Particles while having Mouse-Gravity on!");
				}
			}
			break;
		case Listener.MOUSE_BACKWARDS:
			eM.mouseGravity = !eM.mouseGravity;
			eM.updateMousePos(screenToGamePos(l.getMousePosition()));
			break;
		}
		// Reaction to Keyboard Inputs
		switch (lastKeyInput) {
		case Listener.KEYBOARD_SPACE:
			running = !running;
			break;
		}
	}

	/**
	 * Sleeps for the time of MSPF minus the time the calculation of the remaining
	 * components, exept the time needed for the calculation is greater than MSPF,
	 * therefore trying to reach the target-frametime of MSPF
	 */
	private void sleep() {
		int t = (int) (System.nanoTime() * 1E-6 - lastTimestamp2);
		if (t < MSPF) {
			try {
				Thread.sleep(MSPF - t);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		lastTimestamp2 = (long) (System.nanoTime() * 1E-6);
	}

	/**
	 * Returns the delta time in seconds since the last frame
	 * 
	 * @return A Float
	 */
	private float getDeltaTime() {
		float dt = (System.nanoTime() - lastTimestamp) / 1E9f;
		lastTimestamp = System.nanoTime();
		return dt;
	}

	/**
	 * Converts one Position on the Screen to a Position of the ingame coorinates
	 * 
	 * @param pos ,the Coordinates on the screen in form of a Vector
	 * @return The Vector of the Position on the screen
	 */
	private Vector screenToGamePos(Vector pos) {
		pos.x = ((pos.x - GUI.WIDTH / 2) / gui.getCamera().scale) - gui.getCamera().pos.x;
		pos.y = ((pos.y - GUI.HEIGHT / 2) / gui.getCamera().scale) - gui.getCamera().pos.y;
		return pos;
	}
}
