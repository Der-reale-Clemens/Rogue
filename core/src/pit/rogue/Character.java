package pit.rogue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Character {

	private final int TEX_SIZE = Config.TEX_SIZE;
	
	private int money;
	private Texture sprite;
	private final String tex = "Charakter.png";
	private Circle hitbox;
	private float x;
	private float y;
	private float speed=25f;
	private int dx = 1;
	private int dy = 1;
	private int lastDx;
	private int lastDy;
	private float health = 50;
	private int roomNr;
	private Items bomb;
	private Items key;
	private Items coin;
	private int amountOfBombs=0;
	private int amountOfCoins=0;
	private int amountOfKeys=0;

	private float cooldown = 300;
	private float cooldownCounter;
	
	private float invincibilty = 500;
	private float invincibiltyCounter;
	
	public Character(float pX, float pY) {
		this.sprite = new Texture(Gdx.files.internal(tex));
		hitbox = new Circle(this.x, this.y, TEX_SIZE/2);
		x=pX;
		y=pY;
	}
	
	public void update(float delta) {
		movement();
		warp();
		if(cooldownCounter >= cooldown) {
			cooldownCounter = 0;
			attack();
		} else {
			cooldownCounter += delta;
		}
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
				}
			}
		}
		invincibiltyCounter += delta;
		if(dx==1&&dy==0)this.sprite = new Texture(Gdx.files.internal("Issac2.png"));
		if(dx==-1&&dy==0)this.sprite = new Texture(Gdx.files.internal("Issac4.png"));
		if(dy==1&&dx==0)this.sprite = new Texture(Gdx.files.internal("Issac1.png"));
		if(dy==-1&&dx==0)this.sprite = new Texture(Gdx.files.internal("Issac3.png"));
	}
	
	public void draw(final Rogue game) {
		game.batch.begin();
		game.batch.draw(sprite, x, y, TEX_SIZE, TEX_SIZE, 0, 0, TEX_SIZE, TEX_SIZE, false, true);
		game.batch.end();
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
				if(Math.abs((wtx - x)) <= 10) {
					if(Math.abs(wty - y) <= 10) {
						Map.moveToNextRoom(this);
					}
				}
			}
		}
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
	
	public void collectBomb() {
		if(getX()==bomb.getBombX() && getY()==bomb.getBombY())amountOfBombs++;
	}
	public int getAmountOfBombs() {
		return amountOfBombs;
	}
	public void collectCoins() {
		if(getX()==coin.getCoinX() && getY()==coin.getCoinY())amountOfCoins++;
	}
	public int getAmountOfCoins() {
		return amountOfCoins;
	}
	
	public void collectKey() {
		if(getX()==key.getKeyX() && getY()==key.getKeyY())amountOfKeys++;
	}
	public int getAmountOfKeys() {
		return amountOfKeys;
	}
}
