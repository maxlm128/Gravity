
public class EntityManager {
	Entity[] e;
	int lastIndex;

	public EntityManager() {
		e = new Entity[50];
	}
/**
 * Inserts a Entity into the Entity-Array and returs whether it was successful
 * @param x Position x of the Entity
 * @param y Position y of the Entity
 * @param r	Radius of the Entity
 * @return boolean if inserting was successful
 */
	public boolean newEntity(float x, float y, float r) {
		for (int i = 0; i < e.length; i++) {
			int a = (i + lastIndex) % e.length;
			if (e[a] == null) {
				e[a] = new Entity(x, y, r);
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the Entity at the given Index
	 * @param i ,the Index
	 * @return returns the Entity
 	 */
	public Entity getEntity(int i) {
		return e[i];
	}

}
