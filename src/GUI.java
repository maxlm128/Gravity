import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

//View
public class GUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private Particle[] p;
	private int numberP;
	private float mspf;
	private Error err;
	static protected final int WIDTH = 1440;
	static protected final int HEIGHT = 1080;
	private Camera c;

	public GUI(Listener l) {
		mspf = 0;
		c = new Camera();
		JFrame jFrame = new JFrame();
		jFrame.addMouseListener(l);
		jFrame.addMouseMotionListener(l);
		jFrame.addMouseWheelListener(l);
		jFrame.addKeyListener(l);
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
		g2d.drawString("Entities: " + numberP, 1, 10);
		g2d.drawString("Frame-Time: " + mspf + " ms", 1, 20);
		for (Particle p : p) {
			g2d.fillOval(Math.round((p.pos.x - p.r + c.pos.x) * c.scale) + WIDTH / 2,
					Math.round((p.pos.y - p.r + c.pos.y) * c.scale) + HEIGHT / 2, (int) (p.r * 2 * c.scale),
					(int) (p.r * 2 * c.scale));
			g2d.drawLine(Math.round((p.pos.x + c.pos.x) * c.scale) + WIDTH / 2,
					Math.round((p.pos.y + c.pos.y) * c.scale) + HEIGHT / 2,
					Math.round((p.pos.x + c.pos.x) * c.scale) + WIDTH / 2,
					Math.round((p.pos.y + c.pos.y) * c.scale) + HEIGHT / 2);
		}
		if (err != null) {
			g2d.drawString("Error: " + err.err, 1, 30);
			if (err.isOlderThan(5000)) {
				err = null;
			}
		}
	}

	/**
	 * Draws an Error on the screen
	 * 
	 * @param err ,a class Error
	 */
	public void drawErr(String err) {
		this.err = new Error(err);
	}

	/**
	 * executes the render with Parameters, which are displayed in the Frame
	 * 
	 * @param e       ,Entity-Array of all Entities
	 * @param numberE ,Number of Total Entities
	 */
	public void executeRender(Particle[] p, float mspf, int numberP) {
		this.mspf = mspf * 1000;
		this.p = p;
		this.numberP = numberP;
		repaint();
	}

	/**
	 * Returns the Camera Object of the GUI
	 * 
	 * @return ,a Object of the type Camera
	 */
	public Camera getCamera() {
		return c;
	}
}
