package com.boxfactory.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.boxfactory.assets.Assets;
import com.boxfactory.screens.ext.Pause;
import com.boxfactory.settings.AppSettings;
import com.boxfactory.world.World;

public class GameScreen extends BoxFactoryScreen
{
	public static int WORLD_PAUSE = 0;
	public static int WORLD_RUN = 1;
	private static int gameState;
	private Stage stage;
	private World world;

	public GameScreen(Game game)
	{
		super(game);
	}

	public static int getGameState()
	{
		return gameState;
	}

	public static void setGameState(int value)
	{
		if ((value == WORLD_PAUSE) || (value == WORLD_RUN))
			gameState = value;
	}

	public void dispose()
	{
		stage.dispose();
	}

	public boolean keyDown(int key)
	{
		if (((key == 82) || (key == 4)) && (gameState == WORLD_RUN))
			Pause.show();
		if ((key == 4) && (gameState == WORLD_PAUSE))
			Pause.hide();
		else if ((key == 82) && (gameState == WORLD_PAUSE))
			//TODO: save world progress?
			game.setScreen(new MenuScreen(game));
		return false;
	}

	public void render(float paramFloat)
	{
		world.render();
		Gdx.gl.glClearColor(1.0F, 0.0F, 0.0F, 1.0F);
		Gdx.gl.glClear(16384);
		stage.act(paramFloat);
		stage.draw();
  }

	public void show()
	{
		stage = new Stage(AppSettings.getWorldWidth(), AppSettings.getWorldHeight(), false);
		stage.getCamera().position.set(AppSettings.getWorldWidth() / 2.0F, AppSettings.getWorldHeight() / 2.0F, 0.0F);
		InputMultiplexer localInputMultiplexer = new InputMultiplexer();
		localInputMultiplexer.addProcessor(this);
		localInputMultiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(localInputMultiplexer);
		Gdx.input.setCatchBackKey(true);
		world = new World();
		
		//create bg
		Image bg = new Image(new TextureRegionDrawable(Assets.bg_menu));
		bg.setSize(AppSettings.getWorldWidth(), AppSettings.getWorldHeight() + AppSettings.getWorldHeight() / 7.5F);
		
		//create interface 
		TextureRegionDrawable texture = new TextureRegionDrawable(Assets.line);
		Image line0 = new Image(texture);
		line0.setPosition(world.fieldBorder.x - 2.0F, world.fieldBorder.y);
		line0.setSize(2.0F, world.fieldBorder.height);
		Image line1 = new Image(texture);
		line1.setSize(2.0F, world.fieldBorder.height);
		line1.setPosition(AppSettings.getWorldWidth() - world.fieldBorder.x, world.fieldBorder.y);
		Image line2 = new Image(texture);
		line2.setSize(world.fieldBorder.width, 2.0F);
		line2.rotate(0.0F);
		line2.setPosition(world.fieldBorder.x, AppSettings.getWorldHeight() - 2.0F);
		Image line3 = new Image(texture);
		line3.setSize(4.0F + world.fieldBorder.width, 2.0F);
		line3.rotate(0.0F);
		line3.setPosition(world.fieldBorder.x - 2.0F, world.fieldBorder.y - 2.0F);
		Button left = new Button(Assets.game_btn_skin.get("left", ButtonStyle.class));
    	left.setSize(world.fieldBorder.y - 2.0F, world.fieldBorder.y - 2.0F);
    	left.setPosition(world.fieldBorder.x, 0.0F);
    	Button right = new Button(Assets.game_btn_skin.get("right", ButtonStyle.class));
    	right.setSize(world.fieldBorder.y - 2.0F, world.fieldBorder.y - 2.0F);
    	right.setPosition(left.getX() + right.getWidth() + left.getWidth() / 3.0F, 0.0F);
    	Button jump = new Button(Assets.game_btn_skin.get("jump", ButtonStyle.class));
    	jump.setSize(world.fieldBorder.y - 2.0F, world.fieldBorder.y - 2.0F);
    	jump.setPosition(AppSettings.getWorldWidth() - world.fieldBorder.x - jump.getWidth(), 0.0F);
    	
    	//create buttons listener
    	left.addListener(new ClickListener()
    	{
    		public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
    		{
    			System.out.println("click left_btn");
    		}
    	});
    	right.addListener(new ClickListener()
    	{
    		public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
    		{
    			System.out.println("click right_btn");
    		}
    	});
    	jump.addListener(new ClickListener()
    	{
    		public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
    		{
    			System.out.println("click jump_btn");
    		}
    	});
    	
    	//add actors
    	stage.addActor(bg);
    	stage.addActor(line0);
    	stage.addActor(line1);
    	stage.addActor(line2);
    	stage.addActor(line3);
    	stage.addActor(left);
    	stage.addActor(right);
    	stage.addActor(jump);
    
    	world.create(stage);
    	Pause.create(stage, game);
    	gameState = 1;
	}
}