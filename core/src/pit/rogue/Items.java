package pit.rogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Items {
	
	private Texture sprite;
	private final String coin = "coin3.png";
	private final String bomb = "bomb.png";
	private final String key = "Key.png";
	
	public Items() {
		this.sprite = new Texture(Gdx.files.internal(coin));
		this.sprite = new Texture(Gdx.files.internal(bomb));
		
	}
	public void Key() {
		this.sprite = new Texture(Gdx.files.internal(key));
	}
}
