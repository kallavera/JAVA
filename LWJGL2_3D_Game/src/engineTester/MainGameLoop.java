package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;

public class MainGameLoop
{

	public MainGameLoop()
	{
		
	}

	public static void main(String[] args)
	{
		float[] positions =
		{
			-0.5f,  0.5f, 0,	//TOP LEFT
			-0.5f, -0.5f, 0,	//BOTTOM LEFT
			 0.5f, -0.5f, 0,	//BOTTOM RIGHT
			 
			 0.5f, -0.5f, 0,	//BOTTOM RIGHT
			 0.5f,  0.5f, 0,	//TOP RIGHT
			-0.5f,  0.5f, 0,	//TOP LEFT
		};
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		RawModel model = loader.loadToVAO(positions);
		Renderer renderer = new Renderer();
		
		while(!Display.isCloseRequested())
		{
			DisplayManager.updateDisplay();
			renderer.prepare();
			renderer.render(model);
		}
		
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
