
public class Vector {
	float x,y;
	
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Calculates the directional Vector from this Vector to the Parameter Vector
	 * @param vec2 ,second Vector
	 * @return directional Vector
	 */
	public Vector sub(Vector vec2) {
		return new Vector(vec2.x - x, vec2.y - y);
	}
	/**
	 * Moves this Vector by the directional Vector in the parameter
	 * @param vec2, directional Vector
	 */
	public void add(Vector vec2) {
		x = x + vec2.x;
		y = y + vec2.y;
	}
	
	/**
	 * Normalizes the Vector
	 */
	public void n() {
		float l = l();
		x = x / l;
		y = y / l;
	}
	/**
	 * Calculates the lenght of the vector
	 * @return A float of the length
	 */
	public float l() {
		return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
}

