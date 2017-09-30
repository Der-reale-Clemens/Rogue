package pit.rogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {
	
	private final float TEX_SIZE = Config.TEX_SIZE;
	
	private Rectangle hitbox;
	private String tex = "Charakter.png";
	private float speed = 5f;
	private float x;
	private float y;
	private float health = 10;
	private Texture sprite;
	private boolean isAlive = true;
	
	public Enemy(float x, float y) {
		this.x = x;
		this.y = y;
		sprite = new Texture(Gdx.files.internal(tex));
		hitbox = new Rectangle(this.x, this.y, TEX_SIZE, TEX_SIZE);
	}
	
	public void update(float delta) {
		x += speed * (delta/100);
		
		if(x <= TEX_SIZE)
			x = 65;
		if(x >= 13*TEX_SIZE)
			x = 13*TEX_SIZE - 1;
		if(y <= TEX_SIZE)
			y = TEX_SIZE + 1;
		if(y >= 7*TEX_SIZE)
			y = TEX_SIZE - 1;
	
		
		hitbox.setPosition(x, y);
	}
	
	public void draw(final Rogue game) {
		game.batch.draw(sprite, x, y);
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void dispose() {
		sprite.dispose();
	}
}
