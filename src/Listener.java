import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

//Controller
public class Listener implements MouseListener, MouseMotionListener, MouseWheelListener {

	private Vector lastDragPos;
	private Vector mov;
	private Vector rightClickPos;
	private int lastButton;
	private int scroll;

	public Listener() {
		lastDragPos = new Vector(0, 0);
		rightClickPos = new Vector(0, 0);
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
			rightClickPos.x = e.getX();
			rightClickPos.y = e.getY();
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
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scroll += e.getUnitsToScroll() * 3;
	}
	
	public int getLastButton() {
		return lastButton;
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

	public Vector getRightClickPosition() {
		if (lastButton == MouseEvent.BUTTON3) {
			lastButton = 0;
			return rightClickPos.copy();
		}
		return null;
	}

}