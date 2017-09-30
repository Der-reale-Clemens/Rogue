package pit.rogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GameScreen implements Screen {
	final Rogue game;
	final int WIDTH = Config.WIDTH;
	final int HEIGHT = Config.HEIGHT;

	OrthographicCamera camera;
	private BitmapFont font;
	
	private Room room = new Room(RoomTypes.DEBUGMAP);
	private Character player= new Character();

	public GameScreen(final Rogue game) {
		this.game = game;
		game.setScreen(this);

		// create the camera
		camera = new OrthographicCamera();
		camera.setToOrtho(true, WIDTH, HEIGHT);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		// Setup of OpenGL
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// update camera
		camera.update();
		// set batch coordinates based on camera Matrix
		game.batch.setProjectionMatrix(camera.combined);
		
		update(delta*1000);
	}

	public void update(float delta ) {
		room.draw(game);
		player.draw(game);
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

	}
}
