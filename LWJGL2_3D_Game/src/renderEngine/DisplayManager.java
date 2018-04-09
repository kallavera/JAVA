package renderEngine;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

public class DisplayManager
{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int FPS_CAP = 120;
	
	public static final String TITLE = "LWJGL Tutorial";

	public DisplayManager()
	{
		
	}
	
	public static void createDisplay()
	{
		ContextAttribs attribs = new ContextAttribs(3,2)
		.withForwardCompatible(true)
		.withProfileCore(false);
		
		try
		{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle(TITLE);
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}
		
		glViewport(0, 0, WIDTH, HEIGHT);
	}
	
	public static void updateDisplay()
	{
		Display.sync(FPS_CAP);
		Display.setVSyncEnabled(true);
		Display.update();
		
	}
	
	public static void closeDisplay()
	{
		Display.destroy();
	}
}
