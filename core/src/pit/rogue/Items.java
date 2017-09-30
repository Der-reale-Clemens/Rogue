package pit.rogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Items {
	
	private Texture sprite;
	private final String coin = "coin3.png";
	private final String bomb = "bomb.png";
	private final String key = "Key.png";
	private int bombX;
	private int bombY;
	private int coinX;
	private int coinY;
	private int keyX;
	private int keyY;
	
	public Items() {
		
	}
	public void key() {
		this.sprite = new Texture(Gdx.files.internal(key));
	}
	
	public void bomb(int x, int y) {
		bombX=x;
		bombY=y;
		this.sprite = new Texture(Gdx.files.internal(bomb));
	}
	
	public void money(int x, int y) {
		coinX=x;
		coinY=y;
		this.sprite = new Texture(Gdx.files.internal(coin));
	}
	
	public int getBombX() {
		return bombX;
	}
	public int getBombY() {
		return bombY;
	}
	
	public int getCoinX() {
		return coinX;
	}
	public int getCoinY() {
		return coinY;
	}
	
	public int getKeyX() {
		return keyX;
	}
	public int getKeyY() {
		return keyY;
	}
}
