package pit.rogue;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

public class Bullet {
	private final int BULLET_SIZE=Config.BULLET_SIZE;
	
	private Texture sprite;
	private Circle hitboxBullet;
	private final String bullet ="BulletNormal.png";
	private float x;
	private float y; 
	private float speed=50f;
	private Character player;
	private float dx;
	private float dy;
	private boolean isAlive = true;
	private float damage = 5f;
	
	
	public Bullet(Character player) {
		this.sprite= new Texture(Gdx.files.internal(bullet));
		hitboxBullet= new Circle(this.x, this.y, BULLET_SIZE/2);
		x=player.getX() + BULLET_SIZE/2;
		y=player.getY() + BULLET_SIZE/2;
		dx=player.getLastDX();
		dy=player.getLastDY();
	}
	
	public void draw(final Rogue game) {
		game.batch.draw(sprite, x, y);
	}
	
	
	public void update(float delta) {
		x += speed*(delta/100)*dx;
		y += speed*(delta/100)*dy;
		
		Iterator<Enemy> iter = EnemyManager.getEnemys().iterator();
		while(iter.hasNext()) {
			Enemy enemy = iter.next();
			if(hitboxBullet.overlaps(enemy.getHitbox())) {
				enemy.damage(damage);
				isAlive = false;
				return;
			}
		}
		hitboxBullet.setPosition(x, y);
		
		if(x <= 64 || x >= 13*64 + 32|| y <= 64 || y >= 7*64 +32)
			isAlive = false;
	}
	
	public Circle getHitbox() {
		return hitboxBullet;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void dispose() {
		sprite.dispose();
	}
}
