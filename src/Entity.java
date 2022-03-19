
//Controller, Model
public class Entity {
	float m;
	float r;
	Vector pos;
	Vector vel;
	Vector acc;
	private EntityManager eManager;

	public Entity(float x, float y, float r, EntityManager eManager) {
		this.eManager = eManager;
		pos = new Vector(x, y);
		vel = new Vector(0, 0);
		acc = new Vector(0, 0);
		this.r = r;
		m = 0.0001f;
	}

	/**
	 * Moves the Entity by the vel-Vector
	 */
	public void move() {
		calcGravity();
		vel.add(acc);
		pos.add(vel);
	}

	/**
	 * Calculates the gravity torwards all other Entities and applies the
	 * accelleration to the Vector acc
	 */
	private void calcGravity() {
		for (Entity e : eManager.getEntities()) {
			if (e != this) {
				// Calculate directional vector between this.pos and e.pos
				Vector v = this.pos.sub(e.pos);
				// Multiplies the normalized Vector by the reciprocal(Kehrwert) of the distance
				float l = v.l();
				System.out.println("A " + l);
				v.n();
				v.mul(1 / l);
				System.out.println("B " + v.l());
				// Multiplies the Gravity by the mass
				v.mul(e.m);
				System.out.println("C " + v.l());
				// Adds the Gravity to the acc to combine all gravities together
				acc.add(v);
			}
		}
	}
}
