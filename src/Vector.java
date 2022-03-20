
//Controller, Model
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
	 * @return This Vector
	 */
	public Vector add(Vector vec2) {
		x = x + vec2.x;
		y = y + vec2.y;
		return this;
	}
	
	/**
	 * Multiplies the Vector by the given multiplier
	 * @param mul ,the float multiplier
	 * @return This Vector
	 */
	public Vector mul(double mul) {
		x = (float) (x * mul);
		y = (float) (y * mul);
		return this;
		
	}
	
	/**
	 * normalizes the Vector
	 * @return Vector
	 * @return This Vector
	 */
	public Vector n() {
		float l = l();
		x = x / l;
		y = y / l;
		return this;
	}
	
	/**
	 * Calculates the lenght of the vector
	 * @return A float of the length
	 */
	public float l() {
		return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	/**
	 * Resets the Vector to be x = 0 and y = 0
	 */
	public void r() {
		x = 0;
		y = 0;
	}
	
	/**
	 * Returns a copy of this vector
	 * @return A new Vector
	 */
	public Vector copy() {
		return new Vector(x,y);
	}
	/**
	 * Applies the average Vector of this and the second Vector
	 * @param vec2 ,the second Vector
	 */
	public void avg(Vector vec2) {
		float temp = (x + vec2.x) / 2;
		x = temp;
		vec2.x = temp;
		temp = (y + vec2.y) / 2;
		y = temp;
		vec2.y = temp;
	}
	
	public float diff(Vector vec2) {
		System.out.println((float) Math.sqrt(Math.pow(x - vec2.x, 2) + Math.sqrt(Math.pow(y - vec2.y, 2))));
		return (float) Math.sqrt(Math.pow(x - vec2.x, 2) + Math.sqrt(Math.pow(y - vec2.y, 2)));
	}
}

