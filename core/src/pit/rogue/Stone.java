package pit.rogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Stone {

	private Texture sprite;
	
	
	public Stone() {
		this.sprite = new Texture(Gdx.files.internal("Rock.png"));
	}
}
