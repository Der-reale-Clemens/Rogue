package pit.rogue;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

public class Character {

	private final int TEX_SIZE = Config.TEX_SIZE;
	
	private Texture sprite;
	private final String tex = "Issac1.png";
	private Circle hitbox;
	private float x;
	private float y;
	private float speed=25f;
	private float dx = 1;
	private float dy = 1;
	private int lastDx;
	private int lastDy;
	private float health = 50;
	private int amountOfBombs=0;
	private int amountOfCoins=0;
	private int amountOfKeys=0;

	private boolean isAlive = true;

	private Room room;
	private Tile t1;
	private Tile t2;
	private Tile t3;
	private Tile t4;
	private Tile t5;
	private Tile t6;
	private Tile t7;
	private Tile t8;
	private TileTypes type;
	private boolean walkable;

	private Sound teleportSound;
	private Sound hurtSound;
	private Sound layBombSound;
	private Sound lowHPSound;
	private Sound deathSound;
	private Sound keySound;
	private Sound coinSound;

	private float cooldown = 300;
	private float cooldownCounter;
	
	private float invincibilty = 500;
	private float invincibiltyCounter;
	
	public Character(float pX, float pY) {
		this.sprite = new Texture(Gdx.files.internal(tex));
		hitbox = new Circle(this.x, this.y, TEX_SIZE/2);
		x=pX;
		y=pY;
		teleportSound = Gdx.audio.newSound(Gdx.files.internal("sounds/teleport.wav"));
		hurtSound = Gdx.audio.newSound(Gdx.files.internal("sounds/link hurt.wav"));
		layBombSound = Gdx.audio.newSound(Gdx.files.internal("sounds/lay bomb.wav"));
		lowHPSound = Gdx.audio.newSound(Gdx.files.internal("sounds/low hp.wav"));
		deathSound = Gdx.audio.newSound(Gdx.files.internal("sounds/link dies.wav"));
		keySound = Gdx.audio.newSound(Gdx.files.internal("sounds/key 1.wav"));
		coinSound = Gdx.audio.newSound(Gdx.files.internal("sounds/rupee.wav"));
	}
	
	public void update(float delta, Room room) {
		movement();
		warp();
		if(cooldownCounter >= cooldown) {
			cooldownCounter = 0;
			attack();
		} else {
			cooldownCounter += delta;
		}

		getWalkable(room);

		x += speed*(delta/100)*dx;
		y += speed*(delta/100)*dy;
		hitbox.setPosition(x, y);
		
		if(invincibiltyCounter >= invincibilty) {
			Iterator<Enemy> iter = EnemyManager.getEnemys().iterator();
			while(iter.hasNext()) {
				Enemy enemy = iter.next();
				if(hitbox.overlaps(enemy.getHitbox())) {
					health -= enemy.getDamage();
					invincibiltyCounter = 0;
					hurtSound.play();
					if(health <= 15) {
						lowHPSound.stop();
						lowHPSound.loop();
					}
				}
			}
		}
		invincibiltyCounter += delta;
		
		Iterator<Item> iter = room.getItems().iterator();
		while(iter.hasNext()) {
			Item item = iter.next();
			if(hitbox.overlaps(item.getHitbox())) {
				switch(item.getType()) {
					case 0:
						coinSound.play();
						amountOfCoins++;
						break;
					case 1:
						keySound.play();
						amountOfKeys++;
						break;
					case 2: 
						amountOfBombs++;
						break;
				}
				item.dispose();
				iter.remove();
			}
		}
		
		if(dx==1&&dy==0)this.sprite = new Texture(Gdx.files.internal("Issac2.png"));
		if(dx==-1&&dy==0)this.sprite = new Texture(Gdx.files.internal("Issac4.png"));
		if(dy==1&&dx==0)this.sprite = new Texture(Gdx.files.internal("Issac1.png"));
		if(dy==-1&&dx==0)this.sprite = new Texture(Gdx.files.internal("Issac3.png"));
		
		if(health <= 0) {
			isAlive = false;
			deathSound.play();
		}
	}
	
