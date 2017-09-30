package pit.rogue;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Bullet {
	private final int BULLET_SIZE=Config.BULLET_SIZE;
	
	private Texture sprite;
	private Circle hitboxBullet;
	private final String bullet ="Bullet.png";
	private float x;
	private float y; 
	private float speed=50f;
	private Character player;
	private float dx;
	private float dy;
	private boolean isAlive = true;
	private boolean flipX;
	private boolean flipY;
	
	
	public Bullet(Character player) {
		this.sprite= new Texture(Gdx.files.internal(bullet));
		hitboxBullet= new Circle(this.x, this.y, BULLET_SIZE/2);
		x=player.getX();
		y=player.getY();
		dx=player.getLastDX();
		dy=player.getLastDY();
	}
	
	public void draw(final Rogue game) {
		game.batch.draw(sprite, x, y);
	}
	
	
	public void update(float delta) {
		x += speed*(delta/100)*dx;
		y += speed*(delta/100)*dy;
	}
	
	public Circle returnHitbox() {
		return hitboxBullet;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void dispose() {
		sprite.dispose();
	}
}
