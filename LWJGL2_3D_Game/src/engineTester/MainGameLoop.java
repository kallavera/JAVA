package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Geometry;
import renderEngine.Loader;
import renderEngine.Renderer;

public class MainGameLoop
{
	private static float[] positions = 
		{
			//left bottom triangle
			-0.5f,  0.5f, 0f,
			-0.5f, -0.5f, 0f,
			 0.5f, -0.5f, 0f,
			
			//right top triangle
			
			 0.5f, -0.5f, 0f,
			 0.5f,  0.5f, 0f,
			-0.5f,  0.5f, 0f
		};
	
	

	public static void main(String[] args)
	{
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		Geometry geo = loader.loadToVAO(positions);
		
		while(!Display.isCloseRequested())
		{
			renderer.prepare();
			
			// TICK
			// RENDER
			
			renderer.render(geo);
			
			DisplayManager.updateDisplay();
		}
		
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
