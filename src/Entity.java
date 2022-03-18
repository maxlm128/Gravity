
public class Entity {
	float mass;
	float radius;
	Vector pos;
	Vector vel;
	
	public Entity(float x, float y, float radius) {
		pos = new Vector(x,y);
		vel = new Vector(0,0);
		this.radius = radius;
		mass = (float) ((radius * radius) * Math.PI);
	}
	
	public Vector getPos() {
		return pos;
	}
	
	public void setPos(float x, float y) {
		pos.x = x;
		pos.y = y;
	}
	
	public void moveTo(Vector pos2) {
		
	}
}
