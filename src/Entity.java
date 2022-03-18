
public class Entity {
	float m;
	float r;
	Vector pos;
	Vector vel;
	
	public Entity(float x, float y, float r) {
		pos = new Vector(x,y);
		vel = new Vector(1,1);
		this.r = r;
		m = (float) ((r * r) * Math.PI);
	}
	
	/**
	 * Moves the Entity by the vel-Vector
	 */
	public void move() {
		pos.add(vel);
	}
	/**
	 * Applies the Gravity and Collision-Detection to the Entity
	 */
	public void applyForces() {
		
	}
}
