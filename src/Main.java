
//Controller
public class Main {

	private GUI gui;
	private int fps = 144;
	private EntityManager eManager;

	public static void main(String args[]) {
		new Main();
	}

	public Main() {
		gui = new GUI();
		eManager = new EntityManager();
		mainLoop();
	}

	/**
	 * Main-Loop of the Game
	 */
	private void mainLoop() {
		while (true) {
			try {
				Thread.sleep(1000 / fps);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gui.executeRender(eManager.getEntities(),eManager.getNumberE());
			eManager.moveEntities();
		}
	}

}
