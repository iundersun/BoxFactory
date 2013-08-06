package com.boxfactory.screens.ext;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.boxfactory.assets.Assets;
import com.boxfactory.screens.GameScreen;
import com.boxfactory.screens.MenuScreen;
import com.boxfactory.settings.AppSettings;

public class Pause
{
	private static Image bg;
	private static Button menu;
	private static Button resume;

	public static void create(Stage paramStage, final Game game)
	{
		//create bg
		bg = new Image(new TextureRegionDrawable(Assets.bg_pause));
		
		//create buttons
		resume = new Button(Assets.game_btn_skin.get("resume", ButtonStyle.class));
		menu = new Button(Assets.game_btn_skin.get("menu", ButtonStyle.class));
		resume.setSize(AppSettings.getWorldHeight() / 3.0F, AppSettings.getWorldHeight() / 3.0F);
		menu.setSize(resume.getWidth(), resume.getHeight());
		bg.setSize(AppSettings.getWorldWidth(), AppSettings.getWorldHeight());
		resume.setPosition(-AppSettings.getWorldWidth(), AppSettings.getWorldHeight() / 2.0F - resume.getHeight() / 2.0F);
		menu.setPosition(-AppSettings.getWorldWidth(), AppSettings.getWorldHeight() / 2.0F - menu.getHeight() / 2.0F);
		bg.setPosition(-bg.getWidth(), 0.0F);
		
		//create buttons listener
		resume.addListener(new ClickListener()
		{
			public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
			{
				Pause.hide();
			}
		});
		menu.addListener(new ClickListener()
		{
			public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
			{
				game.setScreen(new MenuScreen(game));
			}
		});
		
		//adds actor
		paramStage.addActor(bg);
		paramStage.addActor(resume);
		paramStage.addActor(menu);
  }

	public static void hide()
	{
		bg.setPosition(-bg.getWidth(), 0.0F);
		resume.setPosition(-AppSettings.getWorldWidth(), resume.getY());
		menu.setPosition(-AppSettings.getWorldWidth(), menu.getY());
		GameScreen.setGameState(1);
	}

	public static void show()
	{
		bg.setPosition(0.0F, 0.0F);
		resume.setPosition(AppSettings.getWorldWidth() / 5.0F, resume.getY());
		menu.setPosition(3.0F * AppSettings.getWorldWidth() / 5.0F, menu.getY());
		GameScreen.setGameState(0);
	}
}