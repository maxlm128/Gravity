
//Controller
public class EntityManager {
	private Entity[] e;
	private int lastIndex;

	public EntityManager() {
		e = new Entity[50];
		newEntity(350, 300, 100);
		newEntity(300, 350, 100);
	}

	/**
	 * Inserts a Entity into the Entity-Array and returs whether it was successful
	 * @param x Position of the Entity
	 * @param y Position of the Entity
	 * @param r Radius of the Entity
	 * @return boolean if inserting was successful
	 */
	public boolean newEntity(float x, float y, float r) {
		for (int i = 0; i < e.length; i++) {
			int a = (i + lastIndex) % e.length;
			if (e[a] == null) {
				e[a] = new Entity(x, y, r, this);
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
