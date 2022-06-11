
//Controller
public class Main {

	private final GUI gui;
	private final Listener l;
	private final EManager eM;
	static final int MSPF = 1000 / 144 ;//(144 FPS)
	private long lastTimestamp;
	private long lastTimestamp2;
	static final int SPEED = 1;
	static final int STEPS = 10 * SPEED;
	static final boolean TRAILS = false;

	public static void main(String args[]) {
		new Main();
	}

	public Main() {
		eM = new EManager();
		l = new Listener(this);
		gui = new GUI(l);
		lastTimestamp = System.nanoTime();
		lastTimestamp2 = (long) (System.nanoTime() * 1E-6);
		;
		mainLoop();
	}

	/**
	 * Main-Loop of the Game
	 */
	private void mainLoop() {
		float dt;
		while (true) {
			dt = getDeltaTime();
			reactToInput(l.getLastButton(), l.getScrollAmount());
			gui.executeRender(eM.getParticles(), dt, eM.getNumberP());
			eM.moveParticles(dt * SPEED);
			sleep();
		}
	}

	/**
	 * Reacts to Inputs given
	 * 
	 * @param lastInput ,a Integer of the Id of Input
	 * @param scroll    ,a Integer for the distance scrolled
	 */
	protected void reactToInput(int lastInput, int scroll) {
		if (scroll != 0) {
			gui.getCamera().zoomCamera(scroll);
		}
		if (eM.mouseGravity) {
			eM.updateMousePos(screenToGamePos(l.getMousePosition()));
		}
		switch (lastInput) {
		case 1:
			gui.getCamera().moveCamera(l.getMouseMovement());
			break;
		case 2:
			eM.deleteAllEntities();
			break;
		case 3:
			Vector pos = l.getMousePosition();
			if (pos != null) {
				if (!eM.mouseGravity) {
					pos = screenToGamePos(pos);
					if (!eM.newParticle(0, 10000000, (float) (Math.PI * Math.pow(10000000, 2) * 2E14), 1000000, 0, 0)) {
						gui.drawErr("Max Number of Entities reached!");
					}
				} else {
					gui.drawErr("You cannot spawn Particles while having Mouse-Gravity on!");
				}
			}
			break;
		case 4:
			eM.mouseGravity = !eM.mouseGravity;
			eM.updateMousePos(screenToGamePos(l.getMousePosition()));
			break;
		}
	}

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

	private Vector screenToGamePos(Vector pos) {
		pos.x = ((pos.x - GUI.WIDTH / 2) / gui.getCamera().scale) - gui.getCamera().pos.x;
		pos.y = ((pos.y - GUI.HEIGHT / 2) / gui.getCamera().scale) - gui.getCamera().pos.y;
		return pos;
	}
}
