package pit.rogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Character {

	private final int TEX_SIZE=Config.TEX_SIZE;
	
	private Texture sprite;
	private final String tex = "";
	private Rectangle hitbox;
	private float x;
	private float y;
	private float speed=1f;

	
	public Character() {
		this.sprite = new Texture(Gdx.files.internal(tex));
		hitbox = new Rectangle(this.x, this.y, TEX_SIZE, TEX_SIZE);
	}
	
	public void update(float delta) {
		x += speed*(delta/100);
	}
	
	public void draw(final Rogue game) {
		game.batch.begin();
		game.batch.draw(sprite, x, y);
		game.batch.end();
	}
	
	public void movement(){
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) hitbox.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) hitbox.x += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) hitbox.y += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) hitbox.y -= 200 * Gdx.graphics.getDeltaTime();
		if(hitbox.x < 1 * TEX_SIZE) hitbox.x = 1 * TEX_SIZE;
		if(hitbox.x > 14 * TEX_SIZE) hitbox.x = 14 * TEX_SIZE;
		if(hitbox.y < 1 * TEX_SIZE) hitbox.y = 1 * TEX_SIZE;
		if(hitbox.y > 7 * TEX_SIZE)hitbox.y = 7 * TEX_SIZE;
	}
	
	public void attack() {
		
		
	}
}
