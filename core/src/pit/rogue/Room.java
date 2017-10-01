package pit.rogue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Room {
	private Tile[][] room;
	private int[] enemys;
	private Texture overlay;
	private List<Item> items = new LinkedList<Item>();
	private List<Bomb> bombs = new LinkedList<Bomb>();
	private final int WIDTH = Config.NUM_CELLS_WIDTH;
	private final int HEIGHT = Config.NUM_CELLS_HEIGTH;
	private final int TEX_SIZE = Config.TEX_SIZE;
	
	private int warpTileX;
	private int warpTileY;
	
	public Room() {
		overlay = new Texture(Gdx.files.internal("RoomOverlay.png"));
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
					case 4:
						room[i][j] = new Tile(TileTypes.RockTile, i*TEX_SIZE, j*TEX_SIZE);
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
					case 2:
						EnemyManager.addEnemy(EnemyTypes.Enemy3, x, y);
				}
				
			}
		}
	}
	
	public void draw(final Rogue game) {
		game.batch.disableBlending();
		game.batch.begin();
		for(int i = 0; i < HEIGHT; i++) {
			for(int j = 0; j < WIDTH; j++) {
				if(room[j][i].getType() == TileTypes.RockTile) {
					game.batch.draw(room[j][i].getTexture(), room[j][i].getX(), room[j][i].getY(), TEX_SIZE, TEX_SIZE, 0, 0, TEX_SIZE, TEX_SIZE, false, true);
				} else {
					game.batch.draw(room[j][i].getTexture(), room[j][i].getX(), room[j][i].getY());
				}
			}
		}
		game.batch.end();
		game.batch.enableBlending();
		game.batch.begin();
		for(Item item : items) {
			item.draw(game);
		}
		Iterator<Bomb> iter = bombs.iterator();
		while(iter.hasNext()) {
			Bomb bomb = iter.next();
			bomb.update();
			bomb.draw(game);
			if(!bomb.isAlive()) {
				iter.remove();
			}
		}
		game.batch.draw(overlay, 0, 0);
		game.batch.end();
	}
	
	public Tile[][] getRoom() {
		return room;
	}
	
	public void addItem(float x, float y, int type) {
		items.add(new Item(x, y, type));
	}
	
	public void addBomb(float x, float y) {
		bombs.add(new Bomb(x, y));
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public int getWarpTileX() {
		return warpTileX;
	}
	
	public int getWarpTileY() {
		return warpTileY;
	}
	
	
}
