package com.boxfactory;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;

public class GameActivity extends AndroidApplication
{
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    initialize(new BoxFactory(), false);
  }
}