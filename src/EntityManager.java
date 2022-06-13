
//Controller
public class EntityManager {
	private Particle[] p;
	private Entity mouse;
	private final int PARTICLE_LIMIT = 5000;
	boolean mouseGravity;
	final protected float G = 6.6743E-11f;

	public EntityManager() {
		mouseGravity = false;
		mouse = new Entity(0, 0, 5E27f, this);
		p = new Particle[0];
//		newParticle(0, 10000000, 1, 50000, 0, 0);
//		newParticle(0, 6.371E6f * 2 + 100000, 5.972E24f, 6.371E6f, 0, 0);
	}

	/**
	 * Inserts a Particle into the Entity-Array and returs whether it was successful
	 * 
	 * @param x  Position of the Particle
	 * @param y  Position of the Particle
	 * @param m  Mass of the Particle
	 * @param r  Radius of the Particle
	 * @param vx Velocity of the Particle
	 * @param vy Velocity of the Particle
	 * @return boolean if inserting was successful
	 */
	public boolean newParticle(float x, float y, float m, float r, float vx, float vy) {
		if(p.length > PARTICLE_LIMIT) {
			return false;
		}
		Particle[] p = new Particle[this.p.length + 1];
		for (int i = 0; i < this.p.length; i++) {
			p[i] = this.p[i];
		}
		p[p.length - 1] = new Particle(x, y, m ,r, vx, vy, this);
		this.p = p;
		return true;
	}

	public void deleteAllEntities() {
		p = new Particle[0];
	}

	/**
	 * Executes the move-Method of every Entity STEPS times with the delta-time dt
	 * 
	 * @param dt ,a float
	 */
	public void moveParticles(float dt) {
		resolveCollision();
		for (int i = 0; i < Main.STEPS; i++) {
			for (Particle p : getParticles()) {
				if (p != null) {
					p.pos.add(p.vel.copy().scl(dt / Main.STEPS));
				}
			}
		}
		calcGravity(dt);
	}

	public void calcGravity(float dt) {
		for (int i = 0; i < p.length; i++) {
			for (int j = i + 1; j < p.length; j++) {
				if (p[i] != null && p[j] != null) {
					Vector dv = p[i].pos.sub(p[j].pos);
					float l = dv.lsq();
					dv.n().scl(G / l).scl(dt);
					p[i].vel.add(dv.copy().scl(p[j].m));
					p[j].vel.add(dv.scl(p[i].m * -1));
				}
			}
		}
	}

	public void resolveCollision() {
		for (int i = 0; i < p.length; i++) {
			for (int j = i + 1; j < p.length; j++) {
				if (p[i] != null && p[j] != null && p[i].pos.sub(p[j].pos).l() <= p[i].r + p[j].r) {
					if (p[i].pos.x == p[j].pos.x && p[i].pos.y == p[j].pos.y) {
						Vector random = new Vector(((float) Math.random() - 0.5f) * 1000, ((float) Math.random() - 0.5f) * 1000);
						p[i].pos.add(random);
					}
					Vector d = p[i].pos.sub(p[j].pos).n().scl(p[j].r + p[i].r - p[i].pos.sub(p[j].pos).l()).scl(0.5f);
					p[j].pos.add(d);
					p[i].pos.add(d.scl(-1));
					// Soft Collision
					// Distance between balls
					float fDistance = p[i].pos.sub(p[j].pos).l();

					// Normal
					float nx = (p[j].pos.x - p[i].pos.x) / fDistance;
					float ny = (p[j].pos.y - p[i].pos.y) / fDistance;

					// Tangent
					float tx = -ny;
					float ty = nx;

					// Dot Product Tangent
					float dpTan1 = p[i].vel.x * tx + p[i].vel.y * ty;
					float dpTan2 = p[j].vel.x * tx + p[j].vel.y * ty;

					// Dot Product Normal
					float dpNorm1 = p[i].vel.x * nx + p[i].vel.y * ny;
					float dpNorm2 = p[j].vel.x * nx + p[j].vel.y * ny;

					// Conservation of momentum in 1D
					float m1 = (dpNorm1 * (p[i].m - p[j].m) + 2.0f * p[j].m * dpNorm2) / (p[i].m + p[j].m);
					float m2 = (dpNorm2 * (p[j].m - p[i].m) + 2.0f * p[i].m * dpNorm1) / (p[i].m + p[j].m);

					// Update ball velocities
					p[i].vel.x = tx * dpTan1 + nx * m1;
					p[i].vel.y = ty * dpTan1 + ny * m1;
					p[j].vel.x = tx * dpTan2 + nx * m2;
					p[j].vel.y = ty * dpTan2 + ny * m2;
				}
			}
		}
	}

	/**
	 * Returns an Array of all Particles in the Game
	 * 
	 * @return Entity-Array
	 */
	public Particle[] getParticles() {
		Particle[] p = new Particle[getNumberP()];
		for (int i = 0; i < p.length; i++) {
			p[i] = this.p[i];
		}
		return p;
	}

	/**
	 * Returns an Array of all Entities in the Game
	 * 
	 * @return Particle-Array
	 */
	public Entity[] getEntities() {
		int l = getNumberP();
		int m = 0;
		if (mouseGravity) {
			m++;
		}
		Entity[] e = new Entity[l + m];
		;
		for (int i = 0; i < l; i++) {
			e[i] = this.p[i];
		}
		if (mouseGravity) {
			e[e.length - 1] = mouse;
		}
		return e;
	}

	/**
	 * Returns the Number of total Particles in the Game
	 * 
	 * @return Integer
	 */
	public int getNumberP() {
		for (int i = 0; i < p.length; i++) {
			if (p[i] == null) {
				return i;
			}
		}
		return p.length;
	}

	/**
	 * Updates the position of the mouse and therefore the source of gravity using a
	 * position vector
	 * 
	 * @param pos, a Vector
	 */
	public void updateMousePos(Vector pos) {
		mouse.pos = pos;
	}

}
