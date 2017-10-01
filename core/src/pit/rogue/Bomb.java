package pit.rogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Bomb {

	private float x;
	private float y;
	private int type;
	
	private Animation fuse;
	private Animation explode;
	
	private float counter;
	private float fuseTime = 200;
	private float explosionTime = 200;
	
	public Bomb(float x, float y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
		
	}
	
	public void update(float delta) {
		
	}
	
	public void draw(final Rogue game) {
		
	}
}
