
public class Entity {
	float m;
	Vector pos;
	final protected float G = 6.673e-11f;

	public Entity(float x, float y, float m) {
		pos = new Vector(x, y);
		this.m = m;
		pos.x = x;
		pos.y = y;
	}
}
