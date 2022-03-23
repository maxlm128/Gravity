import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

//Controller
public class Listener implements MouseListener, MouseMotionListener, MouseWheelListener {

	private Vector lastPos;
	private Vector mov;
	private boolean pressed;
	private int scroll;

	public Listener() {
		lastPos = new Vector(0, 0);
		mov = new Vector(0, 0);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		lastPos.x = e.getX();
		lastPos.y = e.getY();
		pressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		pressed = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (pressed) {
			mov.add(lastPos.sub(new Vector(e.getX(), e.getY())));
			lastPos.x = e.getX();
			lastPos.y = e.getY();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scroll += e.getUnitsToScroll();
	}

	public Vector getMouseMovement() {
		Vector temp = mov.copy();
		mov.reset();
		return temp;
	}

	public int getScrollAmount() {
		int temp = scroll;
		scroll = 0;
		return temp;
	}

}