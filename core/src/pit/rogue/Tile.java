package pit.rogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Tile {
	private TileTypes type;
	private Texture sprite;
	private final int X;
	private final int Y;
	private int roomNr;
	
	public Tile(TileTypes type, int x, int y) {
		this.X = x;
		this.Y = y;
		this.sprite = new Texture(Gdx.files.internal(type.textureName));
		this.type = type;
	}
	
	public Tile(int x, int y, int roomNr) {
		this.X = x;
		this.Y = y;
		this.sprite = new Texture(Gdx.files.internal("WarpTile.png"));
		this.setRoomNr(roomNr);
	}
	
	public void dispose() {
		sprite.dispose();
	}
	
	public int getX() {
		return X;
	}
	
	public int getY() {
		return Y;
	}
	
	public Texture getTexture() {
		return sprite;
	}

	public int getRoomNr() {
		return roomNr;
	}

	public void setRoomNr(int roomNr) {
		this.roomNr = roomNr;
	}
}

