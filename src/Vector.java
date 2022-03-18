
public class Vector {
	float x,y;
	
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Calculates the directional Vector from this Vector to another Vector
	 * @param vec2 second Vector
	 * @return directional Vector
	 */
	public Vector subVector(Vector vec2) {
		return new Vector(vec2.x - x, vec2.y - y);
	}
	
	/**
	 * Normalizes the Vector
	 */
	public void n() {
		float l = length();
		x = x / l;
		y = y / l;
	}
	/**
	 * Calculates the lenght of the vector
	 * @return A float of the length
	 */
	public float length() {
		return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
}

