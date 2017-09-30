package pit.rogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

public class Item {
	
	private Texture sprite;
	private int type;
	private final String coin = "Coin.png";
	private final String bomb = "bomb.png";
	private final String key = "Key.png";
	private Circle hitbox;
	private float x;
	private float y;
	
	public Item(float x, float y, int type) {
		this.x = x;
		this.y = y;
		hitbox = new Circle(this.x, this.y, Config.TEX_SIZE/2);
		switch(type) {
			case 0: 
				sprite = new Texture(Gdx.files.internal(coin));
				break;
			case 1:
				sprite = new Texture(Gdx.files.internal(key));
				break;
			case 2:
				sprite = new Texture(Gdx.files.internal(bomb));
				break;
		}
		this.type = type;
	}
	
	public void draw(final Rogue game) {
		switch(type) {
		case 0:
			game.batch.draw(sprite, x, y, Config.TEX_SIZE/2, Config.TEX_SIZE/2, 0, 0, Config.TEX_SIZE, Config.TEX_SIZE, false, true);
			break;
		case 1:
			game.batch.draw(sprite, x, y, Config.TEX_SIZE/2 + 16, Config.TEX_SIZE/2 + 16, 0, 0, Config.TEX_SIZE, Config.TEX_SIZE, false, true);

		}
	}
	
	public Circle getHitbox() {
		return hitbox;
	}
	
	public int getType() {
		return type;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void dispose() {
		sprite.dispose();
	}
}
