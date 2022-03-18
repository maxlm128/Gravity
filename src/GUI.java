import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JPanel {
	private static final long serialVersionUID = 1L;

	public GUI() {
		JFrame jFrame = new JFrame();
		jFrame.add(this);
		jFrame.setSize(1440,1080);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
	}
	
	/**
	 * Gets executed everytime the Panel is repainted
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawOval(10, 10, 100, 100);
	}
	
	public void drawingLoop() {
		repaint();
	}
	
	public static void render() {
		
	}
}
