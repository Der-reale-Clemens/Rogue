package pit.rogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

public class Item {
	
	private Texture sprite;
	private final String coin = "Coin.png";
	private final String bomb = "bomb.png";
	private final String key = "Key.png";
	private Circle hitbox;
	private int x;
	private int y;
	
	public Item(int x, int y, int type) {
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
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
