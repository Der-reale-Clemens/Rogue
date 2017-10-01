package pit.rogue;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;

public class Bomb {

	private float x;
	private float y;
	private float damage = 15f;
	
	private Animation<TextureRegion> fuse;
	private Animation<TextureRegion> explode;
	
	private Texture fuseSheet;
	private Texture explosionSheet;
	
	private Sound bombExplodeSound;
	
	private float counter;
	private float fuseTime = 1;
	private float explosionTime = 0.8f;
	
	private boolean isExploding;
	private boolean isAlive = true;
	
	public Bomb(float x, float y) {
		this.x = x;
		this.y = y;
		bombExplodeSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bomb explode.wav"));
		
		fuseSheet = new Texture(Gdx.files.internal("bombSheet.png"));
		explosionSheet = new Texture(Gdx.files.internal("explode.png"));
		
		//Fuse
		TextureRegion[][] tmp = TextureRegion.split(fuseSheet, 
				fuseSheet.getWidth() / 2,
				fuseSheet.getHeight() / 1);
		
		TextureRegion[] frames = new TextureRegion[2 * 1];
		int index = 0;
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 2; j++) {
				frames[index++] = tmp[i][j];
			}
		}
		fuse = new Animation<TextureRegion>(0.2f, frames);
		
		//Explosion
		tmp = TextureRegion.split(explosionSheet, 
				explosionSheet.getWidth() / 8,
				explosionSheet.getHeight() / 1);
		
		frames = new TextureRegion[8 * 1];
		index = 0;
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 8; j++) {
				frames[index++] = tmp[i][j];
			}
		}
		explode = new Animation<TextureRegion>(0.12f, frames);
		
		
	}
	
	public void update() {
		System.out.println(counter);
		if(counter >= fuseTime && !isExploding) {
			isExploding = true;
			counter = 0;
			bombExplodeSound.play();
			Circle hitbox = new Circle(x,y, 172/2);
			Iterator<Enemy> iter = EnemyManager.getEnemys().iterator();
			while(iter.hasNext()) {
				Enemy enemy = iter.next();
				if(enemy.getHitbox().overlaps(hitbox)) {
					enemy.damage(damage);
				}
			}
		}
		if(isExploding && counter >= explosionTime) {
			isAlive = false;
		}
		counter += Gdx.graphics.getDeltaTime();
	}
	
	public void draw(final Rogue game) {	
		if(!isExploding) {
			TextureRegion currentFrame = fuse.getKeyFrame(counter, true);
			game.batch.draw(currentFrame, x, y, 16, 16, 32, 32, 1, 1, 180);
		} else {
			TextureRegion currentFrame = explode.getKeyFrame(counter, true);
			//game.batch.draw(currentFrame, x, y);
			game.batch.draw(currentFrame, x, y, 32, 32, 128, 128, 1, 1, 180);
		}
	}
	
	public boolean isAlive() {
		return isAlive;
	}
}
