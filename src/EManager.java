
//Controller
public class EManager {
	private Entity[] e;
	private int lastIndex;
	private static EManager instance;

	public static EManager getI() {
		if (instance == null) {
			instance = new EManager();
		}
		return instance;
	}

	private EManager() {
		e = new Entity[50];
		newEntity(250000, 250000, 125000f, 0, 10000);
		newEntity(250000, 100000, -125000f, 0, 10000);
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
				e[a] = new Entity(x, y, r, vx / (float) Main.FPS, vy / (float) Main.FPS);
				return true;
			}
		}
		return false;
	}

	/**
	 * Executes the move-Method of every Entity
	 */
	public void moveEntities() {
		for (Entity e : e) {
			if (e != null) {
				e.move();
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
