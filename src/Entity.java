
//Controller, Model
public class Entity {
	float m;
	float r;
	Vector pos;
	Vector vel;
	Vector acc;
//	float min = 0;

	public Entity(float x, float y, float r, float vx, float vy) {
		pos = new Vector(x, y);
		vel = new Vector(vx, vy);
		acc = new Vector(0, 0);
		this.r = r;
		m = (float) (Math.PI * Math.pow(this.r, 2));
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
		acc.r();
		for (Entity e : EManager.getI().getEntities()) {
			if (e != this) {
				float G = 6.673e-11f;
//				float temp = pos.sub(e.pos).l();
//				if(min > temp || min == 0) {
//					System.out.println(temp);
//					min = temp;
//				}
				acc.add(pos.sub(e.pos).n().mul(G * Main.FACTOR * 500000000 * (e.m) / Math.pow((pos.sub(e.pos).l()), 2)));
			}
		}
	}
	
	private void calcCollision() {
		for(Entity e : EManager.getI().getEntities()) {
			if(e != this && pos.sub(e.pos).l() < e.r + r) {
				resolveCollision(e);
			}
		}
	}
	
	private void resolveCollision(Entity e2) {
		if(vel.diff(e2.vel) > 10f) {
			Vector temp = vel.copy().mul(0.90f);
			vel = e2.vel.copy().mul(0.90f);
			e2.vel = temp;
		} else {
			vel.avg(e2.vel);
		}
	}
	
}
