package pit.rogue;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Room {
	private Tile[][] room;
	private int[] enemys;
	private List<Item> items = new LinkedList<Item>();
	private final int WIDTH = Config.NUM_CELLS_WIDTH;
	private final int HEIGHT = Config.NUM_CELLS_HEIGTH;
	private final int TEX_SIZE = Config.TEX_SIZE;;
	
	private int warpTileX;
	private int warpTileY;
	
	public Room() {
		
	}
	
	public void createRoom(int[][] map, int[] enemys) {
		room = new Tile[WIDTH][HEIGHT];
		this.enemys = enemys;
		for(int i = 0; i < WIDTH; i++) {
			for(int j = 0; j < HEIGHT; j++) {
				switch(map[j][i]) {
					case 0:
						room[i][j] = new Tile(TileTypes.WallTile, i*TEX_SIZE, j*TEX_SIZE);
						break;
					case 1:
						room[i][j] = new Tile(TileTypes.GroundTile, i*TEX_SIZE, j*TEX_SIZE);

						break;
					case 2:
						room[i][j] = new Tile(TileTypes.WarpTile, i*TEX_SIZE, j*TEX_SIZE);
						warpTileX = i*TEX_SIZE;
						warpTileY = j*TEX_SIZE;
						break;
					case 3:
						room[i][j] = new Tile(TileTypes.WarpTileOff, i*TEX_SIZE, j*TEX_SIZE);
						break;
				}
			}
		}
	}
	
	public void spawnEnemys() {
		for(int i = 0; i < enemys.length; i++) {
			for(int j = 0; j < enemys[i]; j++) {
				Random rng = new Random();
				int x = rng.nextInt(Config.WIDTH-TEX_SIZE);
				while(x < TEX_SIZE)
					x = rng.nextInt(Config.WIDTH-TEX_SIZE);
				int y = rng.nextInt(Config.WIDTH-TEX_SIZE);
				while(y < TEX_SIZE)
					y = rng.nextInt(Config.WIDTH-TEX_SIZE);
				switch(i) {
					case 0:
						EnemyManager.addEnemy(EnemyTypes.Enemy1, x, y);
						break;
					case 1:
						EnemyManager.addEnemy(EnemyTypes.Enemy2, x, y);
						break;
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
	
	public Tile[][] getRoom() {
		return room;
	}
	
	public int getWarpTileX() {
		return warpTileX;
	}
	
	public int getWarpTileY() {
		return warpTileY;
	}
}
