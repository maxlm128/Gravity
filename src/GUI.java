import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

//View
public class GUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private Entity[] e;
	private int numberE;
	private final int WIDTH = 1440;
	private final int HEIGHT = 1080;
	private float scale;

	public GUI() {
		scale = 0.002f;
		JFrame jFrame = new JFrame();
		jFrame.add(this);
		jFrame.setSize(WIDTH, HEIGHT);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
	}

	/**
	 * Gets executed everytime the Panel is repainted, draws all Entities and the
	 * Number of Entities
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
		g2d.setColor(Color.WHITE);
		g2d.drawString("Entities: " + numberE, 1, 10);
		if (e != null) {
			for (Entity e : e) {
				g2d.fillOval((int) ((e.pos.x - e.r / 2) * scale), Math.round((e.pos.y - e.r / 2) * scale),
						(int) (e.r * 2 * scale), (int) (e.r * 2 * scale));
			}
		}
	}

	/**
	 * executes the render with Parameters, which are displayed in the Frame
	 * 
	 * @param e ,Entity-Array of all Entities
	 * @param numberE ,Number of Total Entities
	 */
	public void executeRender(Entity[] e, int numberE) {
		this.e = e;
		this.numberE = numberE;
		repaint();
	}

}
