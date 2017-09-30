package pit.rogue;

public class Room {
	private Tile[][] room;
	private final int WIDTH = Config.NUM_CELLS_WIDTH;
	private final int HEIGHT = Config.NUM_CELLS_HEIGTH;
	private final int TEX_SIZE = Config.TEX_SIZE;
	
	public Room(int[][] map) {
		room = new Tile[WIDTH][HEIGHT];
		for(int i = 0; i < WIDTH; i++) {
			for(int j = 0; j < HEIGHT; j++) {
				switch(map[j][i]) {
					case 0:
						room[i][j] = new Tile(TileTypes.WallTile, i*TEX_SIZE, j*TEX_SIZE);
						break;
					case 1:
						room[i][j] = new Tile(TileTypes.GroundTile, i*TEX_SIZE, j*TEX_SIZE);
				}
			}
		}
	}
	
	
	public void draw(final Rogue game) {
		game.batch.disableBlending();
		game.batch.begin();
		for(int i = 0; i < HEIGHT; i++) {
			for(int j = 0; j < WIDTH; j++) {
				game.batch.draw(room[j][i].getTexture(), room[j][i].getX(), room[j][i].getY());
			}
		}
		game.batch.end();
		game.batch.enableBlending();
	}
}
