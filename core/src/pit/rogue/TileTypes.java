package pit.rogue;

public enum TileTypes {
	WallTile(false, "Wall.png"),
	GroundTile(true, "Ground2.png"),
	WarpTile(true, "WarpTile.png"),
	WarpTileOff(true, "WarpTileOff.png");
	
	boolean walkable;
	String textureName;
	
	TileTypes(boolean walkable, String textureName) {
		this.walkable = walkable;
		this.textureName = textureName;
	}
}
