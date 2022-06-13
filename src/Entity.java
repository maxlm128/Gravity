
public class Entity {
	float m;
	Vector pos;
	protected EntityManager eM;
	final protected float G = 6.673e-11f;

	public Entity(float x, float y, float m, EntityManager eM) {
		this.eM = eM;
		pos = new Vector(x, y);
		this.m = m;
		pos.x = x;
		pos.y = y;
	}
}
