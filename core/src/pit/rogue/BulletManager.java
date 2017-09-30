package pit.rogue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BulletManager {
	private static List<Bullet> bullets = new LinkedList<Bullet>();
	
	public void update(float delta) {
		Iterator<Bullet> iter = bullets.iterator();
		while(iter.hasNext()) {
			Bullet bullet = iter.next();
			bullet.update(delta);
			if(!bullet.isAlive()) {
				bullet.dispose();
				iter.remove();
			}
		}
	}
	
	public static void addBullet(Character player) {
		bullets.add(new Bullet(player));
	}
	
	public List<Bullet> getBullets() {
		return bullets;
	}
	
	public void draw(final Rogue game) {
		game.batch.begin();
		for(Bullet bullet : bullets) {
			bullet.draw(game);
		}
		game.batch.end();
	}
	
	public void dispose() {
		for(Bullet bullet : bullets) {
			bullet.dispose();
		}
		bullets.clear();
	}
}
