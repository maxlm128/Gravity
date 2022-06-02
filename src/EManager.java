
//Controller
public class EManager {
	private Entity[] e;
	private Entity mouse;
	private int lastIndex;
	private static EManager instance;

	public static EManager getI() {
		if (instance == null) {
			instance = new EManager();
		}
		return instance;
	}

	private EManager() {
		e = new Entity[5000];
		//Density of a Neutron Star
		newParticle(0, 0,(float) (Math.PI * Math.pow(10000, 2) * 2E14), 10000, 0, 0);
		newParticle(0, 100000,(float) (Math.PI * Math.pow(10000, 2) * 2E14), 10000, 0, 0);
	}

	/**
	 * Inserts a Entity into the Entity-Array and returs whether it was successful
	 * 
	 * @param x Position of the Entity
	 * @param y Position of the Entity
	 * @param r Radius of the Entity
	 * @return boolean if inserting was successful
	 */
	public boolean newParticle(float x, float y, float m, float r, float vx, float vy) {
		for (int i = 0; i < e.length; i++) {
			int a = (i + lastIndex) % e.length;
			if (e[a] == null) {
				e[a] = new Particle(x, y, m, r, vx, vy);
				return true;
			}
		}
		return false;
	}

	public void deleteAllEntities() {
		e = new Entity[50];
	}

	/**
	 * Executes the move-Method of every Entity STEPS times with the delta-time dt
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
		if(Main.TRAILS) {
			for (Particle p : getParticles()) {
				if (e != null) {
					p.manageTrail();
				}
			}
		}
	}

	/**
	 * Returns an Array of all Entities in the Game
	 * 
	 * @return Entity-Array
	 */
	public Entity[] getEntities() {
		Entity[] e = new Entity[getNumberE()];
		for (int i = 0; i < e.length; i++) {
			e[i] = this.e[i];
		}
		return e;
	}
	
	/**
	 * Returns an Array of all Particles in the Game
	 * 
	 * @return Particle-Array
	 */
	public Particle[] getParticles() {
		int j = 0;
		Particle[] p = new Particle[getNumberP()];
		for (int i = 0; i < e.length; i++) {
			if(e[i] instanceof Particle && j < p.length) {
				p[j] = (Particle) this.e[i];
				j++;
			}
		}
		return p;
	}

	/**
	 * Returns the Number of total Entities in the Game
	 * 
	 * @return Integer
	 */
	public int getNumberE() {
		for (int i = 0; i < e.length; i++) {
			if (e[i] == null) {
				return i;
			}
		}
		return e.length;
	}
	
	/**
	 * Returns the Number of total Particles in the Game
	 * 
	 * @return Integer
	 */
	public int getNumberP() {
		int p = 0;
		for (int i = 0; i < e.length; i++) {
			if (e[i] instanceof Particle) {
				p++;
			} else if(e[i] == null) {
				return p;
			}
		}
		return p;
	}

}
