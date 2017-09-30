package pit.rogue;

public enum TileTypes {
	WallTile(false, "Wall.png"),
	GroundTile(true, "Ground.png");
	
	
	boolean walkable;
	String textureName;
	
	TileTypes(boolean walkable, String textureName) {
		this.walkable = walkable;
		this.textureName = textureName;
	}
}
