
//Controller
public class Main {

	private final GUI gui;
	private final Listener l;
	private final EManager eM;
	static final int FPS = 144;
	private long lastTimestamp;
	static final int SPEED = 100;
	static final int STEPS = 1 * SPEED;
	static final boolean TRAILS = false;

	public static void main(String args[]) {
		new Main();
	}

	public Main() {
		eM = new EManager();
		l = new Listener(this);
		gui = new GUI(l);
		lastTimestamp = System.nanoTime();
		mainLoop();
	}

	/**
	 * Main-Loop of the Game
	 */
	private void mainLoop() {
		while (true) {
			try {
				Thread.sleep(1000 / FPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			reactToInput(l.getLastButton());
			gui.executeRender(eM.getParticles(), eM.getNumberP());
			eM.moveParticles(getDeltaTime() * SPEED);
			int scroll = l.getScrollAmount();
			if (scroll != 0) {
				gui.getCamera().zoomCamera(scroll);
			}
			if (eM.mouseGravity) {
				Vector pos2 = l.getMousePosition();
				eM.updateMousePos(((pos2.x - GUI.WIDTH / 2) / gui.getCamera().scale) - gui.getCamera().pos.x,
						((pos2.y - GUI.HEIGHT / 2) / gui.getCamera().scale) - gui.getCamera().pos.y);
			}
		}
	}

	/**
	 * Reacts to Inputs given
	 * 
	 * @param lastInput ,a Integer of the Id of Input
	 */
	protected void reactToInput(int lastInput) {
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
				if(!eM.mouseGravity) {
					int r = 10000;
					pos.x = ((pos.x - GUI.WIDTH / 2) / gui.getCamera().scale) - gui.getCamera().pos.x;
					pos.y = ((pos.y - GUI.HEIGHT / 2) / gui.getCamera().scale) - gui.getCamera().pos.y;
					if (!eM.newParticle(pos.x, pos.y, (float) (Math.PI * Math.pow(r, 2) * 2E14), 10000, 0, 0)) {
						gui.drawErr("Max Number of Entities reached!");
					}
				} else {
					gui.drawErr("You cannot spawn Particles while having Mouse-Gravity on!");
				}
			}
			break;
		case 4:
			eM.mouseGravity = !eM.mouseGravity;
			Vector pos2 = l.getMousePosition();
			eM.updateMousePos(((pos2.x - GUI.WIDTH / 2) / gui.getCamera().scale) - gui.getCamera().pos.x,
					((pos2.y - GUI.HEIGHT / 2) / gui.getCamera().scale) - gui.getCamera().pos.y);
			break;
		}
	}

	/**
	 * Returns the delta time in ms since the last frame
	 * 
	 * @return A Float
	 */
	private float getDeltaTime() {
		float dt = (System.nanoTime() - lastTimestamp) / 1E9f;
		lastTimestamp = System.nanoTime();
		return dt;
	}
}
