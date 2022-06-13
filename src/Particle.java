
//Controller, Model
public class Particle extends Entity {
	float r;
	Vector vel;

	public Particle(float x, float y, float m, float r, float vx, float vy, EntityManager eM) {
		super(x, y, m, eM);
		vel = new Vector(vx, vy);
		this.r = r;
	}
}
