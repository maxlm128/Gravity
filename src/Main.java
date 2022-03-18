
public class Main {

	GUI gui;
	int fps = 144;
	EntityManager entityManager;

	public static void main(String args[]) {
		new Main();
	}

	public Main() {
		gui = new GUI();
		entityManager = new EntityManager();
		mainLoop();
	}

	private void mainLoop() {
		while (true) {
			try {
				Thread.sleep(1000 / fps);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gui.drawingLoop();
		}
	}

}
