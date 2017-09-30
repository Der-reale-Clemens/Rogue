package pit.rogue;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

public class Enemy {
	
	private final int TEX_SIZE = Config.TEX_SIZE;
	
	private EnemyTypes type;
	private Circle hitbox;
	private String tex = "Charakter.png";
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
		health -= damage;
		if(health <= 0)
			isAlive = false;
	}
	
	public void draw(final Rogue game) {
		game.batch.draw(sprite, x, y, TEX_SIZE, TEX_SIZE, 0, 0, TEX_SIZE, TEX_SIZE, false, true);
	}
	
	public Circle getHitbox() {
		return hitbox;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void dispose() {
		sprite.dispose();
	}
}
