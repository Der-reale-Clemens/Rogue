package pit.rogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class TitleScreen implements Screen{
	final Rogue game;
	
	OrthographicCamera camera;
	private BitmapFont font;
	
	public TitleScreen(final Rogue game) {
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		
		font = new BitmapFont();
		//background = new Texture(Gdx.files.internal("TitleScreen.png"));
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		/*game.batch.disableBlending();
		game.batch.begin();
		game.batch.draw(background, 0, 0);
		game.batch.end();
		
		game.batch.enableBlending();*/
		game.batch.begin();
		font.draw(game.batch, "Welcome to Infinite Dungeons!!!", 100, 150);
		font.draw(game.batch, "Tap Anywhere to begin", 100, 100);
		game.batch.end();
		
		if(Gdx.input.isTouched()){
			game.setScreen(new GameScreen(game));
			dispose();
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
