package pit.rogue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class EnemyManager {
	private static List<Enemy> enemys = new LinkedList<Enemy>();
	
	public void update(float delta) {
		Iterator<Enemy> iter = enemys.iterator();
		while(iter.hasNext()) {
			Enemy enemy = iter.next();
			enemy.update(delta);
			if(!enemy.isAlive()) {
				enemy.dispose();
				iter.remove();
			}
		}
	}
	
	public void addEnemy() {
		enemys.add(new Enemy(64,64));
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
