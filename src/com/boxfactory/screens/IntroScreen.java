package com.boxfactory.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.boxfactory.assets.Assets;
import com.boxfactory.settings.AppSettings;

public class IntroScreen extends BoxFactoryScreen
{
	float animH;
	float animW;
	SpriteBatch batch;
	Vector2 pos;
	Vector2 size;
	float stateTime = 0.0F;

	public IntroScreen(Game paramGame)
	{
		super(paramGame);
	}

	public void hide()
	{
		batch.dispose();
	}

	public void render(float paramFloat)
	{
		Gdx.gl.glClear(16384);
		batch.begin();
		if (Assets.manager.update())
		{
			Assets.loadGame(true);
			game.setScreen(new MenuScreen(game));
		}
		batch.draw(Assets.white, 0.0F, 0.0F, AppSettings.getWorldWidth(), AppSettings.getWorldHeight());
		batch.draw(Assets.bg_intro, pos.x, pos.y, size.x, size.y);
		renderAnimation();
		batch.end();
  }

  public void renderAnimation()
  {
	  stateTime += Gdx.graphics.getDeltaTime();
	  batch.draw(Assets.loading_anim.getKeyFrame(stateTime, true), AppSettings.getWorldWidth() - animH, AppSettings.getWorldHeight() - animW, animH, animW);
  }

  public void show()
  {
	  batch = new SpriteBatch();
	  batch.getProjectionMatrix().setToOrtho2D(0.0F, 0.0F, AppSettings.getWorldWidth(), AppSettings.getWorldHeight());
	  size = new Vector2(Assets.bg_intro.getRegionWidth() * AppSettings.getPositionRatioY(), Assets.bg_intro.getRegionHeight() * AppSettings.getPositionRatioY());
	  pos = new Vector2(AppSettings.getWorldWidth() / 2.0F - size.x / 2.0F, AppSettings.getWorldHeight() / 2.0F - size.y / 2.0F);
	  animH = (110.0F * AppSettings.getPositionRatioY());
	  animW = (64.0F * AppSettings.getPositionRatioY());
	  Assets.manager.clear();
	  Assets.loadGame(false);
  }
}