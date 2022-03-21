
//Controller, Model
public class Entity {
	float m;
	float r;
	Vector pos;
	Vector vel;
	Vector acc;

	public Entity(float x, float y, float r, float vx, float vy) {
		pos = new Vector(x, y);
		vel = new Vector(vx, vy);
		acc = new Vector(0, 0);
		this.r = r;
		//Density of an average neutron star
		m = (float) (Math.PI * Math.pow(this.r, 2) * 1E13);
		System.out.println(m);
	}

	/**
	 * Moves the Entity by the vel-Vector
	 */
	public void move() {
		calcGravity();
		calcCollision();
		pos.add(vel);
		vel.add(acc);
	}

	/**
	 * Calculates the gravity torwards all other Entities and applies the
	 * accelleration to the Vector acc
	 */
	private void calcGravity() {
		acc.reset();
		for (Entity e : EManager.getI().getEntities()) {
			if (e != this) {
				float G = 6.673e-11f;
				acc.add(pos.sub(e.pos).n().mul((float) (G * e.m / Math.pow((pos.sub(e.pos).l()), 2))));
			}
		}
	}

	/**
	 * Calculates whether two circles are touching each other and resolves the
	 * collision if they are colliding
	 */
	private void calcCollision() {
		for (Entity e : EManager.getI().getEntities()) {
			if (e != this && pos.sub(e.pos).l() < e.r + r) {
				Vector d = pos.sub(e.pos).n().mul(e.r + r - pos.sub(e.pos).l());
				e.pos.add(d);
				pos.add(d.mul(-1));
			}
		}
	}

}
