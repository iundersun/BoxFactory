package com.boxfactory.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.boxfactory.assets.Assets;
import com.boxfactory.settings.AppSettings;

public class MenuScreen extends BoxFactoryScreen
{
	private Button back;
	private Image bg_settings;
	float buttonH;
	float buttonW;
	private Image img_name;
	private Button play;
	private Button scores;
	float settBtnH;
	float settBtnW;
	private Button settings;
	private boolean settingsState = false;
	private Button sound;
	Stage stage;
	private Button vibro;

	public MenuScreen(Game paramGame)
	{
		super(paramGame);
	}

	private void sandAwayMenu()
	{
		play.addAction(Actions.moveTo(AppSettings.getWorldWidth(),play.getY(), 0.4F));
		scores.addAction(Actions.moveTo(AppSettings.getWorldWidth(),scores.getY(), 0.4F));
		settings.addAction(Actions.moveTo(AppSettings.getWorldWidth(),settings.getY(), 0.4F));
		img_name.addAction(Actions.moveTo(AppSettings.getWorldWidth(),img_name.getY(), 0.4F));
	}

	private void sandAwaySettings()
	{
		settingsState = false;
		bg_settings.addAction(Actions.moveTo(-this.bg_settings.getWidth(), 0.0F, 0.4F));
		back.addAction(Actions.moveTo(-AppSettings.getWorldWidth(),settings.getY(), 0.4F));
		vibro.addAction(Actions.moveTo(-AppSettings.getWorldWidth(), AppSettings.getWorldHeight() / 2.0F, 0.4F));
		sound.addAction(Actions.moveTo(-AppSettings.getWorldWidth(), AppSettings.getWorldHeight() / 2.0F, 0.4F));
	}

	private void sandInMenu()
	{
		play.addAction(Actions.moveTo(AppSettings.getWorldWidth() / 2.0F -buttonW / 2.0F, 3.0F *buttonH, 0.4F));
		scores.addAction(Actions.moveTo(AppSettings.getWorldWidth() / 2.0F -buttonW / 2.0F, 2.0F *buttonH, 0.4F));
		settings.addAction(Actions.moveTo(AppSettings.getWorldWidth() / 2.0F -buttonW / 2.0F,buttonH, 0.4F));
		img_name.addAction(Actions.moveTo(AppSettings.getWorldWidth() / 2.0F - 355.0F * AppSettings.getPositionRatioX() / 2.0F, AppSettings.getWorldHeight() / 2.0F + 161.0F * AppSettings.getPositionRatioX() / 4.0F, 0.4F));
	}

	private void sandInSettigs()
	{
		settingsState = true;
		if (AppSettings.isSoundOn())
			sound.setStyle((Button.ButtonStyle)Assets.btn_skin.get("sound_on", Button.ButtonStyle.class));
		else 
			sound.setStyle((Button.ButtonStyle)Assets.btn_skin.get("sound_off", Button.ButtonStyle.class));
		if (!AppSettings.isVibroOn())
			vibro.setStyle((Button.ButtonStyle)Assets.btn_skin.get("vibro_on", Button.ButtonStyle.class));
		else
			vibro.setStyle((Button.ButtonStyle)Assets.btn_skin.get("vibro_off", Button.ButtonStyle.class));
		
		bg_settings.addAction(Actions.moveTo(0.0F, 0.0F, 0.4F));
		back.addAction(Actions.moveTo(this.bg_settings.getWidth() - 1.25F *buttonW,buttonH, 0.4F));
		vibro.addAction(Actions.moveTo(this.bg_settings.getWidth() / 5.0F, AppSettings.getWorldHeight() / 2.0F, 0.4F));
		sound.addAction(Actions.moveTo(3.0F *bg_settings.getWidth() / 5.0F, AppSettings.getWorldHeight() / 2.0F, 0.4F));
	}

	public void hide()
	{
		stage.dispose();
	}

	public boolean keyDown(int key)
	{
		if ((key == 4) && (settingsState))
		{
			settingsState = false;
			sandAwaySettings();
			sandInMenu();
		}
		while ((key != 4) || (settingsState))
			return false;
		Gdx.app.exit();
		return false;
	}

