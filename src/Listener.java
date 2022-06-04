import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

//Controller
public class Listener implements MouseListener, MouseMotionListener, MouseWheelListener {

	private Vector lastDragPos;
	private Vector mov;
	private Vector mousePos;
	private int lastButton;
	private int scroll;
	private Main m;

	public Listener(Main m) {
		this.m = m;
		lastDragPos = new Vector(0, 0);
		mousePos = new Vector(0, 0);
		mov = new Vector(0, 0);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			lastDragPos.x = e.getX();
			lastDragPos.y = e.getY();
			lastButton = MouseEvent.BUTTON1;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			lastButton = MouseEvent.BUTTON3;
		} else if (e.getButton() == MouseEvent.BUTTON2) {
			lastButton = MouseEvent.BUTTON2;
		} else if (e.getButton() == 4) {
			lastButton = 4;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		lastButton = 0;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (lastButton == MouseEvent.BUTTON1) {
			mov.add(lastDragPos.sub(new Vector(e.getX(), e.getY())));
			lastDragPos.x = e.getX();
			lastDragPos.y = e.getY();
		}
		m.reactToInput(getLastButton());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePos.x = e.getX();
		mousePos.y = e.getY();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scroll += e.getUnitsToScroll() * 3;
	}

	/**
	 * Returns the last Button pressed in form of an ID
	 * 
	 * @return a Integer
	 */
	public int getLastButton() {
		if (lastButton == 4) {
			lastButton = 0;
			return 4;
		}
		return lastButton;
	}

	/**
	 * Returns a directional Vector of the movement since the last time the method
	 * was executed
	 * 
	 * @return a Vector
	 */
	public Vector getMouseMovement() {
		Vector temp = mov.copy();
		mov.reset();
		return temp;
	}

	/**
	 * Returns the amount scrolled since the last time the method was executed
	 * 
	 * @return a Integer
	 */
	public int getScrollAmount() {
		int temp = scroll;
		scroll = 0;
		return temp;
	}

	/**
	 * Returns the current position of the Mouse in form of a Vector
	 * 
	 * @return a Vector
	 */
	public Vector getMousePosition() {
		if (lastButton == MouseEvent.BUTTON3) {
			lastButton = 0;
		}
		return mousePos.copy();
	}

}