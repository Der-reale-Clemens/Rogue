package pit.rogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class UI {
	
	private final int HEIGHT = Config.HEIGHT;
	private final int WIDTH = Config.WIDTH;
	
	private Texture healthBarRed;
	private Texture healthBarGreen;
	
	public UI() {
		healthBarRed = new Texture(Gdx.files.internal("HealthBarRed.png"));
		healthBarGreen = new Texture(Gdx.files.internal("HealthBarGreen.png"));
	}
	
	public void draw(final Rogue game, Character player) {
		game.batch.begin();
		game.font.setColor(0.0f, 0.0f, 0.0f, 1.0f);
		//game.font.draw(game.batch, "money = " + player.getMoney(), (float) (WIDTH - ((Math.log10(player.getMoney()) + 1) * 15 )), 0);
		game.font.draw(game.batch, "Health: " + player.getHealth(), WIDTH - 110, 0);
		game.batch.end();
		drawHealthBar(game, player);
	}
	
	public void drawHealthBar(final Rogue game, Character player) {
		game.batch.begin();
		//first draw Red Part
		game.batch.draw(healthBarRed, 16, 16);
		//Second part draw green part
		game.batch.draw(healthBarGreen, 18, 18, 128*(player.getHealth()/50) -4, 28);
		game.batch.end();
	}
}
