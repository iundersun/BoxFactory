package com.boxfactory.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets
{
	public static TextureRegion bg_intro;
	public static TextureRegion bg_menu;
	public static TextureRegion bg_pause;
	public static TextureRegion bg_settings;
	public static TextureRegion box;
	public static Skin btn_skin;
  	public static Skin game_btn_skin;
  	public static TextureRegion line;
  	public static Animation loading_anim;
  	public static TextureRegion logo;
  	public static AssetManager manager = new AssetManager();
  	public static TextureRegion white;

  	private static void getGameImages()
  	{
  		if (manager.isLoaded("data/menu.atlas"))
  		{
  			TextureAtlas atlas = manager.get("data/menu.atlas", TextureAtlas.class);
  			bg_menu = atlas.findRegion("bg_menu");
  			logo = atlas.findRegion("logo");
  			bg_settings = atlas.findRegion("bg_settings");
  		}
  		if (manager.isLoaded("data/menu.json"))
  			btn_skin = manager.get("data/menu.json", Skin.class);
  		if (manager.isLoaded("data/game.atlas"))
  		{
  			line = manager.get("data/game.atlas", TextureAtlas.class).findRegion("line");
  			box = manager.get("data/game.atlas", TextureAtlas.class).findRegion("box");
  			bg_pause = manager.get("data/game.atlas", TextureAtlas.class).findRegion("pause");
  		}
  		if (manager.isLoaded("data/game.json"))
  			game_btn_skin = (Skin)manager.get("data/game.json", Skin.class);
  	}

  	public static void loadGame(boolean value)
  	{
  		if (!value)
  		{
  			loadGameImages();
  			return;
  		}
  		getGameImages();
  	}

  	private static void loadGameImages()
  	{
  		manager.load("data/menu.json", Skin.class);
  		manager.load("data/menu.atlas", TextureAtlas.class);
  		manager.load("data/game.json", Skin.class);
  		manager.load("data/game.atlas", TextureAtlas.class);
  	}

  	public static void loadIntro()
  	{
  		loadIntroImages();
  	}

  	private static void loadIntroImages()
  	{
  		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/intro.atlas"));
  		TextureRegion[] animation = new TextureRegion[10];
  		for (int i = 0; ; i++)
  		{
  			if (i >= 10)
  			{
  				loading_anim = new Animation(0.1F, animation);
  				bg_intro = atlas.findRegion("logo");
  				white = atlas.findRegion("white");
  				return;
  			}
  			animation[i] = atlas.findRegion(String.valueOf(i));
  		}
  	}
}