	public void render(float paramFloat)
	{
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
		localInputMultiplexer.addProcessor(this.stage);
		Gdx.input.setInputProcessor(localInputMultiplexer);
		Gdx.input.setCatchBackKey(true);
		Image localImage = new Image(new TextureRegionDrawable(Assets.bg_menu));
		localImage.setFillParent(true);
		
		//create button
		bg_settings = new Image(new TextureRegionDrawable(Assets.bg_settings));
		bg_settings.setSize(AppSettings.getWorldWidth() - AppSettings.getWorldWidth() / 10.0F, AppSettings.getWorldHeight());
		bg_settings.setPosition(-this.bg_settings.getWidth(), 0.0F);
		img_name = new Image(new TextureRegionDrawable(Assets.logo));
		img_name.setPosition(AppSettings.getWorldWidth() / 2.0F - 355.0F * AppSettings.getPositionRatioX() / 2.0F, AppSettings.getWorldHeight() / 2.0F + 161.0F * AppSettings.getPositionRatioX() / 4.0F);
		img_name.setSize(355.0F * AppSettings.getPositionRatioX(), 161.0F * AppSettings.getPositionRatioX());
		buttonH = (62.400002F * AppSettings.getPositionRatioY());
		buttonW = (201.5F * AppSettings.getPositionRatioY());
		play = new Button((Button.ButtonStyle)Assets.btn_skin.get("play", Button.ButtonStyle.class));
		play.setSize(this.buttonW,buttonH);
		scores = new Button((Button.ButtonStyle)Assets.btn_skin.get("scores", Button.ButtonStyle.class));
		scores.setSize(this.buttonW,buttonH);
		settings = new Button((Button.ButtonStyle)Assets.btn_skin.get("settings", Button.ButtonStyle.class));
		settings.setSize(this.buttonW,buttonH);
		
		settBtnW = (0.5F * (327.0F * AppSettings.getPositionRatioY()));
		settBtnH = (0.5F * (340.0F * AppSettings.getPositionRatioY()));
		
		vibro = new Button((Button.ButtonStyle)Assets.btn_skin.get("vibro_on", Button.ButtonStyle.class));
		vibro.setSize(this.settBtnW,settBtnH);
		sound = new Button((Button.ButtonStyle)Assets.btn_skin.get("sound_on", Button.ButtonStyle.class));
		sound.setSize(this.settBtnW,settBtnH);
		back = new Button((Button.ButtonStyle)Assets.btn_skin.get("back", Button.ButtonStyle.class));
		back.setSize(this.buttonW,buttonH);
		
		//create button listener
		play.addListener(new ClickListener()
		{
			public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
			{
				game.setScreen(new GameScreen(MenuScreen.this.game));
				System.out.println("click_play");
			}
		});
		scores.addListener(new ClickListener()
		{
			public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
			{
				Gdx.input.setCatchBackKey(true);
				System.out.println("click_scor");
			}
		});
		settings.addListener(new ClickListener()
		{
			public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
			{
				sandAwayMenu();
				sandInSettigs();
				System.out.println("click_sett");
			}
		});
		vibro.addListener(new ClickListener()
		{
			public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
			{
				if (AppSettings.isVibroOn())
				{
					AppSettings.setVibro(false);
					vibro.setStyle((Button.ButtonStyle)Assets.btn_skin.get("vibro_off", Button.ButtonStyle.class));
					return;
				}
				AppSettings.setVibro(true);
				vibro.setStyle((Button.ButtonStyle)Assets.btn_skin.get("vibro_on", Button.ButtonStyle.class));
			}
		});
		sound.addListener(new ClickListener()
		{
			public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
			{
				if (AppSettings.isSoundOn())
				{
					AppSettings.setSound(false);
					sound.setStyle((Button.ButtonStyle)Assets.btn_skin.get("sound_off", Button.ButtonStyle.class));
					return;
				}
				AppSettings.setSound(true);
				sound.setStyle((Button.ButtonStyle)Assets.btn_skin.get("sound_on", Button.ButtonStyle.class));
			}
		});
		back.addListener(new ClickListener()
		{
			public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
			{
				sandAwaySettings();
				sandInMenu();
				System.out.println("click_back");
			}
		});
		
		//set position
		settings.setPosition(AppSettings.getWorldWidth() / 2.0F -buttonW / 2.0F,buttonH);
		scores.setPosition(settings.getX(), 2.0F *buttonH);
		play.setPosition(settings.getX(), 3.0F *buttonH);
		vibro.setPosition(-AppSettings.getWorldWidth(), AppSettings.getWorldHeight() / 2.0F);
		sound.setPosition(-AppSettings.getWorldWidth(), AppSettings.getWorldHeight() / 2.0F);
		back.setPosition(-AppSettings.getWorldWidth(), settings.getY());
		
		//add actors
		stage.addActor(localImage);
		stage.addActor(bg_settings);
		stage.addActor(img_name);
		stage.addActor(settings);
		stage.addActor(scores);
		stage.addActor(play);
		stage.addActor(vibro);
		stage.addActor(sound);
		stage.addActor(back);
	}
}