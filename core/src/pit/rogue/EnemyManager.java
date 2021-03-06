package pit.rogue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class EnemyManager {
	private static List<Enemy> enemys = new LinkedList<Enemy>();
	
	private Sound itemAppearsSound = Gdx.audio.newSound(Gdx.files.internal("sounds/chest appears 2.wav"));
	
	private Sound enemyDiesSound = Gdx.audio.newSound(Gdx.files.internal("sounds/enemy dies.wav"));
	
	public void update(float delta, float playerX, float playerY, Room room) {
		Iterator<Enemy> iter = enemys.iterator();
		while(iter.hasNext()) {
			Enemy enemy = iter.next();
			enemy.update(delta, playerX, playerY);
			if(!enemy.isAlive()) {
				enemyDiesSound.play();
				if(Math.random() > 0.7) {
					room.addItem(enemy.getX(), enemy.getY(), 0);
				}
				if(EnemyManager.getEnemys().size() == 1) {
					if(Math.random() > 0.7) {
						itemAppearsSound.play();
						room.addItem(Config.WIDTH/2, Config.HEIGHT/2 - 32, 1);
					}
				}
				enemy.dispose();
				iter.remove();
			}
		}
	}
	
	public static void addEnemy(EnemyTypes type, float x, float y) {
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
