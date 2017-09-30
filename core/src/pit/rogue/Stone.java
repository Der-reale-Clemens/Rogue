package pit.rogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Stone {

	private Texture sprite;
	private int stoneX;
	private int stoneY;
	
	public Stone(int x, int y) {
		stoneX=x;
		stoneY=y;
		this.sprite = new Texture(Gdx.files.internal("Rock.png"));
	}
	
}
