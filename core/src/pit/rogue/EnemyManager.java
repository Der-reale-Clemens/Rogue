package pit.rogue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class EnemyManager {
	private static List<Enemy> enemys = new LinkedList<Enemy>();
	
	public void update(float delta, float playerX, float playerY) {
		Iterator<Enemy> iter = enemys.iterator();
		while(iter.hasNext()) {
			Enemy enemy = iter.next();
			enemy.update(delta, playerX, playerY);
			if(!enemy.isAlive()) {
				enemy.dispose();
				iter.remove();
			}
		}
	}
	
	public void addEnemy(EnemyTypes type, float x, float y) {
		enemys.add(new Enemy(type, x, y));
	}
	
	public static List<Enemy> getEnemys() {
		return enemys;
	}
	
	public void draw(final Rogue game) {
		game.batch.begin();
		for(Enemy enemy : enemys) {
			enemy.draw(game);
		}
		game.batch.end();
	}
	
	public void dispose() {
		for(Enemy enemy : enemys) {
			enemy.dispose();
		}
		enemys.clear();
	}
}
