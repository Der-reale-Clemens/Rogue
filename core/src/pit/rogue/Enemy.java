package pit.rogue;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;

public class Enemy {
	
	private final int TEX_SIZE = Config.TEX_SIZE;
	
	
	private Animation<TextureRegion> animation;
	private Texture sheet;
	
	private Sound hitSound;
	
	private float stateTime;
	
	private EnemyTypes type;
	private Circle hitbox;
	private String tex = "Charakter.png";
	private float damage = 5f;
	private float speed = 5f;
	private float x;
	private float y;
	private float health = 10;
	private Texture sprite;
	private boolean isAlive = true;
	private float dx;
	private float dy;
	private int counter;
	
	public Enemy(EnemyTypes type, float x, float y) {
		this.x = x;
		this.y = y;
		sprite = new Texture(Gdx.files.internal(type.textureName));
		hitbox = new Circle(this.x, this.y, TEX_SIZE/2);
		this.health = type.health;
		this.speed = type.speed;
		this.type = type;
		
		hitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/enemy hit.wav"));
		
		
		if(type == EnemyTypes.Enemy1) {
			sheet = new Texture(Gdx.files.internal("bbat.png"));
			TextureRegion[][] tmp = TextureRegion.split(sheet, 
					sheet.getWidth() / 4,
					sheet.getHeight() / 1);
			TextureRegion[] frames = new TextureRegion[4];
			int index = 0;
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < 4; j++) {
					frames[index++] = tmp[i][j];
				}
			}
			animation = new Animation<TextureRegion>(0.3f, frames);
			stateTime = 0;
		}
		
		if(type == EnemyTypes.Enemy2) {
			sprite = new Texture(Gdx.files.internal("Ghost.png"));
		}
		
		if(type == EnemyTypes.Enemy3) {
			sheet = new Texture(Gdx.files.internal("walker.png"));
			TextureRegion[][] tmp = TextureRegion.split(sheet, 
					sheet.getWidth() / 2,
					sheet.getHeight() / 1);
			TextureRegion[] frames = new TextureRegion[2];
			int index = 0;
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < 2; j++) {
					frames[index++] = tmp[i][j];
				}
			}
			animation = new Animation<TextureRegion>(0.3f, frames);
			stateTime = 0;
		}
		
		
	}
	
	public void update(float delta, float playerX, float playerY) {
		if(health <= 0)
			isAlive = false;
		
		//Main Update Code
		
		switch(type) {
			case Enemy1: //Moves Randomly
				if(counter == 10) {
					switchDirection();
					counter = 0;
				}
				counter++;
				break;
			case Enemy2: //Follows Player
				float deltaX = playerX - x;
				float deltaY = playerY - y;
				float total = Math.abs(deltaX) + Math.abs(deltaY);
				dx = deltaX/total;
				dy = deltaY/total;
				break;
		}
		x += speed * (delta/100)*dx;
		y += speed * (delta/100)*dy;
		
		//Checks for Out of Bounds
		if(x <= TEX_SIZE)
			x = 65;
		if(x >= 13*TEX_SIZE)
			x = 13*TEX_SIZE - 1;
		if(y <= TEX_SIZE)
			y = TEX_SIZE + 1;
		if(y >= 7*TEX_SIZE)
			y = TEX_SIZE - 1;
	
		
		hitbox.setPosition(x, y);
	}
	
	private void switchDirection() {
		Random rng = new Random();
		int r = rng.nextInt(3);
		if(r == 2)
			r = -1;
		dx = r;
		r = rng.nextInt(3);
		if(r == 2)
			r = -1;
		dy = r;
	}
	
	public void damage(float damage) {
		hitSound.play();
		health -= damage;
		if(health <= 0)
			isAlive = false;
	}
	
	public void draw(final Rogue game) {
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame;
		switch(type) {
			case Enemy1:
				//game.batch.draw(sprite, x, y, TEX_SIZE, TEX_SIZE, 0, 0, TEX_SIZE, TEX_SIZE, false, true);
				currentFrame = animation.getKeyFrame(stateTime, true);
				game.batch.draw(currentFrame, x, y, 32, 16, 64, 32, 1, 1, 180);
				break;
			case Enemy2:
				if(dx > 0 ) {
					game.batch.draw(sprite, x, y, TEX_SIZE, TEX_SIZE, 0, 0, TEX_SIZE, TEX_SIZE, true, true);
				} else {
					game.batch.draw(sprite, x, y, TEX_SIZE, TEX_SIZE, 0, 0, TEX_SIZE, TEX_SIZE, false, true);
				}
				break;
			case Enemy3:
				//game.batch.draw(sprite, x, y, TEX_SIZE+8, TEX_SIZE+16, 0, 0, TEX_SIZE, TEX_SIZE, false, true);
				currentFrame = animation.getKeyFrame(stateTime, true);
				game.batch.draw(currentFrame, x, y, 32, 32, 64, 32, 1, 2, 180);
				break;
		}
	}
	
	public Circle getHitbox() {
		return hitbox;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public float getDamage() {
		return damage;
	}
	
	public void dispose() {
		sprite.dispose();
	}
}
