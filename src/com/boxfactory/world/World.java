package com.boxfactory.world;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.boxfactory.gameobject.Box;
import com.boxfactory.settings.AppSettings;
//import java.io.PrintStream;
//import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class World
{
	private final int BOX_COLLAPSE = 3;
	private final int BOX_DEL = 0;
	private final int BOX_MOVE = 2;
	private final int BOX_STATIC = 1;
	private final int boxSize;
	private List<Box> boxes = new ArrayList();
 	private int[][] field;
 	private final int box_h_count = 5;
 	private final int box_w_count;
 	private Vector2[][] position;
 	public int score;
 	public final Rectangle fieldBorder;

  	public World()
  	{
  		boxSize = (int)AppSettings.getWorldHeight()/8;
  		if (AppSettings.getWorldWidth() % boxSize == 0)
  			box_w_count = (int)AppSettings.getWorldWidth()/boxSize;
  		else
  			box_w_count = (int)AppSettings.getWorldWidth()/boxSize-1;
  		field=new int[box_w_count][box_h_count];
  		fieldBorder = new Rectangle((AppSettings.getWorldWidth() - boxSize * box_w_count) / 2.0F, 
  				(AppSettings.getWorldHeight() - 5 * boxSize) / 2.0F, boxSize * box_w_count, 6.5F * boxSize);
  		setPos();
 	 }

  	private void createBoxes(Stage paramStage)
  	{
  		this.boxes.add(new Box(this.boxes.size(), new Vector2(-500.0F, -500.0F), this.boxSize));
  		for (int i = 1; ; i++)
  		{
  			if (i >= 5 + 5 * this.box_w_count)
  				return;
  			this.boxes.add(new Box(this.boxes.size(), new Vector2(-500.0F, -500.0F), this.boxSize));
  			paramStage.addActor(((Box)this.boxes.get(i)).getImage());
  		}
  	}

  	private void createStartRandom()
  	{
  		int i = 1;
  		if (i >= 3)
  			return;
  		for (int j = 0; ; j++)
  		{
  			if (j >= this.box_w_count)
  			{
  				i++;
  				break;
  			}
  			if (Math.random() > 0.5D)
  				this.field[j][i] = newBox(getPos(j, i));
  		}
  	}

  	private Vector2 getPos(int x, int y)
  	{
  		return this.position[x][y];
  	}

  	private int newBox(Vector2 paramVector2)
  	{
  		for (int i = 1; ; i++)
  		{
  			if (i >= this.boxes.size())
  				return 0;
    	 if (((Box)this.boxes.get(i)).getState() == 0)
    	 {
    		 ((Box)this.boxes.get(i)).reuse(paramVector2);
    		 return i;
    	 }
  		}
  	}

  	private void print()
  	{
  		int i = 0;
  		if (i >= 5)
  			System.out.println("----------------------------------");
  		for (int k = 0; ; k++)
  		{
  			if (k >= boxes.size())
  			{
  				System.out.println();
  				System.out.println("----------------------------------");
  				return;
  			}
  			if (boxes.get(k).getState() == 1)
  				System.out.print(boxes.get(k).getNumber() + " " + boxes.get(k).position + "| ");
  		}
  	}

  	private void printPos()
  	{
  		System.out.println("boxSize=" + boxSize);
  		System.out.println(box_w_count + " x " + 5);
  		int i = 0;
  		if (i >= 5)
  			return;
  		for (int j = 0; ; j++)
    	{
  			if (j >= this.box_w_count)
  			{
  				System.out.println();
  				i++;
  				break;
  			}
  			System.out.print(this.position[j][i].x + ":" + this.position[j][i].y + "|");
    	}
  	}

  	private void removeBox(int paramInt)
  	{
  		int i = 4;
  		if (i < 0)
  		{
  			boxes.get(paramInt).del();
  			return;
  		}
  		for (int j = 0; ; j++)
  		{
  			if (j >= box_w_count)
  			{
  				i--;
  				break;
  			}
  			if (field[j][i] == paramInt)
  				field[j][i] = 0;
  		}
  	}

  	private void removeLine()
  	{
  		for (int i = 0; ; i++)
  		{
  			if (i >= box_w_count)
  				return;
  			boxes.get(this.field[i][4]).del();
  			field[i][4] = 0;
  		}
  	}

  	private void setPos()
  	{
  		position = new Vector2[box_w_count][box_h_count];
  		float f1 = (AppSettings.getWorldWidth() - boxSize * box_w_count) / 2.0F;
  		float f2 = (AppSettings.getWorldHeight() - 5 * boxSize) / 2.0F;
  		float f3 = AppSettings.getWorldHeight() - f2 - boxSize;
  		int i = 0;
  		if (i >= box_w_count)
  			return;
  		float f4 = f3;
  		for (int j = 0; ; j++)
  		{
  			if (j >= 5)
  			{
  				f1 += boxSize;
  				i++;
  				break;
  			}
  			position[i][j] = new Vector2(f1, f4);
  			f4 -= boxSize;
  		}
  	}

  	private void update()
  	{
  		int i = 3;
  		if (i < 0);
  		for (int k = 0; ; k++)
  		{
  			if (k >= this.box_w_count)
  				removeLine();
  			while (this.field[k][4] == 0)
  			{
  				return;
  			}
  		}
  	}

  	public void create(Stage stage)
  	{
  		createBoxes(stage);
  		createStartRandom();
  	}
  	
  	public void render()
  	{
  		update();
  	}
}