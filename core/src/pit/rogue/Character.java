package pit.rogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Character {

	private final int TEX_SIZE=Config.TEX_SIZE;
	
	private Texture sprite;
	private final String tex = "Charakter.png";
	private Rectangle hitbox;
	private float x = 100;
	private float y = 100;
	private float speed=25f;
	private int dx = 1;
	private int dy = 1;
	private final String bullet ="Bullet.png";

	
	public Character() {
		this.sprite = new Texture(Gdx.files.internal(tex));
		hitbox = new Rectangle(this.x, this.y, TEX_SIZE, TEX_SIZE);
	}
	
	public void update(float delta) {
		movement();
		x += speed*(delta/100)*dx;
		y += speed*(delta/100)*dy;
	}
	
	public void draw(final Rogue game) {
		game.batch.begin();
		game.batch.draw(sprite, x, y);
		game.batch.end();
	}
	
	public void movement(){
		dx=0;
		dy=0;
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) dx = -1;
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) dx = 1;
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) dy = -1;
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) dy = 1;
		if(x < 1 * TEX_SIZE) x = 1 * TEX_SIZE;
		if(x > 13 * TEX_SIZE) x = 13 * TEX_SIZE;
		if(y < 1 * TEX_SIZE) y = 1 * TEX_SIZE;
		if(y > 7 * TEX_SIZE) y = 7 * TEX_SIZE;
	}
	
	public void attack() {
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) ;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
}
