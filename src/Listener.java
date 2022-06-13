import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

//Controller
public class Listener implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

	private Vector lastDragPos;
	private Vector mov;
	private Vector mousePos;
	private int lastMouseButton;
	private char lastKeyButton;
	private int scroll;
	private Main m;
	final int EMPTY = 0;
	static final int LEFT_KLICK = 1;
	static final int MIDDLE_KLICK = 2;
	static final int RIGHT_KLICK = 3;
	static final int MOUSE_BACKWARDS = 4;
	static final int MOUSE_FORWARD = 5;
	static final char KEYBOARD_SPACE = ' ';

	public Listener(Main m) {
		this.m = m;
		lastDragPos = new Vector(0, 0);
		mousePos = new Vector(0, 0);
		mov = new Vector(0, 0);
	}

	/**
	 * Returns a directional Vector of the movement since the last time the method
	 * was executed
	 * 
	 * @return a Vector
	 */
	public Vector getMouseMovement() {
		Vector Mov = mov.copy();
		mov.reset();
		return Mov;
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
		if (lastMouseButton == RIGHT_KLICK) {
			lastMouseButton = EMPTY;
		}
		return mousePos.copy();
	}

	/**
	 * Returns the last Button pressed in form of an ID
	 * 
	 * @return a Integer
	 */
	public int getLastMouseButton() {
		switch (lastMouseButton) {
		case MOUSE_FORWARD:
		case MOUSE_BACKWARDS:
			int button = lastMouseButton;
			lastMouseButton = 0;
			return button;
		}
		return lastMouseButton;
	}

	/**
	 * Returns the last typed Keyboard-Input in char-form
	 * 
	 * @return a char of the last Input
	 */
	public char getLastKeyInput() {
		switch (lastKeyButton) {
		case KEYBOARD_SPACE:
			char button = lastKeyButton;
			lastKeyButton = 0;
			return button;
		}
		return lastKeyButton;
	}

	// Mouse
	@Override
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case LEFT_KLICK:
			lastDragPos.x = e.getX();
			lastDragPos.y = e.getY();
			lastMouseButton = LEFT_KLICK;
			break;
		case RIGHT_KLICK:
		case MIDDLE_KLICK:
		case MOUSE_BACKWARDS:
		case MOUSE_FORWARD:
			lastMouseButton = e.getButton();
			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		lastMouseButton = EMPTY;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (lastMouseButton == MouseEvent.BUTTON1) {
			mov.add(lastDragPos.sub(new Vector(e.getX(), e.getY())));
			lastDragPos.x = e.getX();
			lastDragPos.y = e.getY();
		}
		m.reactToInput(getLastMouseButton(), getLastKeyInput(), getScrollAmount());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePos.x = e.getX();
		mousePos.y = e.getY();
	}

	// MouseWheel
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scroll += e.getUnitsToScroll() * 3;
	}

	// Keyboard
	@Override
	public void keyTyped(KeyEvent e) {
		switch (e.getKeyChar()) {
		case KEYBOARD_SPACE:
			lastKeyButton = e.getKeyChar();
		}
	}

	// Mouse (unused)
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	// Keyboard (unused)
	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}