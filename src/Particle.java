
//Controller, Model
public class Particle extends Entity {
	float r;
	Vector vel;
	float[][] trail;
	int trailIndex;

	public Particle(float x, float y, float m, float r, float vx, float vy) {
		super(x, y, m);
		vel = new Vector(vx, vy);
		if(Main.TRAILS) {
			trail = new float[Main.FPS * 200][2];
		}
		this.r = r;
	}

	/**
	 * Moves the Particle by the vel-Vector
	 */
	public void move(float dt) {
		pos.add(vel.copy().scl(dt));
		calcGravity(dt);
		resolveCollision();
	}

	/**
	 * Calculates the gravity torwards all other Entities which are not colliding
	 * and applies the accelleration to the Vector acc using the Runge-Kutta-Method
	 */
	private void calcGravity(float dt) {
		for (Entity e : EManager.getI().getEntities()) {
			if (e != this) {
//					Vector nextPos = pos.copy().add(vel);
//					Vector vel1 = vel.copy().add(
//							pos.sub(e.pos).n().scl((float) (G * e.m / Math.pow((pos.sub(e.pos).l()), 2))));
//					Vector vel2 = vel.copy().add(nextPos.sub(e.pos).n()
//							.scl((float) (G * e.m / Math.pow((nextPos.sub(e.pos).l()), 2))));
//					vel.x = (vel1.x + vel2.x) / 2;
//					vel.y = (vel1.y + vel2.y) / 2;
//					
//					EULER:
				vel.add(pos.sub(e.pos).n().scl((float) (G * e.m / Math.pow((pos.sub(e.pos).l()), 2))).scl(dt));

//					RUNGE-KUTTA:
//					Vector k1 = vel.copy().add(pos.sub(e.pos).n().scl((float) (G * e.m / Math.pow((pos.sub(e.pos).l()), 2))).scl(dt));
//					
//					Vector k2 = pos.copy().add(k1.copy().scl(0.5f));
//					k2 = vel.copy().add(k2.sub(e.pos).n().scl((float) (G * e.m / Math.pow((k2.sub(e.pos).l()), 2))).scl(dt));
//					
//					Vector k3 = pos.copy().add(k2.copy().scl(0.5f));
//					k3 = vel.copy().add(k3.sub(e.pos).n().scl((float) (G * e.m / Math.pow((k3.sub(e.pos).l()), 2))).scl(dt));
//					
//					Vector k4 = pos.copy().add(k3);
//					k4 = vel.copy().add(k4.sub(e.pos).n().scl((float) (G * e.m / Math.pow((k4.sub(e.pos).l()), 2))).scl(dt));
//					
//					vel.x = (k1.x + (k2.x * 2) + (k3.x * 2) + k4.x) / 6;
//					vel.y = (k1.y + (k2.y * 2) + (k3.y * 2) + k4.y) / 6;
			}
		}
	}

	/**
	 * Calculates whether two Particles are touching each other and resolves the
	 * collision if they are colliding
	 */
	private void resolveCollision() {
		for (Particle p : EManager.getI().getParticles()) {
			if (p != this && pos.sub(p.pos).l() <= p.r + r) {
				Vector d = pos.sub(p.pos).n().scl(p.r + r - pos.sub(p.pos).l()).scl(0.5f);
				p.pos.add(d);
				pos.add(d.scl(-1));
			}
		}
		for (Particle p: EManager.getI().getParticles()) {
			// Distance between balls
			if (p != this && pos.sub(p.pos).l() <= p.r + r) {
				float fDistance = pos.sub(p.pos).l();

				// Normal
				float nx = (p.pos.x - pos.x) / fDistance;
				float ny = (p.pos.y - pos.y) / fDistance;

				// Tangent
				float tx = -ny;
				float ty = nx;

				// Dot Product Tangent
				float dpTan1 = vel.x * tx + vel.y * ty;
				float dpTan2 = p.vel.x * tx + p.vel.y * ty;

				// Dot Product Normal
				float dpNorm1 = vel.x * nx + vel.y * ny;
				float dpNorm2 = p.vel.x * nx + p.vel.y * ny;

				// Conservation of momentum in 1D
				float m1 = (dpNorm1 * (m - p.m) + 2.0f * p.m * dpNorm2) / (m + p.m);
				float m2 = (dpNorm2 * (p.m - m) + 2.0f * m * dpNorm1) / (m + p.m);

				// Update ball velocities
				vel.x = tx * dpTan1 + nx * m1;
				vel.y = ty * dpTan1 + ny * m1;
				p.vel.x = tx * dpTan2 + nx * m2;
				p.vel.y = ty * dpTan2 + ny * m2;

				// Reduction of speed because of friction
				// Vector between Entities
//				Vector r = pos.sub(e.pos).n();
//				
//				Vector velC = vel.copy().n();
//				
//				//Dot Product between vel and r
//				float dotPrdc = velC.x * r.x + velC.y * r.y;
//				
//				if(dotPrdc < 0) {
//					dotPrdc *= -1;
//				}
//				System.out.println(dotPrdc);
//				
//				//Scale r by the dot product
//				if(Math.round(dotPrdc) != 0) {
//					r.scl(dotPrdc * 1000);
//				}
//				
//				//Apply r on the velocity
//				vel.add(r);
			}
		}
	}

	/**
	 * iterates every times the method gets executed one step further through the
	 * TrailArray and puts the coordinates of the entity in
	 */
	public void manageTrail() {
		trailIndex++;
		trailIndex = trailIndex % trail.length;
		trail[trailIndex][0] = pos.x;
		trail[trailIndex][1] = pos.y;
	}

}