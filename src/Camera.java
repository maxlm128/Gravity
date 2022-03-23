
public class Camera {
	Vector pos;
	float scale;

	public Camera() {
		scale = 0.001f;
		pos = new Vector(0, 0);
	}

	public void moveCamera(Vector mov) {
		if (mov != null) {
			pos.add(mov.mul((float) 1 / scale));
		}
	}

	public void zoomCamera(int amount) {
		scale += (float) (scale * amount * -0.03f);
	}
}
