
//Controller
public class EManager {
	private Particle[] p;
	private Entity mouse;
	private final int PARTICLE_LIMIT = 1000;
	boolean mouseGravity;
	private int lastIndex;

	public EManager() {
		mouseGravity = false;
		mouse = new Entity(0, 0, 5E25f, this);
		p = new Particle[PARTICLE_LIMIT];
		// Density of a Neutron Star
		newParticle(0, 0, (float) (Math.PI * Math.pow(10000000, 2) * 2E14), 1000000, 0, 0);
		newParticle(0, 10000000, (float) (Math.PI * Math.pow(10000000, 2) * 2E14), 1000000, 0, 0);
	}

	/**
	 * Inserts a Entity into the Entity-Array and returs whether it was successful
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
		for (int i = 0; i < p.length; i++) {
			int a = (i + lastIndex) % p.length;
			if (p[a] == null) {
				p[a] = new Particle(x, y, m, r, vx, vy, this);
				return true;
			}
		}
		return false;
	}

	public void deleteAllEntities() {
		p = new Particle[PARTICLE_LIMIT];
	}

	/**
	 * Executes the move-Method of every Entity STEPS times with the delta-time dt
	 * 
	 * @param dt ,a float
	 */
	public void moveParticles(float dt) {
		for (int i = 0; i < Main.STEPS; i++) {
			for (Particle p : getParticles()) {
				if (p != null) {
					p.move(dt / Main.STEPS);
				}
			}
		}
		if (Main.TRAILS) {
			for (Particle p : getParticles()) {
				if (this.p != null) {
					p.manageTrail();
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
		Entity[] e = new Entity[l + m];;
		for (int i = 0; i < l; i++) {
			e[i] = this.p[i];
		}
		if(mouseGravity) {
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
	 * Updates the position of the mouse and therefore the source of gravity using a position vector
	 * @param pos, a Vector
	 */
	public void updateMousePos(Vector pos) {
		mouse.pos = pos;
	}

}
