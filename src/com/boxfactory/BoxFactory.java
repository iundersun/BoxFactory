package com.boxfactory;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.boxfactory.assets.Assets;
import com.boxfactory.screens.IntroScreen;
import com.boxfactory.settings.AppSettings;

public class BoxFactory extends Game
{
  boolean firstTimeCreate = true;
  FPSLogger fps;

  public void create()
  {
    AppSettings.setApp();
    Assets.loadIntro();
    setScreen(new IntroScreen(this));
    this.fps = new FPSLogger();
  }

  public void dispose()
  {
    super.dispose();
    getScreen().dispose();
  }

  public void render()
  {
    super.render();
    this.fps.log();
  }
}