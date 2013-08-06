package com.boxfactory.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

public abstract class BoxFactoryScreen
  implements Screen, InputProcessor
{
  Game game;

  public BoxFactoryScreen(Game paramGame)
  {
    this.game = paramGame;
  }

  public void dispose()
  {
  }

  public void hide()
  {
  }

  public boolean keyDown(int paramInt)
  {
    return false;
  }

  public boolean keyTyped(char paramChar)
  {
    return false;
  }

  public boolean keyUp(int paramInt)
  {
    return false;
  }

  public boolean mouseMoved(int paramInt1, int paramInt2)
  {
    return false;
  }

  public void pause()
  {
  }

  public void render(float paramFloat)
  {
  }

  public void resize(int paramInt1, int paramInt2)
  {
  }

  public void resume()
  {
  }

  public boolean scrolled(int paramInt)
  {
    return false;
  }

  public void show()
  {
  }

  public boolean touchDown(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return false;
  }

  public boolean touchDragged(int paramInt1, int paramInt2, int paramInt3)
  {
    return false;
  }

  public boolean touchUp(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return false;
  }
}