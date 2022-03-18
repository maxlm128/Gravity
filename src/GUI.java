import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private Entity[] e;
	private int numberE;

	public GUI() {
		JFrame jFrame = new JFrame();
		jFrame.add(this);
		jFrame.setSize(1440, 1080);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
	}

	/**
	 * Gets executed everytime the Panel is repainted, draws all Entities and the Number of Entities
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.clearRect(0, 0, 1440, 1080);
		g2d.drawString("Entities: " + numberE, 1, 10);
		if (e != null) {
			for (Entity e : e) {
				if (e != null) {
					g2d.drawOval((int) e.pos.x, (int) e.pos.y, (int) e.r, (int) e.r);
				} else {
					break;
				}
			}
		}

	}

	/**
	 * executes the render with Parameters, which are displayed in the Frame
	 * @param e ,Entity-Array of all Entities
	 * @param numberE ,Number of Total Entities
	 */
	public void executeRender(Entity[] e, int numberE) {
		this.e = e;
		this.numberE = numberE;
		repaint();
	}

}
