
//Controller
public class EManager {
	private Entity[] e;
	//TODO: Do Mouse Gravity
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
		e = new Entity[500];
		newEntity(0, 0, 0, 0, 10000);
		newEntity(0, 100000, 1000, 0, 10000);
	}

	/**
	 * Inserts a Entity into the Entity-Array and returs whether it was successful
	 * 
	 * @param x Position of the Entity
	 * @param y Position of the Entity
	 * @param r Radius of the Entity
	 * @return boolean if inserting was successful
	 */
	public boolean newEntity(float x, float y, float vx, float vy, float r) {
		for (int i = 0; i < e.length; i++) {
			int a = (i + lastIndex) % e.length;
			if (e[a] == null) {
				e[a] = new Entity(x, y, r, vx, vy);
				return true;
			}
		}
		return false;
	}

	public void deleteAllEntities() {
		e = new Entity[50];
	}

	/**
	 * Executes the move-Method of every Entity STEPS times
	 */
	public void moveEntities(float dt) {
		for (int i = 0; i < Main.STEPS; i++) {
			for (Entity e : e) {
				if (e != null) {
					e.move(dt / Main.STEPS);
				}
			}
		}
		if(Main.TRAILS) {
			for (Entity e : e) {
				if (e != null) {
					e.manageTrail();
				}
			}
		}
	}

	/**
	 * Returns a Array of all Entities in the Game
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

}
