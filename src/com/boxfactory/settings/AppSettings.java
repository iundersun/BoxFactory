package com.boxfactory.settings;

import com.badlogic.gdx.*;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.*;

public class AppSettings {
    private static float WORLD_HEIGHT = 0.0F;
    private static float WORLD_TARGET_HEIGHT = 480F;
    private static float WORLD_TARGET_WIDTH = 720F;
    private static float WORLD_WIDTH = 0.0F;
    private static String file = ".prefs";
    private static boolean sound = true;
    private static boolean vibro = true;

    
    public static float getPositionRatioX()
    {
        return WORLD_WIDTH / WORLD_TARGET_WIDTH;
    }

    public static float getPositionRatioY()
    {
        return WORLD_HEIGHT / WORLD_TARGET_HEIGHT;
    }

    public static float getWorldHeight()
    {
        return WORLD_HEIGHT;
    }

    public static float getWorldWidth()
    {
        return WORLD_WIDTH;
    }

    public static boolean isSoundOn()
    {	
    	loadSettings();
        return sound;
    }

    public static boolean isVibroOn()
    {
        return vibro;
    }
    
    public static void setApp()
    {
        WORLD_WIDTH = Gdx.graphics.getWidth();
        WORLD_HEIGHT = Gdx.graphics.getHeight();
    }

    public static void setSound(boolean flag)
    {
        sound = flag;
        saveSettings();
    }

    public static void setVibro(boolean flag)
    {
        vibro = flag;
        saveSettings();
    }
    
    private static void loadSettings() 
    {
    	BufferedReader bufferedreader = null;
    	FileHandle filehandle;
    	//TODO: зачем эти проверки передкаждым записью/чтением? сделать 1 метод и выполнить 1 раз
    	if(Gdx.app.getType() == Application.ApplicationType.Desktop)
    		filehandle = Gdx.files.external("boxfactory/" + file);
    	else
    		filehandle = Gdx.files.local(file);
    	if(!filehandle.exists() || filehandle.length() < 1L){
    		System.out.println("?????");
    		saveSettings();
    	} 
    	else{
    		System.out.println(filehandle.path());
    	}
    	try{
    	bufferedreader = new BufferedReader(new InputStreamReader(filehandle.read()));
    	sound = Boolean.parseBoolean(bufferedreader.readLine());
    	vibro = Boolean.parseBoolean(bufferedreader.readLine());
    	} 
        catch (GdxRuntimeException ex) {
        	System.out.println(ex.getMessage());
        } 
        catch (IOException e) {
        	System.out.println(e.getMessage());
        } 
        finally {
        	try {
        		bufferedreader.close();
            } 
        	catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    	
    }
    
    private static void saveSettings()
    {
        BufferedWriter bufferedwriter = null;
    	FileHandle filehandle;
        if(Gdx.app.getType() == Application.ApplicationType.Desktop)
    		filehandle = Gdx.files.external("boxfactory/" + file);
    	else
    		filehandle = Gdx.files.local(file);
        if(!Gdx.files.external("boxfactory/").exists())
        	Gdx.files.external("boxfactory/").mkdirs();
        try{
        	bufferedwriter=new BufferedWriter(new OutputStreamWriter(filehandle.write(false)));
        	bufferedwriter.write(Boolean.toString(sound));
            bufferedwriter.write("\n");
            bufferedwriter.write(Boolean.toString(vibro));
        } 
        catch (GdxRuntimeException ex) {
        	System.out.println(ex.getMessage());
        } 
        catch (IOException e) {
        	System.out.println(e.getMessage());
        } 
        finally {
            if (bufferedwriter != null) {
                try {
                	bufferedwriter.close();
                } catch (IOException e) {
                	System.out.println(e.getMessage());
                }
            }
        }
    }
}