	public void draw(final Rogue game) {
		game.batch.begin();
		game.batch.draw(sprite, x, y, TEX_SIZE, TEX_SIZE, 0, 0, TEX_SIZE, TEX_SIZE, false, true);
		game.batch.end();
	}
	
	public void getWalkable(Room room) {
		Tile t1=room.getRoom()[(int) ((x+69)/TEX_SIZE)][(int) y/TEX_SIZE];
		Tile t2=room.getRoom()[(int) ((x-5)/TEX_SIZE)][(int) y/TEX_SIZE];
		Tile t3=room.getRoom()[(int) x/TEX_SIZE][(int) ((y+69)/TEX_SIZE)];
		Tile t4=room.getRoom()[(int) x/TEX_SIZE][(int) ((y-5)/TEX_SIZE)];
		Tile t5=room.getRoom()[(int) (x+69)/TEX_SIZE][(int) ((y-5)/TEX_SIZE)];
		Tile t6=room.getRoom()[(int) (x+69)/TEX_SIZE][(int) ((y+69)/TEX_SIZE)];
		Tile t7=room.getRoom()[(int) (x-5)/TEX_SIZE][(int) ((y-5)/TEX_SIZE)];
		Tile t8=room.getRoom()[(int) (x-5)/TEX_SIZE][(int) ((y+69)/TEX_SIZE)];
		
		if(t1.getType()==TileTypes.RockTile) {
			dx = (float) -0.035;
		}
		if(t2.getType()==TileTypes.RockTile) {
			dx = (float) 0.035;
		}
		if(t3.getType()==TileTypes.RockTile) {
			dy = (float) -0.035;
		}
		if(t4.getType()==TileTypes.RockTile) {
			dy = (float) 0.035;
		}
		if(t5.getType()==TileTypes.RockTile) {
			dx = (float) -0.035;
			dy = (float) 0.035;
		}
		if(t6.getType()==TileTypes.RockTile) {
			dx = (float) -0.035;
			dy = (float) -0.035;
		}
		if(t7.getType()==TileTypes.RockTile) {
			dx = (float) 0.035;
			dy = (float) 0.035;
		}
		if(t8.getType()==TileTypes.RockTile) {
			dx = (float) 0.035;
			dy = (float) -0.035;
		}
	}
	
	public void movement(){
		dx=0;
		dy=0;
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			dx = -1;
			lastDx = -1;
			lastDy = 0;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			dx = 1;
			lastDx = 1;
			lastDy = 0;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			dy = -1;
			lastDy = -1;
			lastDx = 0;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			dy = 1;
			lastDy = 1;
			lastDx = 0;
		}
		if(x < 1 * TEX_SIZE) x = 1 * TEX_SIZE;
		if(x > 13 * TEX_SIZE) x = 13 * TEX_SIZE;
		if(y < 1 * TEX_SIZE) y = 1 * TEX_SIZE;
		if(y > 7 * TEX_SIZE) y = 7 * TEX_SIZE;
		
		
	}
	
	public void attack() {
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
			BulletManager.addBullet(this);
	}
	
	public void warp() {
		if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			if(EnemyManager.getEnemys().size() == 0) {
				float wtx = Map.getRooms()[Map.getActiveRoom()].getWarpTileX();
				float wty = Map.getRooms()[Map.getActiveRoom()].getWarpTileY();
				if(Math.abs((wtx - x)) <= 15) {
					if(Math.abs(wty - y) <= 15) {
						teleportSound.play();
						Map.moveToNextRoom(this);
					}
				}
			}
		}
	}
	
	public void bomb() {
		//TODO
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getLastDX() {
		return lastDx;
	}
	
	public float getHealth() {
		return health;
	}
	
	public float getLastDY() {
		return lastDy;
	}
	
	public Circle returnHitbox(){
		return hitbox;
	}
	
	public int getAmountOfBombs() {
		return amountOfBombs;
	}

	public int getAmountOfCoins() {
		return amountOfCoins;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public int getAmountOfKeys() {
		return amountOfKeys;
	}
	
	
}
