
//Controller
public class Main {

	private final GUI gui;
	private final Listener l;
	static final int FPS = 144;
	private long lastTimestamp;
	static final int SPEED = 1;
	static final int STEPS = 1 * SPEED;
	static final boolean TRAILS = false;

	public static void main(String args[]) {
		new Main();
	}

	public Main() {
		l = new Listener();
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
			gui.executeRender(EManager.getI().getEntities(), EManager.getI().getNumberE());
			EManager.getI().moveEntities(getDeltaTime() * SPEED);
			reactToInput(l.getLastButton());
			int scroll = l.getScrollAmount();
			if(scroll != 0) {
				gui.getCamera().zoomCamera(scroll);
			}
		}
	}

	/**
	 * Reacts to Inputs given
	 * @param lastInput ,a Integer of the Id of Input
	 */
	private void reactToInput(int lastInput) {
		switch (lastInput) {
		case 1:
			gui.getCamera().moveCamera(l.getMouseMovement());
			break;
		case 2:
			EManager.getI().deleteAllEntities();
			break;
		case 3:
			//TODO: Fix Entity spawning
			Vector pos = l.getRightClickPosition();
			if(pos != null) {
				int radius = 10000;
				pos.x = ((pos.x - GUI.WIDTH/2) / gui.getCamera().scale) - gui.getCamera().pos.x + radius;
				pos.y = ((pos.y - GUI.HEIGHT/2) / gui.getCamera().scale) - gui.getCamera().pos.y + radius;
				if (!EManager.getI().newEntity(pos.x, pos.y, 0, 0, 10000)) {
					gui.drawErr("Max Number of Entities reached");
				}
			}
			break;
		case 4:
			
		}
	}

	/**
	 * Returns the delta time in ms since the last frame
	 * @return A Float
	 */
	private float getDeltaTime() {
		float dt = (System.nanoTime() - lastTimestamp) / 1E9f;
		lastTimestamp = System.nanoTime();
		return dt;
	}
}
