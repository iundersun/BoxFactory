package com.boxfactory.gameobject;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.boxfactory.assets.Assets;

public class Box
{
	private final Rectangle bounds;
	private final Image img;
	private final int number;
	public Vector2 oldPosition;
	public Vector2 position;
	private int state;

	public Box(int num, Vector2 pos, float size)
	{
		number = num;
		position = pos;
		oldPosition = position;
		bounds = new Rectangle(position.x, position.y, size, size);
		state = 0;
		img = new Image(new TextureRegionDrawable(Assets.box));
		img.setSize(bounds.width, bounds.height);
    	img.setZIndex(2);
    	updateImg();
	}

	private void updateImg()
	{
		img.setPosition(position.x, position.y);
	}

	public void del()
	{
		position = new Vector2(-500.0F, -500.0F);
		oldPosition = position;
		state = 0;
		updateImg();
	}

	public Rectangle getBounds()
	{
		return bounds;
	}

	public Image getImage()
	{
		return img;
	}

	public int getNumber()
	{
		return number;
	}

	public int getState()
	{
		return state;
	}

	public void move(Vector2 newPos)
	{
		position = newPos;
		updateImg();
	}

	public void reuse(Vector2 pos)
	{
		position = pos;
		oldPosition = pos;
		state = 1;
		updateImg();
	}
}