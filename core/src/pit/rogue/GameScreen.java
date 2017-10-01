package pit.rogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GameScreen implements Screen {
	final Rogue game;
	final int WIDTH = Config.WIDTH;
	final int HEIGHT = Config.HEIGHT;

	OrthographicCamera camera;
	private BitmapFont font;
	
	private Map map = new Map();
	private Character player = new Character(WIDTH/2-32, HEIGHT/2-32);
	private EnemyManager enemyManager = new EnemyManager();
	private BulletManager bulletManager = new BulletManager();
	private UI ui = new UI();
	
	private Music bgm;
	private Music bgm2;
	
	private boolean swit = false;

	public GameScreen(final Rogue game) {
		this.game = game;
		game.setScreen(this);

		// create the camera
		camera = new OrthographicCamera();
		camera.setToOrtho(true, WIDTH, HEIGHT);
		
		EnemyManager.addEnemy(EnemyTypes.Enemy2, 65, 65);
		
		bgm = Gdx.audio.newMusic(Gdx.files.internal("sounds/Dungeon.ogg"));
		
		bgm.setLooping(true);
		bgm.play();
		
		bgm2 = Gdx.audio.newMusic(Gdx.files.internal("sounds/Miniboss.ogg"));
		bgm2.setLooping(true);
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
		enemyManager.update(delta, player.getX(), player.getY(), Map.getRooms()[Map.getActiveRoom()]);
		bulletManager.update(delta);
		player.update(delta, Map.getRooms()[Map.getActiveRoom()]);
		
		map.draw(game);
		player.draw(game);
		enemyManager.draw(game);
		bulletManager.draw(game);
		ui.draw(game, player);
		
		if(!player.isAlive()) {
			game.setScreen(new GameOverScreen(game));
		}
		if(swit == false && Map.getActiveRoom() == 4) {
			bgm.stop();
			bgm2.play();
			swit =true;
		}
		
		if(map.getActiveRoom()==4) {
			if(EnemyManager.getEnemys().size() == 0) {
				game.setScreen(new VictoryScreen(game));
			}
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

	}
}
