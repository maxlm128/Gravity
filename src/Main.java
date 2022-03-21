
//Controller
public class Main {

	private GUI gui;
	static int FPS = 144;

	public static void main(String args[]) {
		new Main();
	}

	public Main() {
		gui = new GUI();
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
			EManager.getI().moveEntities();
		}
	}

}
